package algorithms;

import java.util.Iterator;
import java.util.Scanner;

/**
 * 支持迭代的，用数组实现的一个容量可调整的栈
 * @param <Item>
 */
public class FixedCapacityStack<Item> implements Iterable<Item>
{
    private Item[] s;
    private int n;

    public FixedCapacityStack()
    {
        this(5);
    }

    public FixedCapacityStack(int capacity)
    {
        s = (Item[]) new Object[capacity];
        n = 0;
    }

    //当栈中元素的数量过大或过小时，调用该方法调整数组大小
    private void resize(int max)
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < s.length; i++)
            temp[i] = s[i];
        s = temp;
    }

    public void push(Item item)
    {
        if (n == s.length)
        {
            resize(2 * s.length);
        }
        s[n] = item;
        n++;
    }

    public Item pop()
    {
        if (isEmpty())
        {
            System.out.println("栈为空！");
            return null;
        }
        else
        {
            Item item=s[--n];
            s[n]=null;
            if(n>0&&n<=0.25*s.length)
                resize(s.length/2);
            return item;
        }
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public int size()
    {
        return n;
    }



    public static void main(String[] args)
    {
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(10);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equalsIgnoreCase("quit"))
        {
            if (!line.equals("-"))
            {
                stack.push(line);
            }
            else
            {
                System.out.println(stack.pop() + " ");
            }
            line = in.nextLine();
        }
        for(String item:stack)
            System.out.println(item);
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>
    {

        private int i=n;
        @Override
        public boolean hasNext()
        {
            return i>0;
        }

        @Override
        public Item next()
        {
            return s[--i];
        }
    }
}
