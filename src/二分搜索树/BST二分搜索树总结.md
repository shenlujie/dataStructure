1、前序遍历    

```
if(node == null){
	return;
}
System.out.println(node.e);
preOrder(node.left);
preOrder(node.right);
```
2、中序遍历   

```
if(node == null){
	return;
}
inOrder(node.left);
System.out.println(node.e);
inOrder(node.right);
```
3、后序遍历

```
if(node == null){
	return;
}
postOrder(node.left);
postOrder(node.right);
System.out.println(node.e);
```
4、层序遍历
用队列实现，先将root入队，然后将其出队打印，接着将左节点入队，将右节点入队

```
queue.enqueue(root);
while(!queue.isEmpty()){
	Node cur = queue.dequeue();
	System.out.println(cur.e);
	if(node.left != null){
		queue.enqueue(node.left);
	}
	if(node.right!= null){
		queue.enqueue(node.right);
	}
}
```
5、add操作（非常简单）
递归调用，如果当前节点为空，直接插入新的节点；如果不为空并且要插入的e比node.e小，则add(node.left,e),否则add(node.right,e)

```
Node add(Node node,e){
	if(node == null){
		node = new Node(e);
		size ++;
		return node;
	}
	if(e.compareTo(node.e < 0)){
		return add(node.left,e);
	}
	if(e.compareTo(node.e > 0)){
		return add(node.right,e);
	}
}
```
6、contains操作（和add一样）

```
		if (node == null){
            return false;
        }
        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
```
7、minimum、maximum、removeMin、removeMax（非常简单）
minimum：左节点为空就返回该节点本身，不为空就返回左节点的最小值；  

```
		if (node.left == null){
            return node;
        }
        return minimum(node.left);
```

maximum：右节点为空就返回该节点本身，不为空就返回右节点的最小值；  

```
		if (node.right == null){
            return node;
        }
        return maximum(node.right);
```

removeMin：左节点为空，就删除本身，返回右节点，并将本身指向右节点的引用置为null，不为空就删除左节点的最小值

```
		if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
```

removeMax：右节点为空，就删除本身，返回左节点，并将本身指向左节点的引用置为null，不为空就删除右节点的最大值

```
if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
```
8、remove操作（思路很简单）   
本身为空，返回空；本身不为空，分三种情况：   
e < node.e   remove（node.left）；   
e > node.e  remove(node.right)；   
e == node.e    
 又分为三种情况：  
 只有左节点的node:删除本身，返回左节点
 只有右节点的node:删除本身，返回右节点
 左右节点都有的node:删除本身，返回右节点中的最小值
 

```
		if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else {//e和node.e相等
            //删除左节点为空的节点
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            //删除右节点为空的节点
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            //删除左右节点都不为空的节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            size ++;
            successor.left = node.left;
            node.left = null;
            node.right = null;
            size --;
            return successor;

        }
```

