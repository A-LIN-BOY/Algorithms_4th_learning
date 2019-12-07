package algolithms;

import java.io.BufferedReader;
import java.io.IOException;

//加权无向图的数据类型
public class EdgeWeightedGraph
{
    private final int V;           //顶点的总数
    private int E;                  //边的总数
    private Bag<Edge>[] adj;        //邻接表

    public EdgeWeightedGraph(int V)     //构建v个顶点，没有边的加权图
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Edge>();
    }

    public EdgeWeightedGraph(BufferedReader in) throws IOException
    {
        int v = Integer.parseInt(in.readLine());
        int e = Integer.parseInt(in.readLine());
        this.V=v;
        this.E=e;
        while (in.readLine() != null)
        {
            String s = in.readLine();
            String[] ss = s.split(" ");
            int one = Integer.parseInt(ss[0]);
            int other = Integer.parseInt(ss[1]);
            double Weight = Double.parseDouble(ss[2]);
            Edge edge = new Edge(one, other, Weight);
            addEdge(edge);
        }
    }

    public int getV()
    {return V;};

    public int getE()
    {return E;}

    //添加一条边
    public void addEdge(Edge e)
    {
        int v=e.either();
        int w=e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v)
    {return adj[v];}

    //返回加权无向图中的所有边
    public Iterable<Edge> edges()
    {
        Bag<Edge> b=new Bag<>();
        for (int i = 0; i < V; i++)
            for(Edge e:adj[i])
                if(e.other(i)>i)
                    b.add(e);
                return b;
    }
}
