package algorithms;

import java.util.Scanner;

public class Insertion extends Sorter
{
    //插入排序
    public void sort(Comparable[]a)
    {
        for (int i = 0; i < a.length; i++)
        {
            Comparable temp=a[i];
            int j=0;
            for (j = i-1; j>=0&&less(temp,a[j]); j--)
                a[j+1]=a[j];
            a[j+1]=temp;
        }
    }

    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        String line=in.nextLine();
        String[] strs=line.split(" ");
        long start=System.currentTimeMillis();
        new Insertion().sort(strs);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(isSorted(strs));
        printArrs(strs);
    }
}
