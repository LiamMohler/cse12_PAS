import static org.junit.Assert.*;

import org.junit.*;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */

    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] arr = new Object[10];
    Integer[] arrInts = { 1, 2, 3 };
    Integer[] arrNonEmptyInts = {1, null, null}; // NOTE: LIST OF SIZE ONE


    private MyArrayList listEmpty, listNonEmpty, listDefaultCap, 
    listCustomCapacity, listWithNull, listWithInt, listPassNull;


    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listPassNull = new MyArrayList(null);
        listNonEmpty = new MyArrayList<>(arrNonEmptyInts);
        listNonEmpty.size = 1;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(arr);
        listWithInt = new MyArrayList<Integer>(arrInts);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test(expected=IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
        listEmpty = new MyArrayList(-5);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        assertEquals("Check size for null input", 0, listPassNull.size);
        assertArrayEquals("Check data", new Integer[5], listPassNull.data);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        assertEquals("Check size for list w/ null input", 10, listWithNull.size);
        assertArrayEquals("Check data", new Integer[10], listWithNull.data);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listCustomCapacity.append(1);
        listCustomCapacity.append(2);
        listCustomCapacity.append(3);
        listCustomCapacity.append(4);
        assertEquals("Check size for list w/ null input", 4, listCustomCapacity.size);
        assertArrayEquals("Check data", new Integer[]{1, 2, 3, 4, null, null}, listCustomCapacity.data);
        assertEquals("Check capacity for list w/ null input", 6, listCustomCapacity.getCapacity());
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listCustomCapacity.append(1);
        listCustomCapacity.append(2);
        listCustomCapacity.append(3);
        listCustomCapacity.append(null);
        assertEquals("Check size for list w/ null input", 4, listCustomCapacity.size);
        assertArrayEquals("Check data", new Integer[]{1, 2, 3, null, null, null}, listCustomCapacity.data);
        assertEquals("Check capacity for list w/ null input", 6, listCustomCapacity.getCapacity());
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listCustomCapacity.append(1);
        listCustomCapacity.append(2);
        listCustomCapacity.append(3);
        listCustomCapacity.prepend(4);
        assertEquals("Check size for list w/ null input", 4, listCustomCapacity.size);
        assertArrayEquals("Check data", new Integer[]{4, 1, 2, 3, null, null}, listCustomCapacity.data);
        assertEquals("Check capacity for list w/ null input", 6, listCustomCapacity.getCapacity());
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listCustomCapacity.append(1);
        listCustomCapacity.append(2);
        listCustomCapacity.append(3);
        listCustomCapacity.prepend(null);
        assertEquals("Check size for list w/ null input", 4, listCustomCapacity.size);
        assertArrayEquals("Check data", new Integer[]{null, 1, 2, 3, null, null}, listCustomCapacity.data);
        assertEquals("Check capacity for list w/ null input", 6, listCustomCapacity.getCapacity());
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testInsertOutOfBound(){
       listEmpty.insert(-3, null);
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for(int i = 0; i<51; i++){
            listEmpty.insert(i, i);
        }
        assertEquals("Check size", 51, listEmpty.size);
        assertEquals("Check capacity", 53, listEmpty.getCapacity());
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
        listEmpty.get(-123);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
        listEmpty.set(-123, null);
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        listWithInt.remove(0);
        listWithInt.remove(0);
        assertEquals("Check size for list w/ null input", 1, listWithInt.size);
        assertArrayEquals("Check data", new Integer[]{3,null,null}, listWithInt.data);
        assertEquals("Check capacity for list w/ null input", 3, listWithInt.getCapacity());
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
        listWithInt.remove(-10);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected=IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){
        listWithInt.expandCapacity(2);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        listDefaultCap.expandCapacity(20);
        assertEquals("Check capacity for list w/ null input", 20, listDefaultCap.getCapacity());
    }
    

}
