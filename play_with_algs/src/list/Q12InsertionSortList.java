package list;

import utils.ListNode;

/**
 * 147 insertion sort list
 * 对链表进行插入排序
 *
 * @author sherman
 */
public class Q12InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preHead = head;
        head = head.next;
        while (head != null) {
            /**
             * 当前节点值比前一个节点值大，直接下一个节点
             */
            if (head.val >= preHead.val) {
                preHead = head;
                head = head.next;
            } else {
                ListNode pre = dummy;
                ListNode cur = pre.next;
                ListNode next = head.next;
                /**
                 * 找到第一个大于等于head.val的节点，即为cur
                 */
                while (head.val > cur.val) {
                    pre = cur;
                    cur = cur.next;
                }
                /**
                 * 将当前节点插入到cur节点之前
                 */
                head.next = cur;
                pre.next = head;
                preHead.next = next;
                head = next;
            }
        }
        return dummy.next;
    }
}
