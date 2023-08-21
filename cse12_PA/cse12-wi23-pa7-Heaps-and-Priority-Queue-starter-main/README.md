
# CSE 12 Winter 2023 PA7 - Heaps and Priority Queue


## Due date: Thursday, March 9 @ 11:59 PM PST

There is an FAQ post on Piazza. Please read that post first if you have any questions.


### Learning goals:

* Implement a MinHeap
* Understand and analyze the working of Priority Queues
* Write JUnit tests to verify proper implementation


# Part 1: Testing and Implementation of MinHeap and MyPriorityQueue [95 points]

In this part of the assignment, you will implement a MinHeap and write JUnit tests to ensure that your implementation functions correctly.

Read the entire write-up before you start coding.

Download the starter code from: [https://github.com/CaoAssignments/cse12-wi23-pa7-Heaps-and-Priority-Queue-starter] and put it in a directory in your working environment. You will find the following files:

```
+--PA 7 
|	+-- MinHeapInterface.java
|	+-- PublicTester.java		
|	+-- MyMinHeap.java			**Create this file**
|	+-- MyPriorityQueue.java		**Create this file**
```

In this assignment, we provide a `PublicTester.java` file that contains all the public test cases (visible on Gradescope) that we will use to test your `MinMyHeap` and `MyPriorityQueue`. **We will not be grading  your custom testers.** We still encourage you to write your own tests to verify that your implementation follows the write-up specifications.



## Part 1a - Implementation of MyMinHeap


**Your task: Implement a MinHeap. In the `MyMinHeap.java` file, add the following:**


### Instance variables:


<table>
  <tr>
   <td><strong>Instance Variable</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>
  <tr>
   <td><code>protected ArrayList&lt;E> data</code>
   </td>
   <td>The underlying data structure of MyMinHeap. <strong>You must use 0-based indexing to store the heap (the root of the tree at index 0).</strong>
   </td>
  </tr>
</table>


In this file, you may import the following:
- `java.util.ArrayList`
- `java.util.Collection`

`MyMinHeap` should have a constraint on the generic parameter `E` such that `E` implements `Comparable<E>` so you can compare the elements. You should also implement `MinHeapInterface<E>`.

Note: Do not add any other instance variables and do not add any static variables (other than `private static final` variables to be used as constants).


### Constructors:

<table>
  <tr>
   <td><strong>Constructor</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>
  <tr>
   <td><code>public MyMinHeap()</code>
   </td>
   <td>No-argument constructor that initializes <code>data</code> to an empty <code>ArrayList</code>.
   </td>
  </tr>
  <tr>
   <td><code>public MyMinHeap(Collection&lt;? extends E> collection)</code>
   </td>
   <td>Initializes a min-heap using the elements in <code>collection</code>.
<ul>

<li>First, initialize <code>data</code> using the <code>ArrayList(Collection&lt;? extends E> collection)</code> constructor by directly passing in the <code>collection</code> argument.

<li>Next, iterate through <code>data</code> <em>backward</em>, percolating each element <em>down</em>. We will soon write the <code>percolateDown()</code> helper method, which can be used here.

<p>

</li>
</ul>
Throws <code>NullPointerException</code> if <code>collection</code> or any element in <code>collection</code> is null.
   </td>
  </tr>
</table>



### Helper Methods:

You should find these methods useful to implement the actual functionality of MyMinHeap and also to implement other helper methods.

**Important Note**: These helper methods are meant to be called inside the core methods and other helper methods. **Therefore, you have some design choice in the helper methods for 1) whether you want to assume all arguments are in-bounds and 2) if you do not want to assume, then what out-of-bounds behavior you want.** If you assume all arguments are in bounds, you must make sure that all arguments follow the assumptions before calling your helper method.



Note: 
* In practice, these would be private methods, but for our assignment, they will be protected so that we can auto-grade your methods and provide feedback for them. To be clear, these methods are **required**.
* All tests assume that you use **0-based indexing** to store the heap in the array.

<table>
  <tr>
   <td>
<strong>Helper Method Name</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>
  <tr>
   <td><code>protected void swap(int from, int to)</code>
   </td>
   <td>Swap the elements at <code>from</code> and <code>to</code> indices in <code>data.</code>
<ul>

<li>You may assume <code>from</code> and <code>to</code> will be within bounds. 
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected static int getParentIdx(int index)</code>
   </td>
   <td>Calculate and return the parent index of the parameter <code>index</code>.
<ul>

<li>This method is irrelevant to what is currently in <code>data</code> and should not make any changes to <code>data</code>.  

<li>You may assume <code>index > 0</code>.
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected static int getLeftChildIdx(int index)</code>
   </td>
   <td>Calculate and return the left child index of the parameter <code>index.</code>
<ul>

<li>This method is irrelevant to what is currently in <code>data</code> and should not make any changes to <code>data</code>. 

<li>You may assume <code>index >= 0</code>.
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected static int getRightChildIdx(int index)</code>
   </td>
   <td>Calculate and return the right child index of the parameter <code>index.</code>
<ul>

<li>This method is irrelevant to what is currently in <code>data</code> and should not make any changes to <code>data</code>. 

<li>You may assume <code>index >= 0</code>.
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected int getMinChildIdx(int index)</code>
   </td>
   <td>Return the index of the smaller child of the element at <code>index</code>. If the two children are equal, return the index of the left child. If the node at <code>index</code> is a leaf (has no children), return <code>-1</code>.
<ul>

<li>Note that it's also possible for a single node in our heap to have only one child. In this case, return the index of the left child (we know that this is a heap so all nodes are as far left as possible)

<li>You may assume that <code>index</code> will be within bounds.

<li><code>getMinChildIndex</code> depends on what is currently in <code>data</code>, but does not make any changes to <code>data</code>.
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected void percolateUp(int index)</code>
   </td>
   <td>Percolate the element at <code>index</code> up until no heap properties are violated by this element (the heap properties will not be violated once this element's parent is not greater than it). Do this by swapping the element with its parent as needed.
<ul>

<li>Note the case where the element that you are percolating is equal to the parent. In this case, the heap property requiring that a node be no greater than its children is already satisfied, so you should not swap the element you are percolating with the parent.

<li>You may assume that <code>index</code> will be within bounds.

<li><code>percolateUp</code> makes changes in <code>data</code>.
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected void percolateDown(int index)</code>
   </td>
   <td>Percolate the element at <code>index</code> down until no heap properties are violated by this element (the heap properties will not be violated once this element is not greater than its children). If swapping is needed, always swap places with the smaller child. If both children are equal and swapping is needed, swap with the left child.
<ul>

<li>Note the case where the element that you are percolating is equal to the smaller child. In this case, the heap property requiring that a node be no greater than its children is already satisfied, so you should not swap the element you are percolating with the child.

<li>You may assume that <code>index</code> will be within bounds.

<li><code>percolateDown</code> makes changes in <code>data</code>.
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>protected E deleteIndex(int index)</code>
   </td>
   <td>Remove the element at <code>index</code> from <code>data</code> and return it.
<ul>

<li>If we are removing the last element then the heap properties are maintained. 

<li>In other cases, we will replace the deleted element with the last element in the heap (the right-most node in the bottom-most level of the heap) to fix the completeness property.

<li>Then, either percolate this element down or percolate this element up as necessary until no heap properties are violated by this element (only one of these actions will be necessary to maintain the heap property, all fixes to the key order property should be by percolating the replacement element).

<li>The <code>deleteIndex</code> explanation can be found in the <a href="https://github.com/CaoAssignments/cse12-wi23-pa7-Heaps-and-Priority-Queue-starter/blob/main/README.md#appendix">Appendix</a>

<li>You can assume that <code>index</code> will be within bounds.

<li><code>deleteIndex</code> makes changes in <code>data</code>.
</li>
</ul>
   </td>
  </tr>
</table>



### Core Methods:


<table>
  <tr>
   <td><strong>Method Name</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>
  <tr>
   <td><code>public void insert(E element)</code>
   </td>
   <td>Add <code>element</code> to the end of the heap (so that it is the right-most element in the bottom-most level) and percolate only the inserted element up until no heap properties are violated (all fixes to the heap properties should be by this percolation).
<p>
    
Throw a <code>NullPointerException</code> and do not add to the heap if <code>element</code> is <code>null</code>.
<p>
The insertion explanation can be found in the <a href="https://github.com/CaoAssignments/cse12-wi23-pa7-Heaps-and-Priority-Queue-starter/blob/main/README.md#appendix">Appendix</a>
  </tr>
  <tr>
   <td><code>public E getMin()</code>
   </td>
   <td>Return the root (this will be the smallest) element of the heap.
If the heap is empty, return <code>null</code> instead.
   </td>
  </tr>
  <tr>
   <td><code>public E remove()</code>
   </td>
   <td>Remove and return the root (this will be the smallest) element in the heap. Use <code>deleteIndex()</code> helper method here.
If the heap is empty, return <code>null</code> instead.
   </td>
  </tr>
  <tr>
   <td><code>public int size()</code>
   </td>
   <td>Return the number of elements in this min-heap.
   </td>
  </tr>
  <tr>
   <td><code>public void clear()</code>
   </td>
   <td>Clear out the entire heap (the heap should be empty after this call).
   </td>
  </tr>
</table>


## Part 1b - Implementation of MyPriorityQueue
In this part of the assignment, you will understand how a priority queue can be implemented using a min heap.


### Use MyMinHeap to implement MyPriorityQueue

```java
public class MyPriorityQueue<E extends Comparable<E>>
```
A priority queue is a queue where elements are sorted by their priority. Popping/dequeuing from the priority queue should always yield an element with the highest priority. In other words, elements with higher priority will be closer to the front of the priority queue. 

Our `MyPriorityQueue` implementation will be using a `MyMinHeap` backend. Our underlying data structure is a min-heap, so smaller elements (as defined by `compareTo()`) have higher priorities. The root node of the min-heap is the one with the highest priority and is also the smallest element in the min-heap. 


### MyPriorityQueue Instance Variables
```java 
protected MyMinHeap<E> heap;
```
- We will be using a single instance variable for our `MyPriorityQueue`. This `MyMinHeap` will contain all information we need to keep track of our heap. You should only use the `MyMinHeap` public methods in your `MyPriorityQueue` implementation. Do not directly access `heap.list` in your implementation of `MyPriorityQueue` (treat it as if it were a private instance variable). 
- Note: Do not add any other instance variables and do not add any static variables (other than `private static final` variables to be used as constants).

#### `protected MyMinHeap<E> heap`
- This heap holds and sorts all elements for our priority queue. Since we are using our `MyMinHeap` to store our elements, `null` is not allowed in this priority queue. 

### MyPriorityQueue Constructors
```java 
public MyPriorityQueue();
public MyPriorityQueue(Collection<? extends E> collection);
```
#### `public MyPriorityQueue()`
- This no-argument constructor initializes `heap` to be an empty `MyMinHeap`. 

#### `public MyPriorityQueue(Collection<? extends E> collection)`
- You may import `java.util.Collection`
- Throw a `NullPointerException` if `collection` or any element in `collection` is `null`. 
- Otherwise, this constructor initializes `heap` to contain the elements in `collection`. Remember that we have already written a handy-dandy constructor for `MyMinHeap`. 


### MyPriorityQueue Methods
```java 
public void push(E element);
public E peek();
public E pop();
public int getLength();
public void clear();
```
#### `public void push(E element)` 
- Throw a `NullPointerException` and do not add to the priority queue if `element` is `null`. 
- Otherwise, add `element` to this priority queue. `heap` should be fixed accordingly. 

#### `public E peek()` 
- Return the element with the highest priority. Remember that this should be whichever element our min-heap says is the smallest element. 
- If the priority queue is empty, return `null` instead.  

#### `public E pop()` 
- Remove and return the element with the highest priority. Remember that this should be whichever element our min-heap says is the smallest element. `heap` should be fixed accordingly. 
- If the priority queue is empty, return `null` (and do no removal) instead.  

#### `public int getLength()` 
- Return the number of elements in the priority queue.

#### `public void clear()` 
- Clear out the entire priority queue (the priority queue should be empty after this call). 
- `heap` should be an empty `MyMinHeap`, not `null`, after this function is completed. 


# Part 2: Coding Style (5 points)
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



# Submission Instructions

**Turning in your code**

Submit all of the following files to Gradescope by **March 9, 2023 @ 11:59 PM PST**



* MyMinHeap.java							
* MyPriorityQueue.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.


# Appendix

## How to compile and run the testers:
Running the tester on UNIX based systems (including a mac):

* Compile: `javac -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. PublicTester.java`
* Execute: `java -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. org.junit.runner.JUnitCore PublicTester`

Running the tester on Windows systems:

* Compile: `javac -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" PublicTester.java`
* Execute: `java -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" org.junit.runner.JUnitCore PublicTester`


## Diagram for deleteIndex




![](https://i.imgur.com/BgHVsVn.png)
 

deleteIndex() Figure 0: Begin deletion at index 0, which currently contains the element `A`.


![](https://i.imgur.com/1x2D1kU.png)

 

deleteIndex() Figure 1: `A` is removed and set aside to be returned. The last element in the heap, `G`, is moved to the old position to replace it. We then begin the process of percolating `G` down. There is a violation because the element `G` is greater than the element `D`. Since both children are equal, we swap with the left child.



![](https://i.imgur.com/zqPNbkD.png)

 

deleteIndex() Figure 2: `G` has now swapped with `D`, but there is still a violation because `G` is greater than `E`, so we will swap those two.


![](https://i.imgur.com/5SXRzil.png)

 

deleteIndex() Figure 3: `G` has now swapped with `E` and there are no more heap violations, thus ending the percolation process.We have completed the deletion process and will return the original deleted element, `A`.


## Diagram for Insert



![](https://i.imgur.com/Ihhh1Pg.png)


 

insert() Figure 0: Begin insertion of element `D`.



![](https://i.imgur.com/taniUri.png)

 

insert() Figure 1: Add `D` to the end of the min-heap.


![](https://i.imgur.com/bFs458F.png)

 

insert() Figure 2: There is a violation since `D` is less than its parent `G`, so we will swap those.



![](https://i.imgur.com/8iVW1zk.png)

 

insert() Figure 3: `D` has now swapped with 'G' and there are no more heap violations, thus ending the percolation process. We have completed the insertion process.
