/**
 * An interface that specifies the familiar queue abstraction.
 * <p>
 * In addition to the methods required in the definition of this interface,
 * a class implementing this interface should provide a public constructor
 * with a single argument of type int, which specifies the initial
 * capacity of the QueueInterface.  The constructor should throw an
 * IllegalArgumentException if the specified capacity is negative.
 */
public interface QueueInterface<E> {

    /**
     * Checks if the queue is empty.
     *
     * @return true if there are no elements in the QueueInterface, false
     * otherwise.
     */
    boolean empty();

    /**
     * Adds the specified element to the tail of this QueueInterface.
     *
     * @param element the element to add to the queue
     * @throws NullPointerException if the specified element is null.
     */
    void enqueue(E element);

    /**
     * Removes the element at the head of this QueueInterface.
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    E dequeue();

    /**
     * Returns the element at the head of this QueueInterface,
     * or null if there was no such element.
     *
     * @return the element at the head, or null if the size was zero.
     */
    E peek();

    /**
     * Returns the number of elements in this QueueInterface.
     *
     * @return the number of elements in this QueueInterface
     */
    int size();

}
