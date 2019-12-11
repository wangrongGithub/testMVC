package RL.TraditionalRL;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SarsaQL
{
    static boolean [][][] AvailableA=new  boolean [Evn2.migong.length][Evn2.migong[0].length][4];
    static double [][][] UserPath=new  double [Evn2.migong.length][Evn2.migong[0].length][4];
    static double [][][] table=new  double [Evn2.migong.length][Evn2.migong[0].length][4];
   static {
       for (int i = 0; i < 5; i++) {//true不可以走
           AvailableA[0][i][0] = true;
           AvailableA[4][i][1] = true;
           AvailableA[i][0][2] = true;
           AvailableA[i][4][3] = true;
       }

   }
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
    public static void SarsaLmd()
    {
        int i=0;
        UserPath=new  double [Evn2.migong.length][Evn2.migong[0].length][4];

        state S=new state(0,0);
        int a=choose_action(S.i, S.j);
        while(S.i<4||S.j<4)
        {
            //执行a
            Evn2.SR sr= Evn2.GetReward(S.i,S.j,a);
            double r=sr.R;
            state newS=new state(sr.i,sr.j);
            //选择下一个a
            int newA=  choose_action(newS.i, newS.j);
            //记录啊
            for(int k=0;k<4;k++)
            {
                if(k==a)
                {
                    UserPath[S.i][S.j][k]=1;
                }
                else
                {
                    UserPath[S.i][S.j][k]=0;
                }
            }

            double error=r+gm*table[newS.i][newS.j][newA]-table[S.i][S.j][a];


            double real=table[newS.i][newS.i][newA];
            for(int i1=0;i1<5;i1++ )
            {
                for(int j1=0;j1<5;j1++)
                {
                    for(int k1=0;k1<4;k1++)
                    {
                        table[i1][j1][k1]+=lr*error*UserPath[i1][j1][k1];
                        UserPath[S.i][S.j][a]*=0.9*0.9;

                    }
                }
            }

           // System.out.println("r="+r+" table[S.i][S.j][a]="+table[S.i][S.j][a]+"r+gm*real-table[S.i][S.j][a]="+(r+gm*real-table[S.i][S.j][a]));
           // System.out.println(S.i+"  "+S.j+"  a="+a+"   "+newS.i+"  "+newS.j+"  newA="+newA);
            S=newS;
            a=newA;
            i++;
        }

System.out.println(i);


    }

    public static void SarsaLmd1()
    {
        int i=0;
        state S=new state(0,0);
        int a=choose_action(S.i, S.j);
        while(S.i<4||S.j<4)
        {
            //执行a
            Evn2.SR sr= Evn2.GetReward(S.i,S.j,a);
            double r=sr.R;
            state newS=new state(sr.i,sr.j);
            //选择下一个a
            int newA=  choose_action(newS.i, newS.i);
            double real=table[newS.i][newS.i][newA];
            table[S.i][S.j][a]+=lr*(r+gm*real-table[S.i][S.j][a]);
            // System.out.println("r="+r+" table[S.i][S.j][a]="+table[S.i][S.j][a]+"r+gm*real-table[S.i][S.j][a]="+(r+gm*real-table[S.i][S.j][a]));
            // System.out.println(S.i+"  "+S.j+"  a="+a+"   "+newS.i+"  "+newS.j+"  newA="+newA);
            S=newS;
            a=newA;
            i++;
        }

        System.out.println(i);


    }




//选择动作啊
  static  int  choose_action(int i, int j)
    {//贪心算法选择
        double abss= Math.random();
        int a=0;
        if(abss>abs||(table[i][j][0]==0&&table[i][j][1]==0&&table[i][j][2]==0&&table[i][j][3]==0))
        {//随机选一个合适的动作
            List<Integer> map=new LinkedList<>();
            for(int t=0;t<4;t++)
            {

                if(AvailableA[i][j][t]==false)
                {
                    map.add(t);
                }

            }
            a=map.get((int)(Math.random()*map.size()));
         //   System.out.println("<"+i+","+j+">:"+map);
        }
        else//选最大的靠右边
        {
               List<Integer> map=new LinkedList<>();
               int max=-1;double numMax=Integer.MIN_VALUE;
                for(int t=0;t<4;t++)
                {
                    if(AvailableA[i][j][t]==false)
                    {
                        if(max==-1||numMax<table[i][j][t])
                        {
                           max=t;
                           numMax=table[i][j][t];
                        }
                        map.add(t);
                    }

                }
                a=max;
             //   System.out.println("<"+i+","+j+">:"+map);

        }
//System.out.println(a);
        return a;




    }

public static void main(String[]args)
{
    int i=10;
    while(i-->=0)
    {
        SarsaLmd();
    }

}


   }




class Evn2{
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


