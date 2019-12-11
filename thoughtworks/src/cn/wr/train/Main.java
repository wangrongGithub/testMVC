package cn.wr.train;
import java.util.LinkedList;
import java.util.List;

public class Main
{
    static int[][] lenRoad;
    static Vertex[] graph;
    static int[][] lenVex;
    public  static void main(String []args)
    {
        Input input=null;
        List<String> lines=input.input();
        String towns="AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        Vertex[] graph=CreateGraph(towns,",");
        lenRoad=new int[graph.length][graph.length];


    }
    static  String getShortRoad(String town1,String town2)
    {
        for(int j=0;j<graph.length;j++)
            for(int k=0;k<graph.length;k++)
            {
                if(lenVex[j][k]>0)
                {
                    lenRoad[j][k]=lenVex[j][k];
                }
                else
                {
                    lenRoad[j][k]= Integer.MAX_VALUE;
                }

            }

        //最短的路径啊
        for(int i=0;i<graph.length;i++)
        {
            for(int j=0;j<graph.length;j++)
                for(int k=0;k<graph.length;k++)
                {
                    if(lenRoad[j][k]==0||lenRoad[j][k]>lenRoad[j][i]+lenRoad[i][k])
                    {
                        lenRoad[j][k]=lenRoad[j][i]+lenRoad[i][k];
                    }

                }
        }


       return "";
    }






    static Vertex[] CreateGraph(String str,String split)
    {
        String[] strs=str.split(split);
        List<Node>list=new LinkedList<>();
        String maxStr="A";
        for(int i=0;i<strs.length;i++)
        {
            String town1=strs[i].substring(0,1);
            String town2=strs[i].substring(1,2);
            Integer distance=Integer.valueOf(strs[i].substring(2,strs[i].length()));
            list.add(new realNode(town1,town2,distance));
            if(town1.compareTo(maxStr)>0)
            {
                maxStr=town1;
            }
        }
        Vertex[]graph=new Vertex[maxStr.charAt(0)-'A'];
        for(int i=0;i<list.size();i++)
        {
            Node node=list.get(i);
            String town1=node.getTown1();
            int index=town1.charAt(0)-'A';
            if(graph[index]==null)
            {
                graph[index]=new Vertex(town1);
            }
            vertexEdge first=graph[index].getEdges().nextEdge.nextEdge;
            vertexEdge newEdge=new vertexEdge(node.getTown2(),first,node.getDis());
            graph[index].getEdges().nextEdge=newEdge;



        }

        return graph;



    }


}
class Vertex
{
    String townName="";
    vertexEdge headEdge=new vertexEdge() ;

    Vertex(String townName)
    {
        this.townName=townName;
    }

    public String getTownName() {
        return townName;
    }

    public vertexEdge getEdges() {
        return headEdge;
    }
}
class vertexEdge
{
    String endTownName="";
    vertexEdge nextEdge=null;
    int distance;
    vertexEdge()
    {

    }
    vertexEdge( String endTownName,
            vertexEdge nextEdge,int distance)
    {
        this.endTownName=endTownName;
        this.nextEdge=nextEdge;
        this.distance=distance;
    }

    public String getEndTownName() {
        return endTownName;
    }

    public vertexEdge getNextEdge() {
        return nextEdge;
    }
}