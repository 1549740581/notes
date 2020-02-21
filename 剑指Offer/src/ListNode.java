/**
 * 链表中的节点
 *
 * @author sherman
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this(val, null);
    }
}
