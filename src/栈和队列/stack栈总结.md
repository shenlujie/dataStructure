1、后进先出，有三个主要方法：入栈一个元素--**push(E e)**、出栈一个元素--**pop()**、查看栈顶的元素--**peek()**；   
2、由动态数组Array实现的普通栈，时间复杂度和Array一致，只需内部维护一个Array，实现stack接口的方法即可，时间复杂度为**O（1）**  
3、由链表LinkedList实现的链表栈，时间复杂度和LinkedList一致，只需内部维护一个LinkedList，实现stack接口的方法即可，时间复杂度也为**O（1）**  
4、在二者的比较测试中，10w级别的数据量，LinkedList会快一些，100w级别的数据量Array比较快，是因为LinkedList中有过多的**new操作**。
