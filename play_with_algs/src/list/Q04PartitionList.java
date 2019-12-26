package list;

import utils.ListNode;

/**
 * 86 partition list
 * 给定一个链表和一个特定值x，对链表进行分隔，使得所有小于x的节点都在大于或等于x的节点之前
 * 注意保留两个分区中每个节点的初始相对位置
 *
 * @author sherman
 */
public class Q04PartitionList {
    /**
     * 维护一个链表
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /**
         * 找到小于x的节点，直接插到less之后
         */
        ListNode less = dummy;

        ListNode pre = dummy;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            if (cur.val >= x) {
                pre = cur;
                cur = cur.next;
            } else {
                /**
                 * 找到小于x的节点
                 */
                if (less.next == cur) {
                    less = cur;
                    pre = cur;
                    cur = cur.next;
                } else {
                    next = cur.next;
                    cur.next = less.next;
                    less.next = cur;
                    less = less.next;
                    /**
                     * 注意pre指针不能动
                     */
                    pre.next = next;
                    cur = next;
                }
            }
        }
        return dummy.next;
    }

    /**
     * 维护两个链表
     */
    public ListNode partition01(ListNode head, int x) {
        ListNode beforeHead = new ListNode(-1);
        ListNode before = beforeHead;
        ListNode afterHead = new ListNode(-1);
        ListNode after = afterHead;
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        /**
         * 注意将after.next值为null
         */
        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }
}
