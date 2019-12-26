package list;

import utils.ListNode;

/**
 * 92 reverse linked list II
 * 反转位置m到n的链表，1 <= m <= n <= 链表长度
 *
 * @author sherman
 */
public class Q02ReverseListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (int i = 1; i < m; ++i) {
            ListNode next = cur.next;
            pre = cur;
            cur = next;
        }
        ListNode preCopy = pre;
        ListNode curCopy = cur;
        for (int i = m; i <= n; ++i) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        preCopy.next = pre;
        curCopy.next = cur;
        return dummy.next;
    }
}
