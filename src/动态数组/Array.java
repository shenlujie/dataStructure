package 动态数组;

/**
*@Description: 实现自定义的Array
*@create: 2018/10/31
*@Author: SLJ
*/
public class Array<E> {

    private E[] data;
    private int size;

    //自定义数组容量
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //默认数组容量为10
    public Array(){
        this(10);
    }

    //添加一个新的构造函数，允许用户传入一个数组并进行初始化
    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    //查询当前数组元素个数
    public int getSize(){
        return size;
    }

    //查询数组容量
    public int getCapacity(){
        return data.length;
    }

    //判断数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //判断数组中是否包含
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    //判断数组中是否包含，如果包含返回索引
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //向数组中添加数据
    public void add(int index,E e){

        if (index < 0 || index > size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<capacity");
        }

        if (size == data.length){
            resize(data.length * 2);
        }


        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    //数组尾添加数据
    public void addLast(E e){
        add(size,e);
    }

    //数组头添加数据
    public void addFirst(E e){
        add(0,e);
    }

    //获取数组中的元素
    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("输入的参数违法，需要满足 index>0 and index<capacity");
        }
        return data[index];
    }

    //更新数组中某一位置的元素
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("输入的参数违法，需要满足 index>0 and index<capacity");
        }
        data[index] = e;
    }

    //删除数组中某一位置的元素,并返回该元素
    public E remove(int index){

        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<capacity");
        }

        E e = data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size --;
        data[size] = null;
        //直接将数组大小调整到一半，此时数组仍然是满的，当再次执行add操作时，又出发resize操作，时间复杂度变为O（n）
        //所以采用lazy的方式
        if (size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return e;
    }

    //删除数组头的元素,并返回该元素
    public E removeFirst(){
        return remove(0);
    }

    //删除数组头的元素,并返回该元素
    public E removeLast(){
        return remove(size-1);
    }

    //删除数组中的某个元素e
    public boolean removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    //两元素交换操作
    public void swap(int index1,int index2){
        if (index1<0 || index1>size || index2<0 || index2>size){
            throw new IllegalArgumentException("index1 & index2 illegal");
        }

        int temp = index1;
        index1 = index2;
        index2 = temp;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("数组的容量为 %d, 数组的元素个数为 %d\n",data.length,size));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    //动态数组扩容操作
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
