package com.wr;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 用Lock的实现;两个condition
*
*
*
*
*
* */

public class TestSczXfzC1
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
    //

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

class WrSyQueue2
{
    String []strs=new String[50];
    int count=0;
    int tail=0;
    int head=0;
    Lock  lock= new ReentrantLock();
    Condition notFull=lock.newCondition();//等待不是满的时候
    Condition notEmpty=lock.newCondition();//等待不是空的时候

    void product()
    {//product
        lock.lock();
        {
            while (count>=strs.length) {
                try {
                    notFull.await();//等待通知不是notFull
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
            notEmpty.signal();//通知不是空啊
        }
        lock.unlock();
    }
    void consume()
    {//product
        lock.lock();
        {
            while (count<=0) {
                try {
                    notEmpty.await();//等待不是null
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
            notFull.signal();
        }
        lock.unlock();
    }
}

class productT2 extends Thread{
    public productT2(String tname)
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

class consumeT2 extends Thread{
   public consumeT2(String tname)
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