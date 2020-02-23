/**
 * 链表中环的入口节点
 * 给一个链表，若其中包含环，请找出该链表的环的入口节点，否则返回null。
 *
 * @author sherman
 */
public class Q55链表环的入口 {
    public ListNode entryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        // 没有环
        if (fast == null) {
            return null;
        } else {
            /**
             * 寻找环
             *
             */
            slow = pHead;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }
}
