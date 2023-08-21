public interface MinHeapInterface<E extends Comparable<E>> {
    void insert(E element);
    E getMin();
    E remove();
    int size();
    void clear();
}