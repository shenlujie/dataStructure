package 优先队列和堆;

import 动态数组.Array;

import java.util.Random;

/**
 * @Description: 使用动态数组为底层数据结构来实现最大堆
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }
    public MaxHeap(){
        data = new Array<>();
    }

    //进行heapify操作的构造函数
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(arr.length-1); i >= 0 ; i--) {
            siftDown(i);
        }
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index cannot be 0");
        }
        return (index-1)/2;
    }

    private int leftChild(int index){
        return (index*2)+1;
    }

    private int rightChild(int index){
        return (index*2)+2;
    }

    //用户调用的添加元素的操纵
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int index){
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0){
            data.swap(parent(index),index);
            index = parent(index);
        }
    }

    //找出堆中最大元素并返回
    public E findMax(){
        if (data.getSize() == 0){
            throw new IllegalArgumentException("cannot find 最大值 when data is empty");
        }
        return data.get(0);
    }

    //用户调用去除最大元素
    public E extractMax(){
        E e = findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);

        return e;
    }

    private void siftDown(int index){

        while (leftChild(index) < data.getSize()){
            int j = leftChild(index);
            if (j + 1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) > 0){
                j = rightChild(index);//把左子树和右子树中最大的值都赋给左子树，这样左子树的索引值代表的就是两个子树中最大值的索引
            }
            if (data.get(j).compareTo(data.get(index)) <= 0){//当前节点的值比两个子树的值都大的时候就已经完成了，跳出循环
                break;
            }
            data.swap(index,j);
            index = j;
        }
    }

    //去除最大元素并替换成新的元素
    public E replace(E e){
        E max = findMax();
        data.set(0,e);
        siftDown(0);
        return max;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        int n = 10000;
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i-1] < arr[i]){
                throw new IllegalArgumentException("MaxHeapTest failed");
            }
        }
        System.out.println("done");
    }


}
