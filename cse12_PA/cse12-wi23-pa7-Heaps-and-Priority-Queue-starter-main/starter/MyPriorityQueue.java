/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * 
 * This file (MyPriorityQueue.java) is meant to replicate the functionality of 
 * java's priorityqueue data structure.
 */

import java.util.Collection;

/**
 * This class replicates the priority queue data strucutre in java using Heaps.
 * 
 * Instance variables:
 * heap - the data stored in the priority queue.
 */
public class MyPriorityQueue<E extends Comparable<E>>{

    /** Instance variables */
    protected MyMinHeap<E> heap;

    /**
     * This method is the no args constructor of MyPriorityQueue, 
     * will make a heap object with nothing else inside.
     */
    public MyPriorityQueue(){
        //initialize
        heap = new MyMinHeap<>();
    }

    /**
     * This method is the arg constructor of MyPriorityQueue, 
     * will make a heap object with the given collection.
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        //initialize w/ colleciton
        heap = new MyMinHeap<>(collection);
    }

    /**
     * This method will add the given element to the queue using insert.
     * 
     * @param element - the element to insert
     */
    public void push(E element){
        //call heap func.
        heap.insert(element);
    }

    /**
     * This method will return the element at the front of the list.
     * 
     * @return - the element at the front (root).
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * This method will remove and return the element at the front of the list.
     * 
     * @return - the element at the front (root).
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * This method will return the size of the queue.
     * 
     * @return - the size of the queue.
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * This method will clear the queue.
     * 
     */
    public void clear(){
        heap.clear();
    }
}
