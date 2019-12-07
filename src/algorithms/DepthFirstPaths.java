package algorithms;

//使用深度优先搜索查找图中的路径

import java.util.Stack;

public class DepthFirstPaths
{
    private boolean[] marked;     //标志顶点是否被访问过
    private int[] edgeTo;         //edgeTo[i]表示到达i顶点是哪个顶点发出的边
    private int s;                //起点

    //根据图g和起点s构造edgeTo数组，也就是构造了图中所有顶点到s的路径
    public DepthFirstPaths(Graph g, int s)
    {
        marked=new boolean[g.V()];
        edgeTo=new int[g.V()];
        this.s=s;
        dfs(g,s);           //从起点s开始，通过深度优先搜索找出图中各顶点的到s的路径
    }

    private void dfs(Graph g, int v)
    {
        marked[v]=true;
        for(int w:g.adj(v))
            if(!marked[w])
            {
                edgeTo[w]=v;        //w首次被访问是通过v伸出的边
                dfs(g,w);
            }
    }

    //s是否有路径到达v
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    //s到v的路径
    public Iterable<Integer> paths(int v)
    {
        if(!hasPathTo(v)) return null;      //这条很重要，要不然与s不连通的顶点会错误的与s连通
        Stack<Integer> path=new Stack<>();
            for(int i=v;i!=s;i=edgeTo[v])
            {
                path.push(i);
            }
            path.push(s);
            return path;
    }
}
