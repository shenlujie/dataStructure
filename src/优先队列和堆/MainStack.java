package 优先队列和堆;

public class MainStack {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            arrayStack.push(i);
            System.out.println(arrayStack);
        }
        arrayStack.pop();
        System.out.println(arrayStack);
        System.out.println(arrayStack.getSize());
        System.out.println(arrayStack.isEmpty());
        System.out.println(arrayStack.peek());
    }
}
