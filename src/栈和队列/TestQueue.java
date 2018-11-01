package 栈和队列;

import java.util.Random;

/**
*@Description: 测试ArrayQueue和LoopQueue的性能差距
*@create: 2018/11/1
*@Author: SLJ
*/
public class TestQueue {

    public static double countTime(Queue<Integer> queue,int count){
        double startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            queue.dequeue();
        }
        double endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int count = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("arrayQueue用时： " + countTime(arrayQueue,count) + " s");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("loopQueue用时： " + countTime(loopQueue,count) + " s");
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        System.out.println("linkedListQueue用时： " + countTime(linkedListQueue,count) + " s");

    }
}
