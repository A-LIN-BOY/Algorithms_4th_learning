package algolithms;

import java.util.Iterator;

//背包
public class Bag<Item> implements Iterable<Item>
{
    private Node first;

    private class Node
    {
        Item item;
        Node next;
    }

    public void add(Item item)
    {
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current=first;
        public boolean hasNext()
        {return current!=null;}

        public void remove()
        {}

        public Item next()
        {
            Item item=current.item;
            current=current.next;
            return item;
        }
    }
    @Override
    public Iterator<Item> iterator()
    { return new ListIterator(); }
}
