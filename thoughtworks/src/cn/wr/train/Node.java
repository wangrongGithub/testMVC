package cn.wr.train;

public interface Node
{
    String getTown1();
    String getTown2();
    Integer getDis();
}
class realNode implements Node{
    String town1="";
    String town2="";
    Integer distance=null;
  public   realNode(String town1,
            String town2,
            Integer distance)
    {
        this.town1=town1;
        this.town2=town2;
        this.distance=distance;
    }


    @Override
    public String getTown1() {
        return town1;
    }

    @Override
    public String getTown2() {
        return town2;
    }

    @Override
    public Integer getDis() {
        return distance;
    }
}
