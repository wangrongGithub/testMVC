import java.util.concurrent.locks.ReentrantLock;

public class TestVolatile {
    static example e1=new example();
    private static volatile  int count = 0;
    static int a=0;
    static int b=0;
    static int x=0;
    static int y=0;

    public static void main(String[] args)
    {
        int i=0,j=0;
        ThreadLocal tl=new ThreadLocal();
        for(i=0;i<10;i++) {
            j++;
            tl.set("");
            Thread thread = new Thread("qr" + i){
                public void run()
                {
                    tl.set("wr  "+this);
                    tl.set("pyb"+Thread.currentThread());

                    System.out.println(Thread.currentThread()+" "+tl.get());

                }
            };
            thread.start();

        }

    }


    public static void main2(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x=b;
            }
        };
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                b=3;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                y=a;
            }
        };
       // while(true)
        {
            //for (int i = 0; i < 10000; i++)
            {
               Thread t1= new Thread(runnable);
                Thread t2=  new Thread(runnable1);
                t1.start();
                t2.start();
                //t1.join();
               // t2.join();
Thread.sleep(200);
                System.out.println("x="+x+" y=  "+y

                );
            }

        }
       // Thread.sleep(1000);//为了等子线程全部运行结束
        //System.out.println(count);
    }


    public static void main1(String[] args)
    {

for(int i = 0;i<10;i++)

    {
        ThreadLocal tl=new ThreadLocal();
        int j=0;
       // tl.set("wr"+i);
        Thread thread=new Thread ("qr"+i){
           // ThreadLocal tl=new ThreadLocal();
            @Override
            public void run() {
                for(int i=0;i<10;i++)
                {
                    TestVolatile.e1.addL();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this+"  "+TestVolatile.e1.getL());
                }
                tl.set("dddd"+j);

            }
        };
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
        System.out.println("main  "+TestVolatile.e1.getL());
}

}
  class example{

     public  long l;
    long getL()
    {
        return l;

    }
    void addL()
    {
        l++;
    }
      void addL(long L)
      {
          l=L;
      }
  }
