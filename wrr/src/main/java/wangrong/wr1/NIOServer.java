package wangrong.wr1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class NIOServer {
    //通道管理器
    private Selector selector;

    //获取一个ServerSocket通道，并初始化通道
    public NIOServer init(int port) throws IOException {
        //获取一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //设置为非阻塞模式
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        //获取通道管理器
        selector=Selector.open();
        //将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
        //只有当该事件到达时，Selector.select()会返回，否则一直阻塞。    连接对应的是accept事情
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        return this;
    }

    public void listen() throws IOException {
        System.out.println("服务器端启动成功");

        //使用轮询访问selector
        while (true) {
            //当有注册的事件到达时，方法返回，否则阻塞。
            selector.select();
            int t = 0;
            //获取selector中的迭代器，选中项为注册的事件
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();


            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //删除已选key，防止重复处理
                ite.remove();
                t++;
                System.out.println("Server" + t);
                if (t > 2) {
                    System.exit(0);

                    //客户端请求连接事件，返回的是服务器的socket
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        //获得客户端连接通道
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        //向客户端发消息
                        String str = "hdfjskkkkkkkkeiuygfkjruieidfiewaosmncz";
                        //服务器端的write对应客户端的read
                        channel.write(ByteBuffer.wrap(new String("send message to client " + channel.toString()).getBytes()));
                        //在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。以便于去读取客户端写的内容
                        channel.register(selector, SelectionKey.OP_READ);

                        //System.out.println("客户端请求连接事件");
                    } else if (key.isReadable()) {//有可读数据事件
                        //获取客户端传输数据可读取消息通道。
                        SocketChannel channel = (SocketChannel) key.channel();
                        //创建读取数据缓冲器
                        ByteBuffer buffer = ByteBuffer.allocate(10);
                        int read = channel.read(buffer);
                        byte[] data = buffer.array();
                        String message = new String(data);

                        System.out.println(channel + "receive message from client, size:" + buffer.position() + " msg: " + message);
                        // ByteBuffer outbuffer = ByteBuffer.wrap(("server.".concat("server wr")).getBytes());
                        // channel.write(outbuffer);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       // new NIOServer().init(9981).listen();\
    URL xmlpath = NIOServer.class.getClassLoader().getResource("");
    ClassLoader cs= NIOServer.class.getClassLoader();
    Class cl=NIOServer.class.getClassLoader().loadClass("java.lang.Thread");
        System.out.println(NIOServer.class.getClassLoader().getParent().getResource("/"));
        System.out.println(xmlpath);
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(extDirs);
        }
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
}
}
 class TestMyClassLoader
{
    public static void main1(String[] args) throws Exception
    {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> c1 = Class.forName("com.xrq.classloader.Person", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }



}
 class MyClassLoader extends ClassLoader
{
    public MyClassLoader()
    {

    }

    public MyClassLoader(ClassLoader parent)
    {
        super(parent);
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        File file = getClassFile(name);
        try
        {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private File getClassFile(String name)
    {
        File file = new File("D:/Person.class");
        return file;
    }

    private byte[] getClassBytes(File file) throws Exception
    {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true)
        {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return baos.toByteArray();
    }
}
