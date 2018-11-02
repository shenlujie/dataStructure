package 链表和递归;

public class ListNode {
    int val;
    ListNode next;
    public ListNode(int val){
        this.val = val;
    }
    public ListNode(int[] arr){
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 0; i < arr.length-1; i++) {
            cur.next = new ListNode(arr[i+1]);
            cur = cur.next;
        }
    }

    //以当前节点为头节点的链表字符串
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        ListNode cur = this;
        while (cur != null){
            stringBuilder.append(cur.val + " -->");
            cur = cur.next;
        }
        stringBuilder.append(" NULL");

        return stringBuilder.toString();
    }
}
