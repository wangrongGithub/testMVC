package cn;

/*
 Representation of graph by adjacency matrix.

 */
public class GraphAdjMatix {
    VertexInfo [] vexs;//Vertex field for storing vertex information.
    int[][] arc;//Two-dimensional array storing edge weights.
    int numVertexes;//The number of vertices in the graph.
    int numEdges;//The number of edges in the graph.

    public VertexInfo[] getVexs() {
        return vexs;
    }

    public int[][] getArc() {
        return arc;
    }

    public int getNumVertexes() {
        return numVertexes;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setVexs(VertexInfo[] vexs) {
        this.vexs = vexs;
    }

    public void setArc(int[][] arc) {
        this.arc = arc;
    }

    public void setNumVertexes(int numVertexes) {
        this.numVertexes = numVertexes;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }
}
