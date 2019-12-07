package algolithms;

import java.util.Scanner;

//希尔排序
public class Shell extends Sorter
{

    @Override
    public void sort(Comparable[] a)
    {
        int n=a.length;
        int h=1;        //先取最小的h
        while(h<n/3) h=h*3+1;       //确保每个子数组的长度不小于3，并且得到满足条件的1/2*(3^k-1)序列中的最大值
        while(h>=1)
        {
            for (int i = h; i < n; i=i+h)
            {
                Comparable temp=a[i];
                int j=0;
                for ( j = i-h; j >=0&&less(temp,a[j]); j=j-h)
                    a[j+h]=a[j];
                a[j+h]=temp;
            }
            h=h/3;
        }
    }

    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        String line=in.nextLine();
        String[] strs=line.split(" +");
        long start=System.currentTimeMillis();
        new Shell().sort(strs);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(isSorted(strs));
        printArrs(strs);
        System.out.println(strs.length);
    }
}
