# CSE 12 Spring 2022 PA6 - Stacks and Queues with a Deque

[There is an FAQ post on Piazza.](https://piazza.com/class/lch5pcfyhil3n8/post/693) **Due date: Thursday, March 2 @ 11:59 PM PST**

### **Learning goals:**

* Implement a Deque data structure with generic types
* Implement a Stack and Queue data structure using a Deque
* Write JUnit tests to verify proper implementation



## Testing and Implementation of Deque, Stack, and Queue [100 points]

In this part of the assignment, you will implement a Deque, Stack, and Queue and write JUnit tests to ensure that your implementation functions correctly.

**Read the entire write-up before you start coding.**

Download the starter code from [here](https://github.com/CaoAssignments/cse12-wi23-pa6-Stacks-and-Queues-starter) and put it in a directory in your working environment. You will find the following files:

```
+-- PA6
|    +-- DequeInterface.java		
|    +-- StackInterface.java
|    +-- QueueInterface.java
|    +-- MyDeque.java            **You will CREATE this file**
|    +-- MyStack.java            **Edit this file**
|    +-- MyQueue.java            **Edit this file**
|    +-- PublicTester.java
|    +-- CustomTester.java       **You will CREATE this file**
```

You will be creating the MyDeque, MyStack, and MyQueue classes that implement the given DequeInterface, StackInterface, and QueueInterface interfaces. Note, we did not provide starter code for MyDeque.



### Part 1: MyDeque
_Deque is pronounced like “deck” **/dɛk/** not “dequeue”._

Once you understand what the behavior of Deque is supposed to be, your next task is to create your own implementation of the provided DequeInterface called MyDeque.

**⚠️DO NOT IMPORT AND USE JAVA'S BUILTIN DEQUE!!! If we see that you do use these functions, you will receive a zero. Furthermore, if we see you import any of the built-in data structure implementations that are not permitted you will not receive any credit (i.e. ArrayLists, Stack, Queue etc.)⚠️**

* Create a file named MyDeque.java. Make sure the MyDeque class implements DequeInterface. MyDeque is a generic class. You only need to implement the methods stubbed out in the interface (also listed in the table below). You may also refer to descriptions for the same methods provided by the Deque [javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html).


#### Instance Variables
**Note:** Do not make any instance variables private, they should have the default access modifier. Do not add any other instance variables and do not add any static variables (other than private static final variables to be used as constants).

<table>
  <tr>
    <td>
      <strong>Instance Variable</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
  </tr>
  <tr>
    <td>
      <code>Object[] data</code>
    </td>
    <td>
      The underlying data structure of the Deque. Treat this as a circular array. For your reference on the expected behavior, view the diagrams for the add/remove methods below.<br>
      <strong>Note:</strong> You don't need to check if the <code>data</code> array is <code>null</code> in any of your code.<br>
      <strong>Note:</strong> <code>null</code> cannot be a valid element in your Deque. It is used to represent an empty space in the array.
    </td>
  </tr>
  <tr>
    <td>
      <code>int size</code>
    </td>
    <td>
      This variable should be equal to the number of valid elements in your <code>data</code> array. A valid element in <code>data</code> is an element in your Deque.<br>
      <strong>Note:</strong> You may assume that nothing (other than possibly your own code) would change <code>size</code> to be something out of bounds of <code>data</code> (i.e., <code>size >= 0 && size &lt;= data.length</code> will always evaluate to <code>true</code>, unless your code manually sets it to be something else).
    </td>
  </tr>
  <tr>
    <td>
      <code>int rear</code>
    </td>
    <td>
      This variable should be equal to the index of the last element in the Deque. When the Deque is initialized, `rear` will start at index 0.
    </td>
  </tr>
  <tr>
    <td>
      <code>int front</code>
    </td>
    <td>
      This variable should be equal to the index of the first element in the Deque. When the Deque is initialized, <code>front</code> will start at index 0. <br>
      <strong>Note:</strong> <code>rear</code> and <code>front</code> can be equal to each other. This will happen when the Deque is empty or only has one element.
    </td>
  </tr>
</table>

#### Method Descriptions
<table>
  <tr>
    <td>
      <strong>Method Name</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
    <td>
      <strong>Exceptions to Throw</strong>
    </td>
  </tr>
  <tr>
    <td>
      <code>public MyDeque(int initialCapacity)</code>
    </td>
    <td>
      Initialize the Object array <code>data</code> with length of initialCapacity.<br>
      <strong>Note:</strong> The capacity of the Deque is the length of the array, while size is the number of <em>valid</em> elements in the array.
    </td>
    <td>
      Throw an <code>IllegalArgumentException</code>if the <code>initialCapacity</code> is negative (&lt; 0).
    </td>
  </tr>
  <tr>
    <td>
      <code>public int size()</code>
    </td>
    <td>
      Returns the number of elements that exist in the deque.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public void expandCapacity()</code>
    </td>
    <td>
      Doubles the current capacity. If the capacity is 0, set the capacity to a default value of 10. This method should preserve the current <code>size</code> and elements in the list.<br>
      Elements need to be contiguous after expanding. This means the <code>front</code> needs to be 0 and <code>rear</code> should be at size-1 or 0 if there are no elements present.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public void addFirst(E element)</code>
    </td>
    <td>
      Before trying to add the element, check if the deque is at capacity and call <code>expandCapacity</code> if necessary.<br>
      Add the specified element to the front of the deque and update <code>front</code>.<br>
      Updates <code>size</code> accordingly.
    </td>
    <td>
      Throw <code>NullPointerException</code> when element is null.
    </td>
  </tr>
  <tr>
    <td>
      <code>public void addLast(E element)</code>
    </td>
    <td>
      Before trying to add the element, check if the deque is at capacity and call <code>expandCapacity</code> if necessary.<br>
      Add the specified element to the rear of the deque and update <code>rear</code>.<br>
      Updates <code>size</code> accordingly.
    </td>
    <td>
      Throw <code>NullPointerException</code> when element is null.
    </td>
  </tr>
  <tr>
    <td>
      <code>public E removeFirst()</code>
    </td>
    <td>
      Removes and returns the element at the front of the deque if there is such an element. If there are no elements in the deque, return <code>null</code>.<br>
      Updates the relevant instance variables accordingly.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public E removeLast()</code>
    </td>
    <td>
      Removes and returns the element at the rear of the deque if there is such an element. If there are no elements in the deque, return <code>null</code>.<br>
      Updates the relevant instance variables accordingly.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public E peekFirst()</code>
    </td>
    <td>
      Returns the element at the front of the deque if there is such an element. If there are no elements in the deque, return <code>null</code>.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public E peekLast()</code>
    </td>
    <td>
      Returns the element at the rear of the deque if there is such an element. If there are no elements in the deque, return <code>null</code>.
    </td>
    <td>
      None
    </td>
  </tr>
</table>

**Removing the Final Element**

Do not explicitly reset `rear` or `front` to 0 when removing the final element in the Deque.

#### Examples

##### Example of Circular Behavior

As stated earlier, the element is inserted into the next available space in the array. In this example, integers are elements in the deque (valid elements of `data`) and `null` represent unoccupied spots in `data`.

Statements on the left-hand side of the array should evaluate to expressions on the right-hand side.

```java
deque.data -> [1, 2, 3, 4, 5] // capacity is 5
deque.size -> 5 
deque.front -> 0
deque.rear -> 4

deque.addFirst(0);
deque.data -> [1, 2, 3, 4, 5, null, null, null, null, 0] // capacity is 10
deque.size -> 6
deque.front -> 9
deque.rear -> 4

deque.removeFirst();

deque.data -> [1, 2, 3, 4, 5, null, null, null, null, null] // capacity is still 10
deque.size -> 5
deque.front -> 0
deque.rear -> 4

deque.removeFirst();
deque.data -> [null, 2, 3, 4, 5, null, null, null, null, null] // capacity is still 10
deque.size -> 4
deque.front -> 1
deque.rear -> 4

deque.addLast(6);
deque.data -> [null, 2, 3, 4, 5, 6, null, null, null, null] // capacity is still 10
deque.size -> 5
deque.front -> 1
deque.rear -> 5
```

**Note:** This example only shows how `front` wraps around. `rear` should wrap around too!

Tip: When determining the index for `front` and `rear`, you can try using the modulus operator `%` to wrap around to the next available space. You are not required to handle the wrapping in this way, you may use whichever implementation as long as the behavior is correct.

##### Example of Adding the First Element and Removing the Final Element

```java
deque.data -> [null, null, null, null, null] // Deque initialized with a capacity of 5
deque.size -> 0
deque.front -> 0
deque.rear -> 0

deque.addLast(0);
deque.data -> [0, null, null, null, null]
deque.size -> 1  // size updated
deque.front -> 0 // front and rear unchanged
deque.rear -> 0

deque.addFirst(10);
deque.data -> [0, null, null, null, 10]
deque.size -> 2
deque.front -> 4  // front wraps around
deque.rear -> 0

deque.removeLast();
deque.data -> [null, null, null, null, 10]
deque.size -> 1
deque.front -> 4  
deque.rear -> 4   

deque.removeLast();
deque.data -> [null, null, null, null, null]
deque.size -> 0
deque.front -> 4  // removing the final element
deque.rear -> 4   // front and rear are NOT reset to 0

deque.addFirst(5);
deque.data -> [null, null, null, null, 5]
deque.size -> 1
deque.front -> 4  // we add at index 4
deque.rear -> 4
```

##### Example of ExpandCapacity

```java
deque.data -> [3, 4, 5, 1, 2] // capacity is 5
deque.size -> 5 
deque.front -> 3
deque.rear -> 2

deque.expandCapacity();
deque.data -> [1, 2, 3, 4, 5, null, null, null, null, null] // capacity is 10
deque.size -> 5
deque.front -> 0
deque.rear -> 4
```

#### Diagrams for MyDeque

The following diagram shows one case where only 4 spaces in the array are occupied in the middle of the array. In this case, when we call `addFirst`, the element will be added to index 0. When we call `removeFirst`, we will remove the element at index 1. When we call `addLast`, the element will be added to index 5. And when we call `removeLast`, we will remove the element at index 4.

**Note:** Blue boxes represent a stored value while white boxes represent `null`.

![](https://i.imgur.com/MNRQE7K.png)

MyDeque can also run into the situation where `front` > `rear`. This means that the front has wrapped around to the end of the array OR the rear has wrapped around to the beginning of the array. The following diagrams show how `addFirst`, `removeFirst`, `addLast`, and `removeLast` should behave in this situation.

![](https://i.imgur.com/JVrjRWJ.png)

##### addFirst when front > rear

![](https://i.imgur.com/vh8Y08k.png)

##### removeFirst when front > rear

![](https://i.imgur.com/hM93gcC.png)

##### addLast when front > rear

![](https://i.imgur.com/d9dx7nm.png)

##### removeLast when front > rear

![](https://i.imgur.com/veTwRtG.png)



### Part 2: MyStack

An Abstract Data Type (ADT) only describes the functionality required and not how the implementation is to be accomplished. Since an ADT is implementation-independent, your next task is to implement the Stack ADT by using a Deque for your underlying data structure.

You will accomplish this in MyStack.java, which contains generic class `MyStack` that implements the Stack ADT. Our Stack ADT is defined in the generic interface, StackInterface.java.

#### Instance Variables

<table>
  <tr>
    <td>
      <strong>Instance Variable</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
  </tr>
  <tr>
    <td>
      <code>MyDeque&lt;E> theStack</code>
    </td>
    <td>
      The underlying data structure of MyStack. You will use your implementation of the Deque ADT (in the form of a <code>MyDeque&lt;E></code> object) to manage your data.<br>
      <strong>Note:</strong> <code>null</code> cannot be a valid element in the <code>MyDeque&lt;E></code> object, which is your underlying data structure. As such, <code>null</code>  cannot be a valid element for <code>theStack</code> either.
    </td>
  </tr>
</table>

#### Public Methods
Each of these non-constructor methods are already defined in StackInterface.java. You need to implement these methods using the `MyDeque<E>` object created in your constructor. There are a couple of ways to implement each method using the `MyDeque<E>` object. As long as the implementation you choose is consistent with all the other `MyStack` methods and the `MyStack` works as you would expect an implementation of the Stack ADT to work (LIFO ordering), you can choose anyway to use the `MyDeque<E>` object.

<table>
  <tr>
    <td>
      <strong>Method Name</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
    <td>
      <strong>Exceptions to Throw</strong>
    </td>
  </tr>
  <tr>
    <td>
      <code>public MyStack (int initialCapacity)</code>
    </td>
    <td>
      Initialize a <code>MyDeque&lt;E></code> object with the parameter <code>initialCapacity</code>.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public boolean empty()</code>
    </td>
    <td>
      Checks whether or not the stack is empty. If it is empty, return true.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public void push(E e)</code>
    </td>
    <td>
      Pushes the specified element to the top of the stack.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public E pop()</code>
    </td>
    <td>
      Removes an element from the top of the stack. Returns the removed element, or <code>null</code> if there was no such element.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public E peek()</code>
    </td>
    <td>
      Returns the element at the top of the stack.
    </td>
    <td>
      None
    </td>
  </tr>
  <tr>
    <td>
      <code>public int size()</code>
    </td>
    <td>
      Returns the number of elements in the stack
    </td>
    <td>
      None
    </td>
  </tr>
</table>

**Note:** When using a method from the MyDeque class, only use the methods outlined in the DequeInterface.java (i.e. removeFirst, addLast, size, etc). If you wrote helper methods in MyDeque, refrain from using them when implementing MyStack.


### Part 3: MyQueue

Similar to the Stack ADT, the Queue ADT is implementation-independent. We can also implement a Queue using our Deque. In MyQueue.java, write the generic class `MyQueue<E>` to implement the given `QueueInterface<E>` interface in QueueInterface.java.

#### Instance Variables
<table>
  <tr>
    <td>
      <strong>Instance Variable</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
  </tr>
  <tr>
    <td>
      <code>MyDeque&lt;E> theQueue</code>
    </td>
    <td>
      The underlying data structure of <code>MyQueue</code>. You will again use your implementation of the Deque ADT (in the form of a <code>MyDeque&lt;E></code> object) to manage your data.<br>
      <strong>Note:</strong> <code>null</code> cannot be a valid element in the <code>MyDeque&lt;E></code> object, which is your underlying data structure. As such, <code>null</code> can't be a valid element for <code>theQueue</code> either.
    </td>
  </tr>
</table>
       
#### Public Methods
Each of these methods are already defined in QueueInterface.java. You only need to complete implementation for these methods in your `MyQueue` class using the `MyDeque<E>` object created in your constructor. Again, there are a couple ways to use the `MyDeque<E>` object to implement each of these methods. Make sure whatever implementation you choose ensures that the proper behavior outlined by the Queue ADT is adhered to (i.e., FIFO ordering).

<table>
  <tr>
   <td><strong>Method Name</strong>
   </td>
   <td><strong>Description</strong>
   </td>
   <td><strong>Exceptions to Throw</strong>
   </td>
  </tr>
  <tr>
   <td><code>public MyQueue (int initialCapacity)</code>
   </td>
   <td>Initialize a <code>MyDeque&lt;E></code> object with the parameter <code>initialCapacity</code>.
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td><code>public boolean empty()</code>
   </td>
   <td>Checks whether or not the stack is empty. If it is empty, return true.
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td><code>public void enqueue(E e)</code>
   </td>
   <td>Adds the specified element to the back of the queue.
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td><code>public E dequeue()</code>
   </td>
   <td>Removes an element from the front of the queue. Returns the removed element, or <code>null</code> if there was no such element.
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td><code>public E peek()</code>
   </td>
   <td>Returns the element at the front of the queue.
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td><code>public int size()</code>
   </td>
   <td>Returns the number of elements in the queue.
   </td>
   <td>None
   </td>
  </tr>
</table>


**Note:** When using a method from the MyDeque class, only use the methods outlined in the DequeInterface.java (i.e. removeFirst, addLast, size, etc). If you wrote helper methods in MyDeque, refrain from using them when implementing MyQueue.



### Part 4: Testing (10 points)
We provide `PublicTester.java`. This contains all the public test cases we will use to grade your Deque, Stack and Queue (visible on Gradescope). 


**Your task**: create `CustomTester.java` and implement tests that can correctly distinguish between good and bad implementations. 
- Your tests will be graded by checking if they pass on a good implementation and fail on a bad implementation. If a test fails on a good implementation, then the test is written incorrectly. If a test passes on a bad implementation, it may be written incorrectly or may be not be rigorous enough (try adding more asserts).
- Gradescope will report whether your CustomTester fails on our good implementation (solution code) as a way to sanity check some of your test cases. However, you will not be able to see whether your tests pass/fail on the bad implementations until the PA is graded. Do your best to write comprehensive test asserts based on the write-up description and public tester examples.
    - ⚠️If you fail `TestCustomTesterSanity` you will receive a 0 on this section! Please read the Gradescope output carefully.⚠️
- Any test you write in `CustomTester.java` will be run against all bad implementations. You will receive 1.25 points for every bad implementation your test is expected to fail, up to 10 points (if your test also passes on the good implementation). 
- There is a total of 10 bad implementations: 8 for `MyDeque`, 1 for `MyStack` , and 1 for `MyQueue`.
- For `MyDeque`, the following methods have bad implementations:
    - MyDeque Constructor
    - `expandCapacity`
    - `addFirst`
    - `addLast`
    - `removeFirst`
    - `removeLast`
- For `MyStack` and `MyQueue`, the stack and queue behavior have bad implementaions.
- *Hint: a tricky part of this PA is the behavior of circular arrays. Make sure you test cases where the array wraps around!* 


#### How to compile and run the testers:
Running the tester on UNIX based systems (including a mac):

* Compile: `javac -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. PublicTester.java`
* Execute: `java -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. org.junit.runner.JUnitCore PublicTester`

Running the tester on Windows systems:

* Compile: `javac -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" PublicTester.java`
* Execute: `java -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" org.junit.runner.JUnitCore PublicTester`




### Part 5: Coding Style (5 points)
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
**Turning in your code**

Submit all of the following files to [Gradescope](https://www.gradescope.com/courses/481483/assignments) by **Thursday, March 2 @11:59PM PST.**
* `MyDeque.java`
* `MyStack.java`
* `MyQueue.java`
* `CustomTester.java`

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.

#### How your assignment will be evaluated [100 points]

* **Correctness** [95 points] You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the specifications in this writeup), you may not earn credit.
    * Tester [10 points]
        * The autograder will test your implementation of the JUnit tests. 
        * This section has a maximum of 10 pts. This means if you pass at least 8 out of 10 custom tester cases, you will get full points for the Testing portion.
    * Deque/Stack/Queue Implementation [85 points]
        * The autograder will test your implementation on the public test cases given in `PublicTester.java` and hidden test cases not described in this PA writeup.
* **Coding Style** [5 points]
    * `MyDeque.java` will be graded on style. `CustomTester.java` will be graded on file, class, method headers and indentation.
