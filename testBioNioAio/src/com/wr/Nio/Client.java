package com.wr.Nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client implements Runnable
{
    Selector selector;
    SocketChannel socket;
    Client(int port)
    {
        try {
            selector=Selector.open();
            socket=SocketChannel.open();
            socket.configureBlocking(false);
            if(socket.connect(new InetSocketAddress(port)))
                System.out.println("connect");
            else {
                System.out.println("register");
                socket.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                selector.select(3000);
                //阻塞,只有当至少一个注册的事件发生的时候才会继续.
//              selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
               // System.out.println("\"client\""+keys.size());
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleKey(key);
                    }catch(Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleKey(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();
            if(key.isConnectable()){
                System.out.println("isConnectable");

                if(sc.finishConnect()) {
                    doWrite(sc, "c-->s");
                    socket.register(selector, SelectionKey.OP_READ);
                }
                else System.exit(1);
            }
            //读消息
            if(key.isReadable()){
                //创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                System.out.println("c");
                //读取到字节，对字节进行编解码
                if(readBytes>0){
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String result = new String(bytes,"UTF-8");
                    System.out.println("客户端收到消息：" + result);
                }
                //没有读取到字节 忽略
//              else if(readBytes==0);
                //链路已经关闭，释放资源

            }
        }
    }
    private void doWrite(SocketChannel sc, String s) throws IOException {
        byte[]bytes=s.getBytes();
        ByteBuffer buffer=ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        sc.write(buffer);
    }
}
