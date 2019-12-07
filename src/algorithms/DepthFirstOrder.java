package algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//有向图中基于深度优先搜索的顶点排序
public class DepthFirstOrder
{
    private boolean[] marked;
    private Queue<Integer> pre;         //所有顶点的前序排列
    private Queue<Integer> post;        //所有顶点的后序排列
    private Stack<Integer> reversePost; //所有顶点的后序排列

    public DepthFirstOrder(Digraph graph)
    {
        int v=graph.V();
        marked=new boolean[v];
        pre=new LinkedList<>();
        post=new LinkedList<>();

        for (int i = 0; i < v; i++)
            if(!marked[i])
                dfs(graph,i);
    }

    private void dfs(Digraph graph, int v)
    {
        pre.add(v);                         //在深度搜索之前加入顶点，构成前序排列
        marked[v]=true;
        for(int w:graph.adj(v))
            if(!marked[w])
                dfs(graph,w);
            post.add(v);                    //在搜索之后加入队列中，构成后序排列
            reversePost.push(v);            //在搜索之后加入栈中，构成逆后序排列
    }

    public Iterable<Integer> pre()
    {return pre;}

    public Iterable<Integer> post()
    {return post; }

    public Iterable<Integer> reversePost()
    {return reversePost;}
}
