package com.wr;

import java.util.ArrayList;

public class testMain
{
    public static  ArrayList<String> fullJustify(String[] words, int L)
    {
        int i=0,sum=0,k=0;
        ArrayList<ArrayList<Integer>> record=new ArrayList<>();
        ArrayList<Integer> list=new ArrayList<>();
        list.add(0);
        record.add(list);
        for(i=0;i<words.length;i++)
        {//第一个放的是总共的长度啊
            if(sum+words[i].length()+record.get(k).size()-1<=L)
            {
                ArrayList<Integer> list1=record.get(k);
                list1.add(i);
                list1.set(0,list1.get(0)+words[i].length());
                System.out.print(words[i]+" ");
                sum+=words[i].length();
                //ArrayList<Integer> list=new ArrayList<>();
            }
            else
            {//新开辟一个啊
                sum=words[i].length();
                 System.out.println();
                System.out.print(words[i]+" ");
                list=new ArrayList<>();
                list.add(0);
                record.add(list);
                k++;
                list.add(i);
                list.set(0,list.get(0)+words[i].length());
            }
        }

        ArrayList<String> result=new ArrayList<>();
        i=0;//记录多少行的啊
        for(ArrayList<Integer> list1:record)
        {
            k=0;
            String str="";
            int blankNum=0,Num=0;

            if(list1.size()==2)
            {
                str+=words[list1.get(1)];

                for(int n=0;n<L-words[list1.get(1)].length();n++)
                {
                    str+=" ";
                }
                result.add(str);
                continue;
            }
            if(i==record.size()-1)
            {
                for(Integer j:list1)
                {
                    str+=words[j]+" ";
                }
                for(int n=0;n<L-str.length();n++)
                {
                    str+=" ";
                }
                result.add(str);
                continue;

            }
            for(Integer j:list1)
            {

                if(k==0)
                {//计算空格
                    blankNum = L / (list1.size() - 2);
                    Num = L % (list1.size() - 2);
                    k++;
                    continue;
                }
                //当是最后一个字符串的时候
                str+=words[j];
                if(k==list1.size()-1)
                {
                    result.add(str);
                    continue;
                }

                int bNum=0;
                if(k>Num)
                {
                    bNum=blankNum+1;
                }
                else
                {
                    bNum=blankNum;
                }
                for(int n=0;n<bNum;n++)
                {
                    str+=" ";
                }
                k++;

            }
            i++;
            //result.add(str);
        }
        return result;
    }
    public static void main(String[]args)
    {
        //String[] words=new String[]{"eee","rrrrrrr","hjhjk","fhjshfj","this","is","ddddd"};
        String[] words=new String[]{"What","must","be","shall","be."};
        System.out.println("\n"+fullJustify(words, 12));

    }



}
