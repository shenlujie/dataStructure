package 栈和队列;
/**
*@Description: 优化过的循环队列，dequeue操作的时间复杂度为O（1）
*@create: 2018/11/1
*@Author: SLJ
*/
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }
    public LoopQueue(){
        this(10);
    }

    @Override
    public E getFront() {
        return data[0];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public void enqueue(E e) {
        if ((tail+1)%data.length == front){
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue has been empty");
        }
        E e = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if (size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return e;
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("loopQueue的容量为：%s，元素个数为%s\n",getCapacity(),size));
        stringBuilder.append("Queue front [");
        for (int i = front; i != tail; i = (i+1)%data.length) {
            stringBuilder.append(data[i]);
            if ((i+1)%data.length != tail){
                stringBuilder.append(" ,");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> arrayQueue = new LoopQueue<>();
        for (int i = 0; i < 12; i++) {
            arrayQueue.enqueue(i);
            System.out.println(arrayQueue);
        }
        arrayQueue.dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);
        arrayQueue. dequeue();
        System.out.println(arrayQueue);


    }
}
