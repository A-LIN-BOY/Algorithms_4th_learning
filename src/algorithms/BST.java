package algorithms;

import java.util.LinkedList;
import java.util.Queue;

//二叉查找树
public class BST<Key extends Comparable<Key>, Value>
{
    private Node root; //二叉查找树的根节点

    private class Node
    {
        private Key key;    //键
        private Value value;    //值
        private Node left, right;   //左子节点、右子节点
        private int N;  //以该结点为根的树中的子结点总数
        public Node (Key key,Value value, int N)
        {
            this.key=key;
            this.value=value;
            this.N=N;
        }
    }

    //返回树的结点数
    public int size()
    {return size(root);}

    //返回以x为树根的树的结点数目
    private int size(Node x)
    {
        if(x==null) return 0;
        else return x.N;
    }

    //get
    public Value get(Key key)
    {
        return get(root,key);
    }

    public Value get(Node x, Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0) return get(x.left,key);
        else if (cmp>0)return get(x.right,key);
        else return x.value;
    }

    //put
    public void put(Key key,Value value)
    {
        root=put(root,key,value);
    }

    //在以x为树根的树中插入结点,并返回该树根x,更新该树的结点数
    private Node put(Node x,Key key,Value value)
    {
        if(x==null) return new Node(key,value,1);
        int cmp=key.compareTo(x.key);
        if(cmp<0)
            x.left=put(x.left,key,value);
        else if(cmp>0)
            x.right=put(x.right,key,value);
        else
            x.value=value;
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    //返回整棵树的最大键
    public Key max()
    {
        return max(root).key;
    }

    //返回以结点x为树根的树的最大键的结点
    private Node max(Node x)
    {
        if(x==null) return null;
        if(x.right==null) return x;
        else return max(x.right);
    }

    //返回整棵树的最小键
    public Key min()
    {
        return min(root).key;
    }

    private Node min(Node x)
    {
        if(x==null) return null;
        if(x.left==null) return x;
        else return min(x.left);
    }

    //下取整
    public Key floor(Key key)
    {
        Node node=floor(root,key);
        if(node==null) return null;
        return node.key;
    }

    private Node floor(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp>0)
        {
            Node temp=floor(x.right,key);
            if(temp==null) return x;
            else return temp;
        }
        else if(cmp<0) return floor(x.left,key);
        else return x;
    }

    //上取整
    public Key ceiling(Key key)
    {
        Node node=ceiling(root,key);
        if(node==null) return null;
        return node.key;
    }

    private Node ceiling(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0) return x;
        if(cmp>0) return ceiling(x.right,key);
        Node temp=ceiling(x.left,key);
        if(temp==null) return x;
        return temp;
    }

    //小于key的键的数量
    public int rank(Key key)
    {
        return rank(root,key);
    }

    private int rank(Node x,Key key)
    {
        if(x==null) return 0;
        int cmp=key.compareTo(x.key);
        if(cmp>0) return size(x.left)+1+rank(x.right,key);
        else if(cmp<0) return rank(x.left,key);
        else return size(x.left);
    }

    //排名为k的键
    public Key select(int k)
    {
        Node node=select(root,k);
        return node==null?null:node.key;
    }

    private Node select(Node x,int k)
    {
        if(x==null) return null;
        int num=size(x.left);
        if(k>num) return select(x.right,k-num-1);
        else if(k<num) return select(x.left,k);
        else return x;
    }


    //删除操作
    public void delete(Key key)
    {
        root=delete(root,key);
    }

    private Node delete(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0) x.left=delete(x.left,key);
        else if(cmp>0) x.right=delete(x.right,key);
        else
        {
            if(x.right==null) return x.left;
            if(x.left==null) return x.right;
            Node back=min(x.right);
            back.right=deleteMin(x.right);
            back.left=x.left;
            back.N=size(back.right)+size(back.left)+1;
            return back;
        }
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    //删除最小的键
    public void deleteMin()
    {
        root=deleteMin(root);
    }

    private Node deleteMin(Node x)
    {
        if(x.left==null) return x.right;
        x.left=deleteMin(x.left);
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    //删除最大的键
    public void deleteMax()
    {
        root=deleteMax(root);
    }

    /*
    一直往右子树走，遇到一个结点的右链接为空时，将其左链接返回
     */
    private Node deleteMax(Node x)
    {
        if(x.right==null) return x.left;
        x.right=deleteMax(x.right);
        x.N=size(x.right)+size(x.left)+1;
        return x;
    }

    //表中所有键的集合，已排序
    public Iterable<Key> keys()
    {
        Queue<Key> queue=new LinkedList<>();
        return keys(root,queue,min(),max());
    }

    private Iterable<Key> keys(Node x, Queue<Key> queue,Key lo,Key hi)
    {
        if(x==null) return null;
        int cmplo=lo.compareTo(x.key);
        int cmohi=hi.compareTo(x.key);
        keys(x.left,queue,lo,hi);
        if(cmplo<=0&&cmohi>=0) queue.add(x.key);
        keys(x.right,queue,lo,hi);
        return queue;
    }

    public void printBST()
    {
        printBST(root);
    }

    private void printBST(Node x)
    {
        if(x==null) return;
        printBST(x.left);
        System.out.println(x.key+":"+x.value);
        printBST(x.right);
    }

    public static void main(String[] args)
    {
        BST bst=new BST();
        bst.put(1,"张三");
        bst.put(2,"李四");
        bst.put(3,"赵五");
        bst.put(4,"刘六");

        bst.printBST();

        System.out.println(bst.get(4));
        bst.delete(4);

        bst.printBST();
        bst.deleteMax();
        bst.printBST();

        Iterable<Integer> keys=bst.keys();
        for(Integer key:keys)
            System.out.println(bst.get(key));
    }
}
