package algolithms;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

//符号图，支持用名字搜索，也可以用下标搜索
public class SymbolGraph
{
    private HashMap<String,Integer> st;          //符号对应索引
    private String[] keys;                  //索引对应符号
    private Graph g;                        //保留一张维护各个顶点关系的表

    /**
     * 从输入流中构建一张图
     * @param file   文件URI，每行第一个字符串代表该顶点，其余字符串是该顶点的相邻顶点
     * @param sp    分隔符，分割各个顶点
     */
    public SymbolGraph(String  file, String sp) throws IOException
    {
        st=new HashMap<>();
        BufferedReader in=getInputStream(file);         //第一次从文件中读入图
        String nextLine;
        while((nextLine=in.readLine())!=null)
        {
            String[] a=nextLine.split(sp);
            for (int i = 0; i < a.length; i++)
                if(!st.containsKey(a[i]))
                    st.put(a[i],st.size());
        }

        keys=new String[st.size()];
        for(String name:st.keySet())
            keys[st.get(name)]=name;

         g=new Graph(st.size());
        in=getInputStream(file);
        while((nextLine=in.readLine())!=null)
        {
            System.out.println(nextLine);

            String[] a=nextLine.split(sp);

            System.out.println(a[0]);

            int v=st.get(a[0]);

            for (int i = 1; i < a.length; i++)
            {
                g.addEdge(v,st.get(a[i]));

                System.out.println(a[i]);
            }
        }

    }

    private BufferedReader getInputStream(String file) throws FileNotFoundException
    {
        BufferedReader bf=new BufferedReader(new FileReader(file));
        return bf;
    }

    public boolean contains(String name)
    {
        return st.containsKey(name);
    }

    public int index(String name)
    {
        return st.get(name);
    }

    public String name(int v)
    {
        return keys[v];
    }

    public Graph getG()
    {
        return g;
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in=new Scanner(System.in);
        String file="";
        SymbolGraph sg=new SymbolGraph(file,",");

        Graph g=sg.getG();
        while(in.hasNextLine())                 //如果输入不存在的话会报空指针异常
        {
            String s=in.nextLine();
            for(int w:g.adj(sg.index(s)))
                System.out.println(" "+sg.name(w));
        }
    }
}
