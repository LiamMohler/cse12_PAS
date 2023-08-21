/**
 * A DequeInterface is a sequential structure with restricted access.
 * <p>
 * Access is available only at the ends of the structure:
 * addFirst(E), E removeFirst(), and E peekFirst()
 * operate on the first element of the list;
 * addLast(E), E removeLast(), and E peekLast()
 * operate on the last element of the list.
 * <p>
 * (A sequential structure which, like DequeInterface, permits access
 * and modification only at the ends is sometimes called a "deque",
 * pronounced "deck", which is short for "double-ended queue.")
 * <p>
 * An implementation of DequeInterface must allow duplicate elements,
 * but must not permit null elements, since some methods
 * use null as a signaling return value.
 * <p>
 * In addition to the methods required in the definition of this interface,
 * a class implementing this interface should provide a public constructor
 * with a single argument of type int, which specifies the initial
 * capacity of the DequeInterface.  The constructor should throw an
 * IllegalArgumentException if the specified capacity is negative.
 */
public interface DequeInterface<E> {

    /**
     * Returns the number of elements in this DequeInterface.
     *
     * @return the number of elements in this DequeInterface
     */
    int size();

    /**
     * Doubles the capacity of this DequeInterface. If the capacity
     * is 0, set capacity to some default capacity. No elements in this
     * DequeInterface should change after the expansion.
     */
    void expandCapacity();

    /**
     * Adds the specified element to the front of this DequeInterface. If this
     * DequeInterface is at capacity, expandCapacity is called to double the
     * size of this container.
     *
     * @param element the element to add to the front of the array
     * @throws NullPointerException if the specified element is null.
     */
    void addFirst(E element);

    /**
     * Adds the specified element to the back of this DequeInterface. If the
     * DequeInterface is at capacity, expandCapacity is called to double the
     * size of this container.
     *
     * @param element the element to add to the back of the array
     * @throws NullPointerException if the specified element is null.
     */
    void addLast(E element);


    /**
     * Removes the element at the front of this DequeInterface, and returns the
     * element removed, or null if there was no such element.
     *
     * @return the element removed, or null if there's none.
     */
    E removeFirst();

    /**
     * Removes the element at the back of this DequeInterface, and returns the
     * element removed, or null if there was no such element.
     *
     * @return the element removed, or null if there's none.
     */
    E removeLast();

    /**
     * Returns the element at the front of this DequeInterface,
     * or null if there was no such element.
     *
     * @return the element at the front, or null if there's none.
     */
    E peekFirst();

    /**
     * Returns the element at the back of this DequeInterface,
     * or null if there was no such element.
     *
     * @return the element at the back, or null if there's none.
     */
    E peekLast();
}
