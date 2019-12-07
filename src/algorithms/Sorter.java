package algorithms;

//排序抽象父类
public  abstract class Sorter
{
    public abstract  void sort(Comparable[] a);

    //比较两个对象大小
   public static boolean less(Comparable m,Comparable y)
   {
       return m.compareTo(y)<0;
   }

   //交换两个对象的位置
    public static void swap(int i,int j,Comparable[] a)
    {
        Comparable temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    //在单行打印数组
    public static void printArrs(Comparable[]a )
    {
        for(Comparable c:a)
            System.out.print(c+" ");
        System.out.println();
    }

    //判断数组是否有序
    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 0; i < a.length-1; i++)
            if(less(a[i+1],a[i]))
                return false;
            return true;
    }
}
