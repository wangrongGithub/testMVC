package cn.wr.train;

import java.util.LinkedList;
import java.util.List;

public interface Handler
{
   List<Node> handler (String str);

}
class realHandler implements Handler {

    @Override
    public List<Node> handler(String str)
    {
        List<Node>list=new LinkedList<>();
        String[] strs=str.split(",");
        for(int i=0;i<strs.length;i++)
        {
            String town1=strs[i].substring(0,1);
            String town2=strs[i].substring(1,2);
            Integer distance=Integer.valueOf(strs[i].substring(2,strs[i].length()));
            list.add(new realNode(town1,town2,distance));
        }



        return list;
    }
}