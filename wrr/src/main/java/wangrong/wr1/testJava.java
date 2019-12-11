package wangrong.wr1;

import java.util.ArrayList;
import java.util.List;

public class testJava {
    int i=0;
 void tt()
{
    System.out.println("testJava");
}
public static void main(String[]o)
{
    testJava g=new wangrong.wr1.config.testJava2();
    g.tt();
}

}
 class testJava1 extends testJava{
    int i=0;

     public void tt()
     {
         super.tt();
         System.out.println("testJava1");
         return ;
     }

}
class testJava2 extends testJava1{
    int i=0;

    public void tt()
    {
        super.tt();
        System.out.println("testJava2");
        return ;
    }

}


class Sick extends Exception{}
class FeverSick extends Sick{}
class JointSick extends Sick{}

class Children extends People{
    public void coldAir() throws FeverSick{
new People() {
void wr()
{}
    @Override
    void coldAir() throws Sick {

    }
};


    }
}

class Adults extends People{
    int i=0;
    public People coldAir1(){
        int j=0;
        class wrr extends People{


            @Override
            void coldAir() throws Sick {

            }
        }
return new wrr();

    }

    @Override
    void coldAir() throws Sick {

    }
}

class Olds extends People{
   public void coldAir() throws JointSick,FeverSick{  }
}

    abstract class People {
    abstract  void coldAir() throws Sick ;
}
interface wrr1{
    int i=0;

}
interface wrr extends  wrr1{
    int i=0;
    public void sp();

}

