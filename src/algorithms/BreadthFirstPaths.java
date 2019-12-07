package algorithms;

import java.util.LinkedList;
import java.util.Queue;

//使用广度优先搜索图中的路径
public class BreadthFirstPaths
{
    private boolean[] marked;
    private int[] egdeTo;
    private int s;

    public BreadthFirstPaths(Graph g, int s)
    {
        marked=new boolean[g.V()];
        egdeTo=new int[g.V()];
        this.s=s;

        bfs(g,s);
    }

    private void bfs(Graph g,int s)
    {
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);       //起点入队
        marked[s]=true;
        while(!queue.isEmpty())
        {
            int v= queue.remove();
            for(int w:g.adj(v))
            {
                if(!marked[w])
                {
                    queue.add(w);
                    marked[w]=true;
                    egdeTo[w]=v;
                }
            }

        }
    }

    public boolean hasPathTo(int w)
    {
        return marked[w];
    }
}
