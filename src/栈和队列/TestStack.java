package 栈和队列;

import java.util.Random;

/**
 *@Description: 测试ArrayStack和LinkedListStack的性能差距
 * 100000数量级的测试数据来看，LinkedListStack性能更优，但两者差距不大，
 * 当测试数据达到1000000以上来看，LinkedListStack性能会下降，是因为过多的new操作的影响
 * 不过，综上考虑两者的性能差距不大，都是O（1）
 *@create: 2018/11/1
 *@Author: SLJ
 */
public class TestStack {
    public static double countTime(Stack<Integer> stack,int count){
        double startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }
        double endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int count = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        System.out.println("arrayStack用时： " + countTime(arrayStack,count) + " s");
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        System.out.println("linkedListStack用时： " + countTime(linkedListStack,count) + " s");

    }
}
