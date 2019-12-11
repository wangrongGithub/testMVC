package cn.wr.SpringBoot;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

import java.util.*;
import java.util.Scanner;

public class MainWr {
    public static class member {
        int beginTime;
        int endTime;
        String region;
        int date;

        @Override
        public String toString() {
            return "member{" +
                    "beginTime=" + beginTime +
                    ", endTime=" + endTime +
                    ", region='" + region + '\'' +
                    ", date=" + date +
                    '}';
        }
    }
    public static class action {
        int beginTime;
        int endTime;
        String region;
        int date;
        double coin;

        @Override
        public String toString() {
            return "action{" +
                    "beginTime=" + beginTime +
                    ", endTime=" + endTime +
                    ", region='" + region + '\'' +
                    ", date=" + date +
                    ", coin=" + coin +
                    '}';
        }
    }
    public static Map<String,Map<Integer,List<member>>> members=new HashMap();//按照地点作为key;里面的map按照date作为key
    public static Map<String,Map<Integer,List<action>>> actions=new HashMap();//按照地点作为key;里面的map按照date作为key
//1:12-16:hangzhou;15:10-14:shanghai;16:16-17:beijing
//1:10-12:beijing:1;1:10-14:hangzhou:3;10-14:shanghai:2;15:11-12:shanghai:0.5;13-14:shanghai:2; 16:16-17:jingbei:1
    public static double getMaxCoins()
    {
        //使用贪心选择的啊;每次贪心选择活动积分最大的，看有没有
        //对每一个用户的
        Set<String>regions=members.keySet();
        double maxCoins=0.0;
        //region匹配
        for(String region:regions)
        {
            Map<Integer,List<member>> mMap=members.get(region);
            Set<Integer> dates=mMap.keySet();
            Map<Integer,List<action>> aMap=actions.get(region);
            //date匹配
            for(Integer date:dates)
            {
                //获取到用户在 region，date的所有空闲时间的啊
                //region匹配，date匹配
                List<member> mList=mMap.get(date);
                List<action>  aList=aMap.get(date);
                //接下来匹配时间段啊;对于用户的每一个空闲的时间段安排活动啊
                System.out.println("地点和date匹配的member= "+mList+" 地点和date匹配的action="+aList);
                //mList表示所有的时间date匹配的members啊
                //List表示所有的时间date匹配的actions啊
                for(member m:mList)
                {//aList是region,date匹配的所有的活动的啊
                    //首先进行一个活动的过滤
                    int startTime=m.beginTime;
                    int endTime=m.endTime;
                    System.out.print(m+"  :");
                    Map<Integer,List<action>>mAction=new HashMap<Integer, List<action>>();
                    for(action a:aList)
                    {
                        if(a.beginTime>=startTime&&a.endTime<=endTime)
                        {//用endTime作为key;弄一个偏移量啊
                            int key=a.endTime-startTime;
                            //存放满足条件的action
                            List<action> mA=mAction.get(key);
                            if(mA==null)
                            {
                                mA=new ArrayList<action>();
                                mAction.put(key,mA);
                            }
                            mA.add(a);
                        }

                    }

                    System.out.println(mAction);
                    double [] coinDP=new double[endTime-startTime+1];
                    for(int i=1;i<=endTime-startTime;i++)
                    {
                        //找到最大的那个啊
                        //表示终止时间是i的啊;在里面找满足条件最大的啊
                        List<action> _aList=mAction.get(i);
                        System.out.println("i="+i+":"+_aList);
                        if(_aList==null)
                        {
                            coinDP[i]=coinDP[i-1];
                            continue;
                        }
                        coinDP[i]=coinDP[i-1];
                        for(action a:_aList)
                        {
                            int low=a.beginTime-startTime;
                            int high=a.endTime-startTime;//这个是high的啊
                            coinDP[i]=Math.max(coinDP[low]+a.coin,coinDP[i]);
                        }
                        System.out.println(coinDP[i]);
                    }
                    maxCoins+=coinDP[coinDP.length-1];

                }

            }

        }
        return maxCoins;
    }
   public static void main(String []a)
   {
       String strMember="1:12-16:hangzhou;15:10-14:shanghai;16:16-17:beijing";
       getMembers( strMember);
       System.out.println(members);
       String strAction="1:15-16:hangzhou:7;1:12-14:hangzhou:8;1:12-13:hangzhou:2;1:13-14:hangzhou:5;1:14-16:hangzhou:3;1:10-12:beijing:1;1:10-14:hangzhou:3;1:10-14:shanghai:2;15:11-12:shanghai:0.5;1:13-14:shanghai:2;16:16-17:beijing:1";
       getActions( strAction);
       System.out.println(actions);
       System.out.println("max coins=   "+getMaxCoins());
   }

    //读取员工的时间数据
    public static void  getMembers(String strMember)
    {
        String []strMembers=strMember.split(";");
        for(int i=0;i<strMembers.length;i++)
        {
            System.out.println(strMembers[i]);
            String []memberStrs=strMembers[i].split(":");
            member m=new member();
            m.date=Integer.valueOf(memberStrs[0]);
            m.beginTime=Integer.valueOf(memberStrs[1].split("-")[0]);
            m.endTime=Integer.valueOf(memberStrs[1].split("-")[1]);
            m.region=memberStrs[2];
            Map<Integer,List<member>> map=members.get(m.region);
            if(map==null)
            {
                map=new HashMap<Integer, List<member>>();
                members.put(m.region,map);
            }
            Integer date=m.date;
            List<member> list=map.get(date);
            if(list==null)
            {
                list=new ArrayList<member>();
                map.put(date,list);
            }
            list.add(m);
        }
    }
    public static void  getActions(String strAction)
    {
        String []strActions=strAction.split(";");
        for(int i=0;i<strActions.length;i++)
        {
            System.out.println(strActions[i]);
            String []actionStr=strActions[i].split(":");
            action a=new action();
            //1:10-12:beijing:1;1:10-14:hangzhou:3;10-14:shanghai:2;15:11-12:shanghai:0.5;13-14:shanghai:2; 16:16-17:jingbei:1
            a.date=Integer.valueOf(actionStr[0]);
            a.beginTime=Integer.valueOf(actionStr[1].split("-")[0]);
            a.endTime=Integer.valueOf(actionStr[1].split("-")[1]);
            a.region=actionStr[2];
            a.coin=Double.valueOf(actionStr[3]);
            Map<Integer,List<action>> map=actions.get(a.region);
            if(map==null)
            {
                map=new HashMap<Integer, List<action>>();
                actions.put(a.region,map);
            }
            Integer date=a.date;
            List<action> list=map.get(date);
            if(list==null)
            {
                list=new ArrayList<action>();
                map.put(date,list);
            }
            list.add(a);
        }

    }








    //AaAAAA来输出
    public static void main2(String[]a)
    {
        Scanner scan=new Scanner(System.in);
        int len=Integer.valueOf(scan.nextLine());
        String str=scan.nextLine();
        List<Integer>flags=new ArrayList<Integer>();
        List<Integer>nums=new ArrayList<Integer>();
        int flag=-1;
        int num=0;
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(flag==-1)
            {
                if(ch>='a'&&ch<='z')
                {
                    flag=0;
                    num=1;
                }
                else
                {
                    flag=1;
                    num=1;
                }
                continue;
            }
            if(flag==0)
            {
                if(ch>='a'&&ch<='z')
                {
                    num++;
                }
                else
                {
                    if(num>0)
                    {
                        flags.add(0);
                        nums.add(num);

                    }
                    flag=1;
                    num=1;
                }
            }
           else if(flag==1)
            {
                if(ch>='A'&&ch<='Z')
                {
                    num++;
                }
                else
                {
                    if(num>0)
                    {
                        flags.add(1);
                        nums.add(num);
                    }
                    flag=0;
                    num=1;

                }
            }
        }
        nums.add(num);
        flags.add(flag);
        flag=0;
        int sum=0;
        for(int i=0;i<nums.size();i++)
        {
            int num1=nums.get(i);
            int flag1=flags.get(i);
            if(flag==flag1)
            {
                sum+=num1;
            }
            else//不一样的
            {
                if(num1==1)//不进行切换
                {
                    sum+=2;
                }
                else//进行切换啊
                {
                    sum+=num1+1;
                    flag=(flag==0)?1:0;
                }
            }
        }
     System.out.println(sum);
    }


    public static void main1(String[]a)
    {
        Scanner scan=new Scanner(System.in);
        //1 5 10 20 50 100
        List<Integer>list=new ArrayList();
        String str=scan.nextLine();
        String []strs=str.split(" ");
        int[]nums=new int[strs.length];
        int amount=scan.nextInt();
        int MNum=0;
        for(int i=0;i<nums.length;i++)
        {
            nums[i]= Integer.valueOf(strs[i]);
            MNum+=nums[i];
        }
        System.out.println(change(amount,nums,MNum));
    }
    public static int change(int amount, int[] nums,int MNum) {
        Object dp[][] = new Object[MNum+1][amount+1];
        // 设置起始状态
        //dp[0] = 1;
        int []coins=new int[]{1,5,10,20,50,100};
        int i=1;
        //每一个都有一个这个东西啊
        //一个object是一个List<List<Integer>>
       // List<List<Integer>>[][] list=new ArrayList<List<Integer>>[][]();
        for(int j=0;j<MNum+1;j++)
        {
            List<List<Integer>> now=new ArrayList<List<Integer>>();
            now.add(new ArrayList<Integer>());
            dp[j][0]=now;
        }
        for (int  coinIndex=0;coinIndex<coins.length;coinIndex++)
        {
            int coin=coins[coinIndex];
            int num=nums[coinIndex];
            for(int j=0;j<num;j++)
            {
                // 记录每添加一种面额的零钱，总金额j的变化;k是金额的变化的啊
                for (int k = 1;k <= amount; k++)
                {
                    if(i==MNum&&k==amount)
                    {
                        System.out.println("进入");
                    }
                    List<List<Integer>> now=new ArrayList<List<Integer>>();
                    List<List<Integer>> dLast=null;
                    if((List<List<Integer>>)dp[i-1][k]!=null)//
                    {
                        dLast=(List<List<Integer>>)dp[i-1][k];
                        for(List<Integer> dL:dLast)
                        {
                            List<Integer> newList=new ArrayList<Integer>();
                            newList.addAll(dL);
                            now.add(newList);
                        }
                    }
                        if (k >= coin)//k>=coin；这样子才有机会加入k的啊  , .....
                        {//如果不存在就设置成0
                            if(dp[i-1][k-coin]!=null) {
                                dLast=(List<List<Integer>>)dp[i-1][k-coin];
                                for (List<Integer> dL : dLast) {
                                    List<Integer> newList = new ArrayList<Integer>();
                                    newList.addAll(dL);
                                    newList.add(coin);
                                    now.add(newList);
                                }
                            }
                        }
                        //去掉重复的啊;将重复的取出掉啊
                    Map<String,List<Integer>>map=new HashMap();
                    for(List<Integer> l:now)
                    {
                        map.put(l.toString(),l);

                    }
                    now=new ArrayList<List<Integer>>();
                    Set<String>set=map.keySet();
                    for(String s:set)
                    {
                        now.add(map.get(s));
                    }
                     dp[i][k]=now;

                    System.out.println(""+(i)+","+k+":"+dp[i][k]);
                }
                        i++;
            }
        }
        if(dp[MNum][amount]==null)
        {
            return -1;
        }
        List<List<Integer>> list=(List<List<Integer>> )  dp[MNum][amount];
        int result=0;
        for(List<Integer> l:list)
        {
            result+=l.size();

        }

        return result;
    }
}
