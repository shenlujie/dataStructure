package 集合和映射;

import 二分搜索树.BST;

import java.util.ArrayList;

/**
*@Description: 使用BST为底层数据结构来实现集合
 * 时间复杂度为O（h）就是O（log n）
 * remove操作也是O（log n）
 * 但是存在特殊情况，最差的时候（添加的元素接近有序）BST会退化成链表，变成O（n）
*@create: 2018/11/3
*@Author: SLJ
*/
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",array)){
            System.out.println("傲慢与偏见前十章的字数共为： " + array.size());
            System.out.println();
            BSTSet<String> bstSet = new BSTSet<>();
            for (int i = 0; i < array.size(); i++) {
                bstSet.add(array.get(i));
            }
            System.out.println("其中不重复的字数共为： " + bstSet.getSize());
        }
    }
}
