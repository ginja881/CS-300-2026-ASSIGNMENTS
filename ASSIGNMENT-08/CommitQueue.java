// TODO: File Header

/**
 * The CommitQueue class represents a queue of commits to be pushed to a remote repository.
 * This class provides basic queue operations such as enqueue, dequeue, peek, clear, and contains.
 */
public class CommitQueue extends LinkedStructure<Commit> implements QueueADT<Commit> {
  public CommitQueue() {
    super();
  }
  
  public Commit peek() {
    return super.head.getData();
  }
  
  public boolean isEmpty() {
    return super.isEmpty();
  }

  public int size() {
    return super.size();
  }

  public void enqueue(Commit value) {
    
    if (super.isEmpty()) {
      super.head = new LinkedNode<Commit>(value);
      super.tail = super.head;
      super.size++;
      return;
    }


    LinkedNode<Commit> newNode = new LinkedNode<Commit>(value, null);
    super.tail.setNext(newNode);
    super.tail = super.tail.getNext();
    super.size++;
  }
  
  
  public Commit dequeue() {
    if (super.isEmpty()) {
      throw new CommitNotFoundException("TRIED TO DEQUEUE ON EMPTY COMMIT QUEUE");
    }
   
    if (super.size == 1) {
      LinkedNode<Commit> oldFront = super.head;
      super.head = null;
      super.tail = null;
      return oldFront.getData();
    }


    LinkedNode<Commit> oldFront = super.head;
    super.head = super.head.getNext();
    return oldFront.getData();
  }

  public void  clear() {
    super.clear();
  }
  
  public boolean contains(Commit value) {
    return super.contains(value);
  }

}
