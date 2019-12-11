package cn.wr.Conf;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReadAllTalks
{
    //模板方法啊
    public static   Map<Integer,List<String>> readTalks(String fileName, SplitNameNum split)
    {
        BufferedReader br=null;
        Map<Integer,List<String>> map=new HashMap();
        try {
            br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
            String str="";
            int i=0;
            while((str=br.readLine())!=null)
            {
                TalkContent tContent=split.spiltLine(str);
                int num=tContent.getTalkNum();
                if(map.get(num)==null)
                {
                    map.put(num,new LinkedList<>());
                }
                map.get(num).add(str);
                i++;
            }
            System.out.println(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
