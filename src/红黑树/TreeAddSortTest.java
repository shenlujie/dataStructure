package 红黑树;

import AVL.AVLTree;
import 二分搜索树.BST;
import 集合和映射.AVLMap;
import 集合和映射.AVLSet;
import 集合和映射.BSTMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @Description: 对于2000000的顺序数据，测试AVL、RBTree的添加操作性能差距（20000000数据机子就跑不动了，不知原因）
 * @create: 2018/11/7
 * @Author: SLJ
 */
public class TreeAddSortTest {
    public static void main(String[] args) {
        int n = 2000000;
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(random.nextInt(Integer.MAX_VALUE));
        }
        Collections.sort(arrayList);

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
