// TODO: File Header

/**
 * The CommitStack class represents a stack of commits that have been pushed to a remote repository.
 * This class provides basic stack operations such as push, pop, peek, clear, and contains.
 */
public class CommitStack extends LinkedStructure<Commit> implements StackADT<Commit> {

  // TODO: Implement and @Override StackADT methods
  // Note that isEmpty(), size(), clear(), and contains() are implemented in LinkedStructure.
  
  public CommitStack() {
    super();
  }

  public boolean isEmpty() {
    return super.isEmpty();
  }

  public int size() {
    return super.size();
  }

  public void push(Commit value) {
     if (super.isEmpty()) {
      super.head = new LinkedNode<Commit>(value, null);
      super.tail = super.head;
      super.size++;
      return;
     }

     LinkedNode<Commit> newTop = new LinkedNode<Commit>(value);
     newTop.setNext(super.head);
     super.head = newTop;
     super.size++;
  }

  public Commit pop() {
    if (super.isEmpty()) {
      return null;
    }

    if (super.size() == 1) {
      LinkedNode<Commit> oldTop = super.head;
      super.head = null;
      super.tail = null;
      super.size--;
      return oldTop.getData();
    }


    LinkedNode<Commit> oldTop = super.head;
    super.head = super.head.getNext();
    super.size--;
    return oldTop.getData();
  }
  
  public boolean contains(Commit value) {
    return super.contains(value);
  }

  public void clear() {
    super.clear();
  }

  public Commit peek() {
    return super.head.getData();
  }  

  private void printAllCommitsNewestHelper(LinkedNode<Commit> currentNode) {
    if (currentNode == null) {
      return;
    }
    System.out.println(currentNode);
    printAllCommitsNewestHelper(currentNode.getNext());
  }

  /**
   * Print all commits in this CommitStack in reverse chronological order (newest first).
   */
  public void printAllCommitsNewestFirst() {
    this.printAllCommitsNewestHelper(super.head);
  }
  
  private void printAllCommitsOldestHelper(LinkedNode<Commit> currentNode) {
    if (currentNode == null) {
      return;
    }

    printAllCommitsOldestHelper(currentNode.getNext());
    System.out.println(currentNode);
  }

  /**
   * Print all commits in this CommitStack in chronological order (oldest first).
   */
  public void printAllCommitsOldestFirst() {
    this.printAllCommitsOldestHelper(super.head);
  }
}
