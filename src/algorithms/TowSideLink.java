package algorithms;

//1.3.31
//一个双向链表的数据结构
public class TowSideLink<Item>
{
    //结点类
    private class DoubleNode
    {
        Item item;
        DoubleNode pre;
        DoubleNode next;
    }

    private DoubleNode head;
    private DoubleNode tail;
    private int size;


    public boolean isEmpty()
    {
        return head == null;
    }

    public int size()
    {
        return size;
    }

    //表头插入结点
    public void insertAtHead(Item item)
    {
        size++;
        DoubleNode node = new DoubleNode();
        node.item = item;
        node.pre = null;
        if (head == null)
        {
            node.next = null;
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head.pre = node;
        head = node;

    }

    //在表尾插入结点
    public void insertAtTail(Item item)
    {
        size++;
        DoubleNode node = new DoubleNode();
        node.item = item;
        node.next = null;
        if (head == null)
        {
            head = node;
            node.pre = null;
        }
        else
        {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
    }

    //从表头删除
    public Item deleteAtHead()
    {
        if (isEmpty())
        {
            System.out.println("链表为空！");
            return null;
        }
        size--;
        DoubleNode temp = head;
        head = head.next;
        if (head == null)
        {
            tail = null;
        }
        else head.pre = null;
        return temp.item;
    }

    //从表尾删除
    public Item deleteAtTail()
    {
        if (isEmpty())
        {
            System.out.println("链表为空！");
            return null;
        }
        size--;
        DoubleNode temp = tail;
        tail = tail.pre;
        if (tail == null)
        {
            head = null;
        }
        else         tail.next = null;

        return temp.item;
    }

    //找出链表中第id个结点
    public DoubleNode findAt(int id)
    {
        DoubleNode node = null;
        if (id <= 0 || id > size)
        {
            System.out.println("id过大或过小");
        }
        else
        {
            int i = 1;
            for (node = head; i < id; i++)
            {
                node = node.next;
            }
        }
        return node;
    }

    //从指定结点之后插入新结点
    public void insertAfter(int id, Item item)
    {
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;

        DoubleNode node = findAt(id);
        if (node == null)
        {
            return;
        }

        size++;
        DoubleNode temp = node.next;
        node.next = newNode;
        newNode.pre = node;
        newNode.next = temp;
        if (temp == null)
        {
            tail = newNode;//如果temp结点为null，也就是node为链尾，插入后则新结点尾
        }
    }

    //在指定结点之前插入新结点
    public void insertBefore(int id, Item item)
    {
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;

        DoubleNode node = findAt(id);
        if (node == null)
        {
            return;
        }

        size++;
        DoubleNode temp = node.pre;
        node.pre = newNode;
        newNode.next = node;
        newNode.pre = temp;
        if (temp == null)
        {
            head = newNode;
        }
    }

    //删除指定结点
    public Item deleteAt(int id)
    {
        DoubleNode node = findAt(id);
        if (node == null)
        {
            return null;
        }

        if(node==head)
            return deleteAtHead();
        else if(node==tail)
            return deleteAtTail();
        else
        {
            node.pre.next=node.next;
            node.next.pre=node.pre;
            return node.item;
        }
    }

    //打印链表的内容
    public void printlink(TowSideLink link)
    {
        DoubleNode temp=link.head;
        while(temp!=null)
        {
            System.out.println(temp.item);
            temp=temp.next;
        }
    }

    public static void main(String[] args)
    {
        TowSideLink<String > link=new TowSideLink();
        link.insertAtHead("张三");
        link.insertAtTail("李四");
        link.insertAfter(0,"王五");
        link.insertBefore(1,"赵六");
        System.out.println("链表中还有的元素:"+link.size());
        link.printlink(link);
        link.deleteAtTail();
        link.deleteAtHead();
        link.deleteAt(1);
        System.out.println("链表中还有："+link.size);
        link.printlink(link);
        link.deleteAtHead();
        link.deleteAtTail();
    }
}
