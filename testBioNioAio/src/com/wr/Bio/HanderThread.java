package com.wr.Bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HanderThread implements Runnable
{
  Socket socket;
    HanderThread (Socket s){socket=s;}
    @Override
    public void run()
    {
        try {
            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();
            InputStreamReader isr=new InputStreamReader(is);
            OutputStreamWriter osw=new OutputStreamWriter(os);
            char[] buffer = new char[20 * 1024];
            int cnt;

            // read() 最多读取 buffer.length 个字节
            // 返回的是实际读取的个数
            // 返回 -1 的时候表示读到 eof，即文件尾
            while ((cnt = isr.read(buffer, 0, buffer.length)) != -1) {
                System.out.println("server 收到 ");
                for(int i=0;i<buffer.length;i++)
                {
                    System.out.println(buffer[i]);
                }
                os.write(new String(buffer).getBytes(), 0, cnt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
