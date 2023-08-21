/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * 
 * This file (MyDeque.java) is meant to replicate the functionality of 
 * java's Deque data structure.  
 */


/**
 * This class implements the Deque Interface to replicate java's ADT: deque.
 * 
 * Instance variables:
 * size - size of the deque
 * rear - end of the deque
 * front - front of the deque
 * data - data held in the deque
 */
public class MyDeque<E> implements DequeInterface<E>{
    Object[] data;
    int size;
    int rear;
    int front;
    /**
     * Constructor to create new MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyDeque(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        //initialize
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }
    /**
     * Returns size of the deque
     *
     * @return - the size
     */
    public int size(){
        return size;
    }
    /**
     * Extends the capacity of the Deque by 10 if its size is greater than
     * 0, otherwise will extend by 10. Then will re-order the array.
     */
    public void expandCapacity(){
        if(data.length == 0){
            data = new Object[10];
            return;
        }
        Object[] newData = new Object[data.length*2];
        // create/sort the array into the iniital output.
        //will need two forloops to transverse, front-end, begining-rear
        if(front > rear){
            //both idx will keep track of where to add into newData
            // x -> x -> R -> X -> F -> x
            int bothIdx = 0;
            for(int i = front; i-front < data.length - front; i++, bothIdx++){
                newData[bothIdx] = data[i];
            }
            for(int i = 0; i < rear+1; i++, bothIdx++){
                newData[bothIdx] = data[i];
            }
        }
        //only need one
        else{
            for(int i = 0, j = front; j < rear+1;i++,j++){
                newData[i] = data[j];
            }
        }
        //by the end all of these will be the same.
        data = newData;
        front = 0;
        if(size == 0 || size == 1){
            rear = 0;
        }
        else{
            rear = size-1;
        }
    }
    /**
     * Adds the element given to the front of the Deque.
     * 
     * @param element - the element to add
     */
    public void addFirst(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        //if size is 0
        if(size == 0){
            data[front] = element;
            rear = front;
        }
        //if before the front is out of bounds
        else if(front-1 < 0){
            data[data.length-1] = element;
            front = data.length-1;
        }
        //if it can be inserted before easily.
        else{
            data[front-1] = element;
            front--;
        }
        size++;
    }
    /**
     * Adds the element given to the front of the Deque.
     * 
     * @param element - the element to add
     */
    public void addLast(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        //if size is 0
        if(size == 0){
            data[rear] = element;
            front = rear;
        }
        //if it cannot be added to end easily, out of bounds, add to front
        else if(rear+1 > data.length-1){
            data[0] = element;
            rear = 0;
        }
        //can be added easily
        else{
            data[rear+1] = element;
            rear++;
        }
        size++;
    }
    /**
     * Removed the first element from the Deque.
     * 
     * @return - the element that was removed
     */
    public E removeFirst(){
        if(size == 0){
            return null;
        }
        //store value, would be deleted otherwise
        E temp = (E) data[front];
        data[front] = null;
        front++;
        size--;
        if(front > size-1){
            front = rear - size+1;
        }
        if(size <= 1){
            front = rear;
        }
        return temp;
    }
    /**
     * Removed the last element from the Deque.
     * 
     * @return - the element that was removed
     */
    public E removeLast(){
        if(size == 0){
            return null;
        }
        //store value so not lost
        E temp = (E) data[rear];
        data[rear] = null;
        rear--;
        size--;
        if(rear < 0){
            rear = front + size-1;
        }
        if(size <= 1){
            rear = front;
        }
        return temp;
    }
    /**
     * Returns the front of the Deque.
     * 
     * @return - the element in the front
     */
    public E peekFirst(){
        if(size == 0){
            return null;
        }
        return (E) data[front];
    }
    /**
     * Returns the end of the Deque.
     * 
     * @return - the element at the end.
     */
    public E peekLast(){
        if(size == 0){
            return null;
        }
        return (E) data[rear];
    }
}
