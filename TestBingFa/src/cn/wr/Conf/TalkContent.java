package cn.wr.Conf;

public interface TalkContent
{
    String getTalkName();
    int getTalkNum();
    void setTalkName(String name);
    void setTalkNum(int num);
}
class realContent implements TalkContent{

String Name="";
    int Num=0;

    @Override
    public String getTalkName() {
        return Name;
    }

    @Override
    public int getTalkNum() {
        return Num;
    }

    @Override
    public void setTalkName(String name) {
        Name=name;
    }

    @Override
    public void setTalkNum(int num)
    {
        Num=num;

    }
}
