package com.wr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.*;
import java.util.*;

public class _3_16 {
    static int t=10;
    static{
        t=9;
        System.out.println(t);
    }

    public static ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict)
    {
        //最短的转换序列啊
        //if(dict.contains(start)&&dict.contains(end))
        //采取广度优先便利的算法啊
        dict.add(start);
        String []dicts=new String[dict.size()];

        dict.toArray(dicts);
        Map<String,List<String>>map=new HashMap<>();
        for(int i=dicts.length-1;i>=0;i--)
        {
            List<String> list=new LinkedList<>();
            for(int j=dicts.length-1;j>=0;j--)
            {
                int k=adjust(dicts[i],dicts[j]);
                if(k!=-1)
                {
                    list.add(dicts[j]);

                }

            }
            if(adjust(dicts[i],end)!=-1)
            {
                list.add(end);
            }
            map.put(dicts[i],list);
        }
        //System.out.println(map);
        //进行广度优
        ArrayList<ArrayList<String>>  result=new ArrayList<>();
        int flag=0;
        Map<Integer, ArrayList<ArrayList<String>>> map1=new HashMap<>();
        ArrayList<ArrayList<String>> list=new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();
        list1.add(start);
        list.add(list1);
        map1.put(1,list);
        int ceng=1;
       // System.out.println(map1);
        while(flag==0)
        {//便利上面一层啊

            ArrayList<ArrayList<String>> oldList=map1.get(ceng);
         //   System.out.println(map1.get(ceng));
            map1.put(++ceng,new ArrayList<>());
            for(List<String> listStr:oldList)
            {
                String last=listStr.get(listStr.size()-1);
                List<String> avalibleStr=map.get(last);
                for(String str:avalibleStr)
                {
                    ArrayList<String> newList=new ArrayList<>();
                   if(!listStr.contains(str))
                   {
                    //   System.out.println(str);

                       newList.addAll(listStr);
                       newList.add(str);
                       if(str.equals(end))
                       {
                           result.add(newList);
                           flag=1;
                       }
                       map1.get(ceng).add(newList);
                   }

                }

            }

        }




return result;



    }

    static int adjust(String s1,String s2)
    {
        int flag=0,i=0,key=-1;
        while(i<s1.length())
        {
            if(s1.charAt(i)!=s2.charAt(i))
            {//如果不相等

                flag++;
                key=i;
            }
               i++;


        }
        if(flag==1)
        {
            return key;
        }
        return -1;
    }

    public static void main(String[]args) throws IOException {
        Selector ss=Selector.open();
        ServerSocketChannel s= ServerSocketChannel.open();
        s.register(ss,SelectionKey.OP_ACCEPT);

        for(int i=1;i<=99;i++)
         if(i%98==(3*i)%98)
         {
             System.out.println(i);
         }
    }
    /*
    * 分割之后所有的子串都要是回文字符串啊
    * */
    public static ArrayList<ArrayList<String>> findLadders2(String start, String end, HashSet<String> dict)
    {
        //最短的转换序列啊
        //if(dict.contains(start)&&dict.contains(end))
        //采取广度优先便利的算法啊
        String s1=start;
        start=end;
        end=s1;


        if(t++>13)
        {
            return null;
        }
        dict.add(start);
        String []dicts=new String[dict.size()];

        dict.toArray(dicts);
        Map<String,List<String>>map=new HashMap<>();
        int flags=0;
        for(int i=dicts.length-1;i>=0;i--)
        {
            List<String> list=new LinkedList<>();
            for(int j=dicts.length-1;j>=0;j--)
            {
                int k=adjust(dicts[i],dicts[j]);
                if(k!=-1)
                {
                    list.add(dicts[j]);

                }

            }
            if(adjust(dicts[i],end)!=-1)
            {
                list.add(end);
            }
            map.put(dicts[i],list);
        }
        if(flags==0)
        {
            return new ArrayList();
        }
        //System.out.println(map);
        //进行广度优
        ArrayList<ArrayList<String>>  result=new ArrayList<>();
        int flag=0;
        Map<Integer, ArrayList<ArrayList<String>>> map1=new HashMap<>();
        ArrayList<ArrayList<String>> list=new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();
        list1.add(start);
        list.add(list1);
        map1.put(1,list);
        int ceng=1;
        // System.out.println(map1);
        while(flag==0)
        {//便利上面一层啊

            ArrayList<ArrayList<String>> oldList=map1.get(ceng);
            //   System.out.println(map1.get(ceng));
            map1.put(++ceng,new ArrayList<>());
            for(List<String> listStr:oldList)
            {
                String last=listStr.get(0);
                List<String> avalibleStr=map.get(last);
                for(String str:avalibleStr)
                {
                    ArrayList<String> newList=new ArrayList<>();
                    if(!listStr.contains(str))
                    {
                        //System.out.println(str);
                        newList.add(str);
                        newList.addAll(listStr);

                        if(str.equals(end))
                        {
                            result.add(newList);
                            flag=1;
                        }
                        map1.get(ceng).add(newList);
                    }

                }

            }

        }




        return result;



    }
    public static ArrayList<ArrayList<String>> findLadders1(String start, String end, HashSet<String> dict)
    {
        //最短的转换序列啊
        //if(dict.contains(start)&&dict.contains(end))
        //采取广度优先便利的算法啊

        dict.add(end);
        String []dicts=new String[dict.size()];
        dict.toArray(dicts);
        //Arrays.sort(dicts);
        Map<String,List<String>>map=new HashMap<>();
        int flags=0;
        for(int i=dicts.length-1;i>=0;i--)
        {
            List<String> list=new LinkedList<>();
            for(int j=dicts.length-1;j>=0;j--)
            {
                int k=adjust(dicts[i],dicts[j]);
                if(k!=-1)
                {
                    list.add(dicts[j]);

                }

            }
            if(adjust(dicts[i],end)!=-1)
            {
                list.add(end);
            }
            map.put(dicts[i],list);
        }
        if(flags==0)
        {
            return new ArrayList();
        }
        System.out.println(map);
        //进行广度优
        ArrayList<ArrayList<String>>  result=new ArrayList<>();
        int flag=0;
        Map<Integer, ArrayList<ArrayList<String>>> map1=new HashMap<>();
        ArrayList<ArrayList<String>> list=new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();
        list1.add(end);
        list.add(list1);
        map1.put(1,list);
        int ceng=1;
        // System.out.println(map1);
        while(flag==0)
        {//便利上面一层啊

            ArrayList<ArrayList<String>> oldList=map1.get(ceng);
               System.out.println(map1.get(ceng));
            map1.put(++ceng,new ArrayList<>());
            for(List<String> listStr:oldList)
            {
                String last=listStr.get(0);
                List<String> avalibleStr=map.get(last);
                for(String str:avalibleStr)
                {
                    ArrayList<String> newList=new ArrayList<>();
                    if(!listStr.contains(str))
                    {
                        System.out.println(str);
                        newList.add(str);
                        newList.addAll(listStr);

                        if(str.equals(start))
                        {
                            result.add(newList);
                            flag=1;
                        }
                        map1.get(ceng).add(newList);
                    }

                }

            }

        }




        return result;



    }

    public static ArrayList<ArrayList<String>> findLadders5(String start, String end, HashSet<String> dict)
    {
        //最短的转换序列啊
        //if(dict.contains(start)&&dict.contains(end))
        //采取广度优先便利的算法啊

        dict.add(start);
        String []dicts=new String[dict.size()];
        dict.toArray(dicts);

        //Arrays.sort(dicts);
        Map<String,List<String>>map=new HashMap<>();
        int flags=0;
        for(int i=dicts.length-1;i>=0;i--)
        {
            List<String> list=new LinkedList<>();
            //
            String[]strs=new String[dicts.length];
            Integer[]num=new Integer[dicts.length];
            for(int m=0;m<dicts.length;m++)
                num[m]=Integer.MAX_VALUE;
            for(int j=dicts.length-1;j>=0;j--)
            {
                int k=adjust(dicts[i],dicts[j]);
                if(k!=-1)
                {//进行插入啊
                    int n=0;
                    if(list.size()==0)
                    {
                        num[0]=k;
                        list.add(dicts[j]);
                        continue;
                    }
                    for(n=list.size()-1;n>=0;n--)
                    {
                        //找到比当前下标小的啊
                        if(num[n]<k)
                        {//找到第一个比这个数值小的数值啊
                            break;
                        }
                        else if(num[n]==k)
                        {//比这个东西小啊
                            if(list.get(n).compareTo(dicts[j])<0)
                                break;
                        }

                        num[n+1]=num[n];


                    }
                    System.out.println(dicts[i]+"  "+list+"  "+n);
                    num[n+1]=k;
                    list.add(n+1,dicts[j]);
                    System.out.println(dicts[i]+"  "+list);


                }

            }
            if(adjust(dicts[i],end)!=-1)
            {
                if(!list.contains(end))
                {list.add(end);}

                flags=1;

            }
            map.put(dicts[i],list);
        }
        System.out.println(map);
        if(flags==0)
        {
            return new ArrayList();
        }
        //进行广度优
        ArrayList<ArrayList<String>>  result=new ArrayList<>();
        int flag=0;
        Map<Integer, ArrayList<ArrayList<String>>> map1=new HashMap<>();
        ArrayList<ArrayList<String>> list=new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();
        list1.add(start);
        list.add(list1);
        map1.put(1,list);
        int ceng=1;
        // System.out.println(map1);
        while(flag==0)
        {//便利上面一层啊

            ArrayList<ArrayList<String>> oldList=map1.get(ceng);

            map1.put(++ceng,new ArrayList<>());
            for(List<String> listStr:oldList)
            {
                String last=listStr.get(listStr.size()-1);
                List<String> avalibleStr=map.get(last);
                for(String str:avalibleStr)
                {
                    ArrayList<String> newList=new ArrayList<>();
                    if(!listStr.contains(str))
                    {
                        //System.out.println(str);

                        newList.addAll(listStr);
                        newList.add(str);
                        if(str.equals(end))
                        {
                            result.add(newList);
                            flag=1;
                        }
                        map1.get(ceng).add(newList);
                    }

                }

            }

        }


        return result;



    }


    public static int longestConsecutive1(int[] num)
    {
        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        if(num.length<=1)
        {
            return num.length;
        }
        Map<Integer,Integer>map=new LinkedHashMap<>();
        for(int i=0;i<num.length;i++)
        {
            map.put(num[i],1);
        }
        int[] count=new int[max-min+1];
        int flag=0,sum=0,sumMax=0;
        //使用相当于动态规划的啊
        for(int i=0;i<num.length;i++)
        {
            sum=0;
            if(map.containsKey(num[i])&&map.get(num[i])==1)
            {
                int n=num[i];
                while(map.containsKey(n))
                {

                    sum++;
                    map.put(n,2);
                    n++;
                }
                n=num[i]-1;
                while(map.containsKey(n))
                {

                    sum++;
                    map.put(n,2);
                    n--;
                }
            }
            if(sum>sumMax)
            {
                sumMax=sum;
            }

        }
    return (sum>sumMax)?sum:sumMax;




    }


    public static int longestConsecutive(int[] num)
    {
        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
        if(num.length<=1)
        {
            return num.length;
        }
        for(int i=0;i<num.length;i++)
        {
            if(min>num[i])
            {
                min=num[i];
            }
            if(max<num[i])
            {
                max=num[i];
            }
        }
        int[] count=new int[max-min+1];
        for(int i=0;i<num.length;i++)
        {
            count[num[i]-min]++;
        }
        int flag=0,sum=0,sumMax=0;
        for(int i=0;i<count.length;i++)
        {//表示还没有连续的
            if(count[i]!=0)
            {
                if(flag==0)
                {
                    flag=1;
                }
                sum++;
            }
            //表示找到了连续的但是和下一个不连续了
            else{
                if(flag!=0)
                {
                    if(sumMax<sum)
                    {
                        sumMax=sum;
                    }

                }
                sum=0;
                flag=0;

            }




        }









        return (sum>sumMax)?sum:sumMax;




    }

    public static int sumNumbers(TreeNode root)
    {
        //使用层序便利啊
        int sum=0;
        Queue<Integer> queue1=new LinkedList();
        Queue<TreeNode> queue=new LinkedList();
        if(root==null)
        {
            return 0;
        }
        queue1.offer(root.val);
        queue.offer(root);
        while(!queue.isEmpty())
        {
            //出队列
            TreeNode head=queue.poll();
            int val=queue1.poll();
            if(head.left==null&&head.right==null)
            {
                sum+=val;
                continue;
            }
            if(head.left!=null)
            {
                queue1.add(val*10+head.left.val);
                queue.add(head.left);
            }
            if(head.right!=null)
            {
                queue1.add(val*10+head.right.val);
                queue.add(head.right);
            }
        }


return sum;



    }
    static class flag{
        int i;
        int j;
        flag(int i1,int j1)
        {
            i=i1;
            j=j1;
        }
    }
    public static void solve(char[][] board)
    {
        List<flag> flags=new LinkedList();

        int i=0,j=0,rows=board.length,cols=board[0].length;
        for(i=0;i<rows;i++)
        {

            for(j=0;j<cols;j++)
            {
                int flag1=0;
                if(board[i][j]=='O')
                {//对它的上下左右进行回溯
                    //上
                    int i1=i,j1=j;
                    while(i1>=0&&board[i1][j]=='O')
                    {//
                        i1--;
                    }
                    if(i1>=0)
                    {
                        flag1++;
                    }
                    //下
                    i1=i+1;
                    while(i1<rows&&board[i1][j]=='O')
                    {//
                        i1++;
                    }
                    if(i1<rows)
                    {
                        flag1++;
                    }
                    while(j1>=0&&board[i][j1]=='O')
                    {//
                        j1--;
                    }
                    if(j1>=0)
                    {
                        flag1++;
                    }
                    //下
                    j1=j+1;
                    while(j1<cols&&board[i][j1]=='O')
                    {//
                        j1++;
                    }
                    if(j1<cols)
                    {
                        flag1++;
                    }
                    if(flag1==4)
                    {
                        //把zhe
                       // for(flag f:flags)
                        {
                            board[i][j]='X';
                        }
                    }

                }
            }
        }
    }











    public static ArrayList<ArrayList<String>> partition(String s)
    {
        //存放0-j的所有回文分区啊
        ArrayList<ArrayList<ArrayList<String>>> arrays=new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            arrays.add(new ArrayList<ArrayList<String>>());
        }
        int i=0,len=s.length();
        boolean[][]flags=new boolean[len][len];
        int [] cuts=new int[len];
        for(i=0;i<len;i++)
            cuts[i]=-1;
        for(i=0;i<len;i++)
        {
            for(int j=i;j>=0;j--)
            {
                //当j+1-----》i-1是一个回文川子;j--->is一个回文字符串
                if(s.substring(i,i+1).equals(s.substring(j,j+1))&&(i-j<2/*说明i是大于j的啊*/||flags[i-1][j+1]))
                {
                    //说明j--->i是一个回文字符串啊;判断
                    flags[i][j]=true;
                    ArrayList<ArrayList<String>> arrayI= arrays.get(i);
                    if(j==0)//说明0-i是一个回文字符串，这样子，说明最小的分割点是0
                    {
                        ArrayList<String> str=new ArrayList<>();
                        str.add(s.substring(0,i+1));
                        arrayI.add(str);
                    }
                    else//j---i是一个回文字符串，则新的分割点有可能发生改变啊； cuts[]表示的0---》i的最小分割点啊
                    {//将0--->j-1的加上j--->i
                        //前面是一个回文字符串啊
                        ArrayList<ArrayList<String>> arrayJ1= arrays.get(j-1);
                        for(ArrayList<String> strs:arrayJ1)
                        {//这样子吧j就改变了
                            //strs.add(s.substring(j,i+1));
                            ArrayList<String> newStrs=new ArrayList<String>();
                            for(String str:strs)
                            {
                                newStrs.add(str);
                            }
                            newStrs.add(s.substring(j,i+1));
                            arrayI.add(newStrs);

                        }


                    }
                }
            }


        }

return arrays.get(len-1);

    }

    public static ArrayList<ArrayList<String>> partition1(String s)
    {
        //存放0-j的所有回文分区啊
        ArrayList<ArrayList<ArrayList<String>>> arrays=new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            arrays.add(new ArrayList<ArrayList<String>>());
        }
        int i=0,len=s.length();
        boolean[][]flags=new boolean[len][len];
        int [] cuts=new int[len];
        for(i=0;i<len;i++)
            cuts[i]=-1;

        /*
        * for(int i=s.length()-1;i>=0;i--){
            dp[i]=Integer.MAX_VALUE;
            for(int j=i;j<s.length();j++)
        *
        * */
        for( i=s.length()-1;i>=0;i--)
        {
            for(int j=i;j<s.length();j++)
            {//判断的i--->j
                //当j+1-----》i-1是一个回文川子;j--->is一个回文字符串
                if(s.charAt(i)==s.charAt(j) && (j-i<2||flags[i+1][j-1]))
                {
                    //说明j--->i是一个回文字符串啊;判断
                    flags[i][j]=true;
                    ArrayList<ArrayList<String>> arrayI= arrays.get(i);
                    if(j==s.length()-1)//说明0-i是一个回文字符串，这样子，说明最小的分割点是0
                    {
                        ArrayList<String> str=new ArrayList<>();
                        str.add(s.substring(i,len));
                        arrayI.add(str);
                    }
                    else//j---i是一个回文字符串，则新的分割点有可能发生改变啊； cuts[]表示的0---》i的最小分割点啊
                    {//将0--->j-1的加上j--->i
                        //前面是一个回文字符串啊
                        ArrayList<ArrayList<String>> arrayJ1= arrays.get(j+1);
                        for(ArrayList<String> strs:arrayJ1)
                        {//这样子吧j就改变了
                            //strs.add(s.substring(j,i+1));
                            ArrayList<String> newStrs=new ArrayList<String>();
                            newStrs.add(s.substring(i,j+1));
                            for(String str:strs)
                            {
                                newStrs.add(str);
                            }

                            arrayI.add(newStrs);

                        }


                    }
                }
            }


        }

        return arrays.get(0);

    }



    public static int minCut1(String s) {
        int []dp=new int[s.length()+1];
        boolean [][]p=new boolean[s.length()][s.length()];
        dp[s.length()]=-1;//确保dp[s.length()-1]=0
        for(int i=s.length()-1;i>=0;i--){
            dp[i]=Integer.MAX_VALUE;
            for(int j=i;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j) && (j-i<2||p[i+1][j-1])){
                    p[i][j]=true;
                    dp[i]=Math.min(dp[i],dp[j+1]+1);
                }
            }
        }
        return dp[0];
    }
    /*
    *计算的是最小分割数字啊
    * */
    public static int minCut(String s)
    {
        //贪心的方法可以解决,去判断每一个分割点啊
        /*
        * 动态规划的思想，去记录每一个可能的
        *
        *
        * */
        int i=0,len=s.length();
        boolean[][]flags=new boolean[len][len];
        int [] cuts=new int[len];
        for(i=0;i<len;i++)
            cuts[i]=-1;
        for(i=0;i<len;i++)
        {
            for(int j=i;j>=0;j--)
            {
                //当j+1-----》i-1是一个回文川子
                if(s.substring(i,i+1).equals(s.substring(j,j+1))&&(i-j<2/*说明i是大于j的啊*/||flags[i-1][j+1]))
                {
                    //说明j--->i是一个回文字符串啊;判断
                    flags[i][j]=true;
                    System.out.println("i="+i+" j="+j);
                    if(j==0)//说明0-i是一个回文字符串，这样子，说明最小的分割点是0
                    { System.out.println("j="+j);
                        cuts[i] = 0;
                    }
                    else//j---i是一个回文字符串，则新的分割点有可能发生改变啊； cuts[]表示的0---》i的最小分割点啊
                    {
                        //前面是一个回文字符串啊

                                cuts[i] =Math.min(cuts[i],cuts[j-1]+1);

                    }
                }
            }


        }

        return cuts[len-1];





    }




}
