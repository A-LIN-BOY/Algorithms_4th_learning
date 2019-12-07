package algolithms;

//深度优先搜索
public class DepthFirstSearch
{
    private boolean[] marked;       //标识那个顶点已被访问
    private int count;              //记录搜索后图中有几个顶点是相互连通的

    //从顶点v开始遍历图g
    public DepthFirstSearch(Graph g, int v)
    {
        marked=new boolean[g.V()];
        dfs(g,v);
    }

    private void dfs(Graph g,int v)
    {
        marked[v]=true;
        for(Integer w:g.adj(v))
            if(!marked[w])
                dfs(g,w);
    }

    public boolean marked(int v)
    {
        return marked[v];
    }

    public int count()
    {
        return count;
    }
}
