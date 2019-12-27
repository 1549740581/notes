package list;

import utils.ListNode;

import java.util.Stack;

/**
 * 445 add two numbers II
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字，将这两数相加会返回一个新的链表。
 * 注意不能对链表中节点进行翻转
 *
 * @author sherman
 */
public class Q07AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stk1 = new Stack<>();
        Stack<Integer> stk2 = new Stack<>();
        while (l1 != null) {
            stk1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stk2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = null;
        while (!stk1.empty() || !stk2.empty() || carry != 0) {
            int n1 = stk1.empty() ? 0 : stk1.pop();
            int n2 = stk2.empty() ? 0 : stk2.pop();
            int val = n1 + n2 + carry;
            ListNode tmp = new ListNode(val % 10);
            /**
             * 新产生的节点插入到head节点之前，并重置head指针
             */
            tmp.next = head;
            head = tmp;
            carry = val / 10;
        }
        return head;
    }
}
