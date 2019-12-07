package algolithms;

//判断G是否是二分图
public class TwoColor
{
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor;

    public TwoColor(Graph g)
    {
        marked=new boolean[g.V()];
        color=new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
        {
            if(!marked[s])
            {
                color[s]=true;
                dfs(g,s);
            }
        }
    }

    private void dfs(Graph g, int v)
    {
        marked[v]=true;
        for (int w:g.adj(v))
        {
            if(!marked[w])
            {
                color[w]=!color[v];
                dfs(g,w);
            }
            else
                if(color[w]==color[v]) isTwoColor=false;
        }
    }

    public boolean isTwoColor()
    {
        return isTwoColor;
    }
}
