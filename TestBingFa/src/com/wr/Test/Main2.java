package com.wr.Test;

import java.util.*;
class data{
    int i,j;
    data(int i1,int j1)
    {
        i=i1;j=j1;
    }
}
public class Main2 {
    public static void main(String []args) {
        /*
        * m = int(input())
s=[]
for i in range(m):
    s.append(input())
t = input()
end_time=[]
ll=[]
for i in range(len(t)):
    for j in range(i+1,len(t)+1):
        small_s = t[i:j]
        if small_s in s:
            end_time.append((i,j))
            ll.append(small_s)

s_end_time = sorted(end_time,key=lambda x:x[1])#按结束位置递增排序

end_t = -1
num = 0
for i in s_end_time:
    start_t = i[0]
    if start_t>=end_t:
        num+=1
        end_t = i[1]

print(num)
        *
        * */
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        String s="";
        List<String> ll=new LinkedList<>();
        List<data> end_time=new LinkedList<>();
        List<Integer> end_time2=new LinkedList<>();
        for(int i=0;i<m;i++)
        {
            s+=scan.nextLine();
        }
        String t=scan.nextLine();
        /*
        * for i in range(len(t)):
    for j in range(i+1,len(t)+1):
        small_s = t[i:j]
        if small_s in s:
            end_time.append((i,j))
            ll.append(small_s)
        * */
        for(int i=0;i<t.length();i++)
        {
            for(int j=i+1;j<t.length()+1;j++)
            {
                String small_s = t.substring(i,j);//[i:j]
                if (s.contains(small_s))
                {
                    ll.add(small_s);
                    end_time.add(new data(i,j));
                    end_time2.add(j);


                }
            }

        }
      Collections.sort(end_time, new Comparator<data>() {
          @Override
          public int compare(data o1, data o2) {

              return o1.j>o2.j?1:-1;
          }
      });
        int end_t = -1;
        int num = 0;
        for (data d:end_time)
        {
           int  start_t = d.i;
            if(start_t >= end_t)
            {
                num += 1;
                end_t =d.j;
            }
        }
        System.out.println(num);
    }
}

