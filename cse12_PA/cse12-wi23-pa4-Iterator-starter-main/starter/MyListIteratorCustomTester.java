/**
 * @author Liam Mohler
 * Date: 2/16/2022
 * This file contains all the private tests.
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * This class contains private test cases for MyListIterator. listLen1 is a
 * linkedlist of length 1 and listLen2 is a linkedlist of length 2.
 */
public class MyListIteratorCustomTester {
    private MyLinkedList listLen1, listLen2;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        listLen1Iter.next();
        assertThrows(NoSuchElementException.class, () -> {
            listLen1Iter.next();
        });
        assertEquals("Index of iterator after 1 next()", 1, listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class, () -> {
            listLen1Iter.previous();
        });
        assertEquals("Index of iterator after 1 next()", 0, listLen1Iter.idx);
        assertFalse("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        listLen1Iter.next();
        assertThrows(NullPointerException.class, () -> {
            listLen1Iter.add(null);
        });
        assertEquals("Index of iterator after 1 next()", 1, listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        assertThrows(IllegalStateException.class, () -> {
            listLen1Iter.set("hello");
        });
        assertEquals("Index of iterator after 1 next()", 0, listLen1Iter.idx);
        assertFalse("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() {
        listLen1Iter.next();
        assertThrows(NullPointerException.class, () -> {
            listLen1Iter.set(null);
        });
        assertEquals("Index of iterator after 1 next()", 1, listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        assertThrows(IllegalStateException.class, () -> {
            listLen1Iter.remove();
        });
        assertEquals("Index of iterator after 1 next()", 0, listLen1Iter.idx);
        assertFalse("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.next();
        assertEquals(false, listLen1Iter.hasNext());
        assertEquals("Index of iterator after 1 next()", 1, listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals(false, listLen1Iter.hasPrevious());
        assertEquals("Index of iterator after 1 next()", 0, listLen1Iter.idx);
        assertFalse("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals(-1, listLen1Iter.previousIndex());
        assertEquals("Index of iterator after 1 next()", 0, listLen1Iter.idx);
        assertFalse("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen1Iter.next();
        assertEquals(1, listLen1Iter.nextIndex());
        assertEquals("Index of iterator after 1 next()", 1, listLen1Iter.idx);
        assertTrue("Able to remove node", listLen1Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen1Iter.forward);
    }
}
