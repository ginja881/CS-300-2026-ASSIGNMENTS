// TODO: File Header

import java.util.Iterator;

/**
 * A generic iterator for use with a data structure backed by a singly linked list.
 *
 * @param <T> the type of data stored in each node of the corresponding linked data structure
 */
class LinkedIterator<T> implements Iterator<T> {

  LinkedNode<T> current;

  /**
   * Creates a new LinkedIterator over the provided linked list.
   * @param startNode the head of the linked list (or equivalent).
   */
  LinkedIterator (LinkedNode<T> startNode) {
    this.current = startNode;
  }

  /**
   * Checks if the next element in the linked data structure exists.
   * @return true only if the iterator has a "next" element
   */
  public boolean hasNext() {
    return this.current != null && this.current.getNext() != null;
  }

  /**
   * Retrieves the data from the current node and advances the cursor.
   * @return the data from the current in the backing linked data structure
   */
  public T next() {
    this.current = this.current.getNext();
    return this.current.getData();
  }
}
