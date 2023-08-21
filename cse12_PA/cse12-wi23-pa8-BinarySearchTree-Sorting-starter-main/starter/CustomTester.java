/**
 * This file contains part of the private tests.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * This class contains the custom/private test cases for MyBST.
 */
public class CustomTester {
    /** Instance variables */
    MyBST<Integer, Integer> tree;


    /**
     * Helper method to initialize all instance variables of MyBST
     *
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
    }


    /**
     * Method to test successor function.
     *
     */
    @Test
    public void testSuccessorCustom() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(treeRoot.getLeft().getRight(), 
            treeRoot.getLeft().successor());
        assertSame(treeRoot.getLeft(), 
            treeRoot.getLeft().getLeft().successor());
        assertSame(treeRoot, treeRoot.getLeft().getRight().successor());
        assertSame(treeRoot.getRight().successor(), null);
        assertSame(treeRoot.getRight().getLeft().successor(), 
            treeRoot.getRight());

    }

    /**
     * Helper method to test insert function.
     *
     */
    @Test
    public void testInsertCustom() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(10, 1);
        assertEquals(10, 
            root.getRight().getRight().getKey().intValue());
        assertEquals(1, 
            root.getRight().getRight().getValue().intValue());
        assertSame(root.getRight(),
            root.getRight().getRight().getParent());
        assertEquals("size of tree", 
            7, tree.size);

        tree.insert(10, 7);
        assertEquals(10, 
            root.getRight().getRight().getKey().intValue());
        assertEquals(7, 
            root.getRight().getRight().getValue().intValue());
        assertSame(root.getRight(), root.getRight().getRight().getParent());
        assertEquals("size of tree", 7, tree.size);

        assertEquals(null, tree.insert(7, 1));
        assertEquals(7, 
            root.getRight().getRight().getLeft().getKey().intValue());
        assertEquals(1, 
            root.getRight().getRight().getLeft().getValue().intValue());
        assertSame(root.getRight().getRight(), 
            root.getRight().getRight().getLeft().getParent());
        assertEquals("size of tree", 8, tree.size);


        assertThrows(NullPointerException.class, () -> {
            assertEquals(null, tree.insert(null, 1));
        });


    }


    /**
     * Method to test search function.
     *
     */
    @Test
    public void testSearchCustom() {
        assertEquals(null, tree.search(null));
        assertNull(tree.search(10));
        assertEquals(30, tree.search(3).intValue());
        assertEquals(1, tree.search(6).intValue());
        assertEquals(1, tree.search(4).intValue());

    }

    /** 
     * Helper method to test remove on root
     *
     */
    @Test
    public void testRemoveCustomRoot() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(1, tree.remove(4).intValue());
        root = tree.root;
        assertEquals(2, root.getLeft().getKey().intValue());
        assertEquals(6, root.getRight().getKey().intValue());
        assertEquals(null, root.getRight().getLeft());
        assertEquals(5, root.getKey().intValue());
        assertEquals(50, root.getValue().intValue());
        assertEquals("size of tree", 5, tree.size);
    }
    /**
     * Helper method to test remove on leaf
     *
     */
    @Test
    public void testRemoveCustomLeaf() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(2, tree.remove(1).intValue());
        root = tree.root;
        assertEquals(2, root.getLeft().getKey().intValue());
        assertEquals(6, root.getRight().getKey().intValue());
        assertEquals(4, root.getKey().intValue());


        assertEquals(null, root.getLeft().getLeft());
        assertEquals(3, 
            root.getLeft().getRight().getKey().intValue());
        assertEquals("size of tree", 5, tree.size);
    }

    /**
     * Helper method to test remove on one child.
     *
     */
    @Test
    public void testRemoveCustomOneChildRightSideTree() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(1, tree.remove(6).intValue());
        root = tree.root;
        assertEquals(2, root.getLeft().getKey().intValue());
        assertEquals(5, root.getRight().getKey().intValue());
        assertEquals(4, root.getKey().intValue());


        assertEquals(null, root.getRight().getLeft());
        assertEquals(null, root.getRight().getRight());
        assertEquals("size of tree", 5, tree.size);
    }

    /**
     * Helper method to test remove on two child
     *
     */
    @Test
    public void testRemoveCustomTwoChildLeftSideTree() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(1, tree.remove(2).intValue());
        root = tree.root;
        assertEquals(3, root.getLeft().getKey().intValue());
        assertEquals(6, root.getRight().getKey().intValue());
        assertEquals(4, root.getKey().intValue());


        assertEquals(1, 
            root.getLeft().getLeft().getKey().intValue());
        assertEquals(null, root.getLeft().getRight());
        assertEquals("size of tree", 5, tree.size);
    }

    /**
     * Helper method to test remove on two child pt.2
     *
     */
    @Test
    public void testRemoveCustomTwoChildLeftSideTree2() {
        tree.insert(7, 7);
        tree.insert(8, 8);
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(1, tree.remove(6).intValue());
        root = tree.root;
        assertEquals(2, root.getLeft().getKey().intValue());
        assertEquals(7, root.getRight().getKey().intValue());
        assertEquals(4, root.getKey().intValue());
        assertEquals(5, 
            root.getRight().getLeft().getKey().intValue());
        assertEquals(8, 
            root.getRight().getRight().getKey().intValue());


        assertEquals(1, 
            root.getLeft().getLeft().getKey().intValue());
        assertEquals(3, 
            root.getLeft().getRight().getKey().intValue());
        assertEquals("size of tree", 7, tree.size);
    }


    /**
     * Helper method to test inorder function
     *
     */
    @Test
    public void testInorder() {
        tree = new MyBST<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        assertEquals(expectedRes, tree.inorder());
    }

}
