package 红黑树;

import AVL.AVLTree;
import 集合和映射.BSTMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Description: 对于2000000的随机数据，测试BST、AVL、RBTree的添加操作性能差距（20000000数据机子就跑不动了，不知原因）
 * @create: 2018/11/7
 * @Author: SLJ
 */
public class TreeAddTest {
    public static void main(String[] args) {
        int n = 2000000;
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(random.nextInt(Integer.MAX_VALUE));
        }

        //test BST
        long startTime = System.nanoTime();
        BSTMap<Integer,Integer> bstMap = new BSTMap<>();
        for (Integer integer : arrayList) {
            bstMap.add(integer,null);
        }
        long endTime = System.nanoTime();
        double totalTime = (endTime - startTime)/1000000000.0;
        System.out.println("BST共耗时：" + totalTime + " s");

        //test AVL
        long startTime1 = System.nanoTime();
        AVLTree<Integer,Integer> avlTree = new AVLTree<>();
        for (Integer integer : arrayList) {
            avlTree.add(integer,null);
        }
        long endTime1 = System.nanoTime();
        double totalTime1 = (endTime1 - startTime1)/1000000000.0;
        System.out.println("AVL共耗时：" + totalTime1);

        //test RBTree
        long startTime2 = System.nanoTime();
        RBTree<Integer,Integer> rbTree = new RBTree<>();
        for (Integer integer : arrayList) {
            rbTree.add(integer,null);
          }
        long endTime2 = System.nanoTime();
        double totalTime2 = (endTime2 - startTime2)/1000000000.0;
        System.out.println("RBTree共耗时：" + totalTime2);
    }

}
