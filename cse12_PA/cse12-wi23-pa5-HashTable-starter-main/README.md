# CSE 12 Winter 2023 PA5 - Hash Table

**Due date: Thursday, Feb 23 @ 11:59 PM PST**

There is an FAQ post on Piazza. Please read that post first if you have any questions.


## **Learning goals:**



* Use Java’s HashSet and HashMap to solve real-world problems 
* Write JUnit tests to verify proper implementation

**Important: You won’t need to implement any hashing algorithms/data structures in this PA. Instead, you’ll need to use Java's built-in HashSet and HashMap to complete this PA.**



## Testing and Implementation of HashMap and HashSet Application [100 points]

### Part 1: Implementation of Hashset/HashMap (85 points)


In this part of the assignment,  you will directly use Java's built-in data structures and write JUnit tests to ensure that you call the functions correctly.

**Make sure to read the entire write-up before you start coding.** You may not change any public class/method or variable names given in the write-up or starter code.

Download the starter code from [here](https://github.com/CaoAssignments/cse12-wi23-pa5-HashTable-starter) and put it in a directory in your working environment.

You will find the following files:

```
+-- PA5
|   +-- PublicTester.java
```

You will have to create the following files: `Student.java`, `Course.java`, `Sanctuary.java`, and `CustomTester.java`.

Here are a list of imports you may use to help you complete this part of the assignment:
- `java.util.Objects`
- `java.util.Map`
- `java.util.HashMap`
- `java.util.Set`
- `java.util.HashSet`
- `java.util.Collections`
- `java.util.ArrayList`
- `java.util.Iterator`

For Tester files, you may import anything you want.


### Part 1a: `HashSet` Application

It’s time to register for classes again, and we need to organize the students and courses during the enrollment period.

You will be using a `HashSet` to help you track course enrollment. Refer to the [JDK 17 documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashSet.html) for more information on `HashSet`. You are allowed to directly use any of the methods listed in the documentation for Java’s `HashSet`.


#### Student

You will first complete the methods to implement the `Student` class in `Student.java`. `Student` objects will be stored in `Course` objects.

**All instance variables in `Student` should be declared as private final.** Think about what will happen to the hash code if you allow those elements to be changed after initialization.


|Instance Variable|Description|
|--- |--- |
|`String firstName`|A string representing the first name of the student.|
|`String lastName`|A string representing the last name of the student.|
|`String PID`|A string representing the PID of the student. This is unique for each student.|



You will be required to implement the following methods.


|Method Name|Description|
|--- |--- |
|`public Student(String firstName, String lastName, String PID)`|Initialize the student’s information. Throw an `IllegalArgumentException` if any of the arguments are null.|
|`public String getFirstName()`|Return the student’s first name|
|`public String getLastName()`|Return the student’s last name|
|`public String getPID()`|Return the student’s PID|
|`public boolean equals(Object o)` <br> *overrides [`Object::equals(Object)`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html)*| Returns `true` if `o` is 1) not `null`, 2) a `Student` object, 3) all the instance variables of `o` equal the corresponding instance variables of the current `Student` object. Otherwise, return `false`.|
|`public int hashCode()` <br>*overrides [`Object::hashCode()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#hashCode())*| Return the hash value generated using the built-in [`Objects.hash`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Objects.html#hash(java.lang.Object...)) function. The hash function should generate a hash value in the order of the student’s `firstName`, `lastName`, and `PID`.|
|`public int compareTo(Student o)` <br> *implements [`Comparable<Student>::compareTo(Student)`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html#compareTo(T))*| Compare this with another `Student` in the order of `lastName`, `firstName`, and `PID`, using [`String::compareTo`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#compareTo(java.lang.String)). That's said, continue to compare `firstName` if and only if `lastName` ties; and continue to compare `PID` if and only if `lastName` and `firstName` tie. <br><br>Throw a `NullPointerException` if `o` is `null`.<br><br> For example, if we have Student 1 with the last name “Jones”, first name “Angela”, and PID “A12345678” and Student 2 with the last name “Smith”, first name “Aaron”, and PID “A12345679”, then `student1.compareTo(student2)` will return a negative value (e.g. -1).|




#### Course

Our Course class will help us store the students registered for this particular course in the form of a `HashSet`.

**All Course variables should be declared as `private final` except for `HashSet<Student> enrolled`, which should have the default access modifier (no modifier).** This is default to help you write JUnit tests more easily.


|Instance Variable|Description|
|--- |--- |
|`HashSet<Student> enrolled`|A collection of students that are enrolled in this course.|
|`int capacity`|The maximum number of students that can be enrolled in this course.|
|`String department`|This course falls under this department.|
|`String number`|A string representing the course number.|
|`String description`|A string representing the description of the course.|



You will be required to implement the following methods.


|Method Name|Description|Exceptions to Throw|
|--- |--- |--- |
|`public Course(String department, String number, String description, int capacity)`|Initialize the course’s information with an initial enrollment of 0 students.|Throw an `IllegalArgumentException` if any of the arguments are null. Throw an `IllegalArgumentException` if capacity is 0 or negative.|
|`public String getDepartment()`|Return the department name.||
|`public String getNumber()`|Return the course number.||
|`public String getDescription()`|Return the description of the course.||
|`public int getCapacity()`|Return the capacity of the course.||
|`public boolean enroll(Student student)`|If there is room in this course and the student is not currently enrolled, add the student to the course. Return `true` for successful enrollment and `false` otherwise (i.e. the student is already in the course).|Throw an `IllegalArgumentException` if `student` is `null`.|
|`public boolean drop(Student student)`|If the student is enrolled in the course, drop them from the course. Return `true` for successfully dropping student and `false` otherwise (i.e. the student is not in the course).|Throw an `IllegalArgumentException` if `student` is `null`.|
|`public void cancel()`|If the course is canceled, all of the students are dropped from the course. Remove all the students from the course.||
|`public boolean isFull()`|If the course is at its capacity, return `true`. Otherwise, return `false`.||
|`public int getEnrolledCount()`|Return the current number of enrolled students.||
|`public int getAvailableSeats()`|Return the number of students that can still enroll in the course (assuming everyone stays enrolled).||
|`public HashSet<Student> getStudents()`|Return a shallow copy of all the students enrolled in this course. The returned set should have a different address than the original set, but the references to students that are stored in the set should be the same. Hint: check the behavior of [`HashSet<E>::clone()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashSet.html#clone()) method.||
|`public ArrayList<Student> getRoster()`|Turn the collection of enrolled students into an `ArrayList<Student>` collection by iterating through the set using the iterator or a for-each loop. Return the final result as a sorted `ArrayList<Student>`. All the students in the course should be listed in the increasing order specified by the `compareTo` method in `Student` in the `ArrayList<Student>` version of the roster. You may use [`Collections.sort`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html#sort(java.util.List)) to sort the list.||
|`public String toString()` *overrides [Object::toString()](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#toString())*|Return a string representation for a Course object. The format should be as follows: `<department> <number> [<capacity>] <description>` where the contents inside the `< >` refer to the instance variables of the class. Do not include the `< >` characters in the return value. <br><br> For example, for a Course with `department = "CSE"`, `number = "12"`, `capacity = 196`, and `description = "Data Structure"`, it should return `"CSE 12 [196] Data Structure"` <br><br> You might find [`String::format()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#format(java.lang.String,java.lang.Object...)) helpful for this method.||




### Part 1b: `HashMap` Application

A wildlife sanctuary needs your help to efficiently track the animals that are in its care. The sanctuary wants to keep track of how many of each species are currently located on its grounds.

You will be using a `HashMap` to help you organize all the animals at the sanctuary. Refer to the [JDK 17 documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashMap.html) for more information on `HashMap`. You are allowed to directly use any of the methods listed in the documentation for Java’s `HashMap`.
    
**All Sanctuary variables should be declared as `private final` except for `HashMap<String, Integer> sanctuary`, which should have the default access modifier (no modifier).** This is default to help you write JUnit tests more easily.

|Instance Variable|Description|
|--- |--- |
|`HashMap<String, Integer> sanctuary`|Container to store all the animal species in the sanctuary. The key (`String`) represents the name of the animal species and the value (`Integer`) represents the number of that species at the sanctuary. **If at anytime, the key is `null` or the value is 0, the species should not be in the sanctuary `HashMap`.**|
|`int maxAnimals`|The maximum number of animals that the sanctuary can support.|
|`int maxSpecies`|The maximum number of species that the sanctuary can support.|



You will be required to implement the following methods.


|Method Name|Description|Exceptions to Throw|
|--- |--- |--- |
|`public Sanctuary(int maxAnimals, int maxSpecies)`|Initialize the `HashMap` with no elements. Initialize the other instance variables accordingly.|If `maxAnimals` or `maxSpecies` is less than or equal to `0`, or `maxSpecies` is larger than `maxAnimals`, throw an `IllegalArgumentException`. |
|`public int countForSpecies(String species)`|Return the number of animals in the sanctuary that are of the given `species`. If the given species does not exist in the sanctuary, return 0.|If `species` is `null`, throw an `IllegalArgumentException`.|
|`public int getTotalAnimals()`|Return the total number of animals in the sanctuary.||
|`public int getTotalSpecies()`|Return the total number of species in the sanctuary.||
|`public int getMaxAnimals()`|Return the maximum allowed number of animals in the sanctuary.||
|`public int getMaxSpecies()`|Return the maximum allowed number of species in the sanctuary.||
|`public int rescue(String species, int num)`|If it does not exceed the `maxAnimals` nor `maxSpecies` of the sanctuary, add `num` animals of `species` to the sanctuary. If adding `num` animals exceeds the maximum limit, add as many animals as permitted. Return the number of animals that could not be rescued.|If `num` is less than or equal to 0, throw an `IllegalArgumentException`. If species is `null`, throw an `IllegalArgumentException`.|
|`public void release(String species, int num)`|Remove `num` animals of species from the sanctuary. Remove the `species` from the sanctuary if its remaining count reaches 0.|Throw an `IllegalArgumentException` if any of the following are `true`: <br> - `num` is less than or equal to 0 <br> - `num` is greater than the number of animals of species at the sanctuary <br> - `species` is `null` <br> - `species` does not exist in the sanctuary|




### **Part 2: JUnit Testing (10 points)**
- We provide `PublicTester.java`.
     This contains all the public test cases we will use to grade your Student, Course, and Sanctuary implementation (visible on Gradescope).
- Your task: create `CustomTester.java` and implement the unit tests in `CustomTester.java` based on the description in the Tests table below.
    * ⚠️There are hidden tests on Gradescope not described in the write-up. Make sure to write additional tests to verify your implementation's correctness! ⚠️
    * Your tests will be graded by checking if they pass on a   good implementation and fail on a bad implementation. If a test fails on a good implementation, then the test is written incorrectly. If a test passes on a bad implementation, it may be written incorrectly or may be not be rigorous enough (try adding more asserts).
    * **Gradescope will report whether your CustomTester fails on our good implementation (solution code) as a way to sanity check some of your test cases. However, you will not be able to see whether your tests pass/fail on the bad implementations until the PA is graded.** Do your best to write comprehensive test asserts based on the write-up description and public tester examples.
    * Some of your tests will be run on several bad implementations. You will receive 1.25 pts for every bad implementation your test fails (if your test also passes on the good implementation). **See the bottom of the writeup [here](#How-your-assignment-will-be-evaluated-100-points) for the new CustomTester point assignment (maximum score of 10pts for CustomTester).**
   * Make sure the names of your tests match exactly with the names in the table. If the names of any tests don't match, you'll not receive points for those tests.
   * Feel free to create more tests for your own benefits (additional test will not be graded).
   * Tip: You might find the `.equals` method of HashSet or HashMap helpful when you write your asserts. However, the downside is that this will not report detailed failure messages, in which case writing other asserts on HashSet/HashMap may be helpful.


#### Tests Table: List of test cases to write and their description
| Test Cases                  | Description                                                                                                                                                                                                          | Point Value |
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|
| `testStudentEquals`         | Call `equals` when the argument is a non-Student object.                                                                                                                                                             | 1.25        |
| `testStudentCompareTo`      | Call `compareTo` to compare two Students that have the same last name and same first name. `this` Student should have a PID that is lexigraphically smaller than the other Student.                                  | 1.25        |
| `testCourseDrop`        | Attempt to `drop` a non-existing student with a non-empty course roster. This means the course should have at least 1 student enrolled already.                                                                  | 1.25        |
| `testCourseEnroll`          | Attempt to `enroll` a valid student into a course that is already at maximum capacity.                                                                                                                               | 1.25        |
| `testCourseGetRoster`       | Call `getRoster` on a course with a large number of enrolled students. (We suggest testing on a course that has 100 validly enrolled students.) <br></br>Hint: How might you generate 100 `Student` objects with a loop? How might you keep track of these `Student` objects and use the ArrayList `equals` method for assert statements?                                                                                           | 1.25        |
| `testSanctConstructor`      | Call the Sanctuary constructor with a negative argument for `maxAnimals`.                                                                                                                                              | 1.25        |
| `testSanctRescuePartial`    | `rescue` animals from an existing species, where rescuing `num` animals will cause the number of animals to exceed the sanctuary's max capacity. This means only some of the animals should be rescued successfully. | 1.25        |
| `testSanctRescueMaxSpecies` | `rescue` a new non-null species when the sanctuary is already at the max capacity for species.                                                                                                                       | 1.25        |
| `testSanctReleasePartial`   | `release` some (not all) of the animals of an existing species in the sanctuary.                                                                                                                                     | 1.25        |
| `testSanctReleaseTooMany`   | Attempt to `release` more animals than exists for a specific animal species in the sanctuary. The existing number of animals for this species should be non-zero.                                                           | 1.25        |


#### How to compile and run the testers:
Running the tester on UNIX based systems (including macOS):

* Compile: `javac -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. PublicTester.java`
* Execute: `java -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. org.junit.runner.JUnitCore PublicTester`

Running the tester on Windows systems:

* Compile: `javac -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" PublicTester.java`
* Execute: `java -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" org.junit.runner.JUnitCore PublicTester`

You should run the above commands in the `starter` directory. To run the custom tester, replace references to PublicTester with CustomTester in the above commands.

⚠️Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn’t compile.


### **Part 3: Coding Style (5 points)**

Coding style is an important part of ensuring readability and maintainability of your code. We will grade your code style in all submitted code files according to the style guidelines. Namely, there are a few things you must have in each file/class/method:

* File header
* Class header
* Method header(s)
* Inline comments
* Proper indentation
* Descriptive variable names
* No magic numbers (Exception: Magic numbers can be used for testing.)
* Reasonably short methods (if you have implemented each method according to the specification in this write-up, you’re fine). This is not enforced as strictly.
* Lines shorter than 80 characters
* Javadoc conventions (`@param`, `@return` tags, `/** comments */`, etc.)


A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.


## Submission Instructions

#### Turning in your code

* Submit all of the following files to [Gradescope](https://www.gradescope.com/courses/481483/assignments) by **Thursday, February 23, 2023, 11:59pm PST**. 
    * `Student.java`
    * `Course.java`
    * `Sanctuary.java`
    * `CustomTester.java`

**Important**: Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.

#### How your assignment will be evaluated [100 points]

* **Correctness** [95 points] You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the specifications in this writeup), you may not earn credit.
    * Tester [10 points]
        * The autograder will test your implementation of the JUnit tests. Your unit tests are expected to fail or pass based on the [Part 2 Test Table](#Tests-Table-List-of-test-cases-to-write-and-their-description).
        * *This section has a maximum of 10 pts. This means if you pass at least 8 out of 10 custom tester cases, you will get full points for the Testing portion.*
    * HashMap/HashSet Implementation [85 points]
        * The autograder will test your implementation on the public test cases given in `PublicTester.java` and hidden test cases not described in this PA writeup.
* **Coding Style** [5 points]
    * `Student.java`, `Course.java`, `Sanctuary.java` will be graded on style. `CustomTester.java` will be graded on file, class, method headers and indentation.


