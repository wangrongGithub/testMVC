package cn;



public class Main {
    public static void main(String[]args)
    {

        GraphAdjMatix graphAdjMatix=new GraphAdjMatix();
        GraphAdjMatix graphAdjMatix1=new GraphAdjMatix();
        GraphAdjList  graphAdjList=new GraphAdjList();
        GraphAdjList  graphAdjList1=new GraphAdjList();
        Main.CreateGraphList(9,9,graphAdjList);
        Main.CreateGraphList(9,9,graphAdjList1);
        CreateGraph(9,9,graphAdjMatix);
        CreateGraph(9,9,graphAdjMatix1);
        // The Breadth-first traversal of the graph based on adjacency matrix.
        Traversal.BreadthFirst(graphAdjMatix, 0);
        // The Depth-first traversal of the graph based on adjacency matrix.
        Traversal.DethFirst(graphAdjMatix1, 0);
        // The Breadth-first traversal of the graph based on adjacency list.
        Traversal.BreadthFirstList(graphAdjList,0);
        // The Depth-first traversal of the graph based on adjacency list.
        Traversal.DethFirstList(graphAdjList1,0);



        }
/*
* Create graph based on adjacency matrix.
*
* */
    public static void CreateGraph(int vexs, int adjxs, GraphAdjMatix graph )
    {
        int[][] arcs=new int[vexs][vexs];

        arcs[0][1]=1;
        arcs[0][3]=1;
        arcs[0][4]=1;
        arcs[1][4]=1;
        arcs[2][1]=1;
        arcs[3][6]=1;
        arcs[4][5]=1;
        arcs[4][7]=1;
        arcs[5][2]=1;
        arcs[5][7]=1;
        arcs[6][7]=1;
        arcs[7][8]=1;
        arcs[8][5]=1;
        graph.setNumVertexes(vexs);
        graph.setNumEdges(13);
        VertexInfo<String>[]vertexInfo=new VertexInfo[vexs];
        for(int i=0;i<vexs;i++)
        {
            vertexInfo[i]=new VertexInfo();
            vertexInfo[i].setVex((char)('A'+i)+"");
            vertexInfo[i].setFlag(false);
            vertexInfo[i].setSubscript(i);

        }
        graph.setVexs(vertexInfo);
        graph.setArc(arcs);




    }
    /*
* Create graph based on adjacency list.
*
* */
    public static void CreateGraphList(int vexs, int adjxs, GraphAdjList graph )
    {
        int[][] arcs=new int[vexs][vexs];

        arcs[0][1]=1;
        arcs[0][3]=1;
        arcs[0][4]=1;
        arcs[1][4]=1;
        arcs[2][1]=1;
        arcs[3][6]=1;
        arcs[4][5]=1;
        arcs[4][7]=1;
        arcs[5][2]=1;
        arcs[5][7]=1;
        arcs[6][7]=1;
        arcs[7][8]=1;
        arcs[8][5]=1;
        graph.setNumVertexes(vexs);
        graph.setNumEdges(13);
        VertexNode[]vertexInfo=new VertexNode[vexs];
        for(int i=0;i<vexs;i++)
        {
            vertexInfo[i]=new VertexNode();
            //vertexInfo[i].setFirstedge();
            VertexInfo<String> v= new VertexInfo();
            v.setVex((char)('A'+i)+"");
            v.setFlag(false);
            v.setSubscript(i);
            vertexInfo[i].setData(v);
            for(int j=0;j<vexs;j++)
            {


                if(arcs[i][j]==1)
                {
                    EdgeNode eNode=new EdgeNode();
                    eNode.setWeigth(1);
                    eNode.setAdjvex(j);
                    if(vertexInfo[i].getFirstedge()!=null)
                    {
                        eNode.setNext(vertexInfo[i].getFirstedge());
                        vertexInfo[i].setFirstedge(eNode);


                    }
                    else
                    {
                        vertexInfo[i].setFirstedge(eNode);

                    }


                }

            }
            graph.setAdjList(vertexInfo);


        }





    }
}
