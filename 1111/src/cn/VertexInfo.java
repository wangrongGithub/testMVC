package cn;
/*
* The class of vertices information
* */
public class VertexInfo<V> {
    V vex;//vertex information
    boolean flag;//visited marks.
    int subscript;//index of vertex.

    public void setSubscript(int subscript) {
        this.subscript = subscript;
    }

    public int getSubscript() {
        return subscript;
    }

    public void setVex(V vex) {
        this.vex = vex;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public V getVex() {
        return vex;
    }

    public boolean getFlag() {
        return flag;
    }


}
