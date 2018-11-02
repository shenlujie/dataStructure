package 链表和递归;

/**
 *@Description: （LeetCode 203）删除链表中等于给定值 val 的所有节点。(递归解法)
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *@create: 2018/11/2
 *@Author: SLJ
 */
class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        //边界问题，基本问题
        if (head == null){
            return null;
        }

        //宏观语义，调用子方法解决问题
        ListNode res = removeElements(head.next,val);

        //根据子方法的结果来解决原问题
        if (head.val == val){
            return res;
        }else {
            head.next = res;
            return head;
        }
    }
}