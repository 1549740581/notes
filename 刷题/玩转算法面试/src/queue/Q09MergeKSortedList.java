package queue;

import utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23 merge k sorted lists
 * 合并k个排序链表，返回合并后的排序链表。
 *
 * @author sherman
 */
public class Q09MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode probe = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        while (!pq.isEmpty()) {
            ListNode removed = pq.remove();
            probe.next = removed;
            probe = probe.next;
            if (removed.next != null) {
                pq.add(removed.next);
            }
        }
        return dummy.next;
    }
}
