package list;

import utils.ListNode;

/**
 * 25 reverse nodes in k-group
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表
 * k是一个正整数，它的值小于或等于链表的长度
 *
 * @author sherman
 */
public class Q11ReverseKGroupList {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        /**
         * pre -> start -> ... -> end -> next
         * [start...end]是需要翻转的链表
         */
        ListNode start;
        ListNode end;
        ListNode next;
        while ((end = hasKNodes(pre, k)) != null) {
            start = pre.next;
            next = end.next;
            /**
             * 翻转[start, end]的节点
             */
            ListNode cur = start, tmpPre = pre;
            while (cur != next) {
                ListNode tmpNext = cur.next;
                cur.next = tmpPre;
                tmpPre = cur;
                cur = tmpNext;
            }
            pre.next = tmpPre;
            start.next = next;
            pre = start;
        }
        return dummy.next;
    }

    /**
     * pre节点之后是否有k个节点，如果有，返回最后一个节点，否则返回null
     */
    private ListNode hasKNodes(ListNode pre, int k) {
        ListNode cur = pre;
        for (int i = 0; i < k; ++i) {
            if (cur.next != null) {
                cur = cur.next;
            } else {
                return null;
            }
        }
        return cur;
    }
}
