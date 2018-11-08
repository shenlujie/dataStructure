1、和stack比较相似，先进先出。有三个主要方法：入队一个元素--**enqueue(E e)**、出队一个元素--**dequeue()**、查看队首的元素--**getFront()**；  
2、由动态数组Array实现的普通队列，入队由**数组尾部添加元素**，时间复杂度为**O（1）**，出队由**数组头部删除元素**，时间复杂度为**O（n）**，只需内部维护一个Array，实现Queue接口的方法即可，getFront()时间复杂度为**O（1）**     
3、由链表LinkedList实现的链表队列，入队由**链表尾部添加元素**，时间复杂度为**O（1）**，出队由**链表头部删除元素**，时间复杂度为**O（1）**，只需内部维护一个LinkedList，实现Queue接口的方法即可，getFront()时间复杂度为**O（1）**       
4、**LoopQueue循环队列详细总结一下（非常简单）：**     
(1) 总体就是ArrayQueue的优化，底层也是一个数组；   
(2) 维护两个指针，front和tail，front指向队列第一个元素，tail指向最后一个元素的后一个空位置；   
(3) 当front == tail，表示队列为空；   
(4) 入队操作enqueue时，tail位置添加元素，tail指针向后移动一个位置，即

```
public void enqueue(E e) {
        if ((tail+1)%data.length == front){
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }
```
(5) 出队操作时，front位置的元素置为null,front指针向后移动一个位置，即

```
public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue has been empty");
        }
        E e = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if (size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return e;
    }
```
(6) 循环队列中非常骚气的的resize操作，将front以及以后的所有元素直到tail之前遍历到新的数组中，即

```
private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }
```

5、在LoopQueue和LinkedListQueue的比较测试中，两者都是O（1）的级别，差距不大。而ArrayQueue由于是O（n）就会很慢。
