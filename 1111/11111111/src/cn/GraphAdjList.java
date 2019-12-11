package cn;
/*
 Representation of graph by adjacency list.

 */
public class GraphAdjList {
    VertexNode[] adjList;  //Array of vertex nodes.
    int numVertexes, numEdges;  //The number of current vertices and edges in the graph.

    public void setAdjList(VertexNode[] adjList) {
        this.adjList = adjList;
    }

    public void setNumVertexes(int numVertexes) {
        this.numVertexes = numVertexes;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public VertexNode[] getAdjList() {
        return adjList;
    }

    public int getNumVertexes() {
        return numVertexes;
    }

    public int getNumEdges() {
        return numEdges;
    }

}
/*
*
*  Class EdgeNode Represent the Edge node of Adjacency List.
* */
    class EdgeNode
{
        int adjvex;         //Adjacent point domain, which stores the subscripts corresponding to the vertex.
        int weigth;        // The weight of the Adjacent edge.
        EdgeNode next;      //Next adjacency edge node.

    public int getAdjvex() {
        return adjvex;
    }

    public int getWeigth() {
        return weigth;
    }

    public EdgeNode getNext() {
        return next;
    }

    public void setAdjvex(int adjvex) {
        this.adjvex = adjvex;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public void setNext(EdgeNode next) {
        this.next = next;
    }
}
/*
*
 Class VertexNode Represent the Vertex node of Adjacency List.
* */
class VertexNode
        {
            VertexInfo data;        //Vertex field for storing vertex information
           EdgeNode firstedge;        //The first adjacent edge of the vertex

            public void setData(VertexInfo data) {
                this.data = data;
            }

            public void setFirstedge(EdgeNode firstedge) {
                this.firstedge = firstedge;
            }

            public VertexInfo getData() {
                return data;
            }

            public EdgeNode getFirstedge() {
                return firstedge;
            }
        }
