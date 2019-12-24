/**
 * 链表倒数第k个节点，k >= 1，最后一个节点为倒数第一个节点
 *
 * @author sherman
 */
public class Q14FindKthToTail {
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 0) {
            return null;
        }
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            --k;
        }
        if (fast == null && k > 0) {
            return null;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
