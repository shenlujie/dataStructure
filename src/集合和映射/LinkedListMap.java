package 集合和映射;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 以链表为底层数据结构来实现映射
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        public Node(){
            key = null;
            value = null;
            next = null;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }

    private int size;
    private Node dummyHead;

    public LinkedListMap() {
        size = 0;
        dummyHead = new Node();
    }

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size ++;
        }else {
            //碰撞的时候更新value
            getNode(key).value = value;
        }


    }

    @Override
    public V remove(K key) {

        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null?null:node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
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
        Map<String,Integer> map = new LinkedListMap<>();
        ArrayList<String> list = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",list)){
            for (String word:list) {
                if (map.contains(word)){
                    map.set(word,map.get(word)+1);
                }else {
                    map.add(word,1);
                }

            }
        }
        System.out.println("pride 在文中出现的次数为:" + map.get("pride"));
        System.out.println("prejudice 在文中出现的次数为:" + map.get("prejudice"));
    }
}
