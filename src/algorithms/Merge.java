package algorithms;

import java.util.Scanner;

//归并排序
public class Merge extends Sorter
{

    private static Comparable[] aux;

    @Override
    public void sort(Comparable[] a)
    {
        if (isSorted(a))
            return;
        if (a.length <= 15)
        {
            new Insertion().sort(a);
            return;
        }
        System.out.println("das");
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi)
    {
        if (lo >= hi) return;

        int mid = (hi - lo) / 2 + lo;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public void merge(Comparable[] a, int lo, int mid, int hi)
    {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        for (int k = lo; k <= hi; k++)
        {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line = "";
        while (in.hasNextLine())
        {
            String temp=in.nextLine();
            line=line+temp;
        }
        String[] strs = line.split(" +");
        long start = System.currentTimeMillis();
        new Merge().sort(strs);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(isSorted(strs));
        printArrs(strs);
    }
}
