package algorithms;

//用数组实现的最小优先级队列，堆按照完全二叉树的逻辑来建
//并在里面实现了堆排序算法

public class MinPQ<Key extends Comparable<Key>>
{
    private Key[] pq;  //数组来存储二叉树，不使用pq[0]
    private int n;  //存储队列的长度

    //构造初始容量为size的优先级队列
    public MinPQ(int size)
    {
        pq= (Key[]) new Comparable[size];
    }

    public boolean isEmpty()
    {
        return n==0;
    }

    public int size()
    {
        return n;
    }

    public void insert(Key v)
    {
        pq[++n]=v;
        swim(n);
    }

    public Key deleteMin()
    {
        if (isEmpty())
        {
            System.out.println("队列为空！");
            return null;
        }
        Key temp=pq[1];
        pq[1]=pq[n];
        pq[n]=null;
        n--;
        sink(1);
        return temp;
    }

    private boolean less(int i, int j, Comparable[]a)
    {
        if(a[i].compareTo(a[j])<0)
            return true;
        return false;
    }

    private void swap(int i, int j, Comparable[]a)
    {
        Comparable temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    private void swim(int k)
    {
        int parent=k/2;
        while(parent>=1)
        {
            if(less(k,parent,pq))
            {
                swap(parent,k,pq);
                k=parent;
                parent=k/2;
            }
            else return;
        }
    }

    private void sink(int k)
    {
        int minson;
        while (k*2+1<=n)
        {
            if (less(k * 2, k * 2 + 1,pq))
                minson = k * 2 ;
            else
                minson = 2 * k+1;
            if (less(minson, k,pq))
            {
                swap(minson, k,pq);
                k = minson;
            }
            else break;
        }
    }

    //对指定可比较的集合进行下沉操作
    public void sink(Comparable[]a,int k,int last)
    {
        int minson;
        while (k*2+1<=last)
        {
            if (less(k * 2, k * 2 + 1,a))
                minson = k * 2 ;
            else
                minson = 2 * k+1;
            if (less(minson, k,a))
            {
                swap(minson, k,a);
                k = minson;
            }
            else break;
        }
    }

    public static void main(String[] args)
    {
        MinPQ pq=new MinPQ(5);
        pq.insert("a");
        pq.isEmpty();
        pq.insert("b");
        pq.insert("c");
        pq.insert("d");
        System.out.println(pq.deleteMin());
        System.out.println(pq.deleteMin());
        System.out.println(pq.deleteMin());
        System.out.println(pq.deleteMin());
        System.out.println(pq.deleteMin());

        String line =" e r t y u i o p l k m j n n b g h v f d cv s x z a ";
        String []lines= line.split(" ");
        new MinPQ(2).sort(lines);
        for(String s:lines)
            System.out.println(s);
    }

    //堆排序
    public  void sort(Comparable[]a)
    {
        //建堆
        int k=(a.length-1)/2;
        while(k>=1)
        {
            sink(a,k,a.length-1);
            k--;
        }

        //删除最小元素加下沉实现排序
        int i=a.length-1;
        while (i>0)
        {
            swap(1, i, a);
            i--;
            sink(a, 1, i);
        }
    }
}
