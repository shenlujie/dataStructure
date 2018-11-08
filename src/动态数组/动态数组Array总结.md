 1. 不是真正意义上的动态，底层还是静态数组
 2. 重点时扩容和缩容操作
 3. addFirst操作的时间复杂度为O(n)，每个元素都要往后移一位
 4. addLast操作的时间复杂度为O(1)，由于resize是O(n)，但是不是每次都resize	，均摊下来是O(2)，即O(1)
 5. add和remove操作的均摊时间复杂度为O(n/2)，也是O(n)
 6. set和get操作都是O(1)

resize扩容操作：
1、执行add操作时，当数组的size达到数组的length时，数组的capacity扩充为原来的两倍，并遍历从index到size-1之间所有的元素往后移一个位置，在index位置插入新的元素

```
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
```
2、执行remove操作时，首先遍历index到size-1之间的所有元素往前移一个位置，维护size,并将索引为size的元素置为null。当size小到length的1/4时，执行resize操作将capacity缩小至原来的一半。

```
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
```

**具体的resize操作**：就是new一个新的数组，length为原来的两倍。

```
private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
```

