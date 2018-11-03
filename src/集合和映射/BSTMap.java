package 集合和映射;

import java.util.ArrayList;

/**
 * @Description: 以BST为底层数据结构实现映射
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    private class Node{
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }

    private int size;
    private Node root;

    public BSTMap() {
        size = 0;
        root = null;
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

    //用户调用add方法
    @Override
    public void add(K key,V value){
        root = add(root,key,value);
    }

    //以node为根的二叉搜索树中添加一个元素<key,value>,并在执行完添加操作后返回该根节点node
    private Node add(Node node,K key,V value){
        //基本问题解决方法,边界条件
        if (node == null){
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else {
            node.value = value;
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
    @Override
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
    @Override
    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null?null:node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if (node == null){
            throw new IllegalArgumentException("key 不存在");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Map<String,Integer> bstMap = new BSTMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",arrayList)){
            for (String word:arrayList) {
                if (bstMap.contains(word)){
                    bstMap.set(word,bstMap.get(word)+1);
                }else {
                    bstMap.add(word,1);
                }

            }
        }
        System.out.println("pride 在文中出现的次数为:" + bstMap.get("pride"));
        System.out.println("prejudice 在文中出现的次数为:" + bstMap.get("prejudice"));
    }
}
