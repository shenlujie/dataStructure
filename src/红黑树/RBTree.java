package 红黑树;

import 集合和映射.FileOperation;
import java.util.ArrayList;

/**
 * @Description: 以BST为底层数据结构实现红黑树
 *
 * 总结：
 * 1、对于完全随机的数据，普通的二分搜索树很好用（极端情况下会退化成链表，性能变为O（n））
 * 2、对于查询较多的操作，AVL很好用
 * 3、红黑树牺牲了平衡性，最高可为2log(n)，但是统计性能是最好的（综合增删查改所有的操作）
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class RBTree<K extends Comparable<K>,V>{

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }

    private int size;
    private Node root;

    public RBTree() {
        size = 0;
        root = null;
    }


    //判断该节点是否为红色节点
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //获取以node为根，key所在的节点
    private Node getNode(Node node,K key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else if (key.compareTo(node.key) > 0){
            return getNode(node.right,key);
        }else {
            return node;
        }
    }

    //当添加的节点大于当前节点值，需执行红黑树的左旋操作,返回执行完之后的头节点
    //          node                         x
    //         /    \                      /   \
    //        T1     x       ----->      node    T3
    //             /   \                /   \
    //            T2    T3             T1    T2
    private Node leftRotate(Node node){
        Node x = node.right;
        //左旋
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    //node为黑，x为红，y为红，需执行红黑树的右旋操作,返回执行完之后的头节点
    //          node                         x
    //         /    \                      /   \
    //        x     T2       ----->      y     node
    //      /   \                              /   \
    //     y    T1                            T1    T2
    private Node rightRotate(Node node){
        Node x = node.left;
        //右旋
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    //颜色反转
    //      node(黑)               node(红)
    //     /     \    ----->      /     \
    //   红       红             黑      黑
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //用户调用add方法
    public void add(K key,V value){
        root = add(root,key,value);
        root.color = BLACK;//最终根节点为黑色
    }

    //以node为根的红黑树中添加一个元素<key,value>,并在执行完添加操作后返回该根节点node
    private Node add(Node node,K key,V value){
        //基本问题解决方法,边界条件
        if (node == null){
            size ++;
            return new Node(key, value);//默认为红色
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else {
            node.value = value;
        }

        //红黑树添加元素的维护操作(维持黑平衡)
        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
    }


    //递归方式实现寻找最小节点
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    //递归方式删除最小节点
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    //用户调用的删除任意元素为e节点
    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;

    }

    //递归实现删除任意元素为e，node为根的节点并返回该node
    private Node remove(Node node,K key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            return node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            return node;
        }else {//e和node.e相等
            //删除左节点为空的节点
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            //删除右节点为空的节点
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            //删除左右节点都不为空的节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            size ++;
            successor.left = node.left;
            node.left = null;
            node.right = null;
            size --;
            return successor;

        }
    }

    //用户调用搜索方法
    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    public V get(K key) {
        Node node = getNode(root,key);
        return node == null?null:node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if (node == null){
            throw new IllegalArgumentException("key 不存在");
        }
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        RBTree<String,Integer> rbTree = new RBTree<>();
        ArrayList<String> arrayList = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",arrayList)){
            for (String word:arrayList) {
                if (rbTree.contains(word)){
                    rbTree.set(word,rbTree.get(word)+1);
                }else {
                    rbTree.add(word,1);
                }

            }
        }
        System.out.println("pride 在文中出现的次数为:" + rbTree.get("pride"));
        System.out.println("prejudice 在文中出现的次数为:" + rbTree.get("prejudice"));
    }
}