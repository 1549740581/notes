package list;

import utils.ListNode;

/**
 * 328 odd even linked list
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 *
 * @author sherman
 */
public class Q05OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddBefore = new ListNode(-1);
        ListNode odd = oddBefore;
        ListNode evenBefore = new ListNode(-1);
        ListNode even = evenBefore;
        int cnt = 1;
        while (head != null) {
            if ((cnt & 1) == 1) {
                odd.next = head;
                head = head.next;
                odd = odd.next;
                odd.next = null;
            } else {
                even.next = head;
                head = head.next;
                even = even.next;
                even.next = null;
            }
            ++cnt;
        }
        odd.next = evenBefore.next;
        return oddBefore.next;
    }

    public ListNode oddEvenList01(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
