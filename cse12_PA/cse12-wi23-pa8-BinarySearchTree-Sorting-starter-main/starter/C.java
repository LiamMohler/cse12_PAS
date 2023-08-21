/*
 * Name: Kaustubh Paliwal
 * Email: kpaliwal@ucsd.edu
 * PID: A17353047
 * Sources Used: JDK 17 Doc, PA8 WriteUp, CSE 12 Zybooks
 *
 * This is a file containing the custom tester for CSE 12 PA8 in Winter 2023.
 * It contains sanity checks on both the classes.
 */
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * The custom tester class for PA8, which covers some basic test cases to 
 * test the implementation of the functions in class MyBST and MyBSTNode
 */
public class C {
    MyBST<Integer, Integer> tree;

    /**
     * Used to setup a MyBST named tree which will be used to test all the 
     * methods in MyBST and MyBSTNode
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
            new MyBST.MyBSTNode<>(25, 0, null);
        MyBST.MyBSTNode<Integer, Integer> one =
            new MyBST.MyBSTNode<>(20, 2, root);
        MyBST.MyBSTNode<Integer, Integer> three =
            new MyBST.MyBSTNode<>(10, 3, one);
        MyBST.MyBSTNode<Integer, Integer> four =
            new MyBST.MyBSTNode<>(22, 4, one);
        MyBST.MyBSTNode<Integer, Integer> seven =
            new MyBST.MyBSTNode<>(5, 7, three);
        MyBST.MyBSTNode<Integer, Integer> eight =
            new MyBST.MyBSTNode<>(12, 8, three);
        MyBST.MyBSTNode<Integer, Integer> twelve =
            new MyBST.MyBSTNode<>(1, 12, seven);
        MyBST.MyBSTNode<Integer, Integer> thirteen =
            new MyBST.MyBSTNode<>(8, 8, seven);
        MyBST.MyBSTNode<Integer, Integer> fourteen =
            new MyBST.MyBSTNode<>(15, 14, eight);
        

        MyBST.MyBSTNode<Integer, Integer> two =
            new MyBST.MyBSTNode<>(36, 3, root);
        MyBST.MyBSTNode<Integer, Integer> five =
            new MyBST.MyBSTNode<>(30, 5, two);
        MyBST.MyBSTNode<Integer, Integer> six =
            new MyBST.MyBSTNode<>(40, 6, two);
        MyBST.MyBSTNode<Integer, Integer> nine =
            new MyBST.MyBSTNode<>(28, 9, five);
        MyBST.MyBSTNode<Integer, Integer> ten =
            new MyBST.MyBSTNode<>(38, 10, six);
        MyBST.MyBSTNode<Integer, Integer> eleven =
            new MyBST.MyBSTNode<>(48, 11, six);
        MyBST.MyBSTNode<Integer, Integer> fifteen =
            new MyBST.MyBSTNode<>(45, 15, eleven);
        MyBST.MyBSTNode<Integer, Integer> sixteen =
            new MyBST.MyBSTNode<>(50, 16, eleven);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(one);
        root.setRight(two);
        one.setLeft(three);
        one.setRight(four);
        two.setLeft(five);
        two.setRight(six);
        three.setLeft(seven);
        three.setRight(eight);
        five.setLeft(nine);
        six.setLeft(ten);
        six.setRight(eleven);
        seven.setLeft(twelve);
        seven.setRight(thirteen);
        eight.setRight(fourteen);
        eleven.setLeft(fifteen);
        eleven.setRight(sixteen);
        tree.size = 17;
    }

    /**
     * Used to test the successor() method in MyBSTNode which gives the 
     * accesending order of the keys in the MyBST tree
     */
    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(treeRoot.getRight().getLeft().getLeft(), 
            treeRoot.successor());
        MyBST.MyBSTNode<Integer, Integer> fourteen = 
            tree.root.getLeft().getLeft().getRight().getRight();
        assertSame(treeRoot.getLeft(), fourteen.successor());
        MyBST.MyBSTNode<Integer, Integer> largest = 
            tree.root.getRight().getRight().getRight().getRight();
        assertNull(largest.successor());
    }

    /**
     * Used to test the insert() function in MyBST which inserts a new element
     * into the MyBST tree. It also contains a NullPointerException case 
     * when the argument is null
     */
    @Test(expected = NullPointerException.class)
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertNull(tree.insert(26, 15));
        tree.insert(null, 4);
        assertEquals(25, root.getKey().intValue());
        assertEquals(36, 
            root.getRight().getKey().intValue());
        assertEquals(30, 
            root.getRight().getLeft().getKey().intValue());
        assertEquals(28, 
            root.getRight().getLeft().getLeft().getKey().intValue());
        assertEquals(26, 
            root.getRight().getLeft().getLeft().getLeft().getKey().intValue());
        assertSame(root.getRight().getLeft().getLeft(), 
            root.getRight().getLeft().getLeft().getLeft().getParent());
        assertEquals("size of tree", 18, tree.size);

        assertEquals(15, tree.insert(26, 20).intValue());
        assertEquals(25, root.getKey().intValue());
        assertEquals(36, root.getRight().getKey().intValue());
        assertEquals(30, 
            root.getRight().getLeft().getKey().intValue());
        assertEquals(28, 
            root.getRight().getLeft().getLeft().getKey().intValue());
        assertEquals(26, 
            root.getRight().getLeft().getLeft().getLeft().getKey().intValue());
        assertEquals(20, 
            root.getRight().
            getLeft().getLeft().getLeft().getValue().intValue());
        assertSame(root.getRight().getLeft().getLeft(), 
            root.getRight().getLeft().getLeft().getLeft().getParent());
        assertEquals("size of tree", 18, tree.size);
    }

    /**
     * Used to test the search() method in MyBST which searches for a given 
     * key inside the MyBST tree
     */
    @Test
    public void testSearch() {
        assertEquals(0, tree.search(25).intValue());
        assertEquals(12, tree.search(1).intValue());
        assertEquals(16, tree.search(50).intValue());
        assertNull(tree.search(46));
        assertNull(tree.search(null));
    }

    /**
     * Used to test the remove() function inside the class MyBST which removes
     * a particular key for the MyBST tree and returns the value it just 
     * removed
     */
    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(14, tree.remove(15).intValue());
        assertNull(root.getLeft().getLeft().getRight().getRight());
        assertEquals("size of tree", 16, tree.size);

        assertEquals(5, tree.remove(30).intValue());
        assertNull(root.getRight().getLeft().getLeft());
        assertEquals(9, 
            root.getRight().getLeft().getValue().intValue());
        assertEquals(28, 
            root.getRight().getLeft().getKey().intValue());
        assertSame(root.getRight(), root.getRight().getLeft().getParent());
        assertEquals("size of tree", 15, tree.size);

        assertEquals(0, tree.remove(25).intValue());
        assertNull(root.getRight().getLeft());
        assertEquals(28, root.getKey().intValue());
        assertEquals(9, root.getValue().intValue());
        assertSame(root, root.getRight().getParent());
        assertSame(root.getLeft().getParent(), root.getRight().getParent());
        assertEquals("size of tree", 14, tree.size);

        assertNull(tree.remove(17));
        assertNull(tree.remove(null));
        assertEquals("size of tree", 14, tree.size);
    }

    /**
     * Used to test the inorder() method inside the class MyBST which returns
     * an ArrayList with the MyBST keys arranged in ascending value
     */
    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft().getLeft().getLeft());
        expectedRes.add(root.getLeft().getLeft().getLeft());
        expectedRes.add(root.getLeft().getLeft().getLeft().getRight());
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft().getLeft().getRight());
        expectedRes.add(root.getLeft().getLeft().getRight().getRight());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft().getLeft());
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        expectedRes.add(root.getRight().getRight().getLeft());
        expectedRes.add(root.getRight().getRight());
        expectedRes.add(root.getRight().getRight().getRight().getLeft());
        expectedRes.add(root.getRight().getRight().getRight());
        expectedRes.add(root.getRight().getRight().getRight().getRight());
        assertEquals(expectedRes, tree.inorder());

        MyBST<Integer, Integer> empty = new MyBST<>();
        assertNotNull(empty.inorder());
    }
}
