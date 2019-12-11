package cn.wr.train;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public interface Input
{

    List<String> input();//用来接收输入；一行一行的接收

}
class realInput implements Input{

    String fileName="";
    @Override
    public List<String> input()
    {
        BufferedReader br=null;
        List<String>list=new LinkedList();
        try {
            String str="";
            br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));

                while((str=br.readLine())!=null)
                {
                    list.add(str);

                }


        } catch (FileNotFoundException e  ) {
            e.printStackTrace();
        }catch (IOException e  ) {
            e.printStackTrace();
        }
        return null;
    }
}