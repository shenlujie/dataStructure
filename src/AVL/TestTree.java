package AVL;

import 哈希表.HashTable;
import 红黑树.RBTree;
import 集合和映射.BSTMap;
import 集合和映射.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description: 测试BST、AVL、RBTree、HashTable的性能差距
 * @create: 2018/11/6
 * @Author: SLJ
 */
public class TestTree {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",arrayList)){

            //Collections.sort(arrayList);

            //测试BST
            long startTime = System.nanoTime();
            BSTMap<String,Integer> bstMap = new BSTMap<>();
            for (String word:arrayList) {
                if (bstMap.contains(word)){
                    bstMap.set(word,bstMap.get(word)+1);
                }else {
                    bstMap.add(word,1);
                }

            }
            for (String word:arrayList) {
                bstMap.contains(word);
            }
            long endTime = System.nanoTime();
            double timeBST = (endTime - startTime)/1000000000.0;
            System.out.println("BST执行以上代码共耗时：" + timeBST + " s");

            System.out.println();

            //测试AVL
            startTime = System.nanoTime();
            AVLTree<String,Integer> avlTree = new AVLTree<>();
            for (String word:arrayList) {
                if (avlTree.contains(word)){
                    avlTree.set(word,avlTree.get(word)+1);
                }else {
                    avlTree.add(word,1);
                }

            }
            for (String word:arrayList) {
                avlTree.contains(word);
            }
            endTime = System.nanoTime();
            double timeAVL = (endTime - startTime)/1000000000.0;
            System.out.println("AVL执行以上代码共耗时：" + timeAVL + " s");

            //测试RBTree
            startTime = System.nanoTime();
            RBTree<String,Integer> rbTree = new RBTree<>();
            for (String word:arrayList) {
                if (rbTree.contains(word)){
                    rbTree.set(word,rbTree.get(word)+1);
                }else {
                    rbTree.add(word,1);
                }

            }
            for (String word:arrayList) {
                rbTree.contains(word);
            }
            endTime = System.nanoTime();
            double timeRB = (endTime - startTime)/1000000000.0;
            System.out.println("RBtree执行以上代码共耗时：" + timeRB + " s");

            //测试HashTable
            startTime = System.nanoTime();
            HashTable<String,Integer> hashTable = new HashTable<>();
            for (String word:arrayList) {
                if (hashTable.contains(word)){
                    hashTable.set(word,hashTable.get(word)+1);
                }else {
                    hashTable.add(word,1);
                }

            }
            for (String word:arrayList) {
                hashTable.contains(word);
            }
            endTime = System.nanoTime();
            double timeHtable = (endTime - startTime)/1000000000.0;
            System.out.println("HashTable执行以上代码共耗时：" + timeHtable + " s");

        }
    }
}
