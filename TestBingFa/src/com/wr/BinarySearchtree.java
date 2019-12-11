package com.wr;

import java.util.*;

/**
 * Created by yanglin on 2019/3/9.   有误
 */

public class BinarySearchtree {
    public static ArrayList<String> wordBreak1(String s, Set<String> dict)
    {
        ArrayList<String> strs=new ArrayList<String>();
        int min=99999,max=0;
        Set<Integer>set=new HashSet<Integer>();
        Map<Integer,ArrayList<String>>map=new HashMap<>();
        int i=0;

        for(String str:dict)
        {
            set.add(str.length());
        }
        Integer []sets=new Integer [set.size()];
        set.toArray(sets);
        int _max=0;
        /*
        * 记录上一层计算到的下边表啊的啊
        * */
        Set<Integer>lastIndexs=new HashSet<>();
        if(map.size()<=0)
        {
            for (int j = 0; j < sets.length; j++)
            {
                ArrayList<String>lists=new ArrayList<String>();
                if(sets[j]>s.length())
                {
                    continue;
                }
                String subStr=s.substring(0,sets[j]);
                System.out.println(subStr);
                if(dict.contains(subStr))
                {
                    lists.add(subStr);
                }
                lastIndexs.add(sets[j]);
                map.put(sets[j],lists);
            }
        }
        Set<Integer>ssss=map.keySet();
        for(Integer in:ssss)
            System.out.println("in "+map.get(in));
        while(map.get(s.length())==null)
        {      //吧mapde
               Set<Integer>lastIndexs1=new HashSet<>();
              //lastIndexs上一层每一个可用的节点进行扩展
                for(Integer index:lastIndexs)
                {
                    /*
                    * 得到这一层的
                    * */
                    ArrayList<String>listIndex=map.get(index);

                    for (int j = 0; j < sets.length; j++)
                    {
                        int flag=0;
                        ArrayList<String>lists=null;
                        System.out.println(dict);
                        //当字符串超长度
                        if(index+sets[j]>s.length())
                        {
                            continue;
                        }
                        String subStr=s.substring(index,index+sets[j]);
                        if(!dict.contains(subStr))
                        {
                            continue;
                        }
                        if((lists=map.get(index+sets[j]))==null)
                        {
                            flag=1;
                            lists=new ArrayList<String>();

                        }
                        for(String ss:listIndex)
                        {
                            if(!lists.contains(ss+" "+subStr))
                            {
                                flag=1;
                                lists.add(ss+" "+subStr);
                            }
                        }
                        if(flag==1)
                        {//说明改变了
                            lastIndexs1.add(index + sets[j]);
                            map.put(index + sets[j], lists);
                        }
                    }
                }
                lastIndexs=lastIndexs1;
                if(lastIndexs.size()<=0)
                {
                    break;

                }
            }
        Set<Integer>ssss1=map.keySet();
        for(Integer in:ssss1)
            System.out.println("in "+map.get(in));
        return map.get(s.length());
    }







    public static ArrayList<String> wordBreak(String s, Set<String> dict)
    {
        ArrayList<String> strs=new ArrayList<String>();
        int min=99999,max=0;
        Set<Integer>set=new HashSet<Integer>();
        Map<Integer,ArrayList<String>>map=new HashMap<>();
        int i=0;

        for(String str:dict)
        {
            set.add(str.length());
        }
        Integer []sets=new Integer [set.size()];
        set.toArray(sets);

        return wordBreak( s,dict,sets);
    }
    public static ArrayList<String> wordBreak(String s, Set<String> dict, Integer []set)
    {
        ArrayList<String> strs=new ArrayList<String>();
        int i=0;

        while(i<set.length)
        {
            if(s.length()<set[i])
            {
                i++;
                continue;
            }

            String subStr=s.substring(0,set[i]);

            if(dict.contains(subStr))
            {
                ArrayList<String> strs1=wordBreak(s.substring(set[i],s.length()),dict,set);

                if(strs1.size()>0)
                {
                    for (String str1 : strs1)
                    {
                        strs.add(subStr + " " + str1);
                    }
                }
                else
                {
                    if(s.substring(set[i],s.length()).length()<=0)
                    {
                        strs.add(subStr);
                    }
                }

            }
            i++;

        }

        return strs;
    }




    public static ArrayList<String> wordBreak2(String s, Set<String> dict)
    {
        ArrayList<String> strs=new ArrayList<String>();
        int min=99999,max=0;
        Set<Integer>set=new HashSet<Integer>();
        Map<Integer,ArrayList<String>>map=new HashMap<>();
        int i=0;

        for(String str:dict)
        {
            set.add(str.length());
        }
        Integer []sets=new Integer [set.size()];
        set.toArray(sets);

        return wordBreak2( s,dict,sets);
    }
    public static ArrayList<String> wordBreak2(String s, Set<String> dict, Integer []set)
    {
        ArrayList<String> strs=new ArrayList<String>();
        int i=0;

        while(i<set.length)
        {
            if(s.length()<set[i])
            {
                i++;
                continue;
            }
//从后面开始划分啊
            String subStr=s.substring(s.length()-set[i],s.length());

            if(dict.contains(subStr))
            {
                ArrayList<String> strs1=wordBreak(s.substring(0,s.length()-set[i]),dict,set);

                if(strs1.size()>0)
                {
                    for (String str1 : strs1)
                    {
                        strs.add(str1 + " " +subStr );
                    }
                }
                else
                {
                    if(s.substring(set[i],s.length()).length()<=0)
                    {
                        strs.add(subStr);
                    }
                }

            }
            i++;

        }

        return strs;
    }






    public static void main(String[] args)
    {
//"cat", "cats", "and", "sand", "dog"
       // Set<String> set=new HashSet<>();set.add("cat");set.add("cats");set.add("and");set.add("dog");set.add("sand");
       // ArrayList<String> lists= wordBreak("catsanddog", set);
       Set<String> set=new HashSet<>();set.add("aaa");set.add("aaaa");//set.add("aaa");set.add("aaaa");set.add("aaaaa");
        ArrayList<String> lists= wordBreak2("aaaaaaa", set);
        System.out.println(lists);
      // lists=wordBreak1("aaaaaa", set);
     //   System.out.println(lists.size()+" "+lists);
      /*  BinarySearchtree test = new BinarySearchtree();
      //node t=  test.create();
        TreeNode root8=  new TreeNode(8);
        TreeNode node6=  new TreeNode(6);
        TreeNode node4=  new TreeNode(4);
        TreeNode node7=  new TreeNode(7);
        TreeNode node5=  new TreeNode(5);
        TreeNode node9=  new TreeNode(9);
        TreeNode node10=  new TreeNode(10);
        TreeNode node11=  new TreeNode(11);
        TreeNode node12=  new TreeNode(12);
        TreeNode node13=  new TreeNode(13);
        root8.left=node6;
        root8.right=node10;
        node6.left=node4;
        node6.right=node7;
        node4.right=node5;
        node10.left=node9;
        node10.right=node13;
        node13.left=node12;
        node12.left=node11;
      //  System.out.println(     postorderTraversal(root8));
        ListNode head=new ListNode(1);
        ListNode tail=head;
        Set<String> set=new HashSet();
        for(int i=2;i<3;i++)
        {
           ListNode node =new ListNode(i);
           tail.next=node;
            tail=node;
           System.out.println(tail.val);
        }
        tail.next=head.next;
System.out.println( tail.next.val+"    "+tail.val);

        System.out.println("----"+detectCycle(head).val);

*/


    }
    public static ArrayList<Integer> postorderTraversal(TreeNode root)
    {

        //后续便利的非递归版本啊
        Stack<TreeNode> nodes=new Stack();
        Stack<Integer>flags=new Stack();
        ArrayList<Integer>result=new ArrayList();
        if(root==null)
            return result;
                    nodes.push(root);
        flags.push(0);
        while(!nodes.isEmpty())
        {
            TreeNode head=nodes.peek();
            int flag=flags.peek();
            //第一次遇见啊；
            if(flag==0)
            {
                flags.pop();
                flags.push(flag+1);
                if(head.left!=null)
                {
                    nodes.push(head.left);
                    flags.push(0);

                }

            }
            else if(flag==1)
            {
                flags.pop();
                flags.push(flag+1);
                if(head.right!=null)
                {
                    nodes.push(head.right);
                    flags.push(0);

                }
            }
            else
            {
                flags.pop();
                int val=nodes.pop().val;
                result.add(val);
                System.out.println(val);
            }




        }






        return result;

    }
    public static ListNode detectCycle(ListNode head)
    {//快慢指针
        if(head==null)
            return null;
        ListNode p1=head,p2=head.next;
        while(p1!=p2&&p1!=null&&p2!=null)
        {
            p1=p1.next;
            if(p2.next!=null)
            {
                p2=p2.next.next;
            }
            else
            {
                return null;
            }
        }
        if(p1==null||p2==null)
            return null;
        else//再重新便利到
        {
            ListNode n1=p1.next,n2=p1.next;
            List<ListNode> list1=new ArrayList();
            List<ListNode>list2=new ArrayList();
            System.out.println(p1.val+"====="+p2.val);
            while(n1!=p1)
            {
                System.out.println("list1"+" "+n1.val);
                list1.add(n1);
                n1=n1.next;

            }
            System.out.println("list1"+" "+p1.val);
            list1.add(p1);
            while(head!=p1)
            {System.out.println("list2"+" "+head.val);
                list2.add(head);
                head=head.next;

            }
            System.out.println("list2"+" "+p1.val);
            list2.add(p1);
            int i=list1.size()-1,j=list2.size()-1;
            System.out.println("ddd "+list1.get(i).val+"   "+list2.get(j).val);
            while(i>=0&&j>=0&&list1.get(i)==list2.get(j))
            {
                System.out.println("ddd "+list1.get(i));
                i--;j--;
            }
            if(i>=0&&j>=0)
            return list1.get(i+1);
            else
            {
                return list1.get(0);
            }
        }

    }



    public static void reorderList(ListNode head)
    {
        //用一个
        ArrayList<ListNode>list=new ArrayList();
        if(head==null||head.next==null)
            return;

        while(head!=null)
        {
            list.add(head);
            head=head.next;
        }
        System.out.println(list);
        int i=0;
        int len=list.size();
        ListNode tail=null;
        while(i<len/2)
        {
            if(tail==null)
            {
                tail=list.get(i);
                tail.next=(list.get(len-i-1));
                tail=tail.next;
            }
            else
            {
                tail.next=(list.get(i));
                tail.next.next=(list.get(len-i-1));
                tail=tail.next.next;
            }
            i++;
        }
        System.out.println(len+"  "+list.get(len/2).val);
        if(len%2!=0)
        { System.out.println(list.get(len/2).val);
            tail.next=(list.get(len/2));
            tail=tail.next;

        }
        tail.next=null;
    }
}
