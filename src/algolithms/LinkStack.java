package algolithms;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 用链表实现的一个栈
 * @param <Item>
 */
public class LinkStack<Item> implements Iterable<Item>
{
    private Node first; //栈顶
    private int N; //元素数量

    private class Node
    {   //定义了结点的内部类
        Item item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first==null;
    }

    public int size()
    {
        return N;
    }

    public void push(Item item)
    {
        Node temp=first;
        first=new Node();
        first.item=item;
        first.next=temp;
        N++;
    }

    public Item pop()
    {   //从栈顶删除元素
        if(isEmpty())
        {
            System.out.println("栈为空！");
            return null;
        }
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return null;
    }

    public static void main(String[] args)
    {
        LinkStack<String>stack=new LinkStack<>();
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
        System.out.println("剩余元素："+stack.size());
    }
}
