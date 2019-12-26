package list;

import utils.ListNode;

/**
 * 83 remove duplicates from sorted list
 *
 * @author sherman
 */
public class Q03RemoveDupInSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
                continue;
            }
            cur = cur.next;
        }
        return head;
    }
}
