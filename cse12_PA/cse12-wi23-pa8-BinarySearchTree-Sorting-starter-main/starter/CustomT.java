/**
 * This file contains custom tests to test the MyBST class and methods
*/

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * What is says in the file header
 */
public class CustomT {
    MyBST<Integer, Integer> tree;

    /**
     * Creates the tree
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
        new MyBST.MyBSTNode<>(15, 1, null);
        MyBST.MyBSTNode<Integer, Integer> ten =
        new MyBST.MyBSTNode<>(10, 1, root);
        MyBST.MyBSTNode<Integer, Integer> twenty =
        new MyBST.MyBSTNode<>(20, 1, root);
        MyBST.MyBSTNode<Integer, Integer> nine =
        new MyBST.MyBSTNode<>(9, 2, ten);
        MyBST.MyBSTNode<Integer, Integer> twelve =
        new MyBST.MyBSTNode<>(12, 30, ten);
        MyBST.MyBSTNode<Integer, Integer> seventeen =
        new MyBST.MyBSTNode<>(17, 50, twenty);
        MyBST.MyBSTNode<Integer, Integer> twentythree =
        new MyBST.MyBSTNode<>(23, 50, twenty);
        MyBST.MyBSTNode<Integer, Integer> eleven =
        new MyBST.MyBSTNode<>(11, 50, twelve);
        MyBST.MyBSTNode<Integer, Integer> eighteen =
        new MyBST.MyBSTNode<>(18, 50, seventeen);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(ten);
        root.setRight(twenty);
        ten.setLeft(nine);
        ten.setRight(twelve);
        twelve.setLeft(eleven);
        twenty.setLeft(seventeen);
        twenty.setRight(twentythree);
        seventeen.setRight(eighteen);
        tree.size = 9;
    }

    /**
     * Tests the successor method of the BST
     */
    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> ten = tree.root.getLeft();
        assertSame(ten.getRight().getLeft(), ten.successor());
        MyBST.MyBSTNode<Integer, Integer> eighteen = 
        tree.root.getRight().getLeft().getRight();
        assertSame(eighteen.getParent().getParent(),eighteen.successor());
    }

    /**
     * Tests the insert method of the BST
     */
    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(19, 1);
        assertEquals(19, 
        root.getRight().getLeft().getRight().getRight().getKey().intValue());
        assertSame(root.getRight().getLeft().getRight(), 
        root.getRight().getLeft().getRight().getRight().getParent());
        assertEquals(1,tree.insert(20, 3).intValue());
        assertEquals(3,root.getRight().getValue().intValue());
        assertEquals("size of tree", 10, tree.size);
    }

    /**
     * Tests the search method of the BST
     */
    @Test
    public void testSearch() {
        assertEquals(50, tree.search(11).intValue());
        assertNull(tree.search(13));
    }

    /**
     * Tests all the remove method cases of the BST
     */
    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(1, tree.remove(10).intValue());
        assertNull(root.getLeft().getRight().getLeft());
        assertNull(root.getLeft().getRight().getRight());
        assertEquals(11, root.getLeft().getKey().intValue());
        assertEquals(12, root.getLeft().getRight().getKey().intValue());
        assertEquals(9, root.getLeft().getLeft().getKey().intValue());

        assertEquals(50, tree.remove(17).intValue());
        assertEquals(18,root.getRight().getLeft().getKey().intValue());
        assertNull(root.getRight().getLeft().getLeft());
        assertNull(root.getRight().getLeft().getRight());


        assertEquals(2, tree.remove(9).intValue());
        assertEquals(11,root.getLeft().getKey().intValue());
        assertEquals(12,root.getLeft().getRight().getKey().intValue());
        assertNull(root.getLeft().getLeft());
        assertEquals("size of tree", 6, tree.size);
    }

    /**
     * Tests the inorder method of the BST
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
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight().getLeft().getRight());
        expectedRes.add(root.getRight());
        expectedRes.add(root.getRight().getRight());
        assertEquals(expectedRes, tree.inorder());
    }
}