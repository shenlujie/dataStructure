package 二分搜索树;

import 栈和队列.ArrayStack;
import 栈和队列.Stack;

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
        int[] arr = {5,3,6,8,4,2};
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
    }
}
