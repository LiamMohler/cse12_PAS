/*
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A12345678
 * Sources used: None
 * 
 * This file is used to create a student obj with a first name, last name,
 * and PID.
 */

import java.util.Objects;

/**
 * This class creates the student Obj.
 * 
 * Instance variables:
 * firstName - The first name of a student
 * lastName - The last name of a student
 * PID - The PID of a student
 */
public class Student implements Comparable<Student>{
    /** Instance variables */
    final private String firstName;
    final private String lastName;
    final private String PID;

    /**
     * The constructor initializes Student obj.
     * 
     * @param firstName the first name
     * @param lastName the last name
     * @param PID the pid
     */
    public Student(String firstName, String lastName, String PID){
        //throw erro
        if(firstName == null || lastName == null || PID == null){
            throw new IllegalArgumentException();
        }
        //initialize
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }
    /**
     * The function returns the first name
     * 
     * @return - the first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * The function returns the last name
     * 
     * @return - the last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * The function returns the pid
     * 
     * @return - the pid
     */
    public String getPID(){
        return PID;
    }

    /**
     * The function returns if two student obj are equal. Meaning same name
     * and PID.
     * 
     * @param o - the object to compare
     * @return - whther or not they are equal
     */
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        // if it is not a student
        if(o.getClass() != this.getClass()){
            return false;
        }
        //if not same first name
        if(!((Student)o).firstName.equals(this.firstName)){
            return false;
        }
        //if not same last name
        if(!((Student)o).lastName.equals(this.lastName)){
            return false;
        }
        //if not same PID
        if(!((Student)o).PID.equals(this.PID)){
            return false;
        }
        return true;
    }

    /**
     * The function uses the Objects.hash() function to hash the student.
     * 
     * @return - the hash
     */
    public int hashCode(){
        return Objects.hash(firstName,lastName,PID);
    }

    /**
     * The function compares the students to find out which is higher/lower
     * in value.
     * 
     * @param o - the student to compare.
     * @return - 0 if same, - number is less, + if bigger
     */
    public int compareTo(Student o){
        if(o == null){
            throw new NullPointerException();
        }
        //compare last names
        if(lastName.compareTo(o.lastName) != 0){
            return lastName.compareTo(o.lastName);
        }
        //compare first names
        if(firstName.compareTo(o.firstName) != 0){
            return firstName.compareTo(o.firstName);
        }
        //compare PIDs
        if(PID.compareTo(o.PID) != 0){
            return PID.compareTo(o.PID);
        }
        return 0;
    }

}
