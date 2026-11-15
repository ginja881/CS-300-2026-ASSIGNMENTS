/*
 * Author: Joseph Carter
 * Email: jtcarter5@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Programming Assignment 07
 * Citations: none 
 */ 


/**
 * Class representing a list of tasks
 */ 
public class TaskList implements SortedTaskListADT<Task> {

  /** The first node in the linked list (highest priority, earliest task). */
  private LinkedTask head;

  /** The last node in the linked list (lowest priority, latest task). */
  private LinkedTask tail;

  /** The total number of tasks in the list. */
  private int size;

  
  /**
   * TaskList constructor 
   */ 
  public TaskList() {
    this.head = null;
    this.tail = this.head;
    this.size = 0;
  }


  public int size() {
    return this.size;
  }

  
  public boolean isEmpty() {
    return this.size == 0;
  }
  
  /**
   * Adds a new task 
   * @param task new task to add 
   */ 
  public void addTask(Task task) {
    if (this.isEmpty()) {
      this.head = new LinkedTask(task);
      this.tail = this.head;
      this.size++;
      return;
    }
    

    LinkedTask currentNode = this.head;
    LinkedTask prevNode = currentNode;
    LinkedTask newNode = new LinkedTask(task);

    if (task.compareTo(currentNode.getTask())  == -1) {
      newNode.setNext(this.head);
      this.head = newNode;
      this.size++;
      return;
    } 
    
    while (currentNode.getNext() != null) {
       Task currentTask = currentNode.getTask();
       int currentResult = task.compareTo(currentTask);
       if (currentResult > 0) {
	 
         newNode.setNext(currentNode.getNext());
	 currentNode.setNext(newNode);
	 this.size++;
	 return;
       } else if (currentResult < 0) {
         newNode.setNext(currentNode);
	 prevNode.setNext(newNode);
	 this.size++;
	 return;
       }

       currentNode = currentNode.getNext();
       prevNode = currentNode;
    }
    
    this.tail.setNext(newNode);
    this.tail = newNode; 
    
    this.size++;
    
  }
  
  public Task get(int index) {
    if (index >= this.size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    int i = 0;
    LinkedTask currentTask = this.head;
    while (currentTask != null) {
      if (i == index) {
	return currentTask.getTask();
      }

      
      currentTask = currentTask.getNext();
      i++;
    }

    return null;
  }
  
  /**
   * Checks if a task exists in list 
   * @param task to check if it exists in array 
   */ 
  public boolean contains(Task task) {
    LinkedTask currentTask = this.head;
    while (currentTask != null) {
      if (currentTask.getTask().equals(task)) {
        return true;
      }
      currentTask = currentTask.getNext();
    }

    return false;
  }

  /**
   * Removes task from list 
   * @param index index for task to remove 
   * @throws IndexOutofBoundsException when index is out of bounds
   * @return removed task 
   */
  public Task remove(int index) {
    if (index < 0 || index >= this.size)  {
      throw new  IndexOutOfBoundsException("ERROR");
    }
    
    if (this.size == 1) {
      LinkedTask oldTask = this.head;
      this.head = null;
      this.tail = null;
      this.size--;

      return oldTask.getTask();
    }

    if (index == 0) {
      LinkedTask oldHead = this.head;
      LinkedTask newHead = this.head.getNext();
      this.head = newHead;

      this.size--;
      return oldHead.getTask();
    }

    LinkedTask currentTask = this.head;
    LinkedTask prevTask = null;
    int currentIndex = 0;
    while (currentTask.getNext() != null) {
      if (currentIndex == index) {
	Task currentActualTask = currentTask.getTask();
	prevTask.setNext(currentTask.getNext());
	this.size--;
        return currentActualTask;
      }

      prevTask = currentTask;
      currentTask = currentTask.getNext();
    }
    
    // Tail case 
    
    LinkedTask oldTail = currentTask;
    this.tail = prevTask;
    this.tail.setNext(null);

    this.size--;

    return oldTail.getTask();
  }
  
  /**
   * Clears the entire list like a stack 
   */ 
  public void clear() {
    while (!this.isEmpty()) {
      this.remove(0);
    }

    this.head = null;
    this.tail = this.head;
    this.size = 0;
  }
  
  /**
   * Obtains string representation of list 
   */ 
  public String toString() {
    if (this.isEmpty()) {
      return null;
    }

    String message = "";
    LinkedTask currentNode = this.head;
    while (currentNode != null) {
      message += currentNode.toString();
	

      currentNode = currentNode.getNext();

    }
    return  message;
  }


}
