package algorithms;

//判断给定的图是否是有环，假设图是无自环和平行边
public class Cycle
{
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g)
    {
        marked=new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
        {
            if(!marked[s])
                dfs(g,s,s);
        }
    }

    /*
    有环思路：首先在DFS中传入当前结点及其父节点p，若v的邻接结点中有有一个结点w已被标志，但它与父节点
    p不相等，则证明w与起点s连通于另一条路径，而v又与s连通于经过p的另一条路径，而v又于w相连，所有有环；
     */
    private void dfs(Graph g, int v,int p)
    {
        marked[v]=true;
        for (int w:g.adj(v))
        {
            if(!marked[w])
                dfs(g,w,v);
            else
                if(w!=p)
                    hasCycle=true;
        }

    }

    public boolean isHasCycle()
    {
        return hasCycle;
    }
}
