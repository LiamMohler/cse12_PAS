/**
 * This file contains part of the public tests (visible on Gradescope).
 * Use this as a guide to write tests to verify your MyMinHeap and
 * MyPriorityQueue implementation.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class contains part of the public test cases for MyMinHeap
 * and MyPriorityQueue
 */
public class PublicTester {
    /**
     * Helper method to initialize all instance variables of MyMinHeap
     *
     * @param data the data array
     */
    static <E extends Comparable<E>> MyMinHeap<E> initMinHeap(List<E> data) {
        MyMinHeap<E> heap = new MyMinHeap<>();
        heap.data = new ArrayList<>(data);
        return heap;
    }

    /**
     * Helper method to initialize all instance variables of MyPriorityQueue
     *
     * @param data the data array
     */
    static <E extends Comparable<E>> MyPriorityQueue<E> initPriorityQueue(
            List<E> data) {
        MyMinHeap<E> heap = new MyMinHeap<>();
        heap.data = new ArrayList<>(data);
        MyPriorityQueue<E> pq = new MyPriorityQueue<>();
        pq.heap = heap;
        return pq;
    }

    // ===================== MyMinHeap Public Tests =====================

    /**
     * Test the MyMinHeap default constructor without any input parameter
     */
    @Test
    public void testMinHeapConstructorNoArg() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        assertEquals("Data ArrayList should be empty", 0, heap.data.size());
    }

    /**
     * Test the MyMinHeap constructor with input data
     */
    @Test
    public void testMinHeapConstructorDataList() {
        MyMinHeap<Integer> heap =
                new MyMinHeap<>(Arrays.asList(5, 4, 1, 7, 2, 9, 6, 3));
        assertEquals("MinHeap properties should be followed",
                Arrays.asList(1, 2, 5, 3, 4, 9, 6, 7), heap.data);
    }

    /**
     * Test the MyMinHeap swap function
     */
    @Test
    public void testMinHeapSwap() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(3, 1, 2));
        heap.swap(0, 1);
        assertEquals(Arrays.asList(1, 3, 2), heap.data);
    }

    /**
     * Test the MyMinHeap getParentIdx function
     */
    @Test
    public void testMinHeapGetParentIdx() {
        assertEquals("Parent index of 1", 0, MyMinHeap.getParentIdx(1));
    }

    /**
     * Test the MyMinHeap getLeftChildIdx function
     */
    @Test
    public void testMinHeapGetLeftChildIdx() {
        assertEquals("Left child index of 0", 1, MyMinHeap.getLeftChildIdx(0));
    }

    /**
     * Test the MyMinHeap getRightChildIdx function
     */
    @Test
    public void testMinHeapGetRightChildIdx() {
        assertEquals("Right child index of 0",
                2, MyMinHeap.getRightChildIdx(0));
    }

    /**
     * Test the MyMinHeap getMinChildIdx function
     */
    @Test
    public void testMinHeapGetMinChildIdx() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(5));
        assertEquals("Minimum child index of 0", -1, heap.getMinChildIdx(0));
        assertEquals("Heap after getting minimum child index should be unchanged.",
                Arrays.asList(5), heap.data);
    }

    /**
     * Test the MyMinHeap percolateUp function
     */
    @Test
    public void testMinHeapPercolateUp() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(1, 2, 4, 4, 2, 0));
        heap.percolateUp(5);
        assertEquals("Heap is properly updated after percolating up.",
                Arrays.asList(0, 2, 1, 4, 2,4), heap.data);
    }

    /**
     * Test the MyMinHeap percolateDown function
     */
    @Test
    public void testMinHeapPercolateDown() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(8, 4, 3, 5, 2));
        heap.percolateDown(4);
        assertEquals("Heap is properly updated after percolating down.",
                Arrays.asList(8, 4, 3, 5, 2), heap.data);
    }

    /**
     * Test the MyMinHeap deleteIndex function
     */
    @Test
    public void testMinHeapDeleteIndex() {
        MyMinHeap<Character> heap =
                initMinHeap(Arrays.asList('A', 'D', 'D', 'E', 'Z', 'X', 'G'));
        heap.deleteIndex(0);
        assertEquals("Heap is properly updated after deleting index.",
                Arrays.asList('D', 'E', 'D', 'G', 'Z', 'X'), heap.data);

        heap.deleteIndex(3);
        assertEquals("Heap is properly updated after deleting index.",
                Arrays.asList('D', 'E', 'D', 'X', 'Z'), heap.data);
        heap.deleteIndex(1);
        assertEquals("Heap is properly updated after deleting index.",
                Arrays.asList('D', 'X', 'D', 'Z'), heap.data);
    }

    /**
     * Test the MyMinHeap insert function
     */
    @Test
    public void testMinHeapInsert() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(11, 12, 13));
        heap.insert(11);
        assertEquals("Heap is properly updated after deleting index.",
                Arrays.asList(11, 11, 13, 12), heap.data);
        heap.insert(7);
        assertEquals("Heap is properly updated after deleting index.",
                Arrays.asList(7,11, 13, 12, 11), heap.data);
        heap.insert(17);
        assertEquals("Heap is properly updated after deleting index.",
                Arrays.asList(7,11, 13, 12, 11, 17), heap.data);
    }

    /**
     * Test the MyMinHeap getMin function
     */
    @Test
    public void testMinHeapGetMin() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(13, 18, 18));
        assertEquals("Minimum element", 13, heap.getMin().intValue());
    }

    /**
     * Test the MyMinHeap remove function
     */
    @Test
    public void testMinHeapRemove() {
        MyMinHeap<Integer> heap =
                initMinHeap(Arrays.asList(13));
        assertEquals("Minimum element", Integer.valueOf(13), heap.remove());
        assertEquals("Heap after removing one element.",
                Arrays.asList(), heap.data);
                
    }

    /**
     * Test the MyMinHeap size function
     */
    @Test
    public void testMinHeapSize() {
        MyMinHeap<Integer> heap =
                initMinHeap(Arrays.asList(13, 18, 18, 25, 18, 19));
        assertEquals("Size of heap", 6, heap.size());
    }

    /**
     * Test the MyMinHeap clear function
     */
    @Test
    public void testMinHeapClear() {
        MyMinHeap<Integer> heap =
                initMinHeap(Arrays.asList(13, 18, 18, 25, 18, 19));
        heap.clear();
        assertEquals("Heap after clear() should be empty",
                0, heap.data.size());
    }

    // ===================== MyPriorityQueue Public Tests =====================
    // Note: Many of these tests assume that your heap works correctly.

    /**
     * Test the MyPriorityQueue No-arg constructor
     */
    @Test
    public void testPriorityQueueNoArgConstructor() {
        MyMinHeap<Float> heap = new MyMinHeap<>();
        MyPriorityQueue<Float> pq = new MyPriorityQueue<>();
        assertNotNull("Priority queue's underlying heap is not null.",
                pq.heap);
        assertEquals("Heap after no-arg constructor.",
                heap.data, pq.heap.data);
    }

    /**
     * Test the MyPriorityQueue Collection-arg constructor
     */
    @Test
    public void testPriorityCollectionConstructor() {
        List<Integer> startingList = Arrays.asList(5, 4, 1, 7, 2, 9, 6, 3);
        MyMinHeap<Integer> expectedHeap = initMinHeap(startingList);
        MyPriorityQueue<Integer> pq = initPriorityQueue(startingList);

        assertNotNull("Priority queue's underlying heap is not null.",
                pq.heap);
        assertEquals("Priority queue after constructor.",
                expectedHeap.data, pq.heap.data);
    }

    /**
     * Test the MyPriorityQueue push function
     */
    @Test
    public void testPriorityQueuePush() {
        List<Integer> startingList = Arrays.asList(11, 12, 13);
        MyMinHeap<Integer> expectedHeap = initMinHeap(startingList);
        MyPriorityQueue<Integer> pq = initPriorityQueue(startingList);
        pq.push(11);
        expectedHeap.insert(11);
        assertEquals("Priority queue after push.", expectedHeap.data,
                pq.heap.data);
        assertEquals(
                "Bigger length after insertion",
                4,
                pq.getLength());
    }

    /**
     * Test the MyPriorityQueue peek function
     */
    @Test
    public void testPriorityQueuePeek() {
        List<Integer> startingList = Arrays.asList(13, 18, 18);
        MyMinHeap<Integer> expectedHeap = initMinHeap(startingList);
        MyPriorityQueue<Integer> pq = initPriorityQueue(startingList);
        assertEquals(
                "Minimum element of [13,18,18].",
                expectedHeap.getMin(),
                pq.peek());
    }

    /**
     * Test the MyPriorityQueue pop function
     */
    @Test
    public void testPriorityQueuePop() {
        List<Integer> startingList = Arrays.asList(13, 18, 18, 25, 18, 19);
        MyMinHeap<Integer> expectedHeap = initMinHeap(startingList);
        MyPriorityQueue<Integer> pq = initPriorityQueue(startingList);
        assertEquals(
                "Minimum element of [13, 18, 18, 25, 18, 19].",
                expectedHeap.remove(),
                pq.pop());

        assertEquals("Priority queue after pop.", expectedHeap.data,
                pq.heap.data);
        assertEquals(
                "Smaller length after insertion",
                5,
                pq.getLength());
    }

    /**
     * Test the MyPriorityQueue getLength function
     */
    @Test
    public void testPriorityQueueGetLength() {
        MyPriorityQueue<Integer> pq =
                initPriorityQueue(Arrays.asList(13, 18, 18, 25, 18, 19));
        assertEquals("Priority queue's length", 6, pq.getLength());
    }

    /**
     * Test the MyPriorityQueue clear function
     */
    @Test
    public void testPriorityQueueClear() {
        MyPriorityQueue<Integer> pq =
                initPriorityQueue(Arrays.asList(13, 18, 18, 25, 18, 19));
        pq.clear();
        assertEquals("Priority queue should be empty",
                List.of(), pq.heap.data);
    }
}
