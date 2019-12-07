package algolithms;

//图的各种处理算法
public class GraphProcess
{
    //计算v的度数
    public static int degree(Graph G, int v)
    {
        int count=0;
        Iterable<Integer> n=G.adj(v);
        for (Integer w:n)
        {
            count++;
        }
        return count;
    }

    //计算所有顶点的最大度数
    public static int maxDegree(Graph G)
    {
        int max=0;
        for(int i=0;i<G.V();i++)
        {
            if(degree(G,i)>max)
                max=degree(G,i);
        }
        return max;
    }

    //计算所有顶点的平均度数(一条边连着两个顶点，因此一条边贡献两个度)
    public static double aveDegree(Graph G)
    {
        return 2*G.E()/G.V();
    }
}
