package algorithms;

import java.io.DataInputStream;
import java.util.Stack;

//有向图
public class Digraph
{
    private int V;  //顶点数目
    private int E;  //边数目
    private Stack<Integer>[] adj;   //邻接表

    //创建一个含有V个顶点但不含有边的图
    public Digraph(int v)
    {
        this.V=v;
        adj=(Stack<Integer>[])new Stack[v];
        for (int i = 0; i < v; i++)
        {
            adj[i]=new Stack<Integer>();
        }
    }

    //从标准输入流in读入一幅图
    public Digraph(DataInputStream in) throws Exception
    {
        this(in.readInt());
        int E=in.readInt();     //读取边数，不是类的E
        for (int i = 0; i < E; i++)
        {
            int u=in.readInt();
            int w=in.readInt(); //读取两个相邻的顶点
            addEdge(u,w);       //添加一条边
        }
    }

    //顶点数
    public int V()
    {
        return V;
    }

    //边数
    public int E()
    {
        return E;
    }

    //向图中添加一条边
    public void addEdge(int v,int w)
    {
        adj[v].add(w);
        E++;
    }

    //和v相邻的所有顶点
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }


    //有向图的反向图
    public Digraph reverse()
    {
        Digraph digraph=new Digraph(V);
        for (int i = 0; i < V; i++)
        {
            for(int w:adj(i))
                digraph.addEdge(w,i);
        }
        return digraph;
    }

    //字符串表示
    public String toString()
    {
        String s=V+" vertices, "+ E+ " edges\n";
        for (int i = 0; i < V; i++)
        {
            s+=i+": ";
            for(int w:this.adj(i))
                s+=w+" ";
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args)
    {
        Digraph g=new Digraph(2);
        System.out.println(g.toString());
    }
}
