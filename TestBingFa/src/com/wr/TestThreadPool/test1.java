package com.wr.TestThreadPool;



public class test1 {
   public static void main(String[] args)

   {

   }
    public static void graph()
    {

    }
}
class edge{

}
class pre{

        synchronized public  void t()
        {
            System.out.println("pre");
        }
}
class child extends  pre{

    public  void t(){
        System.out.println("child");
    }
}