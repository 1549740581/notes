package fun.sherman.heap;

/**
 * 使用MaxHeap作为优先队列的底层实现
 * Created on 2020/5/1
 *
 * @author sherman tang
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.remove();
    }

    @Override
    public E getFront() {
        return maxHeap.getMax();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
