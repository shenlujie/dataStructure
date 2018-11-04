package 集合和映射;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Description: 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 结果中不允许出现重复元素
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class Solution2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums1.length; i++) {
            treeSet.add(nums1[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (treeSet.contains(nums2[i])){
                list.add(nums2[i]);
                treeSet.remove(nums2[i]);
            }
        }

        int[] a = new int[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] a = new Solution2().intersection(nums1,nums2);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
