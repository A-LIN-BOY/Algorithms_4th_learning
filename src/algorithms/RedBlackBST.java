package algorithms;

//2-3结点红黑树


/**
 * 该红黑树的定义：
 * 1.红链接均为左链接
 * 2.没有任何一个结点同时和两条红链接相连
 * 3.该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑链接数量相同
 */
public class RedBlackBST<Key extends Comparable<Key> ,Value>
{
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private Node root;
    private class Node
    {
        private Key key;    //键
        private Value value;    //值
        private Node left,right; //左右子结点
        int N;  //该子树结点数目
        boolean color;  //由其父结点指向该结点的链接的颜色

        Node(Key key,Value value,int N,boolean color)
        {
            this.key=key;
            this.value=value;
            this.N=N;
            this.color=color;
        }
    }

    //判断结点的是否是红色
    private boolean isRed(Node x)
    {
        if(x==null) return false;
        return x.color= RED;
    }

    //左旋转
    private Node rotateLeft(Node h)
    {
        Node x=h.right;
        h.right=x.left;
        x.left=h;
        x.color=h.color;
        h.color= RED;
        x.N=h.N;
        h.N=1+size(h.right)+size(h.left);
        return x;
    }

    //右旋转
    private Node rotateRight(Node h)
    {
        Node x=h.left;
        h.left=x.right;
        x.left=h;
        x.color=h.color;
        h.color=RED;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        return x;
    }

    //转换颜色
    private void flipColors(Node h)
    {
        h.color=RED;
        h.right.color=BLACK;
        h.left.color=BLACK;
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

    //插入结点
    public void put(Key key,Value value)
    {
        root=put(root,key,value);
        root.color=BLACK;
    }

    private Node put(Node x, Key key, Value value)
    {
        if(x==null) return new Node(key,value,1,RED);

        int cmp=key.compareTo(x.key);
        if(cmp<0) x.left=put(x.left,key,value);
        else if(cmp>0) x.right=put(x.right,key,value);
        else x.value=value;

        if(isRed(x.right)&&(!isRed(x.left))) x=rotateLeft(x);
        if(isRed(x.left)&&isRed(x.left.left)) x=rotateRight(x);
        if(isRed(x.left)&&isRed(x.right)) flipColors(x);
        x.N=1+size(x.left)+size(x.right);
        return x;
    }
}
