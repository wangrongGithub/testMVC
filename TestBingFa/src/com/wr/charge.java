package com.wr;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

 class UndirectedGraphNode {
      int label;
     ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  };

public class charge
{//存储
    /**
     * Definition for undirected graph.
     * class UndirectedGraphNode {
     *     int label;
     *     ArrayList<UndirectedGraphNode> neighbors;
     *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     * };
     */

        public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node)
        {//吧总共几个节点知道;无方向图的便利啊;采取广度便利；
            UndirectedGraphNode head=node,newHead=null;

            Queue<UndirectedGraphNode> queue=new LinkedList<>();
            Map<UndirectedGraphNode,Integer >map=new HashMap<>();
            Map<UndirectedGraphNode,UndirectedGraphNode >nodes=new HashMap<>();
            //入队并访问
            newHead=new UndirectedGraphNode(head.label);
            queue.add(head);
            map.put(head,0);
            nodes.put(head,newHead);
            while(!queue.isEmpty())
            {
                UndirectedGraphNode top=queue.poll();
                ArrayList<UndirectedGraphNode> neighbors=top.neighbors;
                for(UndirectedGraphNode nb:neighbors)
                {
                    if(!map.containsKey(nb))
                    {
                        //rudui fangw

                        UndirectedGraphNode newNode=new UndirectedGraphNode(nb.label);
                        queue.add(nb);
                        map.put(nb,0);
                        nodes.put(nb,newNode);
                    }

                }
            }
            Set<UndirectedGraphNode> set=nodes.keySet();
            //进行复制啊

            System.out.println(set);
            for(UndirectedGraphNode nd:set)
            {
                System.out.println(nd.label);
                ArrayList<UndirectedGraphNode> neighbors=nd.neighbors;
                ArrayList<UndirectedGraphNode> neighbors1=new ArrayList<>();
                nodes.get(nd).neighbors=neighbors1;
                for(UndirectedGraphNode nn:neighbors)
                {
                    neighbors1.add(nodes.get(nn));
                }
            }
           return newHead;

        }



    public static  RandomListNode copyRandomList(RandomListNode head)
    {
        if(head==null)
        {
            return null;
        }
        RandomListNode p=head;
        while(p!=null)
        {
            RandomListNode q=new RandomListNode(p.label);
            //System.out.print(p.label+"--"+q.label+"  ");
            q.next=p.next;
            p.next=q;
            p=q.next;
        }
        p=head;
        //链表的拆分啊
        RandomListNode newHead=head.next,newTail=head.next;
        p=head;
        while(p!=null)
        {//新的尾巴是

            //给上一次的newTail初始化
            //给新的弄random的尾巴
            if(p.random!=null)
            {
               // System.out.println(p.label+",,"+p.random.next);
                newTail.random = p.random.next;
            }
            //把p往后面指一个
            p=newTail.next;
            if(p!=null)
            {
                newTail=p.next;
            }

        }

        p=head;newTail=head.next;
        while(p!=null)
        {//新的尾巴是

            //给上一次的newTail初始化
            //给新的弄random的尾巴

            //把p往后面指一个
            p.next=newTail.next;
            p=p.next;
            //把newTail往后面指一个
            if(p!=null)
            {
                newTail.next=p.next;
                newTail=newTail.next;
            }

        }
//System.out.println(newHead.label+" "+newHead.random.label+" "+newHead.next);
        return newHead;

    }
    public static int canCompleteCircuit(int[] gas, int[] cost)
    {//cost:从第i到i+1加油站的需要的油量cost[i]
        //满足的条件是：gas[j---j+n]>cost[j-----j+n]
        int i=0,j=0,sum=0;
        int len=gas.length;
        for(i=0;i<len;i++)
        {
            int flag=0;
            sum=0;//每次吧sum支撑0
            for(j=i;j<len;j++)
            {

                sum+=gas[j]-cost[j];
                if(sum<0)
                {
                    flag=1;
                    break;
                }
                System.out.println("i="+i+" "+sum);
            }

            if(flag==0)
            {
                for(j=0;j<i;j++)
                {
                    System.out.println("i="+i+" "+sum);
                    sum+=gas[j]-cost[j];
                    if(sum<0)
                    {
                        flag=1;
                        break;
                    }

                }
                if(flag==0)
                {return i;}

            }

        }
        System.out.println("i="+i+" "+sum);
        return -1;
    }

    public static int candy(int[] ratings)
    {
        //分糖果的问题啊，n个小孩子分糖果啊
        if(ratings.length<=1)
        {
            return ratings.length;
        }
        int sum=1;
        int []Candy=new int[ratings.length];
        Candy[0]=1;
        int min=0;
        for(int i=1;i<ratings.length;i++)
        {
            if(ratings[i-1]<ratings[i])
            {//右边比左边大
                Candy[i]=Candy[i-1]+1;
               // System.out.println("<");
            }
            else if(ratings[i-1]==ratings[i])
            {//右边与左边一样大
                Candy[i]=1;
               // System.out.println("=");
            }
            else
            {
                //System.out.println(">"+Candy[i-1]);
                Candy[i]=1;
                //左边只有一颗糖果，右边比左边小
                if(Candy[i-1]==1)
                {
                    int j=i-1;

                    //左边的比右边的大，左边加1
                    while(j>=0&&ratings[j]>ratings[j+1]&&Candy[j]<=Candy[j+1])
                    {
                     //  System.out.println( " ***");
                        Candy[j]++;
                        sum++;
                        j--;
                    }

                }

            }
            sum+=Candy[i];

        }

        return sum;
    }
    //动态规划；假设第一个小孩子得到的是正确的
public static void main(String[]args)
{//"pie","pear","apple","peach"
    UndirectedGraphNode n1=new UndirectedGraphNode(1);
    UndirectedGraphNode n2=new UndirectedGraphNode(2);
    UndirectedGraphNode n3=new UndirectedGraphNode(3);
    UndirectedGraphNode n4=new UndirectedGraphNode(4);
    ArrayList<UndirectedGraphNode> an1=new ArrayList();
    n1.neighbors=an1;an1.add(n3);an1.add(n4);an1.add(n2);
    ArrayList<UndirectedGraphNode> an2=new ArrayList();
    n2.neighbors=an2;an2.add(n1);an2.add(n4);
    ArrayList<UndirectedGraphNode> an3=new ArrayList();
    n3.neighbors=an3;an3.add(n3);an3.add(n1);

      System.out.println(cloneGraph(n1));










}
    /**
     * 状态转移方程：
     * f(i) 表示s[0,i]是否可以分词
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     *
     */
    public static ArrayList<String> wordBreak3(String s, Set<String> dict)
    {/**
     * 状态转移方程：
     * f(i) 表示s[0,i]是否可以分词
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     *
     */

        int len = s.length();
        int min=9999;
        //java不能定义数组
        List<ArrayList<String>> arrays = new ArrayList(s.length()+1);
        for(int i=0;i<s.length()+1;i++)
        {
            arrays.add(new ArrayList<>());

        }
        String []sets=new String [dict.size()];
        dict.toArray(sets);
        Set<Integer>set=new HashSet<Integer>();
        for(String str:dict)
        {
            if(min>str.length())
            {
                min=str.length();
            }
            set.add(str.length());
        }
        //System.out.println(set);
        Integer []sets1=new Integer [set.size()];
        set.toArray(sets1);
        //System.out.println(arrays);
        //为什么分开了两个a
        for (int i = len-1; i >= 0; --i){


            for (int j = i+1; j <=len; ++j)
            {
                String subStr=s.substring(i, j);
                //System.out.println(subStr);
                //如果在本来的dict里面有，就直接放进去

                ArrayList<String> listj=null;

                if ((listj=arrays.get(len-j)).size()>0 && dict.contains(subStr))
                {
                    ArrayList<String> list=arrays.get(len-i);
                    System.out.println("list "+listj);
                    for(String str:listj)
                    {
                        System.out.println("substr "+subStr+" sublist "+str);
                        list.add(subStr+" "+str);
                    }
                }
            }
            String subStr=s.substring(i, len);
            if(dict.contains(subStr))
            {
                ArrayList<String> list=arrays.get(len-i);
                list.add(subStr);
                System.out.println("i="+i+" "+arrays);
                continue;
            }
            System.out.println("i="+i+" "+arrays);
        }
      System.out.println(arrays);
        // arrays.get(s.length())进行相关排序啊
        Collections.sort(arrays.get(s.length()),new Comparator(){


            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof  String &&o2 instanceof String)
                {//进行排序
                    String str1=o1.toString();
                    String str2=o2.toString();
                    //
                    String[]strs1=str1.split(" ");
                    String[]strs2=str2.split(" ");
                    int i=strs1.length-1;int j=strs2.length-1;
                    while(i>=0&&j>=0)
                    {
                        if(strs1[i].length()>strs2[j].length())
                        {//o1>o2
                           return 1;
                        }
                        else if(strs1[i].length()<strs2[j].length())
                        {
                            return -1;
                        }
                        else
                        {
                            i--;
                            j--;
                        }

                    }

                }
                return 0;
            }
        });
        return arrays.get(s.length());
    }

    public static ArrayList<String> wordBreak5(String s, Set<String> dict)
    {/**
     * 状态转移方程：
     * f(i) 表示s[0,i]是否可以分词
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     *
     */

        int len = s.length();
        //java不能定义数组
        List<ArrayList<String>> arrays = new ArrayList(s.length()+1);
        for(int i=0;i<s.length()+1;i++)
        {
            arrays.add(new ArrayList<>());

        }

        String []sets=new String [dict.size()];
        dict.toArray(sets);
        Set<Integer>set=new HashSet<Integer>();
        for(String str:dict)
        {
            set.add(str.length());
        }
        Integer []sets1=new Integer [set.size()];
        set.toArray(sets1);
        int flag=0;
        for(int i=0;i<sets1.length;i++)
        {

            if(dict.contains((s.substring(len-sets1[i],len))))
            {
                flag=1;
            }

            if (sets1[i]<=s.length()&& dict.contains((s.substring(0,sets1[i]))))
            {//可以分割的都写好啊
                ArrayList<String> list=arrays.get(sets1[i]);
                list.add(0,s.substring(0,sets1[i]));
            }

        }
        //从头输出来;判断一下
       if(flag==0)
       {
           return arrays.get(0);
       }



        for (int i = 1; i <= len; ++i)
        {
            System.out.println("i="+i);
            int flag1=0;
            for(int k=0;k<sets1.length;k++)
            {
                if(sets1[k]<=i&& dict.contains((s.substring(i-sets1[k],i))))
                {
                    flag1=1;
                }
            }
            if(flag1==0)
            {
                continue;
            }




            for (int j = 0; j <i; ++j)
            {
                ArrayList<String> listj=null;
                String subStr=s.substring(j, i);
                if ((listj=arrays.get(j)).size()>0 && dict.contains(subStr))
                {
                    ArrayList<String> list=arrays.get(i);
                    for(String str:listj) {
                        list.add(0,str+" "+subStr);
                    }
                }
            }
        }
        Collections.sort(arrays.get(s.length()),new Comparator(){


            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof  String &&o2 instanceof String)
                {//进行排序
                    String str1=o1.toString();
                    String str2=o2.toString();
                    //
                    String[]strs1=str1.split(" ");
                    String[]strs2=str2.split(" ");
                    int i=strs1.length-1;int j=strs2.length-1;
                    while(i>=0&&j>=0)
                    {
                        if(strs1[i].length()>strs2[j].length())
                        {//o1>o2
                            return 1;
                        }
                        else if(strs1[i].length()<strs2[j].length())
                        {
                            return -1;
                        }
                        else
                        {
                            i--;
                            j--;
                        }

                    }

                }
                return 0;
            }
        });
     //   System.out.println(arrays);
        return arrays.get(s.length());
    }
public static boolean wordBreak1(String s, Set<String> dict){

        int len = s.length();
        boolean[] arrays = new boolean[len+1];

        arrays[0] = true;
        for (int i = 1; i <= len; ++i){
            for (int j = 0; j < i; ++j){
                if (arrays[j] && dict.contains(s.substring(j, i))){
                    arrays[i] = true;
                    break;
                }
            }
        }
        return arrays[len];
    }
    public static boolean wordBreak(String s, Set<String> dict)
    {

        Set<Integer>set=new HashSet<Integer>();
        for(String str:dict)
        {
            set.add(str.length());
        }
        Integer []sets=new Integer [set.size()];
        set.toArray(sets);
        return wordBreak( s,  dict, sets);
    }
    public static boolean wordBreak(String s, Set<String> dict,  Integer []sets)
    {
        for(int i=0;i<sets.length;i++)
        {
            int len=sets[i];
            if(len>s.length()&&s.equals(""))
            {
                return true;

            }
            if(len>s.length())
            {
                continue;
            }
            //如果包含
            if(dict.contains(s.substring(0,len)))
            {
                System.out.println(s+"   "+s.substring(0,len)+"   "+s.substring(len,s.length()));
                //将b穿
                boolean result=wordBreak(s.substring(len,s.length()), dict,sets);
                System.out.println(result+"   "+s.substring(len,s.length()));
                if(result==true)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
