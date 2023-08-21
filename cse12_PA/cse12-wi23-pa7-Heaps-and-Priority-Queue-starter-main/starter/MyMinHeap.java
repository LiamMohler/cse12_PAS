/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * 
 * This file (MyMinHeap.java) is meant to replicate the functionality of 
 * java's heap data structure.
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements the MinHeapInterface Interface to replicate 
 * java's ADT: heap.
 * 
 * Instance variables:
 * data - the data stored in the heap.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E>{

    /** Instance variables */
    protected ArrayList<E> data;


    /**
     * This method is the no args constructor of MyMinHeap, will make a heap
     * with nothing else inside.
     */
    public MyMinHeap(){
        //initialize to new arraylist.
        data = new ArrayList<>();
    }

    /**
     * This method is the arg constructor of MyMinHeap, will make a heap
     * with the passed in collection.
     * 
     * @param collection - the collection to initialize the heap with
     */
    public MyMinHeap(Collection<? extends E> collection){
        //if collection is null then throw error
        if(collection == null){
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        for (int i = data.size() - 1; i >= 0; i--) {
            //if any data is null then throw error
            if(data.get(i) == null){
                data = new ArrayList<>();
                throw new NullPointerException();
            }
            //otherwise percolate as intended.
            percolateDown(i);
        }
    }
    /**
     * This method will swap the data in the from and to indicies.
     * 
     * @param from - the data to switch #1
     * @param to - the data to switch #2
     */
    protected void swap(int from, int to){
        //oldData to keep track of data
        E oldData = data.get(from);
        //change each
        data.set(from, data.get(to));
        data.set(to, oldData);
    }
    /**
     * This method will find the parent of a passed in index.
     * 
     * @param index - the index wnt to get parent of
     * @return - returns the parent of the passed in index.
     */
    protected static int getParentIdx(int index){
        //parent = index-1/2
        return((index-1)/2);
    }

    /**
     * This method will find the left child of the passed in index.
     * 
     * @param index - the index wnt to find
     * @return - returns the child of the passed in index.
     */
    protected static int getLeftChildIdx(int index){
        //leftchild = index*2 +1
        return((index*2)+1);
    }

    /**
     * This method will find the right child of the passed in index.
     * 
     * @param index - the index wnt to find
     * @return - returns the child of the passed in index.
     */
    protected static int getRightChildIdx(int index){
        //leftchild = index*2 +2
        return((index*2)+2);
    }

    /**
     * This method will find the min child of a given index
     * 
     * @param index - the index wnt to find
     * @return - returns the min child of the passed in index.
     */
    protected int getMinChildIdx(int index){
        int leftChildIdx = getLeftChildIdx(index);
        int rightChildIdx = getRightChildIdx(index);
        //if leaf node
        if(leftChildIdx > data.size()-1){
            return -1;
        }
        //if only have one child
        if(rightChildIdx > data.size()-1){
            return leftChildIdx;
        }
        //if has 2 child, look through to find smaller.
        if(data.get(leftChildIdx).compareTo(data.get(rightChildIdx)) <= 0){
            return leftChildIdx;
        }

        //return the right if its not left.
        return rightChildIdx;
    }

    /**
     * This method will percolate up a given object given its index.
     * 
     * @param index - the index wnt to perc up
     */
    protected void percolateUp(int index){
        //forever loop
        while(true){
            //if we are at 0, no way to perc up, no parent, break.
            if(index == 0){
                break;
            }
            //find parIdx
            int parIdx = getParentIdx(index);
            //if it is less then parent go up
            if(data.get(index).compareTo(data.get(parIdx)) < 0){
                swap(index, parIdx);
                index = parIdx;
            }
            //if its not then break.
            else{
                break;
            }
        }
    }

    /**
     * This method will percolate down a given object given its index.
     * 
     * @param index - the index wnt to perc down
     */
    protected void percolateDown(int index){
        while(true){
            //if has no child then break
            if(getMinChildIdx(index) == -1){
                break;
            }
            int chIdx = getMinChildIdx(index);
            //if the child is smaller, then swap
            if(data.get(index).compareTo(data.get(chIdx)) > 0){
                swap(index, chIdx);
                index = chIdx;
            }
            //break if its bigger
            else{
                break;
            }
        }
    }

    /**
     * This method will delete a given object given its index.
     * 
     * @param index - the index wnt to perc down
     * @return - the deleted object.
     */
    protected E deleteIndex(int index){
        //if its the last index then just remove
        if(index == data.size()-1){
            return data.remove(index);
        }
        //store data to ret later.
        E removeData = data.get(index);
        //swap then delete
        swap(index, data.size()-1);
        data.remove(data.size()-1);

        //now find out to perclate down/up
        if(getMinChildIdx(index) == -1){
            percolateUp(index);
        }
        else if(data.get(getMinChildIdx(index)).compareTo(data.get(index))<0){
            percolateDown(index);
        }
        else{
            percolateUp(index);
        }
        //ret
        return removeData;
    }

    /**
     * This method will insert a given element into the MinHeap.
     * 
     * @param element - the element to add
     */
    public void insert(E element){
        //throw error if null
        if(element == null){
            throw new NullPointerException();
        }
        //add the element to end then percolate
        data.add(element);
        percolateUp(data.size()-1);
    }

    /**
     * This method will get the root, the min of the heap.
     * 
     * @return - the root/min
     */
    public E getMin(){
        if(data == null || data.isEmpty()){
            return null;
        }
        //root is min
        return data.get(0);
    }
    /**
     * This method will remove the root from the heap.
     * 
     * @return - returns the removed element.
     */
    public E remove(){
        if(getMin() == null){
            return null;
        }
        //delete index does all of remove
        return deleteIndex(0);
    }
    /**
     * This method will return the size of the ehap
     * 
     * @return - returns the size.
     */
    public int size(){
        return data.size();
    }

    /**
     * This method will clear the heap, removing all elements.
     * 
     */
    public void clear(){
        data.clear();
    }
}
