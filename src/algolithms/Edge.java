package algolithms;

//带权重的边的数据类型
public class Edge implements Comparable<Edge>
{
    private final int v;      //顶点之一
    private final int w;      //另一个顶点
    private final double weight;  //边的权重

    public Edge(int v,int w, double weight)
    {
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double getWeight()
    {return weight;}

    //返回顶点v，用于未知顶点是什么时
    public int either()
    {return v;}

    //返回不是vertex的另一个顶点
    public int other(int vertex)
    {
        if(vertex==v) return w;
        if(vertex==w) return v;
        throw new RuntimeException("参数不是合格顶点");
    }

    @Override
    public int compareTo(Edge that)
    {
        if(this.weight>that.weight) return 1;
        if(this.weight<that.weight) return -1;
        return 0;
    }

    public String toString()
    { return String.format("%d-%d %.2f",v,w,weight);}
}
