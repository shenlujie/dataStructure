package 链表和递归;

/**
 *@Description: （LeetCode 203）删除链表中等于给定值 val 的所有节点。(递归简化解法)
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *@create: 2018/11/2
 *@Author: SLJ
 */
class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        head.next = removeElements(head.next,val);
        return head.val == val ? head.next:head;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5,6,6,7,7,8};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);
        ListNode res = new Solution3().removeElements(listNode,6);
        System.out.println(res);

    }
}