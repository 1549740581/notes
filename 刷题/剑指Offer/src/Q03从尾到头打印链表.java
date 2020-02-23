import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表的节点值
 *
 * @author sherman
 */
public class Q03从尾到头打印链表 {
    /**
     * 使用栈实现
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    ArrayList<Integer> list = new ArrayList<>();

    /**
     * 使用递归实现
     */
    public ArrayList<Integer> printListFromTailToHead01(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead01(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
