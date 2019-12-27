import java.util.ArrayList;

/**
 * 把整数排成最小的数
 * 输入数组{3，32，321}，输出最小的数：321323
 *
 * @author sherman
 */
public class Q32PrintMinNumber {

    public String printMinNumber(int[] numbers) {
        ArrayList<String> lists = new ArrayList<>();
        for (int elem : numbers) {
            lists.add(elem + "");
        }
        lists.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for (String str : lists) {
            sb.append(str);
        }
        return sb.toString();
    }
}
