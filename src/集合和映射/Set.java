package 集合和映射;

/**
*@Description: 集合的特点是不能存放重复元素
*@create: 2018/11/3
*@Author: SLJ
*/
public interface Set<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
