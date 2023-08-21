/**
 * This file contains some private tests 
 */

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.ForkJoinTask;

/**
 * This class contains private test cases for MyDeque, MyStack, and MyQueue
 */
public class CustomTester {
    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }

    
    
    /** Test the Deque constructor, initialize deque with capacity 10 */
    @Test
    public void testDequeConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyDeque<Integer> deque = new MyDeque<>(-10);
        });
    }
    
    
    /** Test expandCapacity with several elements at the start of the array */
    @Test
    public void testExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {6, null, null, null, null, null, null, null, 1, 5};
        Integer[] finalOrdering = {1, 5, 6, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null};
        initDeque(deque, orig, 3, 8, 0);

        deque.expandCapacity();

        assertEquals("Capacity should have doubled", 20, deque.data.length);
        assertEquals("Size should not have changed", 3, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 2, deque.rear);
        assertEquals("Index 3 should not have changed", 1,
        deque.data[0]);
        assertEquals("Index 3 should not have changed", 5,
        deque.data[1]);
        assertEquals("Index 3 should not have changed", 6,
        deque.data[2]);
        for (int i = 0; i < 20; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }
    }

    /** Test expandCapacity with several elements at the start of the array */
    @Test
    public void testExpandCapacity2() {
        MyDeque<Integer> deque = new MyDeque<>(1);
        Integer[] orig = {null,6,null};
        Integer[] finalOrdering = {6,null,null,null,null,null};
        initDeque(deque, orig, 1, 1, 1);

        deque.expandCapacity();

        assertEquals("Capacity should have doubled", 6, deque.data.length);
        assertEquals("Size should not have changed", 1, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 0, deque.rear);
        for (int i = 0; i < 6; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test addFirst to deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testAddFirst() {
        {
                MyDeque<Integer> deque = new MyDeque<>(10);
                Integer[] orig = {4, 5, 6, null, null, null, null, null, null, null};
                initDeque(deque, orig, 3, 0, 2);

                deque.addFirst(6);

                assertEquals("Capacity should not change if deque not full", 10,
                        deque.data.length);
                assertEquals("Should increment size", 4, deque.size);
                assertEquals("Front should move one index when inserting into " +
                        "non-empty deque", deque.data.length-1, deque.front);
                assertEquals("Rear shouldn't change when calling addFirst", 2,
                        deque.rear);
                assertEquals("6 should have been inserted into index 2",
                        6, deque.data[deque.data.length-1]);
                assertEquals("Index 3 should not have changed", 4,
                        deque.data[0]);
                assertEquals("Index 4 should not have changed",
                        5, deque.data[1]);
                assertEquals("Index 5 should not have changed", 6,
                        deque.data[2]);
                assertThrows(NullPointerException.class, () -> {
                deque.addFirst(null);
                });
        }
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {4, 5, 6};
        initDeque(deque, orig, 3, 0, 2);
        deque.addFirst(3);
        assertEquals("Capacity should not change if deque not full", 6,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 5, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 2,
        deque.rear);
        assertEquals("Index 3 should not have changed", 4,
                        deque.data[0]);
        assertEquals("Index 3 should not have changed", 5,
        deque.data[1]);
        assertEquals("Index 3 should not have changed", 6,
                        deque.data[2]);
        assertEquals("Index 3 should not have changed", 3,
        deque.data[deque.front]);

    }

    /**
     * Test addFirst to deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testAddLast() {
        {
                MyDeque<Integer> deque = new MyDeque<>(10);
                Integer[] orig = {null, null, null, null, null, null, null, 4, 5, 6};
                initDeque(deque, orig, 3, 7, 9);

                deque.addLast(7);

                assertEquals("Capacity should not change if deque not full", 10,
                        deque.data.length);
                assertEquals("Should increment size", 4, deque.size);
                assertEquals("Front should move one index when inserting into " +
                        "non-empty deque", 0, deque.rear);
                assertEquals("Rear shouldn't change when calling addFirst", 7,
                        deque.front);
                assertEquals("6 should have been inserted into index 2",
                        7, deque.data[0]);
                assertEquals("Index 3 should not have changed", 4,
                        deque.data[7]);
                assertEquals("Index 4 should not have changed",
                        5, deque.data[8]);
                assertEquals("Index 5 should not have changed", 6,
                        deque.data[9]);
                assertThrows(NullPointerException.class, () -> {
                deque.addFirst(null);
                });
        }
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {4, 5, 6};
        initDeque(deque, orig, 3, 0, 2);
        deque.addLast(7);
        assertEquals("Capacity should not change if deque not full", 6,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 3, deque.rear);
        assertEquals("Rear shouldn't change when calling addFirst", 0,
        deque.front);
        assertEquals("Index 3 should not have changed", 4,
                        deque.data[0]);
        assertEquals("Index 3 should not have changed", 5,
        deque.data[1]);
        assertEquals("Index 3 should not have changed", 6,
                        deque.data[2]);
        assertEquals("Index 3 should not have changed", 7,
        deque.data[deque.rear]);
        
    }

    /**
     * Test removeFirst from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, 4, 1, null, null, null, null, null, null};
        initDeque(deque, orig, 2, 2, 3);

        assertEquals("Element removed should be returned", 4,
                deque.removeFirst().intValue());

        assertEquals("Array length shouldn't be changed", 10,
                deque.data.length);
        assertEquals("Size should decrement", 1, deque.size);
        assertEquals("Front should move one index after removing from " +
                "non-empty deque", 3, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 3,
                deque.rear);
        assertEquals("Index 3 should remain unchanged", 1,
                deque.data[3]);
        assertNull("Index 1 should have been set to null", deque.data[2]);
        deque.removeFirst();
        assertNull(deque.removeFirst());
    }

    /**
     * Test removeFirst from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, 4, 1, null, null, null, null, null, null};
        initDeque(deque, orig, 2, 2, 3);

        assertEquals("Element removed should be returned", 1,
                deque.removeLast().intValue());

        assertEquals("Array length shouldn't be changed", 10,
                deque.data.length);
        assertEquals("Size should decrement", 1, deque.size);
        assertEquals("Front should move one index after removing from " +
                "non-empty deque", 2, deque.rear);
        assertEquals("Rear should not change after calling removeFirst", 2,
                deque.rear);
        assertEquals("Index 3 should remain unchanged", 4,
                deque.data[2]);
        assertNull("Index 1 should have been set to null", deque.data[3]);
        deque.removeFirst();
        assertNull(deque.removeFirst());
    }

    /** Test empty on stack with size 0 */
    @Test
    public void testStackEmpty() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {23, null, null, null, null, null, null, null,
                null, null};
        initDeque(stack.theStack, orig, 1, 0, 0);

        assertFalse("Call to empty should return true", stack.empty());
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
        assertEquals("Size should not have changed", 1, stack.theStack.size);
        assertEquals("Front should not have changed", 0, stack.theStack.front);
        assertEquals("Rear should not have changed", 0, stack.theStack.rear);
        assertEquals("Rear should not have changed", 23, (int)stack.peek());


        stack.push(1);
        assertEquals("Size should not have changed", 2, stack.theStack.size);
        assertEquals("Rear should not have changed", 1, (int)stack.peek());
        stack.pop();
        assertEquals("Size should not have changed", 1, stack.theStack.size);
        assertEquals("Rear should not have changed", 23, (int)stack.peek());
    }

    /** Test empty on queue with size 0 */
    @Test
    public void testQueueEmpty() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {23, null, null, null, null, null, null, null,
                null, null};
        initDeque(queue.theQueue, orig, 1, 0, 0);

        assertFalse("Call to empty should return true", queue.empty());
        assertEquals("Capacity should not have changed", 10,
                queue.theQueue.data.length);
        assertEquals("Size should not have changed", 1, queue.theQueue.size);
        assertEquals("Front should not have changed", 0, queue.theQueue.front);
        assertEquals("Rear should not have changed", 0, queue.theQueue.rear);
        assertEquals("Rear should not have changed", 23, (int)queue.peek());

        queue.enqueue(1);
        assertEquals("Size should not have changed", 2, queue.theQueue.size);
        assertEquals("Front should not have changed", 0, queue.theQueue.front);
        assertEquals("Rear should not have changed", 1, queue.theQueue.rear);
        assertEquals("Rear should not have changed", 23, (int)queue.peek());
        queue.dequeue();
        assertEquals("Size should not have changed", 1, queue.theQueue.size);
        assertEquals("Front should not have changed", 1, queue.theQueue.front);
        assertEquals("Rear should not have changed", 1, queue.theQueue.rear);
        assertEquals("Rear should not have changed", 1, (int)queue.peek());

        queue.enqueue(2);
    }
}
