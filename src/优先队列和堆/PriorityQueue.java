package 优先队列和堆;

import 栈和队列.Queue;

/**
 * @Description: 使用最大堆为底层数据结构来实现优先队列
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private  MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
