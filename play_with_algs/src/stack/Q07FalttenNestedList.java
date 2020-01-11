package stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 341 flatten nested list iterator
 * 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * @author sherman
 */

interface NestedInteger {
    /**
     * @return true if this NestedInteger holds a single integer, rather than a nested list.
     */
    boolean isInteger();

    /**
     * @return the single integer that this NestedInteger holds, if it holds a single integer
     * Return null if this NestedInteger holds a nested list
     */
    Integer getInteger();

    /**
     * @return the nested list that this NestedInteger holds, if it holds a nested list
     * Return null if this NestedInteger holds a single integer
     */
    List<NestedInteger> getList();
}

public class Q07FalttenNestedList implements Iterator<Integer> {

    private Stack<NestedInteger> stk = new Stack<>();


    public Q07FalttenNestedList(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stk.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        if (stk.isEmpty()) {
            return false;
        }
        while (!stk.isEmpty() && !stk.peek().isInteger()) {
            NestedInteger tmp = stk.pop();
            List<NestedInteger> tmpList = tmp.getList();
            for (int i = tmpList.size() - 1; i >= 0; --i) {
                stk.push(tmpList.get(i));
            }
        }
        return !stk.isEmpty();
    }

    @Override
    public Integer next() {
        return stk.pop().getInteger();
    }
}
