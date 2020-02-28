package list;

import utils.ListNode;

/**
 * 148 sort list
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * @author sherman
 */
public class Q13SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        /**
         * 找到链表的中点
         */
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        /**
         * left:  head -> ... -> pre
         * right: slow -> ... -> null
         */
        pre.next = null;
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(slow);
        return merge(left, right);
    }

    /**
     * 合并两个有序链表
     */
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left != null ? left : right;
        return dummy.next;
    }

    /**
     * 以head为头结点，截取size（head节点也计算在内）个节点
     * head -> node1 -> node2 -> node3 -> ... -> null
     *
     * >>> res = cut(head, 2)
     * 链表1：head -> node1 -> null
     * 链表2：node2 -> ... -> null
     * res指向node2
     */
    private ListNode cut(ListNode head, int size) {
        ListNode probe = head;
        while (--size > 0 && probe != null) {
            probe = probe.next;
        }
        if (probe == null) {
            return null;
        }
        ListNode next = probe.next;
        probe.next = null;
        return next;
    }

    /**
     * 自底向上归并排序，可以在常数级空间复杂度完成
     */
    public ListNode sortListBU(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode probe = head;
        /**
         * 计算链表长度
         */
        int length = 0;
        while (probe != null) {
            ++length;
            probe = probe.next;
        }

        for (int size = 1; size < length; size *= 2) {
            ListNode cur = dummy.next;
            /**
             * 指向局部有序链表的最后一个节点
             */
            ListNode tail = dummy;
            while (cur != null) {
                ListNode left = cur;
                /**
                 * 假设size = 1
                 * 链表为：node1 -> node2 -> node3 -> ... -> null
                 *
                 * 执行完毕后:
                 * left：node1 -> null
                 * right: node2 -> node3 -> ... -> null
                 */
                ListNode right = cut(left, size);
                /**
                 * 执行完毕后：
                 * left: node1 -> null
                 * right: node2 -> null
                 * cur: node3 -> ... -> null
                 */
                cur = cut(right, size);
                /**
                 * 合并left、right后，此时局部有序链表长度为2，更新tail指针
                 */
                tail.next = merge(left, right);
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return dummy.next;
    }
}
