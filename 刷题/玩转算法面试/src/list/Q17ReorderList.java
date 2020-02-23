package list;

import utils.ListNode;

/**
 * 143 reorder list
 * 给定一个单链表 L：L0 -> L1 -> … -> Ln-1 -> Ln，将其重新排列后变为： L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 ->...
 *
 * @author sherman
 */
public class Q17ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        boolean isOdd = fast.next == null;
        ListNode mid = slow;
        ListNode pre = slow;
        ListNode cur = pre.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (isOdd) {
            mid.next = null;
        } else {
            /**
             * mid: 3
             * 1 -> 2 -> 3 <-> 4 <- 5 <- 6
             * mid.next.next = null
             * 1 -> 2 -> 3 -> 4 -> null
             * 6 -> 5 -> 4 -> null
             */
            mid.next.next = null;
        }
        ListNode probe = head;
        while (probe != mid) {
            ListNode next = probe.next;
            ListNode preNext = pre.next;
            pre.next = next;
            probe.next = pre;
            pre = preNext;
            probe = next;
        }
    }
}
