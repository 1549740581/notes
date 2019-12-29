package list;

import utils.ListNode;

/**
 * 19 remove Nth node from end of list
 * 给定一个链表，删除链表中倒数第N个节点
 *
 * @author sherman
 */
public class Q15RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        for (int i = 0; i < n + 1; ++i) {
            if (fast == null) {
                return null;
            } else {
                fast = fast.next;
            }
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
