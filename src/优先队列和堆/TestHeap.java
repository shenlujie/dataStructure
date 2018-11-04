package 优先队列和堆;

import java.util.Random;

/**
 * @Description: 测试是否使用heapify来初始化heap的效率差距（代码有一定bug）
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class TestHeap {

    public static double countTime(Integer[] arr,boolean isHeapity){

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapity){
            maxHeap = new MaxHeap<>(arr);
        }else {
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < arr.length; i++) {
                maxHeap.add(arr[i]);
            }
        }
        /*int[] b = new int[arr.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < b.length; i++) {
            if (b[i-1] < b[i]){
                throw new IllegalArgumentException("MaxHeapTest failed");
            }
        }*/
        System.out.println("done");

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int n = 10000000;
        Integer[] integers = new Integer[n];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = countTime(integers,true);
        double time2 = countTime(integers,false);
        System.out.println("使用heapity的耗时为： " + time1 + " s");
        System.out.println("不使用heapity的耗时为： " + time2 + " s");
    }
}
