/*
 * Author: Max Phillips
 * Email: max.phillips@wisc.edu
 * Course: CS300, Spring 2026
 * Assignment: P8: Version Control (Stacks+Queues+Iterators)
 * Citations:
 * - https://www.geeksforgeeks.org/java/java-implementing-iterator-and-iterable-interface/
 * - SP26 P7 (by Yiwei/Yuyuan/Anthony)
 */

// PROVIDED FILE

/**
 * This class abstracts a generic singly linked list.
 * It is intended to be used to back a stack or queue.
 * It does *not* have add, remove, or get functionality -- this must be implemented via extension.
 *
 * @param <T> the type of data stored in each node of this list
 */
public abstract class LinkedStructure<T> implements Iterable<T> {

  // The first node in the linked list.
  protected LinkedNode<T> head;

  // The last node in the linked list.
  protected LinkedNode<T> tail;

  // The total number of nodes in the list.
  protected int size;

  /**
   * Creates an empty linked list.
   */
  public LinkedStructure() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Returns the number of nodes currently in the list.
   *
   * @return the size of the list
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns true if the list contains no nodes (size of 0).
   *
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0 && this.head == null && this.tail == null;
  }

  /**
   * Removes all items from the list, resetting it to an empty state.
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Returns true if this LinkedStructure contains a particular object, false otherwise.
   * @param obj the object to search for
   * @return true if this data structure contains the object, false otherwise
   */
  public boolean contains(T obj) {
    for (T t : this) {
      if (t.equals(obj)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Creates and returns an iterator over this linked list.
   *
   * @return a new LinkedIterator over this linked list
   */
  public LinkedIterator<T> iterator() {
    return new LinkedIterator<T>(this.head);
  }

  /**
   * Returns a linked list representation from head to tail.
   * Each node's toString() is concatenated together.
   * Returns "EMPTY" for an empty list.
   *
   * @return string representation of this linked list
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "EMPTY";
    }
    String result = "";
    LinkedNode current = head;
    while (current != null) {
      result += current.toString();
      current = current.getNext();
    }
    return result;
  }
}
