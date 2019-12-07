package algolithms;

import java.util.Scanner;

//快速排序（包含普通快速排序和三向切分快速排序）

public class Quick extends Sorter
{
    @Override
    public void sort(Comparable[]a)
    {
        sort(a,0,a.length-1);
    }
    public void sort(Comparable[] a, int lo, int hi)
    {
        if(hi<=lo) return;
        if(isSorted(a)) return;
        int mid=partition(a,lo,hi);
        sort(a,lo,mid-1);
        sort(a,mid+1,hi);
    }

    //用三向切分的快速排序
    public static void sort3Way(Comparable[]a, int lo, int hi)
    {
        if(hi<=lo) return;
        int i=lo,lt=lo,gt=hi;
        int rint=randInt(lo,hi);
        swap(lo,rint,a);
        Comparable v=a[lo];
        while(i<=gt)
        {
            if(less(a[i],v))
            {
                swap(lt,i,a);
                i++;
                lt++;
            }
            else if(less(v,a[i]))
            {
                swap(i,gt,a);
                gt--;
            }
            else i++;
        }

        sort3Way(a,lo,lt-1);
        sort3Way(a,gt+1,hi);
    }

    //切分点，每次能确定一个点的正确位置
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i=lo,j=hi+1;
        int rint=randInt(lo,hi);
        swap(rint,lo,a);
        Comparable temp=a[lo];
        while(true)
        {
            while(less(a[++i],temp)) if(i==hi) break;
            while(less(temp,a[--j])) if(j==lo) break;
            if(i>=j) break;
            swap(i,j,a);
        }
        swap(lo,j,a);
        return j;
    }



    //返回一个在区间[lo,hi]的一个整数
    private static int randInt(int lo, int hi)
    {
            return (int)(Math.random()*(hi-lo+1))+lo;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line="";
        while (in.hasNextLine())
        {
           String temp= in.nextLine();
           line=line+temp;
        }
        String[] strs = line.split(" +");
        long start = System.currentTimeMillis();
        Quick.sort3Way(strs,0,strs.length-1);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(isSorted(strs));
        printArrs(strs);
    }
}
