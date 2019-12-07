package algolithms;

import java.util.Scanner;

/**
 * 用链表实现的队列
 */
public class LinkQueue<Item>
{
    //队头
    private Node head;
    //队尾
    private Node tail;
    //队列容量
    private int size;

    //内部队列结点类
    private class Node
    {
        private Item item;
        private Node next;
    }
    //方法区

    //判断对列是否为空
    public boolean isEmpty()
    {
        return head==null;
    }
    //获取队列长度
public int getSize()
{
    return size;
}
    //入队
public void enQueue(Item item)
{
    Node node=new Node();
    node.item=item;
    node.next=null;
    size++;
    if(isEmpty()) head=tail=node;
    else
    {
        tail.next=node;
        tail=node;
    }
}
    //出队
    public Item deQueue()
    {
        if(isEmpty())
        {
            System.out.println("空队列！");
            return null;
        }
        size--;
        Node temp=head;
        head=head.next;
        if(head==null) tail=null; //如果出队后为空队列，则tail应为null
        return temp.item;
    }

    //main测试方法
    public static void main(String[] args)
    {
        LinkQueue<String> queue=new LinkQueue<>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equalsIgnoreCase("quit"))
        {
            if (!line.equals("-"))
            {
                queue.enQueue(line);
            }
            else
            {
                System.out.println(queue.deQueue() + " ");
            }
            line = in.nextLine();
        }
        System.out.println("队中还有："+queue.size+"个元素");
    }
}
