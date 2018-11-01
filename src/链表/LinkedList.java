package 链表;

/**
*@Description: 自定义实现链表
*@create: 2018/11/1
*@Author: SLJ
*/
public class LinkedList<E> {

    //链表的节点类
    private class Node{
        public E e;
        public Node next;

        Node(E e,Node next){
            this.e = e;
            this.next = next;
        }
        Node(E e){
            this(e,null);
        }
        Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;//头节点
    private int size;//元素个数

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //链表指定位置添加元素e
    public void add(int index,E e){

        if (index < 0 || index > size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<size");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
        size ++;
    }

    //链表头部添加元素e
    public void addFirst(E e){
        add(0,e);
    }


    //链表尾部添加元素e
    public void addLast(E e){
        add(size,e);
    }

}
