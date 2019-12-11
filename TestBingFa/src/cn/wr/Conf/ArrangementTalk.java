package cn.wr.Conf;

import org.junit.Test;

import java.util.*;

public interface ArrangementTalk
{
    public Map<Integer, Map<String, Integer>> getArrange(List<Integer> minAlltime,List<Integer> maxAlltime,Map<Integer, List<String>> map, LimitConstranit limit);
    public Map<Integer, List<List<Integer>>> getArrange(Integer minTime,Integer maxTime, List<Integer>orgins, LimitConstranit limit);



}
class realArrangement implements ArrangementTalk
{

    public Map<Integer, Map<String, Integer>> getArrange(List<Integer> minAlltime,List<Integer> maxAlltime,Map<Integer, List<String>> map/*有好几种活动*/)
    {
      //List<Map<Integer, Integer>> lm=new realArrangement().getArrange1(min,max, orgins/*从大到小排序啊*/, null,nums);
        List<Integer>orgins=new LinkedList<>();
        Set<Integer> set=map.keySet();
        Map<Integer, Integer> map1=new HashMap<>();
        for(Integer i:set)
        {
            //i以及i的活动的个数
            orgins.add(i);
            map1.put(i,map.get(i).size());
        }
        Collections.sort(orgins, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2)
                {
                    return -1;
                }
                else
                {
                    return  1;
                }
            }
        });
        List<Integer> nums=new LinkedList<>();
        System.out.println(orgins);
        for(Integer k:orgins)
        {//i以及i的活动的个数
            nums.add(map1.get(k));
        }
        System.out.println(nums);
        Queue<Map<List<Integer>,List<Map<Integer, List<Integer>>>> >queue1=new LinkedList<>();
        Queue<Map<List<Integer>,List<Map<Integer, List<Integer>>>>>queue2=new LinkedList<>();
        for(int i=0;i<minAlltime.size();i++)
        {
            int min=minAlltime.get(i);  int max=maxAlltime.get(i);
            List<Map<Integer, Integer>> lm=new realArrangement().getArrange1(min,max, orgins/*从大到小排序啊*/, null,nums);
            Map<List<Integer>,List<Map<Integer, List<Integer>>>> map2=new HashMap<>();
            List<Integer>list=new ArrayList<>();
            List<Map<Integer, List<Integer>>> realLm=new LinkedList<>();
            list.add(i);
            for( Map<Integer, Integer> mm:lm)
            {
                Map<Integer, List<Integer>> realMap=new HashMap<>();
                for (int j = 0; j < orgins.size(); j++)
                {
                    int orgin = orgins.get(j);
                    int num = 0;
                    if(mm.get(orgin)==null)
                    {
                        mm.put(orgin,0);

                    }
                    num=mm.get(orgin);

                    List<Integer> ll=new LinkedList<>();
                    ll.add(num);
                    realMap.put(orgin,ll);
                }
                realLm.add(realMap);
            }
            map2.put(list,realLm);
            queue1.offer(map2);
            System.out.println(map2);
        }
        //进行合并啊
        while(queue1.size()!=2)
        {
            System.out.println(queue1.size());
            while (queue1.size() >= 2)
            {
                Map<List<Integer>, List<Map<Integer, List<Integer>>>> obj1 = queue1.poll();
                Map<List<Integer>, List<Map<Integer, List<Integer>>>> obj2 = queue1.poll();

                queue2.offer(join(false,obj1, obj2, orgins, nums));//不需要相等
            }
            if(!queue1.isEmpty())
            {
                queue2.offer(queue1.poll());
            }
            queue1.addAll(queue2);
            queue2.clear();

        }
        Map<List<Integer>, List<Map<Integer, List<Integer>>>> obj1 = queue1.poll();
        Map<List<Integer>, List<Map<Integer, List<Integer>>>> obj2 = queue1.poll();
        System.out.println(obj1);System.out.println(obj2);
        Map<List<Integer>,List<Map<Integer, List<Integer>>>> result=(join(true,obj1, obj2, orgins, nums));//不需要相等
        System.out.println(result);
        Set<List<Integer>>set1=result.keySet();
        //找出第一n组
        List<Map<Integer, List<Integer>>> r1=null;
        List<Integer> ll=null;
        for(List<Integer> l2:set1)
        {
            ll=l2;
            r1=result.get(l2);
        }
        int index=(int)(Math.random()*r1.size());
        Map<Integer, List<Integer>> r2=r1.get(index);
        //第一田
        for(Integer k:ll)
        {//{60=[3, 2, 0, 0], 45=[0, 1, 5, 0], 30=[0, 0, 0, 7], 5=[0, 0, 0, 1]}
            System.out.println(minAlltime.get(k)+" "+maxAlltime.get(k));
            for(int i=0;i<orgins.size();i++)
            {
                int orgin=orgins.get(i);
                int num=r2.get(orgin).get(k);
               // Map<Integer, List<String>> map
                List<String> talks=map.get(orgin);
                while(num>0)
                {
                    num--;
                    System.out.println(talks.get(0));
                    talks.remove(talks.get(0));

                }

            }


        }





       return null;

    }
    Map<List<Integer>,List<Map<Integer, List<Integer>>>> join(boolean b,Map<List<Integer>,List<Map<Integer, List<Integer>>>>  obj1,Map<List<Integer>,List<Map<Integer, List<Integer>>>>  obj2,List<Integer>orgins,List<Integer>sums)
    {
        //进行便利啊

        Set<List<Integer>>set1=obj1.keySet();
        Set<List<Integer>>set2=obj2.keySet();
        Map<List<Integer>,List<Map<Integer, List<Integer>>>> map=new HashMap<>();
        //进行合并啊
        List<Integer> list1=null;
        for(List<Integer> l:set1)
        {
            list1=l;
        }
        List<Integer> list2=null;
        for(List<Integer> l:set2)
        {
            list2=l;
        }
        List<Integer>list=new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        List<Map<Integer, List<Integer>>> numList=new LinkedList<>();
        map.put(list,numList);
        List<Map<Integer, List<Integer>>> lm1=obj1.get(list1);
        List<Map<Integer, List<Integer>>> lm2=obj2.get(list2);
        for(Map<Integer, List<Integer>> o1:lm1)
        {
            for(Map<Integer, List<Integer>> o2:lm2)
            {
                int i = 0;
                //List<Map<Integer, List<Integer>>>
                Map<Integer, List<Integer>> m1=new LinkedHashMap<>();
                for ( i = 0; i < orgins.size(); i++)
                {//看lm1 和lm2能不能合并啊
                    List<Integer> ll=new LinkedList<>();
                    int orgin=orgins.get(i);
                    int num=sums.get(i);
                    List<Integer> l1=o1.get(orgin);
                    List<Integer> l2=o2.get(orgin);
                    ll.addAll(l1);ll.addAll(l2);
                    int num1=0;
                    for(Integer i1:l1)
                    {
                        num1+=i1;
                    }
                    int num2=0;
                    for(Integer i1:l2)
                    {
                        num2+=i1;
                    }

                    if(b==false)
                    {
                        if(num1+num2>num)
                        {
                            //System.out.println("num1+mum2="+num1+";"+num2+";"+num);
                            break;
                        }
                    }
                    else
                    {
                        if(num1+num2!=num)
                        {
                            //System.out.println("num1+mum2="+num1+";"+num2+";"+num);
                            break;
                        }

                    }

                    m1.put(orgin,ll);
                }
                    if (i>=orgins.size())
                    {//说明可以合并啊
                        numList.add(m1);
                    }


            }
        }
        return  map;

    }



    @Override
    public Map<Integer, Map<String, Integer>> getArrange(List<Integer> minAlltime,List<Integer> maxAlltime,Map<Integer, List<String>> map/*有好几种活动*/, LimitConstranit limit)
    {
        Map<Integer, Map<String, Integer>> Talks=new HashMap<>();
        //统计一下有
        Set<Integer> set=map.keySet();
        List<Integer>orgins=new LinkedList<>();
        Map<Integer, Integer> map1=new HashMap<>();
        for(Integer i:set)
        {
            //i以及i的活动的个数
            orgins.add(i);
            map1.put(i,map.get(i).size());
        }

        Collections.sort(orgins, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2)
                {
                    return -1;
                }
                else
                {
                    return  1;
                }
            }
        });
        //Map<Integer, List<List<Integer>>> map1=getArrange(minAlltime.get(0),maxAlltime.get(0), orgins/*从大到小排序啊*/, null);
       // System.out.println(map1);
        //先凑最大的啊；再来看小的满足不
        //假设max是从大到小；先吧大活动用完
        Stack<Map<Integer,Integer>>stack=new Stack<>();
        //存储的是可以用的map 啊
        Stack<Map<Integer,Integer>>sums=new Stack<>();
        //存储的是了以及分割的 啊
        Stack<List<Map<Integer, Integer>>>selects=new Stack<>();
        //Stack< List<Map<Integer, Integer>>>lms=new Stack<>();
        //选择合适的动作啊;从0开始啊
        int i=maxAlltime.size()-1;
        while(true)
        {
            //用站实现啊
            int min=minAlltime.get(i);
            int max=maxAlltime.get(i);
            //
            //选择合适的啊
            List<Integer> nums=new LinkedList<>();
            //set=map1.keySet();
           // List<Integer>orgins=new LinkedList<>();
          //  Map<Integer, Integer> map1=new HashMap<>();
            for(Integer k:orgins)
            {
                //i以及i的活动的个数
                nums.add(map1.get(k));
            }
            //获取到当前的啊；选择一个lm啊
            List<Map<Integer, Integer>> lm=new realArrangement().getArrange1(min,max, orgins/*从大到小排序啊*/, null,nums);
            int j=0;

            Map<Integer,Integer> cur=new LinkedHashMap<>();
            int sum=0;
            int flag=0;
            Map<Integer, Integer>  select=null;
            if(stack.size()==minAlltime.size()-1)
            {
                for(Integer k:orgins)
                {
                    //i以及i的活动的个数
                    sum+=k*map1.get(k);
                   // map1.put(k,map1.get(k)-curnow.get(k));
                }
                if(sum>=minAlltime.get(0)&&sum<=maxAlltime.get(0))
                {
                    break;


                }
                else
                {
                    flag=2;
                }

            }
            else
            {
                while(j<lm.size())
                {
                    //看是否可以选择啊
                    flag=0;
                    select=lm.get(j);
                    for(Integer k:orgins)
                    {
                        //i以及i的活动的个数
                        if(map1.get(k)>=select.get(k))
                        {
                            continue;
                        }
                        else
                        {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0)
                    {
                        break;
                    }
                    j++;
                }
            }
            //从j开始选择啊;j肯定可以选择啊
            if(j<lm.size()&&flag!=2)//没有满足条件的，进行会说处理
            {
                stack.push(select);
                Map<Integer,Integer> mm=new HashMap<>();
                mm.putAll(map1);
                selects.push(lm);//lm是可以选择的啊
                sums.push(mm);
                for(Integer k:orgins)
                {
                    //i以及i的活动的个数
                    map1.put(k,map1.get(k)-select.get(k));
                }
                i--;
            }
            else
            {
                //说明这次剩下的不行啊;在上一个里面选择啊
                Map<Integer,Integer> curnow=null;
                Map<Integer,Integer> sumnow=null;
                List<Map<Integer, Integer>> lmnow=null;
                while(!stack.isEmpty())//选择另一个满足条件的进行啊
                {
                     curnow=stack.pop();
                     sumnow=sums.peek();
                    lmnow=selects.peek();
                    j=lmnow.indexOf(curnow);
                    if(j<lmnow.size()-1)
                    {
                        j=lmnow.size()+1;
                        stack.push(lmnow.get(j));
                        map1.clear();
                        map1.putAll(sumnow);
                        for(Integer k:orgins)
                        {
                            //i以及i的活动的个数
                            map1.put(k,map1.get(k)-lmnow.get(j).get(k));
                        }
                        break;
                    }
                    else//这一层已经尝试完了；开始尝试下一层
                    {
                        sums.pop();
                        selects.pop();
                        i++;
                        sumnow=sums.peek();
                        curnow=stack.peek();
                        map1.clear();
                        map1.putAll(sumnow);
                        for(Integer k:orgins)
                        {
                            //i以及i的活动的个数
                            map1.put(k,map1.get(k)-curnow.get(k));
                        }
                    }
                }
            }
        }

return null;

        //先看组成方式啊
       // Map<Integer, List<Integer>>

    }

    public  class value{
        List<Integer> list=new LinkedList<>();
        int index;
        int sum;
        Map<Integer,Integer> cur=new HashMap<>();
        public value(int index,int sum) {
            this.index = index;this.sum=sum;
        }
    }

    public Map<Integer,  List<Map<Integer,Integer>>> getArrange(Integer minTime,Integer maxTime, List<Integer> orgins/*从大到小排序啊*/, LimitConstranit limit,List<Integer>nums)
    {
        //看怎么组成啊;用动态规划啊
        //用BFS
        Queue<value> queue=new LinkedList<>();

        Map<Integer,  List<Map<Integer,Integer>>> map=new LinkedHashMap<>();
        for(int i=0;i<orgins.size();i++)
        {
            value v=new value(i,orgins.get(i));
            v.cur.put(orgins.get(i),1);
            v.sum=orgins.get(i);
            queue.offer(v);
        }
        System.out.println("1111");

        while(!queue.isEmpty())
        {
            //出队列
            value v=queue.poll();
            System.out.println(v.sum);
            int startIndex=v.index;
            if(v.sum>=minTime&&v.sum<=maxTime)
            {//存储起来啊;如果满足条件就存储起来啊
                List<Map<Integer,Integer>> ll=map.get(v.sum);
                if(ll==null)
                {
                     ll=new LinkedList<>();
                     map.put(v.sum,ll);
                }
                ll.add(v.cur);
            }
            //从startIndex开始从大到小
            for(int i=startIndex;i<orgins.size();i++)
            {
                int  num=orgins.get(i);
                if(v.sum+num>maxTime)
                {
                    continue;
                }
                //说明不能加了啊

                if(v.cur.get(num)==null)
                {
                    v.cur.put(num,0);
                }

                if (v.cur.get(num) + 1 > nums.get(i))
                {
                    continue;
                }
                //i是last value的下标
                value v1=new value(i,v.sum+num);
                v1.cur.putAll(v.cur);
                v1.cur.put(num,v.cur.get(num)+1);
                queue.offer(v1);
            }
        }




        return map;
    }

    public   List<Map<Integer,Integer>> getArrange1(Integer minTime,Integer maxTime, List<Integer> orgins/*从大到小排序啊*/, LimitConstranit limit,List<Integer>nums)
    {
        //看怎么组成啊;用动态规划啊
        //用BFS
        Queue<value> queue=new LinkedList<>();
        List<Map<Integer,Integer>> list=new LinkedList<>();
        Map<Integer,  List<Map<Integer,Integer>>> map=new LinkedHashMap<>();
        for(int i=0;i<orgins.size();i++)
        {
            value v=new value(i,orgins.get(i));
            v.cur.put(orgins.get(i),1);
            v.sum=orgins.get(i);
            queue.offer(v);
        }
       // System.out.println("1111");

        while(!queue.isEmpty())
        {
            //出队列
            value v=queue.poll();
            //System.out.println(v.sum);
            int startIndex=v.index;
            if(v.sum>=minTime&&v.sum<=maxTime)
            {//存储起来啊;如果满足条件就存储起来啊
                List<Map<Integer,Integer>> ll=map.get(v.sum);
                if(ll==null)
                {
                    ll=new LinkedList<>();
                    map.put(v.sum,ll);
                }
                list.add(v.cur);
                ll.add(v.cur);
            }
            //从startIndex开始从大到小
            for(int i=startIndex;i<orgins.size();i++)
            {
                int  num=orgins.get(i);
                if(v.sum+num>maxTime)
                {
                    continue;
                }
                //说明不能加了啊

                if(v.cur.get(num)==null)
                {
                    v.cur.put(num,0);
                }

                if (v.cur.get(num) + 1 > nums.get(i))
                {
                    continue;
                }
                //i是last value的下标
                value v1=new value(i,v.sum+num);
                v1.cur.putAll(v.cur);
                v1.cur.put(num,v.cur.get(num)+1);
                queue.offer(v1);
            }
        }




        return list;
    }

    public Map<Integer, List<List<Integer>>> getArrange(Integer minTime,Integer maxTime, List<Integer> orgins/*从大到小排序啊*/, LimitConstranit limit)
    {
        //看怎么组成啊;用动态规划啊
        //用BFS
        Queue<value> queue=new LinkedList<>();

        Map<Integer, List<List<Integer>>> map=new LinkedHashMap<>();

        for(int i=0;i<orgins.size();i++)
        {
            value v=new value(i,orgins.get(i));
            v.list.add(orgins.get(i));
            v.cur.put(orgins.get(i),1);//将这个value  put进去啊
            queue.offer(v);
        }
        System.out.println("1111");

        while(!queue.isEmpty())
        {
            //出队列
            value v=queue.poll();

            System.out.println(v.sum);
            int startIndex=v.index;
            //如果这个的sum在合理的范围内；存储起来啊；就是在合理的范围内进行存储啊！
            if(v.sum>=minTime&&v.sum<=maxTime)
            {//存储起来啊
                List<List<Integer>> ll=map.get(v.sum);
                if(ll==null)
                {
                    ll=new LinkedList<>();
                    map.put(v.sum,ll);
                }
                ll.add(v.list);
            }
            for(int i=startIndex;i<orgins.size();i++)
            {

                if(v.sum+orgins.get(i)>maxTime)
                {
                    break;
                }
                value v1=new value(i,v.sum+orgins.get(i));
                v1.list.addAll(v.list);v1.list.add(orgins.get(i));
                queue.offer(v);
            }



        }




        return null;
    }


}