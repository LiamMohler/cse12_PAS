/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A12345678
 * Sources used: None
 * 
 * This file is used to create a course obj.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


/**
 * This class creates a course with certain characteristics.
 * 
 * Instance variables:
 * capacity - capacity of class
 * number - course #
 * department - department of class
 * enrolled - HashSet of students enrolled in class
 * description - desc. of the class
 */
public class Course {
    /** Constants (Magic Numbers) */
    private static final String space = " ";
    private static final String leftBracket = "[";
    private static final String rightBracket = "]";

    /** Instance variables */
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;


    /**
     * The course constructor. Will make a course with the following...
     * 
     * @param department - the department
     * @param number - the number
     * @param description - the description
     * @param capacity - the capacity
     */
    public Course(String department, String number, String description, int capacity){
        //check for errors.
        if(department == null || number == null || description == null)
            throw new IllegalArgumentException();
        if(capacity <= 0)
            throw new IllegalArgumentException();
        //set instance vats
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        enrolled = new HashSet<>();
    }
    /**
     * Will return the department.
     * 
     * @return - the department
     */
    public String getDepartment(){
        return department;
    }
    /**
     * Will return the number.
     * 
     * @return - the number
     */
    public String getNumber(){
        return number;
    }
    /**
     * Will return the description.
     * 
     * @return - the description
     */
    public String getDescription(){
        return description;
    }
    /**
     * Will return the capacity.
     * 
     * @return - the capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Will enroll a studnet in the course.
     * 
     * @param student - the student to enroll in class.
     * @return - whether or not the operation was successful.
     */
    public boolean enroll(Student student){
        if(student == null){
            throw new IllegalArgumentException();
        }
        //if it is within bounds
        if(enrolled.size() < capacity && !enrolled.contains(student)){
            enrolled.add(student);
            return true;
        }
        return false;
    }
    /**
     * Will drop a student from the class.
     * 
     * @param student - the student to drop.
     * @return - whether or not it worked.
     */
    public boolean drop(Student student){
        if(student == null){
            throw new IllegalArgumentException();
        }
        //check if has student
        if(enrolled.contains(student)){
            enrolled.remove(student);
            return true;
        }
        return false;
    }
    /**
     * Will clear the enrolled list.
     * 
     */
    public void cancel(){
        enrolled.clear();
    }
    /**
     * Will return if the class is full.
     * 
     * @return - true if full, false if not.
     */
    public boolean isFull(){
        if(capacity <= enrolled.size()){
            return true;
        }
        return false;
    }
    /**
     * Will return the # of enrolled
     * 
     * @return - # of enrolled
     */
    public int getEnrolledCount(){
        return enrolled.size();
    }
    /**
     * Will return the # of available seats.
     * 
     * @return - # of available seats
     */
    public int getAvailableSeats(){
        return capacity - enrolled.size();
    }
    /**
     * Will return the hashset of students.
     * 
     * @return - shallow copy of enrolled.
     */
    public HashSet<Student> getStudents(){
        return (HashSet<Student>) enrolled.clone();
    }
    /**
     * Will return the roster of a the course
     * 
     * @return - the arraylist containing roster. 
     */
    public ArrayList<Student> getRoster(){
        //create iterator
        Iterator<Student> it = enrolled.iterator();
        ArrayList<Student> retList = new ArrayList<>();
        //loop through
        while(it.hasNext()){
            retList.add(it.next());
        }
        //sort list to return.
        Collections.sort(retList);
        return retList;
    }
    /**
     * Will return the course in a stirng format.
     * 
     * @return - string format of course
     */
    public String toString(){
        String retString = department + space + number + space + 
            leftBracket + capacity + rightBracket + space + description;
        return(retString);
    }

}
