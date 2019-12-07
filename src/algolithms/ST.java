package algolithms;

import java.util.*;

//用数组实现的有序符号表
public class ST<Key extends Comparable<Key>, Value>
{
    private Key[] keys;
    private Value[] values;
    private int n;  //键值对的数量

    //构造方法
    public ST()
    {
        new ST(100);
    }

    //带参数的构造方法
    public ST(int cap)
    {
        keys = (Key[]) new Comparable[cap];
        values = (Value[]) new Object[cap];
        n = 0;
    }

    //将键值对存入表中（若值为空则将健key从表中删除）
    public void put(Key key, Value value)
    {
        if (value == null)
        {
            delete(key);
            return;
        }

        int k=rank(key);
        if(contains(key))
             values[k]=value;
        else
        {
            for (int i = n-1 ; i >=k ; i--)
            {
                keys[i+1]=keys[i];
                values[i+1]=values[i];
            }
            n++;
            values[k]=value;
            keys[k]=key;
        }
    }

    //获取健key对应的值（若key不存在则返回空）
    public Value get(Key key)
    {
        int k=rank(key);
        if(contains(key))
            return values[k];
        return null;
    }

    //从表中删去键key（及其对应的值）
    public void delete(Key key)
    {
        if(!contains(key))
        {
            System.out.println("不存在该键");
            return;
        }
        int k=rank(key);
        System.out.println("删除："+values[k]);
        for (int i = k+1; i <n ; i++)
        {
            keys[i-1]=keys[i];
            values[i-1]=values[i];
        }
        n--;
    }

    //键key是否存在于表中
    public boolean contains(Key key)
    {
        int i=rank(key);
        if((keys[i]!=null) && (keys[i].compareTo(key)==0))
            return true;
        return false;
    }

    //表是否为空
    public boolean isEmpty()
    {
        return n == 0;
    }

    //表中的键值对数量
    public int size()
    {
        return n;
    }

    //最小的键
    public Key min()
    {
        return keys[0];
    }

    //最大的键
    public Key max()
    {
        return keys[n - 1];
    }

    //小于等于key的最大键
    public Key floor(Key key)
    {
        int k=rank(key);
        if(contains(key))
            return keys[k];
        if(k==0) return null;
        return keys[k-1];
    }

    //大于等于key的最小键
    public Key ceiling(Key key)
    {
        return keys[rank(key)];
    }

    //小于key的键的数量
    public int rank(Key key)
    {
        int lo=0;
        int hi=n-1;
        while(lo<=hi)
        {
            int mid=lo+(hi-lo)/2;
            int cmp=keys[mid].compareTo(key);
            if(cmp==0)
                return mid;
            else if(cmp>0)
                hi=mid-1;
            else
                lo=mid+1;
        }
        return lo;
    }

    //排名为k的键
    public Key select(int k)
    {
        return keys[k];
    }

    //删除最小的键
    public void deleteMin()
    {
            delete(keys[0]);
    }

    //删除最大的键
    public void deleteMax()
    {
            delete(keys[n-1]);
    }

    //[lo,hi]之间键的数量
    public int size(Key lo, Key hi)
    {
        return rank(hi) - rank(lo) + 1;
    }

    //[lo,hi]之间的所有键，已排序
    public Iterable<Key> keys(Key lo, Key hi)
    {
        int lindex = rank(lo);
        int hindex = rank(hi);
        ArrayList<Key> al = new ArrayList<>();
        for (int i = lindex; i <= hindex; i++)
            al.add(keys[i]);
        return al;
    }

    //表中的所有键的集合，已排序
    Iterable<Key> keys()
    {
        Key min = keys[0];
        Key max = keys[n - 1];
        return keys(min, max);
    }

    public static void main(String[] args)
    {
        ST st=new ST(50);
        Scanner in=new Scanner(System.in);
        String line=in.nextLine();
        String[] words=line.split(" +");
        System.out.println("数量："+words.length);
        for(String s:words)
            st.put(new Random().nextInt(30),s);

        ArrayList<Integer> al=(ArrayList<Integer>)st.keys();
        for(Integer integer:al)
            System.out.println(""+integer+":"+st.get(integer));

        System.out.println(st.isEmpty());

        st.delete(3);

        System.out.println("是否包含键90:"+st.contains(90));

        System.out.println("floor 5:"+st.floor(5));
        System.out.println("ceiling 8:"+st.ceiling(8));

    }
}


