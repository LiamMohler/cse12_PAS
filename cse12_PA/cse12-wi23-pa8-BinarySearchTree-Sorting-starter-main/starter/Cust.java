/*
 * Name: Peeyush Jha
 * Email: pejha@ucsd.edu
 * PID: A17403256
 * Sources used: Lecture Notes, zybook
 * 
 * This is an example of the custom tester for CSE 12 PA8 in Winter 2023.
 */

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * This class creates a test fixture and runs multiple tests on 
 * my implementation for MyBST.  
 * 
 * Instance variables:
 * treee - Object BST that will be used to set up the test fixture
 */
public class Cust {
    MyBST<Integer, Integer> tree;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(8, 0, null);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 1, root);
        MyBST.MyBSTNode<Integer, Integer> ten =
                new MyBST.MyBSTNode<>(10, 2, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 3, three);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 4, three);
        MyBST.MyBSTNode<Integer, Integer> fourteen =
                new MyBST.MyBSTNode<>(14, 5, ten);
        MyBST.MyBSTNode<Integer, Integer> four =
                new MyBST.MyBSTNode<>(4, 6, six);
        MyBST.MyBSTNode<Integer, Integer> seven =
                new MyBST.MyBSTNode<>(7, 7, six);
        MyBST.MyBSTNode<Integer, Integer> thirteen =
                new MyBST.MyBSTNode<>(13, 8, fourteen);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(three);
        root.setRight(ten);
        three.setLeft(one);
        three.setRight(six);
        six.setLeft(four);
        six.setRight(seven);
        ten.setRight(fourteen);
        fourteen.setLeft(thirteen);
        
        tree.size = 9;
    }

    /**
     * Tests Successor when successor is not null
     */
    @Test
    public void testSuccessorNonNull() {  
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        MyBST.MyBSTNode<Integer, Integer> succ1 = tree.root.getRight();
        assertSame(succ1, treeRoot.successor());
        MyBST.MyBSTNode<Integer, Integer> succ2 = tree.root.getRight()
                .getRight().getLeft();
        assertSame(succ2, succ1.successor());
        MyBST.MyBSTNode<Integer, Integer> succ3 = tree.root.getLeft()
                .getRight().getRight();
        assertSame(treeRoot, succ3.successor());
        MyBST.MyBSTNode<Integer, Integer> succ4 = tree.root.getLeft();
        MyBST.MyBSTNode<Integer, Integer> succ5 = tree.root.getLeft()
                .getRight().getLeft();
        assertSame(succ5, succ4.successor());
        MyBST.MyBSTNode<Integer, Integer> succ6 = tree.root.
                getLeft().getLeft();
        assertSame(succ4, succ6.successor());
        MyBST.MyBSTNode<Integer, Integer> succ7 = tree.root
                .getRight().getRight();
        assertSame(succ7, succ2.successor());
    }

    /**
     * Tests Successor when successor is null
     */
    @Test
    public void testSuccessorNull() {
        
        MyBST.MyBSTNode<Integer, Integer> succ1 = tree.root.
                getRight().getRight();
        assertNull(succ1.successor());
        
    }

    /**
     * Tests insert when when a node with same key already exists
     */
    @Test
    public void testInsertDuplicate() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        int val = tree.insert(10, 45);
        assertEquals(45, root.getRight().getValue().intValue());
        assertSame(root.getRight(), root.getRight().getRight().getParent());
        assertEquals("size of tree", 9, tree.size);
        assertEquals(2,val);
    }

    /**
     * Tests insert when he node's key is null
     */
    @Test (expected = NullPointerException.class)
    public void testInsertNullKey() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        int val = tree.insert(null, 5);
        assertEquals(45, root.getRight().getValue().intValue());
        assertSame(root.getRight(), root.getRight().getRight().getParent());
    }

    /**
     * Tests insert when when a node with same key doesn't exist
     */
    @Test
    public void testInsertUnique() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        Integer val = tree.insert(2, 34);
        assertEquals(2, root.getLeft().getLeft().getRight().
                getKey().intValue());
        assertEquals(34, root.getLeft().getLeft().
                getRight().getValue().intValue());
        assertSame(root.getLeft().getLeft(), root.getLeft().
                getLeft().getRight().getParent());
        assertEquals("size of tree", 10, tree.size);
        assertEquals(null,val);
    }

    /**
     * Tests Search for different nodes
     */
    @Test
    public void testSearch() {
        assertEquals(1, tree.search(3).intValue());
        assertNull(tree.search(65));
        assertEquals(8, tree.search(13).intValue());
        assertEquals(4, tree.search(6).intValue());
        assertEquals(0,tree.search(8).intValue());
        assertEquals(7, tree.search(7).intValue());
    }

    /**
     * Tests remove for 2 nodes
     */
    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(3, tree.remove(1).intValue());
        assertNull(root.getLeft().getLeft());
        assertEquals(1, tree.remove(3).intValue());
        assertEquals(6, root.getLeft().getKey().intValue());
        assertEquals("size of tree", 7, tree.size);
    }

    /**
     * Tests remove for a node which doesn't exist
     */
    @Test 
    public void testRemoveNull() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(null,tree.remove(37));
        assertEquals("size of tree", 9, tree.size);
    }

    /**
     * Tests remove for 3 nodes
     */
    @Test 
    public void testRemove3() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(2,tree.remove(10).intValue());
        assertEquals(14, root.getRight().getKey().intValue());
        assertEquals("size of tree", 8, tree.size);
        assertEquals(4,tree.remove(6).intValue());
        assertEquals(7, root.getLeft().getRight().getKey().intValue());
        assertEquals("size of tree", 7, tree.size);
        assertEquals(6,tree.remove(4).intValue());
        assertEquals(8, root.getKey().intValue());
        assertEquals("size of tree", 6, tree.size);
    }

    /**
     * Tests Inorder for the whole BST
     */
    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight().getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root.getLeft().getRight().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight());
        expectedRes.add(root.getRight().getRight().getLeft());
        expectedRes.add(root.getRight().getRight());
        assertEquals(expectedRes, tree.inorder());
    }

    /**
     * Tests Inorder when BST is empty
     */
    @Test
    public void testInorderEmpty() {
        MyBST.MyBSTNode<Integer, Integer> root2 = null;
        tree.root = root2;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        assertEquals(expectedRes, tree.inorder());
    }
}

