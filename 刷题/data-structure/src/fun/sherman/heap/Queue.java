package fun.sherman.heap;

/**
 * Created on 2020/5/1
 *
 * @author sherman tang
 */
public interface Queue<E> {
    int size();

    void enqueue(E e);

    E dequeue();

    E getFront();

    boolean isEmpty();
}
