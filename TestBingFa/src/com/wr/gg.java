package com.wr;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class gg {

    public static int[] qsort(int a[]){
        int i=0;
        int j = a.length-1;
            sort(a,i,j);
        return a;
    }
    public  static int sort(int a[],int l,int r){

        int key = a[l];
        int i=l;
        int j=r;
        if(l>=r)
        {
           return 0;
        }

        while(i<j)
        {

            while (i<j&& a[j] > key) j--;

            if(i<j)
              a[i] = a[j];
            while (i<j&& a[i] <= key) i++;
            if(i<j)
                a[j] = a[i];
        }
        a[i]=key;
        sort(a,l,i-1);
        sort(a,i+1,r);
        return i;
    }
    public static void main(String[] args){
        int [] arr=new int[]{2,4,6,3,2,0,9,4,2};

        qsort(arr);
        for(int i=0;i<arr.length;i++)
        System.out.println(arr[i]);
    }
}



class MyException extends Exception{
    public MyException(String m){super(m);}
    List<String> list=new LinkedList<>();

    List<String> l= Collections.synchronizedList(list);
}
 class Mainq {
    public void test1() throws RuntimeException{
        String[] sexs = {"男性","女性","中性"};
        for(int i = 0; i < sexs.length; i++){
            if("中性".equals(sexs[i])){
                try {
                    throw new MyException("不存在中性的人！");
                } catch (MyException e) {
                    // TODO Auto-generated catch block
                  //  e.printStackTrace();
                    RuntimeException rte=new RuntimeException(e);//包装成RuntimeException异常
                    rte.initCause(e);
                    throw rte;//抛出包装后的新的异常
                }
            }else{
                System.out.println(sexs[i]);
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int [] arr=new int[]{2,4,6,3,2,0,9,4,2};

        gg.qsort(arr);
        System.out.println(arr);

    }

}

class node{
    node left,right;
    int val;
    node(int data){
        this.val = data;
    }
    node(){}
}
 class TestTree {

    public node create( ){
        node root = new node();
        Scanner s = new Scanner(System.in);
        String str="";
        str = s.nextLine();
        System.out.println(str);
        String []strs=str.split(" ");
        System.out.println(strs.length);
        if(strs[0].equals("#"))
        {
            return null;
        }
        int i=0;
        node[] nodes=new node[strs.length];
        while(i<strs.length)
        {
         if(!strs[i].equals("#"))
         {

             nodes[i]=new node();
         }
         i++;
        }
       i=0;
        while(i<strs.length/2)
        {
            if(nodes[i]==null)
            {
                continue;
            }
            if(2 * i + 1<strs.length) {
                nodes[i].left = nodes[2 * i + 1];
            }
            if(2 * i + 2<strs.length)
            {
                nodes[i].left = nodes[2 * i + 2];
            }
            i++;
        }

        return root;
    }


    public void print(node t){
        if(t==null)
            System.out.println("#");
        else{
            System.out.println(t.val);
            print(t.left);
            print(t.right);
        }

    }
}

