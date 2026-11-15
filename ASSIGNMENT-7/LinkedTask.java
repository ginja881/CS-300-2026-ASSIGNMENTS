/*
 * Author: Joseph Carter
 * Email: jtcarter5@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Programming Assignment 07
 * Citations: none 
 */ 

/*
 * Class for representing tasks
 *
 */
public class LinkedTask {

  /** The task data stored in this node. */
  private Task task;

  /** A reference to the next LinkedTask in the list, or null if this is the last node. */
  private LinkedTask next;

  public LinkedTask(Task task) {
    if (task == null) {
      throw new IllegalArgumentException("(ERROR)  task cannot be null");
    }
    this.task = task;
    this.next = null;
  }

  /**
   * Constructs new LinkedTask
   * @param task New task
   * @param next Next Task 
   */
  public LinkedTask(Task task, LinkedTask next) {
    if (task == null) {
      throw new IllegalArgumentException("(ERROR)  task cannot be null");
    }
    this.task = task;
    this.next = next;
  }

  // ---- PROVIDED ACCESSORS (do not modify) ----

  /**
   * Returns the Task stored in this node.
   *
   * @return the task data
   */
  public Task getTask() {
    return this.task;
  }

  /**
   * Returns the next LinkedTask in the list.
   *
   * @return the next node, or null if this is the last node
   */
  public LinkedTask getNext() {
    return this.next;
  }

  // ---- END OF PROVIDED CODE ----

  /**
   * Sets the next task 
   * @param next next task
   */
  public void setNext(LinkedTask next) {
    this.next = next;
  }

  // - Returns the task's string representation followed by " -> " if there is
  //   a next node, or " -> END" if this is the last node
  // - Example with next:    "HIGH: Fix bug (1) -> "
  // - Example without next: "HIGH: Fix bug (1) -> END"
  @Override
  public String toString() {
    String nextNode;
    if (this.next == null) {
      nextNode = " END";
    } else {
      nextNode = " ";
    }

    return this.task + " ->" + nextNode;
  }

}
