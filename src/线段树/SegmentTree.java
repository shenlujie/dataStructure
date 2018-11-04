package 线段树;

/**
 * @Description: 以数组为底层数据结构实现线段树
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4*arr.length];
        this.merger = merger;

        buildSegmentTree(0,0,data.length-1);
    }

    private void buildSegmentTree(int treeIndex,int l,int r){
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    private int leftChild(int index){
        return 2*index + 1;
    }

    private int rightChild(int index){
        return 2*index + 2;
    }

    public E query(int queryL,int queryR){
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length
        || queryL > queryR){
            throw new IllegalArgumentException("查询条件不合法");
        }
        return query(0,0,data.length-1,queryL,queryR);
    }

    //以treeIndex为根节点，l--r为范围，区间[queryL,queryR]对应的值
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if (queryL == l && queryR == r){
            return tree[treeIndex];
        }
        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryR <= mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }else if (queryL >= mid + 1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }

        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
        return merger.merge(leftResult,rightResult);
    }

    public void set(int index,E e){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex,int l,int r,int index,E e){
        if (l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index <= mid){
            set(leftTreeIndex,l,mid,index,e);
        }else {
            set(rightTreeIndex,mid+1,r,index,e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);

    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null){
                stringBuilder.append(tree[i]);
            }else {
                stringBuilder.append("null");
            }
            if (i < tree.length-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2,0,3,-5,2,-1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums,(a,b) -> a+b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0,2));
        System.out.println(segmentTree.query(2,5));
        System.out.println(segmentTree.query(0,5));
    }
}
