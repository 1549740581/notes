package list;

import utils.ListNode;

/**
 * 237 delete node in linked list
 * 给定一个单链表中的节点，O(1)时间复杂度内删除该节点
 *
 * @author sherman
 */
public class Q14DeleteNodeInList {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        /**
         * 最后一个节点
         */
        if (node.next == null) {
            node = null;
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
