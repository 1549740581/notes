package list;

import utils.ListNode;

/**
 * 206 reverse linked list
 *
 * @author sherman
 */
public class Q01ReverseList {

    /**
     * 迭代
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归
     */
    public ListNode reverseList01(ListNode head) {
        return reverseList01(null, head);
    }

    private ListNode reverseList01(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverseList01(cur, next);
    }
}
