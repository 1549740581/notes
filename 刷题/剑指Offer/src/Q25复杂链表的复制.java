/**
 * 复杂链表的复制
 *
 * 每个节点有两个指针，一个指向下一个节点，另一个指向任意节点
 *
 * @author sherman
 */
public class Q25复杂链表的复制 {
    /**
     * 在每个节点之后复制一个新节点并串联起来
     */
    private void cloneNode(RandomListNode pHead) {
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
    }

    /**
     * 连接clone的random指针
     */
    private void connectRandom(RandomListNode pHead) {
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null) {
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }
    }

    /**
     * 将原链表和clone链表分开
     */
    private RandomListNode splitList(RandomListNode pHead) {
        RandomListNode cloneHead = null;
        RandomListNode cloneNode = null;
        RandomListNode cur = pHead;
        if (cur != null) {
            cloneHead = cur.next;
            cloneNode = cur.next;
            cur.next = cloneNode.next;
            cur = cur.next;
        }
        while (cur != null) {
            cloneNode.next = cur.next;
            cloneNode = cloneNode.next;
            cur.next = cloneNode.next;
            cur = cur.next;
        }
        return cloneHead;
    }

    /**
     * 复制复杂链表
     */
    public RandomListNode clone(RandomListNode pHead) {
        cloneNode(pHead);
        connectRandom(pHead);
        return splitList(pHead);
    }
}
