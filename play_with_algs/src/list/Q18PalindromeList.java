package list;

import utils.ListNode;

/**
 * 234 palindrome linked list
 * 判断一个链表是否为回文链表。
 *
 * @author sherman
 */
public class Q18PalindromeList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        boolean isEven = fast.next != null;
        /**
         * 如果链表长度是偶数，现在这里做判断
         * 避免在后面while(head != mid)的循环中做特殊处理
         */
        if (isEven && slow.val != slow.next.val) {
            return false;
        }
        ListNode mid = slow;
        ListNode pre = mid;
        ListNode cur = pre.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        while (head != mid) {
            if (head.val != pre.val){
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }
}
