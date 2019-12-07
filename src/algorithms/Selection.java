package algorithms;


import java.util.Scanner;

//选择排序
public class Selection extends Sorter
{
    public  void sort(Comparable[] a)
    {
        if (a == null || a.length == 0)
        {
            System.out.println("空数组");
            return;
        }
        for (int i = 0; i < a.length-1; i++)
        {
            int min=i;
            for(int j=i+1;j<a.length;j++)
                if(less(a[j],a[min]))
                    min=j;
            swap(i,min,a);
        }
    }

    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        String line=in.nextLine();
        String[] strs=line.split(" ");
        long start=System.currentTimeMillis();
        new Selection().sort(strs);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(isSorted(strs));
        printArrs(strs);
    }
}
