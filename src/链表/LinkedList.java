package 链表;

/**
*@Description: 自定义实现链表,当前链表的增删查改的时间复杂度都是O（n），
 * 所以一般来说链表都是用来只对链表头进行增删查操作，为O（1），而没有修改操作
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

    //链表中查找元素
    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<=size");
        }
        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        return prev.e;
    }

    //判断链表中是否包含此元素
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //修改链表index对应的元素
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<=size");
        }
        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.e = e;
    }

    //删除链表相应位置的元素
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<=size");
        }
        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node delNode = cur.next;
        cur.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.e;
    }

    //删除链表中第一个元素
    public E removeFirst(){
        return remove(0);
    }

    //删除链表中最后一个元素
    public E removeLast(){
        return remove(size-1);
    }

    //从链表中删除元素e
    public void removeElement(E e){
        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        Node prev = dummyHead.next;
        while (prev != null){
            stringBuilder.append(prev + " -->");
            prev = prev.next;
        }
        stringBuilder.append("NULL");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(3,666);
        System.out.println(linkedList);
        linkedList.remove(3);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
