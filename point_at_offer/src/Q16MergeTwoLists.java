/**
 * 合并两个单调递增的链表，合成后的链表满足单调不减规则
 *
 * @author sherman
 */
public class Q16MergeTwoLists {
    // 非递归形式
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        /**
         * 链表直接使用if即可，不需要使用while循环
         */
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummyNode.next;
    }

    // 递归形式
    public ListNode merge01(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = null;
        if (list1.val < list2.val) {
            head = list1;
            head.next = merge01(list1.next, list2);
        } else {
            head = list2;
            head.next = merge01(list1, list2.next);
        }
        return head;
    }
}
