package RL.TraditionalRL;

import java.util.*;

public class cishu
{

    public static void main(String[]args)
    {
        Scanner scan=new Scanner(System.in);
        String longStr=scan.nextLine();
        String shortStr=scan.nextLine();
        Map<Character,List<Integer>>map=new HashMap<>();
        for(int i=0;i<shortStr.length();i++)
        {
            map.put(shortStr.charAt(i),new LinkedList<>());
        }
        int num=0;
        int realMax=-1,realMin=-1;
        for(int i=0;i<longStr.length();i++)
        {
            List<Integer> c=map.get(longStr.charAt(i));
            if(c==null)
            {
                continue;
            }
            if(c.size()==0)
            {//判断啊
                    c.add(i);
                    num++;
            }
            else//判断是否更新啊
            {
                    //判断newi-min和之前的进行一个判断啊
                    if(num<map.size())
                    {
                        c.add(i);

                    }
                    else
                    {//每一次当前的是第一个
                        int min=-1, max=-1,_min=-1, _max=-1;
                        c.add(i);
                        Set<Character> set=map.keySet();
                        Character[] chars=new Character[set.size()];
                        set.toArray(chars);
                        for(int j=0;j<set.size();j++)
                        {
                            if(min==-1&&max==-1)
                            {
                                min=max=map.get(chars[j]).get(0);
                                _min=_max=map.get(chars[j]).get(map.get(chars[j]).size()-1);
                                continue;
                            }

                            if(min>map.get(chars[j]).get(0))
                            {
                                min=map.get(chars[j]).get(0);

                            }
                            if(max<map.get(chars[j]).get(0))
                            {
                                max=map.get(chars[j]).get(0);

                            }
                            if(_min>map.get(chars[j]).get(map.get(chars[j]).size()-1))
                            {
                                _min=map.get(chars[j]).get(map.get(chars[j]).size()-1);

                            }
                            if(_max<map.get(chars[j]).get(map.get(chars[j]).size()-1))
                            {
                                _max=map.get(chars[j]).get(map.get(chars[j]).size()-1);

                            }
                        }
                        realMax=max;realMin=min;
                        if(max-min>=_max-_min)
                        {//只留下最后
                            realMax=_max;realMin=_min;
                            for(int j=0;j<set.size();j++)
                            {
                                List<Integer>oldlist= map.get(chars[j]);
                                List<Integer>list=new LinkedList<>();
                                list.add(oldlist.get(oldlist.size()-1));
                                map.put(chars[j],list);
                            }

                        }



                    }


            }

        }
System.out.println(realMax+"  "+realMin);
        }









    public static void main1(String[]args)
    {
        Scanner scan=new Scanner(System.in);
        Map<Integer,List<Integer[]>> row=new HashMap<>();
        Map<Integer,List<Integer[]>> col=new HashMap<>();
        int n=Integer.valueOf(scan.nextLine());
      //  System.out.println(n);
        for(int i=0;i<n;i++)
        {

            String str=scan.nextLine();

            String[]strs=str.split(" ");
            int x1=Integer.valueOf(strs[0]);
            int y1=Integer.valueOf(strs[1]);
            int x2=Integer.valueOf(strs[2]);
            int y2=Integer.valueOf(strs[3]);
            if(y1>y2)
            {
                int k=y1;
                y1=y2;
                y2=k;
            }
            if(x1>x2)
            {
                int k=y1;
                x1=x2;
                x2=k;
            }
       //     System.out.println(str);
            if(x1==x2)
            {
                insert( row, x1, y1,y2);
            }
            else
            {
                insert( col, y1, x1,x2);
            }
         //   System.out.println(str);
        }

        print(row); print(col);
        int num=get(col)+get(row);
        print(row); print(col);
      //  System.out.println(col);System.out.println(row);
        Set<Integer>setRow=row.keySet();
        for(Integer i:setRow)
        {
            //去掉重复的啊;对于row中的每一个来进行在col里面找重复的啊
            List<Integer[]>list=row.get(i);
            //但对于list中的每一个，看 重复的啊
            for(int is=0;is<list.size();is++)
            {
                Integer[] ss=list.get(is);
                for(int j1=ss[0];j1<=ss[1];j1++)
                {
                    List <Integer[]> colList=col.get(j1);
                    if(colList!=null)
                    {
                        for(int j2=0;j2<colList.size();j2++)
                        {
                           // System.out.println(colList.get(j2).length);
                            if(i>=colList.get(j2)[0]&&i<=colList.get(j2)[1])
                            {
                                num--;
                            }



                        }



                    }

                }


            }


        }
      //  System.out.println("--------------");
      //  print(row); print(col);

        System.out.println(num);
      /*
      * 3
     1 2 3 2
2 5 2 3
1 4 3 4
      * */



    }
    public static void print(Map<Integer,List<Integer[]>> row)
    {
        Set<Integer>set=row.keySet();
        for(Integer i:set)
        {
            List<Integer[]>list=row.get(i);
            System.out.print("i="+i);
            for(int i1=0;i1<list.size();i1++)
            {
                System.out.print("("+list.get(i1)[0]+","+list.get(i1)[1]+")");
            }
            System.out.println();
        }

    }

    public static void  insert(Map<Integer,List<Integer[]>> row,int x1,int y1,int y2)
    {
        List<Integer[]> num=row.get(x1);
        if(num==null)
        {
            num=new ArrayList<>();
            num.add(new Integer[]{y1,y2});
            row.put(x1,num );
        }
        else
        {

             /*
            *
            * 7
1 2 3 2
2 5 3 5
1 4 3 4
2 3 2 5
2 6 2 8
2 1 2 4
2 7 2 10
            * */
             int index=-1;
            for(int j=0;j<num.size();j++)
            {
                if(num.get(j)[0]>y1)
                {
                    index=j;

                    break;
                }
            }
            if(index!=-1)
            num.add(index,new Integer[]{y1, y2});
            else
            {
                num.add(new Integer[]{y1, y2});
            }
        }
    }
/*
* 将行的进行一个排序啊
*
* */
    public static int get(Map<Integer,List<Integer[]>> row)
    {
        Set<Integer>set=row.keySet();
        int num=0;
        for(Integer i:set)
        {
            //去掉重复的啊
            List<Integer[]>list=row.get(i);
            List<Integer[]>newList=new LinkedList<>();
            Integer[] last=list.get(0);
            newList.add(last);
            num+=last[1]-last[0]+1;
            for(int is=1;is<list.size();is++)
            {
                Integer[]now=list.get(is);
                if(last[1]<now[0])
                {
                    num+=now[1]-now[0]+1;
                    newList.add(now);
                    last=now;
                }
                else
                {
                    num+=(now[1] -last[1]>0)?now[1] -last[1]:0;
                    last[1]=last[1]>now[1]?last[1]:now[1];
                }
            }
            row.put(i,newList);

        }
       return num;
    }
}
