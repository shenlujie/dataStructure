package AVL;

import 集合和映射.FileOperation;

import java.util.ArrayList;

/**
 * @Description:
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class AVLTree<K extends Comparable<K>,V> {

    private class Node{
        K key;
        V value;
        Node left;
        Node right;
        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }

    private int size;
    private Node root;

    public AVLTree() {
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

    //获取该节点的height
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    //获取该节点的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断该树是否是二分搜索树
    public boolean isBST(){
        ArrayList<K> list = new ArrayList<>();
        inOrder(root,list);
        if (list.size() == 0){
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i-1)) < 0){
                return false;
            }
        }
        return true;
    }

    //中序遍历结果存入list，list中就是顺序的结果
    private void inOrder(Node node,ArrayList<K> list){
        if (node == null){
            return;
        }
        inOrder(node.left,list);
        list.add(node.key);
        inOrder(node.right,list);
    }

    //右旋转变为使y节点变为平衡的(LL)
    //              y                                         x
    //            /   \                                    /      \
    //           x     T4                                z           y
    //          /  \             -------->             /   \       /   \
    //         z    T3                                T1    T2    T3     T4
    //        /  \
    //       T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //开始旋转
        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    //左旋转变为使y节点变为平衡的(RR)
    //              y                                         x
    //            /   \                                    /      \
    //           T4     x                                y           z
    //                /  \       -------->             /   \       /   \
    //             T3     z                           T4    T3    T2     T1
    //                  /  \
    //                 T2   T1
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T3 = x.left;
        //开始旋转
        x.left = y;
        y.right = T3;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    //判断是否为平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    //递归调用判断该节点是否是平衡的（满足平衡因子绝对值大于一）
    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //用户调用add方法
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
        //更新height
        node.height = Math.max(getHeight(node.left),getHeight(node.right))+1;
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1){
//            System.out.println(balanceFactor);
//        }

        //平衡维护(四种情况)
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
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

        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            retNode = node;
        }else {//e和node.e相等
            //删除左节点为空的节点
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }
            //删除右节点为空的节点
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }
            //删除左右节点都不为空的节点
            else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right,successor.key);
                size ++;
                successor.left = node.left;
                node.left = null;
                node.right = null;
                size --;
                retNode = successor;
            }

        }

        if (retNode == null){
            return null;
        }

        //更新height
        retNode.height = Math.max(getHeight(retNode.left),getHeight(retNode.right))+1;
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        //平衡维护(四种情况)
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
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
        AVLTree<String,Integer> bstMap = new AVLTree<>();
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
        System.out.println("是否为二分搜索树：" + bstMap.isBST());
        System.out.println("是否为平衡二叉树：" + bstMap.isBalanced());

        for (String word:arrayList) {
            bstMap.remove(word);
            if (!bstMap.isBST() || !bstMap.isBalanced()){
                System.out.println("error");
            }
        }
    }
}

