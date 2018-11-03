package 二分搜索树;

import 栈和队列.ArrayStack;
import 栈和队列.LoopQueue;
import 栈和队列.Queue;
import 栈和队列.Stack;

import java.util.Random;

public class BST<E extends Comparable<E>> {
    private class Node{
        E e;
        Node left;
        Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //用户调用add方法
    public void add(E e){
        root = add(root,e);
    }

    //以node为根的二叉搜索树中添加一个元素e,并在执行完添加操作后返回该根节点node
    private Node add(Node node,E e){
        //基本问题解决方法,边界条件
        if (node == null){
            size ++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }else if (e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }
        return node;
    }

    //用户调用搜索方法
    public boolean contains(E e){
        return contains(root,e);
    }

    //以node为根的二叉搜索树中搜索一个元素e,存在返回true，不存在返回false
    private boolean contains(Node node,E e){
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
    }

    //用户调用的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    //以node为根的前序遍历
    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //用户调用的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    //以node为根的中序遍历
    private void inOrder(Node node){
        if (node == null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //用户调用的后序遍历
    public void postOrder(){
        postOrder(root);
    }

    //以node为根的后序遍历
    private void postOrder(Node node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);

    }

    //用户调用的前序遍历非递归实现方式
    public void preOrderNR(){
        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    //用户调用的层序遍历非递归实现方式
    public void levelOrder(){
        Queue<Node> queue = new LoopQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node cur = queue.dequeue();
            System.out.println(cur.e);
            if (cur.left != null){
                queue.enqueue(cur.left);
            }
            if (cur.right != null){
                queue.enqueue(cur.right);
            }
        }
    }

    //用户调用寻找最小节点
    public E minimum(){
        if (isEmpty()){
            throw new IllegalArgumentException("dst has been empty");
        }
        return minimum(root).e;
    }

    //递归方式实现寻找最小节点
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    //用户调用寻找最大节点
    public E maximum(){
        if (isEmpty()){
            throw new IllegalArgumentException("dst has been empty");
        }
        return maximum(root).e;
    }

    //递归方式实现寻找最大节点
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    //用户调用删除最小节点并返回
    public E removeMin(){
        E e = minimum();
        root = removeMin(root);
        return e;
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

    //用户调用删除最大节点并返回
    public E removeMax(){
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    //递归方式删除最大节点
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    //用户调用的删除任意元素为e节点
    public void remove(E e){
        root = remove(root,e);
    }

    //递归实现删除任意元素为e，node为根的节点并返回该node
    private Node remove(Node node,E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        generateBSTString(root,0,stringBuilder);
        return stringBuilder.toString();
    }

    //生成二叉查找树的字符串表示形式
    private void generateBSTString(Node node,int depth,StringBuilder stringBuilder){
        if (node == null){
            stringBuilder.append(generateDepthString(depth) + "null\n");
            return;
        }
        stringBuilder.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left,depth+1,stringBuilder);
        generateBSTString(node.right,depth+1,stringBuilder);
    }

    //生成节点深度的字符串，用--表示
    private String generateDepthString(int depth){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("--");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        /*int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = new Random().nextInt(1000);
        }*/
        int[] arr = {5,3,6,2,4,8};
        for (int i:arr) {
            bst.add(i);
        }
        System.out.println("前序遍历结果：");
        bst.preOrder();
        System.out.println("非递归方式的前序遍历结果:");
        bst.preOrderNR();
        System.out.println("中序遍历结果:");
        bst.inOrder();
        System.out.println("后序遍历结果:");
        bst.postOrder();
        System.out.println("层序遍历结果：");
        bst.levelOrder();
        System.out.println("bst中的最小元素：" + bst.minimum());
        System.out.println("bst中的最大元素：" + bst.maximum());
        /*System.out.println("删除的最小元素为：" + bst.removeMin());
        System.out.println("删除最小元素的结果为：");
        System.out.println(bst);
        System.out.println("删除的最大元素为：" + bst.removeMax());
        System.out.println("删除最大元素的结果为：");
        System.out.println(bst);*/
        System.out.println("删除元素为3的节点结果是：");
        bst.remove(3);
        System.out.println(bst);


    }
}
