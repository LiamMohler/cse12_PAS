/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * 
 * This file (MyBST.java) is meant to replicate the functionality of 
 * java's BST data structure.
 */
import java.util.ArrayList;


/**
 * This class replicates the BST data structure in java.
 * 
 * Instance variables:
 * size - The size of the BST
 * root - the root node of the tree
 */
public class MyBST<K extends Comparable<K>, V> {

    /** Instance variables */
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * This method will return the size of the BST
     * @return - returns size
     */
    public int size() {
        return size;
    }

    /**
     * This method will insert a key/value pair into the BST
     * @param key - the key to insert
     * @param value - the value to insert
     * @return - the the vlaue replacedd if it is, otherwise null
     */
    public V insert(K key, V value) {
        if(key == null){
            throw new NullPointerException();
        }
        //curNode.getKey().compareTo(key) < 0 -> means that key is greater
        MyBSTNode<K, V> curNode = root;
        while(true){
            if(curNode.getKey().compareTo(key) > 0){
                if(curNode.getLeft() == null){
                    MyBSTNode<K, V> newNode = 
                        new MyBSTNode<K,V>(key, value, curNode);
                    curNode.setLeft(newNode);
                    size ++;
                    return null;
                }
                else{
                    curNode = curNode.getLeft();
                }
            }
            else if(curNode.getKey().compareTo(key) < 0){
                if(curNode.getRight() == null){
                    MyBSTNode<K, V> newNode = 
                        new MyBSTNode<K,V>(key, value, curNode);
                    curNode.setRight(newNode);
                    size ++;
                    return null;
                }
                else{
                    curNode = curNode.getRight();
                }
            }
            else{
                V retV = curNode.getValue();
                curNode.setValue(value);
                return retV;
            }
        }
    }

    /**
     * This method will search for a specified key in the BST and return its
     * value.
     * @param key - the key to search for
     * @return - the valye of the key if found, otherwise null.
     */
    public V search(K key) {
        if(key == null){
            return null;
        }

        MyBSTNode<K, V> curNode = root;
        while(true){
            if(curNode.getKey().compareTo(key) > 0){
                if(curNode.getLeft() == null){
                    return null;
                }
                else{
                    curNode = curNode.getLeft();
                }
            }
            else if (curNode.getKey().compareTo(key) < 0){
                if(curNode.getRight() == null){
                    return null;
                }
                else{
                    curNode = curNode.getRight();
                }
            }
            else{
                return curNode.getValue();
            }
        }
    }


    /**
     * This method will remove a key from the BST.
     * @param key - the key to remove
     * @return - the value of the removed key.
     */
    public V remove(K key) {

        //the null case
        if(key == null){
            return null;
        }

        //the root case
        if(key.equals(root.getKey())){
            size --;
            V retVal = root.getValue();

            if(root.getLeft() != null && root.getRight() == null){
                //has a left node but no right node.
                root = root.getLeft();
                if(root != null)
                    root.setParent(null);
                return retVal;
            }
            else if(root.getLeft() == null && root.getRight() != null){
                //has a left node but no right node.
                root = root.getRight();
                if(root != null)
                    root.setParent(null);
                return retVal;
            }
            else if(root.getLeft() == null && root.getRight() == null){
                root = null;
            }
            else{
                //the case that it has left and right node
                MyBSTNode<K, V> suc = root.successor();
                K tempK = suc.getKey();
                V tempV = suc.getValue();
                remove(tempK);
                size++;
                root.setKey(tempK);
                root.setValue(tempV);
            }

            
            //do some stuff to set left and right nodes
            return retVal;
        }




        //case of not null or root
        MyBSTNode<K, V> curNode = root;
        while(true){
            if(curNode.getKey().compareTo(key) > 0){
                if(curNode.getLeft() == null){
                    return null;
                }
                else{
                    curNode = curNode.getLeft();
                }
            }
            else if (curNode.getKey().compareTo(key) < 0){
                if(curNode.getRight() == null){
                    return null;
                }
                else{
                    curNode = curNode.getRight();
                }
            }
            else{
                //do stuff to remove CURNODE = NODE WANT TO REMOVE
                size--;
                V retVal = curNode.getValue();

                if(curNode.getLeft()  == null && curNode.getRight() == null){
                    //remove from tree
                    
                    if(curNode.getParent().getLeft() != null && curNode.getParent().getLeft().equals(curNode)){
                        curNode.getParent().setLeft(null);
                    }
                    else{
                        curNode.getParent().setRight(null);
                    }
                    

                }
                else if(curNode.getLeft()!= null&&curNode.getRight() != null){
                    //remove from tree 2 -> set to successor
                    MyBSTNode<K, V> suc = curNode.successor();
                    K tempK = suc.getKey();
                    V tempV = suc.getValue();
                    remove(tempK);
                    size++;
                    curNode.setKey(tempK);
                    curNode.setValue(tempV);
                }
                else{
                    //remove from tree 1 -> set child to node spot
                    if(curNode.getLeft() != null){
                        if(curNode.getParent().getLeft() != null && curNode.getParent().getLeft().equals(curNode)){
                            curNode.getParent().setLeft(curNode.getLeft());
                            curNode.getLeft().setParent(curNode.getParent());
                        }
                        else{
                            curNode.getParent().setRight(curNode.getLeft());
                            curNode.getLeft().setParent(curNode.getParent());
                        }
                    }
                    else{
                        if(curNode.getParent().getLeft() != null && curNode.getParent().getLeft().equals(curNode)){
                            curNode.getParent().setLeft(curNode.getRight());
                            curNode.getRight().setParent(curNode.getParent());
                        }
                        else{
                            curNode.getParent().setRight(curNode.getRight());
                            curNode.getRight().setParent(curNode.getParent());
                        }
                    }
                }
                return retVal;
            }
        }
    }


    /**
     * This method will turn the BST into an in order arraylist.
     * @return - the arraylist of the BST in order.
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        // TODO
        ArrayList<MyBSTNode<K, V>> ret = new ArrayList<MyBSTNode<K, V>>();
        MyBSTNode<K, V> smallest = root;
        if(smallest == null){
            return ret;
        }
        while(true){
            if(smallest.getLeft() == null){
                ret.add(smallest);
                break;
            }
            smallest = smallest.getLeft();
        }
        while(smallest.successor() != null){
            ret.add(smallest.successor());
            smallest = smallest.successor();
        }
        return ret;
    }


    /**
     * This class replicates the functionality of a Node in a BST.
     * 
     * Instance variables:
     * key - The key to a certain node
     * value - the value of a certain node
     * parent - the parent
     * left - the left child
     * right - the right child
     */
    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Finds the successor and returns it.
         *
         * @return - the node that is the successor.
         */
        public MyBSTNode<K, V> successor() {
            //if it doesnt have right then it doesnt have somiething bigger\
            //when smaller nodes above tree
            if(getRight() == null){
                MyBSTNode<K, V> tempNode = this;
                while(tempNode.getParent() != null){
                    if(tempNode.getParent().getLeft() != null && tempNode.getParent().getLeft().equals(tempNode)){
                        return tempNode.getParent();
                    }
                    tempNode = tempNode.getParent();
                }
                return null;
            }
            //loop through the left of the right to find smalled bigger.
            //for when there are smaller nodes underneath
            MyBSTNode<K, V> curNode = this.getRight();
            while(curNode.getLeft() != null){
                curNode = curNode.getLeft();
            }
            return curNode;
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }
}
