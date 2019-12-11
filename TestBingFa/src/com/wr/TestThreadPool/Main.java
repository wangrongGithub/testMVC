package com.wr.TestThreadPool;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String []args)
    {
        int [] a=new int[]{2,4,3,3,2,99,22,4};
        int one=0;
        for(int i=0;i<a.length;i++)
        {
            one^=a[i];
        }
        int loc=0;
       while(one!=0)
       {
           if(one%2!=0)
           {
               loc++;
               break;
           }
           one/=one;
           loc++;

       }
      // System.out.println(loc);
       int two=0,three=0;

        for(int i=0;i<a.length;i++)
        {
            int t=0;
            one=a[i];
            while(t<loc)
            {

                one/=10;
                t++;

            }
          //  System.out.println(one);
            if(one%2!=0)
            {
              //  System.out.println("two:  "+a[i]);
                two^=a[i];

            }
            else
            {
              //  System.out.println("three:  "+a[i]);
                three^=a[i];
            }

        }


        ThreadPoolExecutor thread= (ThreadPoolExecutor)Executors.newFixedThreadPool(3);


System.out.println(two+"  "+three);




    }



}
