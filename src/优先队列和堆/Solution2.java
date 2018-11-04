package 优先队列和堆;

import java.util.*;
import java.util.PriorityQueue;

/**
 * @Description: 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * (经典的从100000个元素中，找出最大的前十个元素)
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * @create: 2018/11/4
 * @Author: SLJ
 */
public class Solution2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!treeMap.containsKey(nums[i])){
                treeMap.put(nums[i],1);
            }else {
                treeMap.put(nums[i],treeMap.get(nums[i])+1);
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (a,b) -> treeMap.get(a) - treeMap.get(b)
        );
        for (int key:treeMap.keySet()) {
            if (queue.size() < k){
                queue.add(key);
            }else if(treeMap.get(key) > treeMap.get(queue.peek())){
                queue.remove();
                queue.add(key);
            }
        }
        List<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()){
            list.add(queue.remove());
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        List<Integer> list = new Solution2().topKFrequent(nums,k);
        for (int num:list) {
            System.out.println(num);
        }
    }
}
