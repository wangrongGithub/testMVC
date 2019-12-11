package cn.wr.Conf;

public interface SplitNameNum
{
    //对每一行进行分割啊；返回的是String+Integer
    TalkContent spiltLine(String content);

}
class realSplit implements SplitNameNum{

    @Override
    public TalkContent spiltLine(String content)
    {
        String[]strs=content.split(" ");
        TalkContent tc=new realContent();
        if(strs[strs.length-1].equals("lightning"))
        {
            tc.setTalkName(content.substring(0,content.length()-9));
            tc.setTalkNum(5);
        }
        else
        {
            int num=Integer.valueOf(strs[strs.length-1].substring(0, strs[strs.length-1].length()-3));
            tc.setTalkNum(num);
            tc.setTalkName(content.substring(0,content.length()-strs[strs.length-1].length()));

        }
        return tc;
    }
}
