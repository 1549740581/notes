package list;

import utils.ListNode;

/**
 * 61 rotate list
 * 给定一个链表，旋转该链表，将链表中每个节点向右移动k个位置，其中k是非负数
 *
 * @author sherman
 */
public class Q16RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode tail = null;
        ListNode cur = head;
        while (cur != null) {
            ++len;
            if (cur.next == null) {
                tail = cur;
            }
            cur = cur.next;
        }
        k %= len;
        if (k == 0){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; ++i) {
            fast = fast.next;
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        dummy.next = slow.next;
        slow.next = null;
        tail.next = head;
        return dummy.next;
    }
}
