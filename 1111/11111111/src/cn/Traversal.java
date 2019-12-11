package cn;

import java.util.*;

public class Traversal {
/*
* The breadth-first traversal of the graph based on adjacency matrix
* */
   public static  void BreadthFirst(GraphAdjMatix graph,int key)
    {
        VertexInfo [] vexs=graph.getVexs();
        int[][] arc=graph.getArc() ;
        List<String> output=new ArrayList<>();
        Queue<VertexInfo> queue=new LinkedList();

        queue.add(vexs[key]);
        vexs[key].setFlag(true);

        output.add(vexs[key].vex.toString());
        while(!queue.isEmpty())
        {
/*
* queue head element dequeue.
*
* */
            VertexInfo vex=queue.poll();
            int subScript=vex.getSubscript();
            /*
* visited the non-visited adjacent nodes of the queue head node  and joining it to the queue.
*
* */
            for(int i=0;i<graph.getNumVertexes();i++)
            {
                if(arc[subScript][i]==1&&!vexs[i].getFlag())
                {
                    queue.add(vexs[i]);
                    vexs[i].setFlag(true);
                    output.add(vexs[i].vex.toString());

                }

            }






        }
        System.out.print("The result of breadth-first traversal of the graph based on adjacency matrix:");
for(int i=0;i<output.size();i++)
{

    if(i==output.size()-1)
    { System.out.print(output.get(i));
        System.out.println();
    }
    else
    {
        System.out.print(output.get(i)+"-->");
    }
}


    }
    /*
* The Depth-first traversal of the graph based on adjacency matrix.
* */
    static void DethFirst(GraphAdjMatix graph,int key)
    {
        VertexInfo [] vexs=graph.getVexs();;
        int[][] arc=graph.getArc() ;
        List<String> output=new ArrayList<>();
        Stack<VertexInfo> stack=new Stack<>();
        stack.push(vexs[key]);
        while(!stack.isEmpty())
        {
            /*
            * The top element of the stack is popped and visited.
            * */
            VertexInfo vex=stack.pop();
            int subScript=vex.getSubscript();
            if( vexs[subScript].getFlag()==true)
            {
                continue;
            }
            vexs[subScript].setFlag(true);
            output.add(vexs[subScript].vex.toString());

           /*
* Non-visited adjacent nodes of the top element join the stack.
*
* */
            for(int i=0;i<graph.getNumVertexes();i++)
            {
                if(arc[subScript][i]==1&&!vexs[i].getFlag())
                {
                    stack.push(vexs[i]);


                }

            }

        }
        System.out.print("The result of depth-first traversal of the graph based on adjacency matrix:");
        for(int i=0;i<output.size();i++)
        {

            if(i==output.size()-1)
            { System.out.print(output.get(i));
                System.out.println();
            }
            else
            {
                System.out.print(output.get(i)+"-->");
            }
        }


    }

/*
* The breadth-first traversal of the graph based on adjacency list.
* */
    public static  void BreadthFirstList(GraphAdjList graph,int key)
    {
        VertexNode[]vertexInfo=graph.getAdjList();
        List<String> output=new ArrayList<>();
        Queue<VertexNode> queue=new LinkedList();
        queue.add(vertexInfo[key]);
        vertexInfo[key].getData().setFlag(true);

        output.add(vertexInfo[key].getData().getVex().toString());
        while(!queue.isEmpty())
        {
/*
* queue head element dequeue.
*
* */
            VertexNode vex=queue.poll();
            int subScript=vex.getData().getSubscript();

            EdgeNode p=vertexInfo[subScript].getFirstedge();
            /*
* visited the non-visited adjacent nodes of the queue head node  and joining it to the queue .
*
* */
            while(p!=null)
            {


                int tag=p.getAdjvex();
                if(!vertexInfo[tag].getData().getFlag()) {
                    queue.add(vertexInfo[tag]);
                    vertexInfo[tag].getData().setFlag(true);
                    output.add(vertexInfo[tag].getData().getVex().toString());
                }
                p=p.getNext();

            }


        }


        System.out.print("The result of breadth-first traversal of the graph based on adjacency list:");
        for(int i=0;i<output.size();i++)
        {

            if(i==output.size()-1)
            { System.out.print(output.get(i));
                System.out.println();
            }
            else
            {
                System.out.print(output.get(i)+"-->");
            }
        }




        }
/*
* The depth-first traversal of the graph based on adjacency list.
* */

    static void DethFirstList(GraphAdjList graph,int key)
    {

        VertexNode[]vertexInfo=graph.getAdjList();
        List<String> output=new ArrayList<>();
        Stack<VertexNode> stack=new Stack<>();
        stack.push(vertexInfo[key]);




        while(!stack.isEmpty())
        {
            /*
            * The top element of the stack is popped and visited.
            * */
            VertexNode vex=stack.pop();
            int subScript=vex.getData().getSubscript();

            if( vertexInfo[subScript].getData().getFlag()==true)
            {
                continue;
            }

            vertexInfo[subScript].getData().setFlag(true);
            output.add(vertexInfo[subScript].getData().getVex().toString());
            EdgeNode p=vertexInfo[subScript].getFirstedge();
            /*
* Non-visited adjacent nodes of the top element join the stack.
*
* */
            while(p!=null)
            {
                int tag=p.getAdjvex();

                if(!vertexInfo[tag].getData().getFlag())
                {
                    stack.push(vertexInfo[tag]);




                }

                p=p.getNext();

            }



        }
        System.out.print("The result of depth-first traversal of the graph based on adjacency list:");
        for(int i=0;i<output.size();i++)
        {

            if(i==output.size()-1)
            { System.out.print(output.get(i));
                System.out.println();
            }
            else
            {
                System.out.print(output.get(i)+"-->");
            }
        }


    }




}



