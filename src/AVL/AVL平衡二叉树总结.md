**所谓平衡，是指任意一个节点，左右子树的高度差不超过1，也就是说，该二叉树的所有叶子节点要么在最后一层，要么在倒数第二层。**
1、在BST的基础上，通过自平衡的方式，有效避免了BST遇到顺序插入时退化为链表的尴尬处境（变为链表后，时间复杂度变为O（n））
2、自平衡就是指 当遇到四种情况（LL,LR,RR,RL）能够通过左旋、右旋的操作使之不再倾斜，重新平衡。
3、执行add、remove操作时，在BST的基础上，添加几个操作：
更新height、计算平衡因子、维护平衡（四种情况，具体情况看代码，非常清晰）

```
		//更新height
        node.height = Math.max(getHeight(node.left),getHeight(node.right))+1;
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //平衡维护(四种情况)
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
```
4、当然，AVL的所有操作都是O（logn），而且不会退化成链表
