package Trie;

import 集合和映射.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @Description: 以Java自带的TreeMap为底层数据结构来实现字典树Tire
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class Trie {
    private class Node{
        boolean isWord;
        TreeMap<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private int size;
    private Node root;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }

    public static void main(String[] args) {

        Trie trie = new Trie();
        long startTime = System.nanoTime();

        ArrayList<String> list = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",list)){
            System.out.println("傲慢与偏见的字数共为： " + list.size());
            for (int i = 0; i < list.size(); i++) {
                trie.add(list.get(i));
            }
            System.out.println("其中不重复的字数共为： " + trie.getSize());
        }

        long endTime = System.nanoTime();
        System.out.println("执行以上代码共耗时: " + (endTime-startTime)/1000000000.0 + " s");
    }
}
