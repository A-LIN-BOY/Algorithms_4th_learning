package algorithms;

//使用深度优先搜索找出图中的所有连通分量
public class CC
{
    private boolean[] marked;
    private int[] id;           //标志下标对应的顶点所在的连通分量id
    private int count;          //记录连通分量的个数

    public CC(Graph g)
    {
        marked=new boolean[g.V()];
        id=new int[g.V()];
        for (int i = 0; i < g.V(); i++)         //遍历各个顶点，若该顶点还没标志，则以该顶点为起点进行深度优先搜索
            if(!marked[i])
            {
                dfs(g,i);
                count++;
            }
    }

    private void dfs(Graph g,int v)
    {
        marked[v]=true;
        id[v]=count;
        for(int w:g.adj(v))
            if(!marked[w])
                dfs(g,w);
    }

    public boolean connected(int v, int w)
    {
        return id[v]==id[w];
    }

    public int id(int v)
    {
        return id[v];
    }

    public int getCount()
    {
        return count;
    }
}
