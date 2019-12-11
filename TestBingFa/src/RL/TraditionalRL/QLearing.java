package RL.TraditionalRL;

import java.util.LinkedList;
import java.util.List;

public class QLearing
{
    static List<Integer> list=new LinkedList<>();
    static class state{
        int i=0,j=0;
        state(int _i,int _j)
        {
            i=_i;j=_j;
        }

    }
    static  double af=0.9;
    static  double lr=0.1;
    static  double gm=0.9;
    static double abs=0.9;
    static int avarge=0;
    static double [][] table=new double[Evn1.Location][2];
    static double [][] table1=new double[Evn3.migong.length*Evn3.migong[0].length][4];
    static double [][] table2=new double[Evn3.migong.length][Evn3.migong[0].length];
static{
    for(int i=0;i<table1.length;i++)
    {
        for(int j=0;j<4;j++)
        {
        }
    }
}
public static void RL1()
    {
        Evn3.SR sr=null;
        state S=new state(0,0);
        int i=0;
        while(S.i<Evn3.migong.length-1||S.j<Evn3.migong[0].length-1)
        {
            double abss= Math.random();
            int a=0;

            int index=S.i*Evn3.migong[0].length+S.j;
            if(index>=25)
            {
                break;
            }
            //没有更新过啊
            if(abss>abs||(table1[index][0]==0&&table1[index][1]==0&&table1[index][2]==0&&table1[index][3]==0))
            {//随机选一个动作
                //随机选一个可用的动作
                while(true)
                {
                    a = (int) (Math.random() * 4);
                    if(a==0)
                    {
                        if(S.i-1>=0)
                        {
                            break;
                        }
                    }
                    else if(a==1)
                    {
                        if(S.i+1<5)
                        {
                            break;
                        }
                    }else if(a==2)
                    {
                        if(S.j-1>=0)
                        {
                            break;
                        }
                    }
                    else
                    {
                        if(S.j+1<5)
                        {
                            break;
                        }
                    }
                }
                sr=Evn3.GetReward(S.i,S.j,a);
            }
            else//选最大的
            {

                a=table1[index][0]>table1[index][1]?0:1;
                a=table1[index][a]>table1[index][2]?a:2;
                a=table1[index][a]>table1[index][3]?a:3;
                sr=Evn3.GetReward(S.i,S.j,a);
            }
            double real=0;
            int index1=sr.i*Evn3.migong[0].length+sr.j;
            if(index1>=25)
            {
                real=0;
            }
           else
            {
                real=table1[index1][0]>table1[index1][1]?0:1;
                real=real>table1[index1][2]?real:2;
                real=real>table1[index1][3]?real:3;
            }
            table1[index][a]=table1[index][a]+af*(sr.R+gm*real-table1[index][a]);
            S.i=sr.i;S.j=sr.j;
            i++;

        }
        avarge+=i;
      //  System.out.println();
        System.out.println(i);
    }

    public static void RL3()
    {
        Evn3.SR sr=null;
        state S=new state(0,0);
        int i=0;
        while(S.i<Evn3.migong.length-1||S.j<Evn3.migong[0].length-1)
        {
            double abss= Math.random();
            int a=0;

            int index=S.i*Evn3.migong[0].length+S.j;
            if(index>=25)
            {
                break;
            }
            //没有更新过啊
            if(abss>abs||(table1[index][0]==0&&table1[index][1]==0&&table1[index][2]==0&&table1[index][3]==0))
            {//随机选一个动作
                //随机选一个可用的动作
                while(true)
                {
                    a = (int) (Math.random() * 4);
                    if(a==0)
                    {
                        if(S.i-1>=0)
                        {
                            break;
                        }
                    }
                    else if(a==1)
                    {
                        if(S.i+1<5)
                        {
                            break;
                        }
                    }else if(a==2)
                    {
                        if(S.j-1>=0)
                        {
                            break;
                        }
                    }
                    else
                    {
                        if(S.j+1<5)
                        {
                            break;
                        }
                    }


                }
                sr=Evn3.GetReward(S.i,S.j,a);
            }
            else//选最大的
            {
                a=table1[index][0]>table1[index][1]?0:1;
                a=table1[index][a]>table1[index][2]?a:2;
                a=table1[index][a]>table1[index][3]?a:3;
                sr=Evn3.GetReward(S.i,S.j,a);

            }
            double real=0;
            //当是负数的时候就是说明到了墙壁的位置啊
            if(sr.R>=0&&S.i==sr.i&&S.j==sr.j)
            {
                continue;
            }
            int index1=sr.i*Evn3.migong[0].length+sr.j;
            if(index1>=25)
            {
                real=0;
            }
            else
            {
                real=table1[index1][a];
            }
            table1[index][a]=table1[index][a]+af*(sr.R+gm*real-table1[index][a]);
           // System.out.print(i+" <"+S.i+","+S.j+","+a+">, ");
            S.i=sr.i;S.j=sr.j;

            i++;

        }
        avarge+=i;
     //   System.out.println();
        System.out.println(i);
    }




    public static void RL4()
    {
        //sarsa方法啊
        Evn3.SR sr=null;
        state S=new state(0,0);
        int i=0;
        int a=0; int a1=0;
        //选择a啊
        while(true)
        {
            a = (int) (Math.random() * 4);
            if(a==0)
            {
                if(S.i-1>=0)
                {
                    break;
                }
            }
            else if(a==1)
            {
                if(S.i+1<5)
                {
                    break;
                }
            }else if(a==2)
            {
                if(S.j-1>=0)
                {
                    break;
                }
            }
            else
            {
                if(S.j+1<5)
                {
                    break;
                }
            }
        }
        while(S.i<Evn3.migong.length-1||S.j<Evn3.migong[0].length-1)
        {
            double abss= Math.random();


            int index=S.i*Evn3.migong[0].length+S.j;
            if(index>=25)
            {
                break;
            }
            //执行a,得到新的
            sr=Evn3.GetReward(S.i,S.j,a);
            //没有更新过啊
            double real=0;
            //当是负数的时候就是说明到了墙壁的位置啊
            int index1=sr.i*Evn3.migong[0].length+sr.j;
            //用贪心选择a1S
            //没有更新过啊
            if(abss>abs||(table1[index1][0]==0&&table1[index1][1]==0&&table1[index1][2]==0&&table1[index1][3]==0))
            {//随机选一个动作
                //随机选一个可用的动作
                while(true)
                {
                    a1 = (int) (Math.random() * 4);
                    if(a1==0)
                    {
                        if(sr.i-1>=0)
                        {
                            break;
                        }
                    }
                    else if(a1==1)
                    {
                        if(sr.i+1<5)
                        {
                            break;
                        }
                    }else if(a1==2)
                    {
                        if(sr.j-1>=0)
                        {
                            break;
                        }
                    }
                    else
                    {
                        if(sr.j+1<5)
                        {
                            break;
                        }
                    }


                }
                //得到

            }
            else//选最大的
            {

                a1=table1[index1][0]>table1[index1][1]?0:1;
                a1=table1[index1][a1]>table1[index1][2]?a1:2;
                a1=table1[index1][a1]>table1[index1][3]?a1:3;
               // sr=Evn3.GetReward(S.i,S.j,a);

            }
           real=table1[index1][a1];
            table1[index][a]=table1[index][a]+af*(sr.R+gm*real-table1[index][a]);
            S.i=sr.i;S.j=sr.j;
            a=a1;
            i++;
        }
        avarge+=i;
        list.add(i);
        System.out.println(i);
    }



    public static void RL()
    {
        Evn1.SR sr=null;
        int S=0;
        int i=0;
        while(S!=-1)
        {
            double abss= Math.random();
            int a=0;
            i++;
            if(abss>abs||(table[S][0]==0&&table[S][1]==0))
            {//随机选一个动作
                 a=(int)(Math.random()*2);
                sr=Evn1.GetReward(S,a);
            }
            else//选最大的靠右边
            {
                a=table[S][0]>table[S][1]?0:1;
                sr=Evn1.GetReward(S,a);

            }
            double real=0;
            if(sr.S==-1)
            {
                real=0;
            }
            else
            {
                real=table[sr.S][0]>table[sr.S][1]?table[sr.S][0]:table[sr.S][1];
            }

            table[S][a]=table[S][a]+af*(sr.R+gm*real-table[S][a]);
            S=sr.S;
        }
        System.out.println(i);
    }
    public static void main(String []args)
    {
        int i=10000;
        while(i-->0)
        {
            RL4();
            for(int j=0;j<table1.length;j++)
            {

                for(int k=0;k<table1[0].length;k++)
                {
                    if(table1[j][k]!=0)
                    {}
                  //  System.out.println("<"+j/5+","+j%5+","+k+","+table1[j][k]+">");
                }

            }

}
System.out.println(avarge/10000);
        }




}
class Evn1{
   static int Location=20;
    public static SR GetReward(int S,int A)
    {//左边
        if(A==0)
        {
            int s=(S==0)?0:(S-1);

           return new SR(s,0);
        }
        else
        {
            //用-1表示终止状态
            if(S==Location-2)
            return new SR(-1,1);
            else
            {
                return new SR(S+1,0);
            }
        }

    }
    static class SR{
        int S;
        int R;
        SR(int _s,int _r)
        {
            S=_s;
            R=_r;
        }
    }
}


class Evn3{
    static  int [][] migong=new int[5][5];
    static {
        migong[0]=new int[]{0,0,0,1,0};
        migong[1]=new int[]{0,1,0,0,0};
        migong[2]=new int[]{0,0,1,1,0};
        migong[3]=new int[]{0,0,1,0,0};
        migong[4]=new int[]{1,0,0,0,0};

    }
    public static SR GetReward(int i,int j,int A)
    {   //上边
        if(A==0)
        {
            int I=(i==0)?0:(i-1);
            int r=0;
            if(migong[I][j]==1)
            {
                r=-1;
                I=i;
            }
            return new SR(I,j,r);
        }
        //下边
        else  if(A==1)
        {
            int I=(i==4)?4:(i+1);
            int r=0;
            if(I==4&&j==4)
            {
                r=1;
            }
            if(migong[I][j]==1)
            {
                r=-1;
                I=i;
            }
            return new SR(I,j,r);
        }
        //左边
        else if(A==2)
        {
            int J=(j==0)?0:(j-1);
            int r=0;
            if(migong[i][J]==1)
            {
                r=-1;
                J=j;
            }
            return new SR(i,J,r);
        }
        else
        {
            int J=(j==4)?4:(j+1);
            int r=0;
            if(i==4&&J==4)
            {
                r=1;
            }
            if(migong[i][J]==1)
            {
                r=-1;
                J=j;
            }
            return new SR(i,J,r);
        }


    }
    static class SR{
        int i;int j;
        int R;
        SR(int _i,int _j,int _r)
        {
            i=_i;j=_j;
            R=_r;
        }
    }
}

