/**
 * 复杂链表
 */
public class RandomListNode {
    int label;
    RandomListNode next = null;
    /**
     * 随机指向链表中某个节点
     */
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
