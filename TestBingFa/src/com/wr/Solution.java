package com.wr;

/**
 *    public class ListNode {
 *        int val;
 *        ListNode next = null;
 *
 *        ListNode(int val) {
 *            this.val = val;
 *        }
 *    }
 *
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

 class ListNode {
        int val;
         ListNode next = null;

      ListNode(int val) {
             this.val = val;
         }
     }

public class Solution {
     public static void main(String[]args)

     {
        System.out.println(Solution.jis(0));
         Solution s=new s1();
         System.out.println(s.jis(0));
     }

    public static int movingCount(int threshold, int rows, int cols)
    {
        if(threshold==0||rows<=0||cols<=0)
        {
            return 0;

        }
        int i=0,j=0;
        int[][]matrix=new int[rows][cols];
        int[][]flag=new int[rows][cols];
        for(i=0;i<rows;i++)
        {
            for(j=0;j<cols;j++)
            {
                matrix[i][j]=jis(i)+jis(j);
                if(matrix[i][j]>threshold)
                {
                    flag[i][j]=1;
                }
            }

        }
        //采取广度优先便利的规则啊
        int num=0;
        List<Integer[]> list=new LinkedList();
        list.add(0,new Integer[]{0,0});
        flag[0][0]=1;//入队列时候进行访问啊
        while(list.size()>0)
        {
            //出队列并进行访问啊
            Integer[] index=list.remove(list.size()-1);

            System.out.println(index[0]+" "+index[1]);

            //往上面走
            if(index[0]-1>=0&&flag[index[0]-1][index[1]]==0)
            {
                flag[index[0]-1][index[1]]=1;
                num++;
                list.add(0,new Integer[]{index[0]-1,index[1]});
            }
            //往下面走
            if(index[0]+1<rows&&flag[index[0]+1][index[1]]==0)
            {
                flag[index[0]+1][index[1]]=1;
                num++;
                list.add(0,new Integer[]{index[0]+1,index[1]});
            }
            //往左面走
            if(index[1]-1>=0&&flag[index[0]][index[1]-1]==0)
            {
                flag[index[0]][index[1]-1]=1;
                num++;
                list.add(0,new Integer[]{index[0],index[1]-1});
            }
            //往右面走
            if(index[1]+1<cols&&flag[index[0]][index[1]+1]==0)
            {
                flag[index[0]][index[1]+1]=1;
                num++;
                list.add(0,new Integer[]{index[0],index[1]+1});
            }






        }
        return num;
    }
    public static int jis(int num)
    {
        System.out.println("s");
        return 1;
    }
}
class s1 extends Solution{
    public static  int jis(int num)
    {
        System.out.println("s1222");
        return 0;
    }

}