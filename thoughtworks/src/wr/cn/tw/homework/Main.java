package wr.cn.tw.homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main
{

    public static Integer[][] CreateGraph(String str, String split,Map<String,Integer> map)
    {
        String[] strs=str.split(split);
        Integer[][] lens=new Integer[3][];
        List<List<Integer>>list=new ArrayList<>();
        String maxStr="A";
        for(int i=0;i<strs.length;i++)
        {

            String town1=strs[i].substring(0,1);
            String town2=strs[i].substring(1,2);
            if(map.get(town1)==null)
            {
                list.add(new LinkedList<>());
                map.put(town1,map.size()+1);
            }
            if(map.get(town2)==null)
            {
                map.put(town2,map.size()+1);
            }
            list.get(map.get(town1)).add(map.get(town2));

        }

      return null;


    }

}
