package com.wr;

import java.util.Stack;

public class Main
{
    public static String simplifyPath(String path)
    {
        String []paths=path.split("/");
        Stack<String>stack=new Stack();
        for(int i=0;i<paths.length;i++)
        {
            System.out.print(i+":"+paths[i]+"===");
            if(paths[i].equals("."))
            {

            }
            else if(paths[i].equals(".."))
            {
                if(!stack.isEmpty())
                {
                    stack.pop();
                }
            }
            else if(paths[i].equals(""))
            {

            }
            else
            {
                if(paths[i].contains(":"))
                {

                }
                else
                {
                    stack.push(paths[i]);
                }

            }


        }
        System.out.println();
        String str="";
        if(stack.isEmpty())
        {
            return "/";
        }
        while(!stack.isEmpty())
        {
            str="/"+stack.pop()+str;


        }
        return str;
    }


    public static void main(String[] args)
    {
        //int[] data=new int[]{2,-1,888888,9,2,9,2,-1,9,-1};
        System.out.println(simplifyPath("/abc/..."));
    }
    static int find4(int[] data)
    {
        int i=0,one=0,two=0,three=0,four=0;
        for(i=0;i<data.length;i++)
        {
            three|=two&one&data[i];//对于最高位进位的功能
            two|=one&data[i];
            one=data[i]^one;
            four=~(three&~two&~one);
            one&=four;
            two&=four;
            three&=four;
        }
        return one;
    }

    static int find3(int[] data)
    {
        int i=0,one=0,two=0,three=0;
        for(i=0;i<data.length;i++)
        {
           // three|=two&one&data[i];//对于最高位进位的功能
            two|=one&data[i];
            one=data[i]^one;
            three=~(two&one);
            one&=three;
            two&=three;




        }
        return one;
    }
}

