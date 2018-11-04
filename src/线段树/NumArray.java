package 线段树;

/**
 * @Description: 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class NumArray {
    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        if (nums.length > 0){
            Integer[] integers = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                integers[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(integers,(a,b)->a+b);
        }


    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new IllegalArgumentException("segmentTree is null");
        }
        return segmentTree.query(i,j);
    }
}
