package com.wr.Nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server
{
    private static  int port=12345;
    ServerHandler server;
    public static void main(String[] args) throws InterruptedException {
       new Server().start(port);
       Thread.sleep(1000);
        new Thread(new Client(port)).start();

    }
    public static void start(int port)
    {
        new Thread(new ServerHandler(port),"server").start();
    }
}
class ServerHandler implements Runnable
{ private int port=12345;
boolean started=false;
    ServerHandler(int p){
        port=p;
        try {
            serverChannel =ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(port),1024);
            selector=Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);//1 << 4
            started=true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ServerSocketChannel serverChannel;
    Selector selector;

    @Override
    public void run()
    {
        while(started)
        {
            try {

                selector.select(3000);
                //阻塞,只有当至少一个注册的事件发生的时候才会继续.
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                System.out.println("server"+keys.size());
                SelectionKey key = null;
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

    void handleKey(SelectionKey key) throws IOException {
        if(key.isValid())
        {
            if(key.isAcceptable())
            {
                //这个key监听的channel；
                ServerSocketChannel ssc=(ServerSocketChannel)key.channel();
                SocketChannel  sc=ssc.accept();
                if(sc!=null) {
                    System.out.println("++" + sc + "**");
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc,"s--->c");
                }


            }
            else if(key.isReadable())
            {
                SocketChannel  sc=(SocketChannel) key.channel();
                ByteBuffer buffer=ByteBuffer.allocate(1024) ;
                int len=sc.read(buffer);
                if(len>0)
                {
                    buffer.flip();
                    byte[] bytes=new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String str=new String(bytes,"UTF-8");
                    System.out.println("server "+str);
                    doWrite(sc,"server reply"+str);
                }
                else
                {
                    key.cancel();
                    sc.close();

                }


            }
        }
    }

    private void doWrite(SocketChannel sc, String s) throws IOException
    {
        byte[]bytes=s.getBytes();
        ByteBuffer buffer=ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        sc.write(buffer);
    }
}
