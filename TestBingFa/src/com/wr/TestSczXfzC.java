package com.wr;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
* 用Lock的实现;一个condition
*
*
*
*
*
* */
public class TestSczXfzC
{
    public static WrSyQueue wq=new WrSyQueue();
    public static  long start=System.currentTimeMillis();
    public static  long end=System.currentTimeMillis();
    public static void main(String[]args)
    {
        long start=System.currentTimeMillis();
        for(int i=0;i<100;i++)
        {
            main1(null);
        }
        System.out.println("总共消耗时间"+(System.currentTimeMillis()-start));
    }
public static void main1(String[]args)
{
    //10个生产
    for(int i=0;i<5;i++ )
    {
        Thread t=new productT("productThread "+i);
         t.start();

    }

    for(int i=0;i<5;i++ )
    {
        Thread t=new consumeT("productThread "+i);
        t.start();

    }
}


}

class WrSyQueue1
{
    String []strs=new String[50];
    int count=0;
    int tail=0;
    int head=0;
    Lock  lock= new ReentrantLock();
    Condition queue=lock.newCondition();
    void product()
    {//product
        lock.lock();
        {
            while (count>=strs.length) {
                try {
                    queue.await();//等待通知生产
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //进行生产;使用循环队列实现啊

            strs[tail]=Thread.currentThread()+"  :product "+count;
            tail=(tail+1)%strs.length;
            count++;
            System.out.println(Thread.currentThread()+"  :product "+count);


            //生产完了通知消费
            queue.signal();
        }
        lock.unlock();
    }
    void consume()
    {//product
        lock.lock();
        {
            while (count<=0) {
                try {
                    queue.await();//等待通知消费
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //进行消费
            count--;
            strs[head]=Thread.currentThread()+"  :consume "+count;
            head=(head+1)%strs.length;
            System.out.println(Thread.currentThread()+"  :consume "+count);
            //生产完了通知消费
            queue.signal();
        }
        lock.unlock();
    }
}

class productT1 extends Thread{
    public productT1(String tname)
    {
        super(tname);
    }
    public void run()
    {
        for(int i=0;i<100;i++) {
            TestSczXfz.wq.product();
        }
        TestSczXfz.end=System.currentTimeMillis();
        System.out.println(TestSczXfz.end-TestSczXfz.start);

    }
}

class consumeT1 extends Thread{
   public consumeT1(String tname)
   {
    super(tname);
   }
    public void run()
    {
        for(int i=0;i<100;i++)
        TestSczXfz.wq.consume();
        TestSczXfz.end=System.currentTimeMillis();
        System.out.println(TestSczXfz.end-TestSczXfz.start);

    }
}