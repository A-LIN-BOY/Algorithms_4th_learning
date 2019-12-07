package algolithms;

//计算强连通分量
public class KosarajuSCC
{
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph graph)
    {
        int v= graph.V();
        marked=new boolean[v];
        id=new int[v];
        DepthFirstOrder order=new DepthFirstOrder(graph.reverse()); //按照有向图的反向图的逆后序进行深度优先搜索，即可得到强连通分量
        for(int i:order.reversePost())
            if(!marked[i])
            {dfs(graph,i);count++;}
    }

    private void dfs(Digraph graph, int v)
    {
        marked[v]=true;
        id[v]=count;
        for(int w: graph.adj(v))
            if(!marked[w])
                dfs(graph,w);
    }

    public boolean stronglyConnected(int v,int w)
    {return id[w]==id[v];}

    public int id(int v)
    {return id[v];}

    public int count()
    {return count;}
}
