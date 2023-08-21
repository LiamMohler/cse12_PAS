/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * 
 * This file (MyArraylist.java) is meant to replicate the functionality of 
 * java's arraylist data structure.  
 */


 /**
 * This class replicates the arraylist datastructure in java.
 * 
 * Instance variables:
 * data - The data that an MyArrayList holds
 * size - The size of the MyArrayList
 */
public class MyArrayList<E> implements MyList<E> {

    /** Constants (Magic Numbers) */
    private final static int FIVE = 5;
    private final static int THREE = 3;
    private final static int ONE = 1;
    private final static int ZERO = 0;


    /** Instance variables */
    Object[] data;
    int size;


    /**
     * The constructor is not no arg constructor and initializes an arraylist
     * with a size of 0, capacity of 5
     */
    public MyArrayList(){
        data = new Object[FIVE];
        size = ZERO;
    }

    /**
     * This constructor takes in an intial capacity, making a list of that
     * capacity.
     * 
     * @param initialCapacity the initial capacity of the MyArrayList obj. 
     */
    public MyArrayList(int initialCapacity){
        //if out of bounds, throw error
        if(initialCapacity < ZERO){
            throw new IllegalArgumentException();
        }
        else{
            data = new Object[initialCapacity];
        }
    }

    /**
     * This constructor takes in a generic array (of any type), and sets the 
     * MyListArr to it.
     * 
     * @param Arr the array to set MyArrayList to 
     */
    public MyArrayList(E[] Arr){
        //if nothing then do the same as no arg constr.
        if(Arr == null){
            data = new Object[FIVE];
            size = ZERO;
        }
        //otherwise gonna shallow copy data
        else{
            data = Arr;
            //even nulls are included in size for this
            size = Arr.length;
        }
    }

    /**
     * This function takes in a required capacity and will expand the capacity
     * based on requirements.
     * 
     * @param requiredCapacity the size required
     */
    public void expandCapacity (int requiredCapacity){
        //if less than the length, then it is already big enough, error
        if(requiredCapacity < data.length){
            throw new IllegalArgumentException();
        }
        else{
            if(data.length == ZERO){
                //make it with 5 capacity
                data = new Object[FIVE];
            }
            else if(data.length + THREE > requiredCapacity){
                //make it with +3 capacity
                Object[] newData = new Object[data.length + THREE];
                for(int i = ZERO;i<data.length;i++){
                    newData[i] = data[i];
                }
                data = newData;
                //wont want to do anything after this so return
                return;
            }
            if(data.length < requiredCapacity){
                //make new list with req capacity
                Object[] newData = new Object[requiredCapacity];
                for(int i = ZERO;i<data.length;i++){
                    newData[i] = data[i];
                }
                data = newData;
            }
        }
    }

    /**
     * This function will return the capacity of a MyArrayList object.
     * 
     * @return the length of array, length = capacity
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * This function will insert an element into a specific index of the
     * MyArrayList obect.
     * 
     * @param index the index to insert
     * @param element the element to insert
     */
    public void insert(int index, E element){
        //if not in bounds throw error
        if(index < ZERO || index > size)
            throw new IndexOutOfBoundsException();

        //if not enough space then extend capcity
        if(getCapacity()-size < ONE)
            expandCapacity(getCapacity()+ONE);
        
        //create temp obj.
        Object[] newData = new Object[getCapacity()];
        //set index to the element
        newData[index] = element;
        for(int i = ZERO;i<data.length-ONE;i++){
            //whenever it reaches the index skip over to the next one (offset)
            if(i >= index)
                newData[i+ONE] = data[i];
            else
                newData[i] = data[i];
            
        }
        //set data to temp obj
        data = newData;
        size ++;
    }

    /**
     * This function will append an element to the end of a MyArrayList obj.
     * 
     * @param element the element to insert
     */
    public void append(E element){
        //if need more space then add
        if(getCapacity()-size < ONE){
            expandCapacity(getCapacity()+ONE);
        }

        //create temp obj, deep copy data over
        Object[] newData = new Object[getCapacity()];
        for(int i = ZERO;i<data.length;i++){
            newData[i] = data[i];
        }
        //add the new data to end of size
        newData[size] = element;
        data = newData;
        size ++;
    }

    /**
     * This function will prepend an element to the end of a MyArrayList obj.
     * 
     * @param element the element to insert
     */
    public void prepend(E element){
        //if need more space then extend capacity
        if(getCapacity()-size < ONE){
            expandCapacity(getCapacity()+ONE);
        }

        //create temp var and deep copy
        Object[] newData = new Object[getCapacity()];
        for(int i = ZERO;i<data.length-ONE;i++){
            //offset by one
            newData[i+ONE] = data[i];
        }
        //set data to temp
        newData[ZERO] = element;
        data = newData;
        size ++;
    }

    /**
     * This function will return the data at a particular index.
     * 
     * @param index the index to get data at
     * @return returns the data at an index
     */
    @SuppressWarnings("unchecked")
    public E get(int index){
        //make sure in bounds
        if(index < ZERO || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (E)data[index];
    }

    /**
     * This function will set the data at a particular index to a certain 
     * value.
     * 
     * @param index the index to set
     * @param element the element to insert
     * @return returns the old data (before the set)
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element){
        //make sure in bounds
        if(index < ZERO || index >= size){
            throw new IndexOutOfBoundsException();
        }

        //make sure we still got the retVal when we change it
        Object retVal = data[index];
        data[index] = element;

        return (E)retVal;
    }


    /**
     * This function will remove a certain index of the MyArrayList object.
     * 
     * @param index the index to remove
     * @return returns the removed data
     */
    @SuppressWarnings("unchecked")
    public E remove(int index){
        //make sure that index in bounds
        if(index < ZERO || index >= size){
            throw new IndexOutOfBoundsException();
        }

        //know what we will be removing so can return
        Object retVal = data[index];

        //create temp obj
        Object[] newData = new Object[getCapacity()];
        for(int i = ZERO;i<data.length-ONE;i++){
            //if its creater than the index, then we will want to move its
            //index forward once
            if(i >= index)
                newData[i] = data[i+ONE];
            else
                newData[i] = data[i];
            
        }
        //set data to temp
        data = newData;
        size--;

        return (E)retVal;
    }

    /**
     * This function will return the size of the MyArrayList obj.
     * 
     * @return returns the size 
     */
    public int size(){
        return size;
    }
}