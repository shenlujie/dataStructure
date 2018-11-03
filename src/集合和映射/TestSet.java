package 集合和映射;

import java.util.ArrayList;

/**
 * @Description: 测试BSTSet和LinkedListSet的性能区别
 * 由于BSTSet是二叉搜索树为底层结构，时间复杂度为O（h）即O（log n）
 * 而LinkedListSet是链表为底层结构，时间复杂度为O（n），因为add时存在contains判断操作
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class TestSet {

    public static double countTime(Set<String> set){
        long startTime = System.nanoTime();

        ArrayList<String> list = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",list)){
            System.out.println("傲慢与偏见前36章的字数共为： " + list.size());
            for (int i = 0; i < list.size(); i++) {
                set.add(list.get(i));
            }
            System.out.println("其中不重复的字数共为： " + set.getSize());
        }

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        BSTSet<String> bstSet = new BSTSet<>();
        System.out.println("BSTSet共耗时： " + countTime(bstSet) + "s");
        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        System.out.println("LinkedListSet共耗时: " + countTime(linkedListSet) + "s");

    }
}
