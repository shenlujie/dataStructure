package 栈和队列;

public class LinkedListQueue<E> implements Queue<E>{

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

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null){
            tail = new Node(e);
            head = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue has empty");
        }
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if (head == null){
            tail = null;
        }
        size --;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue has empty");
        }
        return tail.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("loopQueue的元素个数为%s\n",size));
        stringBuilder.append("Queue front [");
        Node prev = head;
        while (prev != null){
            stringBuilder.append(prev + " -->");
            prev = prev.next;
        }
        stringBuilder.append("NULL] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        for (int i = 0; i < 12; i++) {
            linkedListQueue.enqueue(i);
            System.out.println(linkedListQueue);
        }
        linkedListQueue.dequeue();
        System.out.println(linkedListQueue);
        linkedListQueue. dequeue();
        System.out.println(linkedListQueue);
    }
}
