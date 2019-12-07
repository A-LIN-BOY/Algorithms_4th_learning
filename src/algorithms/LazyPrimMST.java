package algorithms;

import java.util.LinkedList;
import java.util.Queue;

//最小生成树的Prim算法的延时实现
public class LazyPrimMST
{
    private boolean[] marked;       //最小生成树的顶点
    private Queue<Edge> mst;    //最小生成树的边
    private MinPQ<Edge> pq;         //横切边（当中会有失效的边）

    public LazyPrimMST(EdgeWeightedGraph graph)
    {
        int v=graph.getV();
        marked=new boolean[v];
        mst=new LinkedList<>();
        pq=new MinPQ<>(100);            //因为在我的实现没有自动调节队列大小的功能，所以这能预先分配较大的空间

        visit(graph,0);
        while(!pq.isEmpty())
        {
            Edge e=pq.deleteMin();
            int one=e.either();int other=e.other(one);
            if(marked[one]&&marked[other]) continue;        //如果这两个顶点都已经标志过了，则这是一条失效边
            mst.add(e);
            if(!marked[one]) visit(graph,one);              //找出这条横切边中还没标志的顶点，并将其标记、加边
            if(!marked[other]) visit(graph,other);
        }
    }

    //标志顶点v并将顶点v与未标记的点的边加入横切边中
    private void visit(EdgeWeightedGraph graph,int v)
    {
        marked[v]=true;
        for(Edge e:graph.adj(v))
            if(!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges()
    {return mst;}

    public double weight()
    {
        double weight=0;
        Iterable<Edge> edges=edges();
        for(Edge e:edges)
           weight+=e.getWeight();
        return weight;
    }
}
