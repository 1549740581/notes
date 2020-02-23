package list;

import utils.ListNode;

/**
 * 203 remove linked list elements
 * 删除链表中等于给定值【val】的所有节点
 *
 * @author sherman
 */
public class Q08RemoveElemInList {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
