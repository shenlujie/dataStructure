1、本次实现的链表为单向链表，一个节点node中包含一个元素e和一个指向下一个node的引用next，node外部设置一个虚拟头节点dummyHead（这玩意由妙用）；  
2、增删查改四个操作原理基本一致，详细分析一下（非常简单）：
（1） 指定index执行add操作：设置一个前驱结点prev，初始化为dummyHead，循环遍历到index前一个节点，然后精彩的操作就是：

```
Node node = new Node(e);
Node nextNode = prev.next;
node.next = nextNode;
prev.next = node;
```

```
public void add(int index,E e){

        if (index < 0 || index > size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<size");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);//简化后的骚操作
        size ++;
    }
```
（2）指定index执行remove操作：同样道理，设置一个当前节点cur，初始化为dummyHead，循环遍历到index前一个节点，然后精彩的操作就是：

```
public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<=size");
        }
        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node delNode = cur.next;
        cur.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.e;
    }
```
（3）指定index执行get操作：同样道理，设置一个前驱结点prev，初始化为dummyHead.next，也就是头节点，循环遍历到index这个节点，然后精彩的操作就是：直接返回这个节点的值就完事了

```
public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index 需要满足 index>0 and index<=size");
        }
        Node prev = dummyHead.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        return prev.e;
    }
```
（4）和get操作一模一样，就是把返回值的操作换成赋值操作。

