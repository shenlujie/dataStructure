package 集合和映射;

import 链表.LinkedList;

import java.util.ArrayList;

/**
 * @Description: 基于链表为底层数据结构来实现集合
 * 时间复杂度为O（n），因为add操作有contains判断
 * remove操作也是O（n）
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class LinkedListSet<E extends Comparable<E>> implements Set<E>{

    private LinkedList<E> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!linkedList.contains(e)) {
            linkedList.addFirst(e);
        }

    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",array)){
            System.out.println("傲慢与偏见前十章的字数共为： " + array.size());
            System.out.println();
            LinkedListSet<String> linkedListSet = new LinkedListSet<>();
            for (int i = 0; i < array.size(); i++) {
                linkedListSet.add(array.get(i));
            }
            System.out.println("其中不重复的字数共为： " + linkedListSet.getSize());
        }
    }
}
