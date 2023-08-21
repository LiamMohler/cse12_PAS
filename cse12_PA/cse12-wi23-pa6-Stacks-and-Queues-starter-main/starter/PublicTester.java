/**
 * This file contains some public tests (visible on Gradescope)
 * Use this as a guide to write tests to verify your MyDeque, MyStack, and
 * MyQueue implementation.
 */

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains public test cases for MyDeque, MyStack, and MyQueue
 */
public class PublicTester {
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

    // ------------ Deque ---------------

    /** Test the Deque constructor, initialize deque with capacity 10 */
    @Test
    public void testDequeConstructor() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        assertEquals("Capacity should be initialized to 10", 10,
                deque.data.length);
        assertEquals("Size should be initialized to 0", 0, deque.size);
        assertEquals("Front should be initialized to 0", 0, deque.front);
        assertEquals("Rear should be initialized to 0", 0, deque.rear);
    }

    /** Test expandCapacity with several elements at the start of the array */
    @Test
    public void testExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {5, 6, 1, null, null, null, null, null, null, null};
        Integer[] finalOrdering = {5, 6, 1, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null};
        initDeque(deque, orig, 3, 0, 2);

        deque.expandCapacity();

        assertEquals("Capacity should have doubled", 20, deque.data.length);
        assertEquals("Size should not have changed", 3, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 2, deque.rear);
        for (int i = 0; i < 20; i++) {
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
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, null, 4, 5, 6, null, null, null, null};
        initDeque(deque, orig, 3, 3, 5);

        deque.addFirst(6);

        assertEquals("Capacity should not change if deque not full", 10,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 2, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 5,
                deque.rear);
        assertEquals("6 should have been inserted into index 2",
                6, deque.data[2]);
        assertEquals("Index 3 should not have changed", 4,
                deque.data[3]);
        assertEquals("Index 4 should not have changed",
                5, deque.data[4]);
        assertEquals("Index 5 should not have changed", 6,
                deque.data[5]);
    }

    /**
     * Test addLast to deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, 4, 5, 1, null, null, null, null, null};
        initDeque(deque, orig, 3, 2, 4);

        deque.addLast(-1);

        assertEquals("Capacity should not change if deque not full", 10,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front shouldn't change when called addLast", 2,
                deque.front);
        assertEquals("Rear should move one index when inserting into " +
                "non-empty deque", 5, deque.rear);
        assertEquals("-1 should have been inserted into index 5",
                -1, deque.data[5]);
        assertEquals("Index 2 should not have changed", 4,
                deque.data[2]);
        assertEquals("Index 3 should not have changed", 5,
                deque.data[3]);
        assertEquals("Index 4 should not have changed", 1,
                deque.data[4]);
    }

    /**
     * Test removeFirst from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, 3, 4, 1, null, null, null, null, null, null};
        initDeque(deque, orig, 3, 1, 3);

        assertEquals("Element removed should be returned", 3,
                deque.removeFirst().intValue());

        assertEquals("Array length shouldn't be changed", 10,
                deque.data.length);
        assertEquals("Size should decrement", 2, deque.size);
        assertEquals("Front should move one index after removing from " +
                "non-empty deque", 2, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 3,
                deque.rear);
        assertEquals("Index 2 should remain unchanged", 4,
                deque.data[2]);
        assertEquals("Index 3 should remain unchanged", 1,
                deque.data[3]);
        assertNull("Index 1 should have been set to null", deque.data[1]);
    }

    /**
     * Test removeLast from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, 3, 4, 1, null, null, null, null, null, null};
        initDeque(deque, orig, 3, 1, 3);

        assertEquals("Element removed should be returned", 1,
                deque.removeLast().intValue());

        assertEquals("Array length shouldn't be changed", 10,
                deque.data.length);
        assertEquals("Size should decrement", 2, deque.size);
        assertEquals("Front should not change after calling removeLast", 1,
                deque.front);
        assertEquals("Rear should move one index after removing from " +
                "non-empty deque", 2, deque.rear);
        assertEquals("Index 1 should remain unchanged", 3,
                deque.data[1]);
        assertEquals("Index 2 should remain unchanged", 4,
                deque.data[2]);
        assertNull("Index 3 should have been set to null", deque.data[3]);
    }

    /**
     * Test peekFirst from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testPeekFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {23, 5, 1, null, null, null, null, null, null,
                null};
        Integer[] finalOrdering = {23, 5, 1};
        initDeque(deque, orig, 3, 0, 2);

        assertEquals("Value at front should be returned",
                Integer.valueOf(23), deque.peekFirst());

        assertEquals("peekFirst should not change capacity", 10,
                deque.data.length);
        assertEquals("peekFirst should not change size", 3, deque.size);
        assertEquals("peekFirst should not change front", 0, deque.front);
        assertEquals("peekFirst should not change rear", 2, deque.rear);
        for (int i = 0; i < deque.size; i++) {
            assertEquals("Deque elements should not have changed",
                    finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test peekLast from deque containing several elements in the middle
     * of the array
     */
    @Test
    public void testPeekLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {3, 5, 2, null, null, null, null, null, null, null};
        Integer[] finalOrdering = {3, 5, 2};
        initDeque(deque, orig, 3, 0, 2);

        assertEquals("Value at front should be returned",
                Integer.valueOf(2), deque.peekLast());

        assertEquals("peekLast should not change capacity", 10,
                deque.data.length);
        assertEquals("peekLast should not change size", 3, deque.size);
        assertEquals("peekLast should not change front", 0, deque.front);
        assertEquals("peekLast should not change rear", 2, deque.rear);
        for (int i = 0; i < deque.size; i++) {
            assertEquals("Deque elements should not have changed",
                    finalOrdering[i], deque.data[i]);
        }
    }

    // ------------ Stack ---------------

    /** Test the Stack constructor, initialize stack with capacity 10 */
    @Test
    public void testStackConstructor() {
        MyStack<Integer> stack = new MyStack<>(10);

        assertEquals("Capacity should be initialized to 10", 10,
                stack.theStack.data.length);
        assertEquals("Size should be initialized to 0", 0,
                stack.theStack.size);
        assertEquals("Front should be initialized to 0", 0,
                stack.theStack.front);
        assertEquals("Rear should be initialized to 0", 0,
                stack.theStack.rear);
    }

    /** Test empty on stack with size 0 */
    @Test
    public void testStackEmpty() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {null, null, null, null, null, null, null, null,
                null, null};
        initDeque(stack.theStack, orig, 0, 0, 0);

        assertTrue("Call to empty should return true", stack.empty());
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
        assertEquals("Size should not have changed", 0, stack.theStack.size);
        assertEquals("Front should not have changed", 0, stack.theStack.front);
        assertEquals("Rear should not have changed", 0, stack.theStack.rear);
    }

    /** Test push on empty stack */
    @Test
    public void testStackPush() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {null, null, null, null, null, null, null, null,
                null, null};
        initDeque(stack.theStack, orig, 0, 0, 0);

        stack.push(3);

        // peekLast also works
        assertEquals("Element should be at the top of the stack",
                Integer.valueOf(3), stack.theStack.peekFirst());
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
        assertEquals("Size should be incremented", 1, stack.theStack.size);
        assertEquals("Front should not have changed", 0, stack.theStack.front);
        assertEquals("Rear should not have changed", 0, stack.theStack.rear);
    }

    /** Test pop on a stack with several elements */
    @Test
    public void testStackPop() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {1, 2, 3, null, null, null, null, null, null, null};
        initDeque(stack.theStack, orig, 3, 0, 2);

        Integer res = stack.pop();

        if (res == 1 || res == 3) {
            assertEquals("Size should have decremented", 2,
                    stack.theStack.size);
            if (stack.theStack.peekFirst() != 2 &&
                    stack.theStack.peekLast() != 2) {
                fail("Next element to remove should be 2");
            }
        } else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
    }

    /** Test peek on a stack with several elements */
    @Test
    public void testStackPeek() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {1, 2, 3, null, null, null, null, null, null, null};
        initDeque(stack.theStack, orig, 3, 0, 2);

        Integer res = stack.peek();

        if (res == 1 || res == 3) {
            assertEquals("Size should not have decremented", 3,
                    stack.theStack.size);
            if (stack.theStack.peekFirst() != 1 &&
                    stack.theStack.peekLast() != 3) {
                fail("Elements on either end should not have changed");
            }
        } else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should not have changed", 10,
                stack.theStack.data.length);
        assertEquals("Front should not have changed", 0, stack.theStack.front);
        assertEquals("Rear should not have changed", 2, stack.theStack.rear);
    }

    // ------------ Queue ---------------

    /** Test the Queue constructor, initialize queue with capacity 10 */
    @Test
    public void testQueueConstructor() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        assertEquals("Capacity should be initialized to 10", 10,
                queue.theQueue.data.length);
        assertEquals("Size should be initialized to 0", 0,
                queue.theQueue.size);
        assertEquals("Front should be initialized to 0", 0,
                queue.theQueue.front);
        assertEquals("Rear should be initialized to 0", 0,
                queue.theQueue.rear);
    }

    /** Test empty on queue with size 0 */
    @Test
    public void testQueueEmpty() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {null, null, null, null, null, null, null, null,
                null, null};
        initDeque(queue.theQueue, orig, 0, 0, 0);

        assertTrue("Call to empty should return true", queue.empty());
        assertEquals("Capacity should not have changed", 10,
                queue.theQueue.data.length);
        assertEquals("Size should not have changed", 0, queue.theQueue.size);
        assertEquals("Front should not have changed", 0, queue.theQueue.front);
        assertEquals("Rear should not have changed", 0, queue.theQueue.rear);
    }

    /** Test enqueue on empty queue */
    @Test
    public void testQueueEnqueue() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {null, null, null, null, null, null, null, null,
                null, null};
        initDeque(queue.theQueue, orig, 0, 0, 0);

        queue.enqueue(3);

        // peekLast also works
        assertEquals("Element should be at the front of the queue",
                Integer.valueOf(3), queue.theQueue.peekFirst());
        assertEquals("Capacity should not have changed", 10,
                queue.theQueue.data.length);
        assertEquals("Size should be incremented", 1, queue.theQueue.size);
        assertEquals("Front should not have changed", 0, queue.theQueue.front);
        assertEquals("Rear should not have changed", 0, queue.theQueue.rear);
    }

    /** Test deqeue on a queue with several elements */
    @Test
    public void testQueueDequeue() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {1, 2, 3, null, null, null, null, null, null, null};
        initDeque(queue.theQueue, orig, 3, 0, 2);

        Integer res = queue.dequeue();

        assertEquals("Size should have decremented", 2, queue.theQueue.size);
        if (res == 1) {
            if (queue.theQueue.peekFirst() != 2) {
                fail("Next element to remove should be 2");
            }
        } else if (res == 3) {
            if (queue.theQueue.peekLast() != 2) {
                fail("Next element to remove should be 2");
            }
        } else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should not have changed", 10,
                queue.theQueue.data.length);

    }

    /** Test peek on a queue with several elements */
    @Test
    public void testQueuePeek() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {1, 2, 3, null, null, null, null, null, null, null};
        initDeque(queue.theQueue, orig, 3, 0, 2);

        Integer res = queue.peek();

        if (res == 1 || res == 3) {
            assertEquals("Size should not have decremented", 3,
                    queue.theQueue.size);
            if (queue.theQueue.peekFirst() != 1 &&
                    queue.theQueue.peekLast() != 3) {
                fail("Elements on either end should not have changed");
            }
        } else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should not have changed", 10,
                queue.theQueue.data.length);
        assertEquals("Front should not have changed", 0, queue.theQueue.front);
        assertEquals("Rear should not have changed", 2, queue.theQueue.rear);
    }
}
