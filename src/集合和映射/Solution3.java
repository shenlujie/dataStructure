package 集合和映射;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description: 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 结果中如有重复元素需要重复出现
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class Solution3 {
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])){
                map.put(nums1[i],map.get(nums1[i])+1);
            }else {
                map.put(nums1[i],1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])){
                list.add(nums2[i]);
                map.put(nums2[i],map.get(nums2[i])-1);
                if (map.get(nums2[i]) == 0){
                    map.remove(nums2[i]);
                }
            }

        }

        int[] a = new int[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,9,4,5};
        int[] nums2 = {9,4,9,8,4};
        int[] a = new Solution3().intersect(nums1,nums2);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
