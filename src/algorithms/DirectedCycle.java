package algorithms;

import java.util.Stack;

//判断有向图是否有环
public class DirectedCycle
{
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;          //标志在调用栈上的顶点
    private Stack<Integer> cycle;       //环中的所有顶点

    public DirectedCycle(Digraph graph)
    {
        int v=graph.V();
        marked=new boolean[v];
        edgeTo=new int[v];
        onStack=new boolean[v];
        for (int i = 0; i < v; i++)
        {
            if(!marked[i])
                dfs(graph,i);
        }
    }

    private void dfs(Digraph graph,int v)
    {
        if(hasCycle()) return;
        marked[v]=true;
        onStack[v]=true;
        for(int w: graph.adj(v))
        {
            if(!marked[w])
            {
                edgeTo[w]=v;
                dfs(graph,w);
            }
            else if(onStack[w])
            {
                cycle=new Stack<>();
                for(int i=v;i!=w;i=edgeTo[i])
                    cycle.push(i);
                cycle.push(w);
                cycle.push(v);
                return;
            }
        }
        onStack[v]=false;
    }

    public boolean hasCycle()
    {  return cycle!=null; }

    public Iterable<Integer> getCycle()
    { return cycle;}
}
