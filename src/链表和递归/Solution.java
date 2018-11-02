package 链表和递归;

/**
*@Description: （LeetCode 203）删除链表中等于给定值 val 的所有节点。(常规解法)
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
*@create: 2018/11/2
*@Author: SLJ
*/
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while(cur.next != null){
            if(cur.next.val == val){
                ListNode delNode = cur.next;
                cur.next = delNode.next;
                delNode.next = null;
            }else{
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}