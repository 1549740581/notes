package list;

import utils.ListNode;

/**
 * 82 remove duplicates from sorted list II
 * 给定一个【排序】链表，删除所有含有重复数字的节点，只保留原始链表中【没有重复出现】的数字。
 * 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * 1 -> 2 -> 5
 *
 * @author sherman
 */
public class Q09RemoveElemInListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /**
         * cur初始值不可能为null
         */
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur.next != null) {
            /**
             * 注意题目说是排序数组，可能递增、可能递减
             */
            if (cur.next.val != cur.val) {
                pre = cur;
                cur = cur.next;
            } else {
                ListNode probe = cur.next;
                while (probe != null && probe.val == cur.next.val) {
                    probe = probe.next;
                }
                if (probe == null) {
                    pre.next = null;
                    break;
                } else {
                    /**
                     * 中间链表存在内存泄漏!
                     */
                    cur = probe;
                    pre.next = cur;
                }
            }
        }
        return dummy.next;
    }
}
