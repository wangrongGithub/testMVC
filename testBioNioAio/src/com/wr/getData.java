package com.wr;

import com.wr.Bio.HanderThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Map;
import java.util.Set;

public class getData {
    static ServerSocket server;
    static int port=12345;
    public  static void main(String []args)
    {
        start(port);
    }
    public  static void start(int port)
    {//ERR_INVALID_HTTP_RESPONSE;ERR_CONNECTION_REFUSED
        OutputStream os=null;
        try {
            server=new ServerSocket(port);
            String str="<!DOCTYPE html>\n" +
                    "\n" +
                    "<html>\n" +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
                    +"\n" +
                    "<body>\n" +System.currentTimeMillis()+
                    "\n" +
                    "<p>为告诉过。</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            StringBuilder sb=new StringBuilder("");
            Socket socket=server.accept();
            byte []data=new byte[1024];
            socket.getInputStream().read(data,0,1024);
            System.out.println(new String(data));
            os=socket.getOutputStream();
            long c=System.currentTimeMillis();
            PrintStream writer = new PrintStream(os, true);
            writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
            writer.println("Content-Type:text/html; charset=UTF-8");
            writer.println("Connection: keep-alive");
            writer.println("Content-Length:" + str.length());// 返回内容字节数
            writer.println();

            writer.write(str.toString().getBytes("UTF-8"));



        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(server!=null)
            {
                try {
                    os.close();
                    server.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                server=null;
            }
        }


    }
/*
access-control-allow-credentials: true
access-control-allow-headers: DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,body
access-control-allow-methods: GET, POST, OPTIONS
access-control-allow-origin: https://blog.csdn.net
date: Fri, 22 Mar 2019 05:46:13 GMT
server: openresty
status: 200
*
*
* */



    public static StringBuilder main1(Socket socket) throws IOException
    {
        URL url=new URL("https://data.melbourne.vic.gov.au/resource/d6mv-s43h.json");
        URLConnection urlc=url.openConnection();
        InputStream is=urlc.getInputStream();
        byte[] data=new byte[1024];
        int len=0;
        Map headers = urlc.getHeaderFields();
        Set<String> keys = headers.keySet();
        for( String key : keys )
        { String val = urlc.getHeaderField(key); System.out.println(key+" "+val); }
        System.out.println( urlc.getLastModified() );
        StringBuilder sb=new StringBuilder();
        while((len=is.read(data,0,1024))>=0)
        {

            sb.append(new String(data));

        }
        is.close();
         return sb;
    }
}
