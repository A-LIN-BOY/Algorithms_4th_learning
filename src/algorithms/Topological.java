package algorithms;

//有向图的拓扑排序
public class Topological
{
    private Iterable<Integer> order;        //顶点的拓扑排序

    public Topological(Digraph graph)
    {
        DirectedCycle cycleFinder=new DirectedCycle(graph);     //构造有向图的判环器
        if(!cycleFinder.hasCycle())
        {
            DepthFirstOrder depthFirstOrder=new DepthFirstOrder(graph);         //构造图的顶点比例顺序
            order=depthFirstOrder.reversePost();                //遍历顺序的逆后序即为顶点的拓扑排序
        }
    }

    public Iterable<Integer> order()
    {return order;}

    public boolean isDAG()          //是否是有向无环图
    {return order!=null;}
}
