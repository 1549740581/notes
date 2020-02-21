import java.util.HashMap;
import java.util.Map;

/**
 * 两个链表的第一个公共节点
 *
 * @author sherman
 */
public class Q36两链表公共节点 {
    public ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null){
            return null;
        }
        Map<ListNode, Integer> maps = new HashMap<>();
        ListNode probe = head1;
        while (probe != null) {
            maps.put(probe, 1);
            probe = probe.next;
        }
        probe = head2;
        while (probe != null) {
            if (maps.get(probe) != null){
                return probe;
            }
            probe = probe.next;
        }
        return null;
    }
}
