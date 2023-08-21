/**
 * This is a public tester file for PA3. It contains test cases 
 * for the MyLinkedList and Node classes.
 */

import org.junit.*;

import static org.junit.Assert.*;

public class MyLinkedListPublicTester {
    private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = {"CSE 12", "Paul Cao", "Christine Alvarado"};

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and
     * a list with several entries (0,1,2)
     */
    @Before
    public void setUp() {
        emptyIntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
    }

    /**
     * One way to test the code is to manually populate the list to ensure 
     * the internal state is correct. In this way, potential errors in add() 
     * won't cause failures for other methods.
     *
     * However, for you own custom tester, feel free to populate the list 
     * in whatever way you want.
     */
    private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.threeStringList.new Node(this.strData[0]);
        MyLinkedList<String>.Node node1 =  
            this.threeStringList.new Node(this.strData[1]);
        MyLinkedList<String>.Node node2 =  
            this.threeStringList.new Node(this.strData[2]);

        this.threeStringList.head.next = node0;
        node0.prev = this.threeStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = node2;
        this.threeStringList.size = 3;
    }


    // ------------ MyLinkedList() -------------

    @Test
    public void testConstructor() {
        /* assertSame is used when you want to check that if two objects 
           refer to the same object */
        assertSame("Data stored at dummy head should be null", 
            null, this.emptyIntegerList.head.data);
        assertSame("Data stored at dummy tail should be null", 
            null, this.emptyIntegerList.tail.data);
        assertSame("Head should point to next", 
            this.emptyIntegerList.tail, this.emptyIntegerList.head.next);
        assertSame("Next should point to head", 
            this.emptyIntegerList.head, this.emptyIntegerList.tail.prev);
    }

    // ------------ add(E) -----------------

    @Test
    public void testAddEmpty() {
        this.emptyIntegerList.add(12);
        assertEquals("New node should be accessible from head", 
            Integer.valueOf(12), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail", 
            Integer.valueOf(12), this.emptyIntegerList.tail.prev.data);
        assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
        assertSame("Make sure the referece from head and tail are the same", 
            this.emptyIntegerList.head.next, this.emptyIntegerList.tail.prev);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
    }

    @Test
    public void testAddNotEmpty() {
        this.populateLinkedList();

        // Obtain the reference to the node before tail
        MyLinkedList<String>.Node oldLastNode = this.threeStringList.tail.prev;
        this.threeStringList.add("Gary Gillespie");

        assertEquals("Tail node should point back to the new node", 
            "Gary Gillespie", this.threeStringList.tail.prev.data);
        assertEquals("Previous last node should point to the new added node",
            "Gary Gillespie", oldLastNode.next.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node previous should be previous last node",
            oldLastNode, this.threeStringList.tail.prev.prev);
        assertSame("New added node next should point to tail",
            this.threeStringList.tail.prev.next, this.threeStringList.tail);
    }


    // ------------ add(int, E) -----------------

    @Test
    public void testAddIdxEmpty() {
        this.emptyIntegerList.add(0, 12);
        assertEquals("Check head reference is updated",
            Integer.valueOf(12), this.emptyIntegerList.head.next.data);
        assertEquals("Check tail reference is updated", 
            Integer.valueOf(12), this.emptyIntegerList.tail.prev.data);
        assertEquals("Check size is updated", 1, this.emptyIntegerList.size);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
    }

    @Test
    public void testAddIdxGreaterThanSize() {
        this.populateLinkedList();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            threeStringList.add(4, "Staff");
        });
    }

    // ------------ getNth(int) -----------------

    @Test
    public void testGetNth() {
        this.populateLinkedList();
        assertSame("Check the first element is returned correctly", 
            this.threeStringList.head.next, this.threeStringList.getNth(0));
        assertSame("Check the second element is returned correctly", 
            this.threeStringList.head.next.next, this.threeStringList.getNth(1));
    }

    // ------------ get(int) -----------------

    @Test
    public void testGet() {
        this.populateLinkedList();
        assertEquals("Get should return the correct data at index 0", 
            this.strData[0], this.threeStringList.get(0));
        assertEquals("Get should return the correct data at index 1", 
            this.strData[1], this.threeStringList.get(1));
        assertEquals("Get should return the correct data at index 2", 
            this.strData[2], this.threeStringList.get(2));
    }

    // ------------ set(int, E) -----------------

    @Test
    public void testSetHead() {
        this.populateLinkedList();
        this.threeStringList.set(0, "CSE 100");
        assertEquals("Value at index 0 should be reset",
             "CSE 100", this.threeStringList.head.next.data);
    }

    @Test
    public void testSetNull() {
        this.populateLinkedList();

        assertThrows(NullPointerException.class, () -> {
            threeStringList.set(0, null);
        });
    }

    // ------------ remove() -----------------

    @Test
    public void testRemoveFirstElement() {
        this.populateLinkedList();
        MyLinkedList<String>.Node node1 = this.threeStringList.head.next.next;
        String removedNode = this.threeStringList.remove(0);
        assertSame("Old node at position 0 should be removed", 
            node1, this.threeStringList.head.next);
        assertSame("Old node at position 0 should be removed", 
            node1.prev, this.threeStringList.head);
        assertEquals("Removed node should be returned Correctly",
            removedNode, "CSE 12");
        assertEquals(2, this.threeStringList.size());
    }

    @Test
    public void testRemoveLastElement() {
        this.populateLinkedList();
        MyLinkedList<String>.Node node1 = this.threeStringList.tail.prev.prev;
        this.threeStringList.remove(2);
        // We can also check whether we update the prev and next correctly.
        assertSame("Tail.prev should point to the new last element", 
            node1, this.threeStringList.tail.prev);
        assertSame("The new last node should point to tail", 
            node1.next, this.threeStringList.tail);
        assertSame("The tail should point to the new last node", 
            node1, this.threeStringList.tail.prev);
        assertEquals("Size of linked list",
            2, this.threeStringList.size());
    }

    // ------------ clear() -----------------

    @Test
    public void testClearNonEmptyList() {
        this.populateLinkedList();
        threeStringList.clear();
        assertEquals("Size should be updated", 0, threeStringList.size());
        assertTrue("LinkedList should be empty", threeStringList.isEmpty());
    }

    // ------------ size() -----------------

    @Test
    public void testSize() {
        assertEquals("An empty list should have a size of 0", 
            0, emptyIntegerList.size());
    }

    // ------------ isEmpty() -----------------

    @Test
    public void testIsEmpty() {
        this.populateLinkedList();
        assertFalse("A list with 3 elements should not be empty", 
            threeStringList.isEmpty());
    }
}
