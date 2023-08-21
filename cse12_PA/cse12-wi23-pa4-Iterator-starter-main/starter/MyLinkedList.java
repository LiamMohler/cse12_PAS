
/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * 
 * This file (MyLinkedList.java) is meant to replicate the functionality of 
 * java's LinkedList data structure.  
 */

import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;
 /**
 * This class replicates the LinkedList data structure in java.
 * 
 * Instance variables:
 * size - The size of the MyLinkedList
 * head - The head node (first)
 * tail - The tail node (last)
 */

public class MyLinkedList<E> extends AbstractList<E> {

    /** Instance variables */
    int size;
    Node head;
    Node tail;

    /**
     * This method will return the listIteratior
     * @return - returns the listIterator
     */
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }
    /**
     * This method will return the iterator
     * @return - returns the iterator
     */
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    /**
     * The no arg constructor of a Linked List, sets defaults for size, head
     * tail.
     */
    public MyLinkedList() {
        /* Add your implementation here */
        // TODO
        size = 0;
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * This function will return the size of the linked list.
     * 
     * @return - returns the size
     */
    @Override
    public int size() {
        // need to implement the size method
        return size;
    }

    /**
     * This method will get the value of a node in MyLinkedList.
     * 
     * @param index - index to get
     * @return - the element at the index.
     */
    @Override
    public E get(int index) {
        //if not in bounds, throw error
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //make temp node
        Node curNode = head.getNext();
        int curIndex = 0;
        //while it is not index, we want to go until it reachest index
        while (curIndex < index){
            //next node is set to curNode, increment index
            curNode = curNode.getNext();
            curIndex++;
        }
        //return the element
        return (E) curNode.getElement(); 
    }

    /**
     * This method add a node into the list into an index with data.
     * 
     * @param index - index to add into
     * @param data - element to add
     */
    @Override
    public void add(int index, E data) {
        /* Add your implementation here */
        // TODO
        //if index out of bounds then throw error
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        //if data is null, then null pointer error
        if(data == null){
            throw new NullPointerException();
        }
        Node curNode = head.getNext();
        int curIndex = 0;
        while (curIndex < index){
            //loop though nodes to index
            curNode = curNode.getNext();
            curIndex++;
        }
        Node newNode = new Node(data);
        //set the new node infront of curNode, and change pointers.
        curNode.getPrev().setNext(newNode);
        newNode.setPrev(curNode.getPrev());
        curNode.setPrev(newNode);
        newNode.setNext(curNode);
        size++;
    }

    /**
     * This method add a node into the list at the end with given data.
     * 
     * @param data - element to add
     * @return - true if worked, false if didnt.
     */
    public boolean add(E data) {
        //if data is null, throw error
        if(data == null){
            throw new NullPointerException();
        }
        //create new node, and set pointers to it
        Node newNode = new Node(data);
        tail.getPrev().setNext(newNode);
        newNode.setPrev(tail.getPrev());
        tail.setPrev(newNode);
        newNode.setNext(tail);
        //increase size
        size++;
        return true; // TODO
    }

    /**
     * This method set the node at a given index to a given element.
     * 
     * @param index - index to set
     * @param data - element to set to
     * @return - returns the old data
     */
    public E set(int index, E data) {
        //if is empty, cannot preform, throw error
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //if not in bounds throw error
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        //if data is null throw error.
        if(data == null){
            throw new NullPointerException();
        }
        //transverse list
        Node curNode = head.getNext();
        int curIndex = 0;
        while (curIndex < index){
            curNode = curNode.getNext();
            curIndex++;
        }
        //store old data (needed for return)
        E oldData = curNode.getElement();
        //set element
        curNode.setElement(data);
        return (E) oldData; // TODO
    }

    /**
     * This method will remove a Node in the linked list at a given index.
     * 
     * @param index - index to remove
     * @return - will return the data that was remocved
     */
    public E remove(int index) {
        //if is empty, cannot remove, error
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //if out of bounds, cannot remove, error
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        //transverse list until it reaches index
        Node curNode = head.getNext();
        int curIndex = 0;
        while (curIndex < index){
            curNode = curNode.getNext();
            curIndex++;
        }
        //remove it from list by setting the prev to the next, and the next's
        //prev to the prev.
        curNode.getPrev().setNext(curNode.getNext());
        curNode.getNext().setPrev(curNode.getPrev());
        //decrement size and return
        size--;
        return (E) curNode.getElement();
    }

    /**
     * This method will clear the Linked List of all nodes (except dummy).
     */
    public void clear() {
        /* Add your implementation here */
        //lose everything by setting head to tail, tail to head.
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * This method check if the list is empty.
     * 
     * @return - returns if it is empty.
     */
    public boolean isEmpty() {
        //its empty if the next element is null (only null elements are dummy)
        if(head.getNext().getElement() == null){
            return true;
        }
        return false;  // TODO
    }

    /**
     * This method will return the node of the list at a given index.
     * 
     * @param index - index to add into
     * @return - returns the node at an index
     */
    protected Node getNth(int index) {
        //if out of bounds, throw error
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        //if is empty throw error, there are no elements
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //make temp node -> transverse
        Node curNode = head.getNext();
        int curIndex = 0;
        while (curIndex < index){
            curNode = curNode.getNext();
            curIndex++;
        }
        //return the node
        return (Node) curNode; 
    }
    
    /**
     * This class replicates the Iterator data structure in java.
     * 
     * Class variables:
     * left - left node
     * right - right node
     * idx - cur index
     * forward - moving fwd
     * canRemoveOrSet - can remove/set
     */
    protected class MyListIterator implements ListIterator<E> {

        // class vars
        Node left,right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
         * This method will create the ListIterator. Default constructor
         */
        public MyListIterator(){
            canRemoveOrSet = false;
            forward = true;
            idx = 0;
            left = head;
            right = head.next;
        }
        /**
         * This method will return true if there is a next element.
         * @return - true if has next element, else false.
         */
        public boolean hasNext() {
            // your code here
            if(right.getElement() != null){
                return true;
            }
            return false;
        }
        /**
         * This method will return the elemnt of the next node. Will change
         * the index, and if it can remove/set.
         * @return - the element of the next node.
         */
        public E next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            idx++;
            canRemoveOrSet = true;
            forward = true;
            left = left.getNext();
            right = right.getNext();
            return left.getElement();
        }
        /**
         * This method will return true if there is a previous element.
         * @return - true if has previous element, else false.
         */
        public boolean hasPrevious(){
            if(left.getElement() != null){
                return true;
            }
            return false;
        }
        /**
         * This method will return the elemnt of the previous node. Will change
         * the index, and if it can remove/set, anf fwd.
         * @return - the element of the previous node.
         */
        public E previous(){
            if(!hasPrevious()){
                throw new NoSuchElementException();
            }
            idx--;
            canRemoveOrSet = true;
            forward = false;
            left = left.getPrev();
            right = right.getPrev();
            return right.getElement();
        }
        /**
         * This method will return the index of the element that would be 
         * returned by next.
         * @return - the next index
         */
        public int nextIndex(){
            if(size == 0){
                return 0;
            }
            if(!hasNext()){
                return idx;
            }
            return idx;
        }
        /**
         * This method will return the index of the element that would be 
         * returned by previous.
         * @return - the previous index
         */
        public int previousIndex(){
            if(!hasPrevious()){
                return -1;
            }
            return idx-1;
        }
        /**
         * This method will add the given element into the LinkedList.
         * @param element - the element to add.
         */
        public void add(E element){
            if(element == null){
                throw new NullPointerException();
            }

            Node newNode = new Node(element);
            newNode.setNext(right);
            newNode.setPrev(left);
            left.setNext(newNode);
            right.setPrev(newNode);
            left = newNode;
            right = newNode.getNext();
            canRemoveOrSet = false;
            if(size() == 0){
                idx = 0;
            }
            idx++;
            
        }
        /**
         * This method will set the current index to the given element.
         * @param element - the element to set.
         */
        public void set(E element){
            if(element == null){
                throw new NullPointerException();
            }
            if(canRemoveOrSet == false){
                throw new IllegalStateException();
            }
            if(forward)
                left.setElement(element);
            else
                right.setElement(element);
        }
        /**
         * This method will remove the current index from the LinkedList.
         */
        public void remove(){
            if(canRemoveOrSet == false){
                throw new IllegalStateException();
            }
            canRemoveOrSet = false;
            if(forward){
                idx--;
                left.getPrev().setNext(right);
                right.setPrev(left.getPrev());
                left = left.getPrev();
            }
            else{
                right.getNext().setPrev(left);
                left.setNext(right.getNext());
                right = right.getNext();
            }
        }
    }
}
