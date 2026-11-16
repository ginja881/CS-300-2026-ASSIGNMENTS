/*
 * Author: Joseph Carter
 * Email: jtcarter5@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Programming Assignment 08
 * Citations: none 
 */ 


import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Tester class for a version control system.
 */
public class VCSTester {

  public static void main(String[] args) {
    if (runAllTests()) {
      System.out.println("All tests passed!");
    } else {
      System.out.println("Some tests failed.");
    }
  }

  /**
   * Runs all test methods
   * @return true if all tests pass
   */
  public static boolean runAllTests() {
    boolean allPass = true;

    allPass &= testCommitChangesDefensiveCopy();
    allPass &= testStackPushPop();
    allPass &= testQueueEnqueueDequeue();
    allPass &= testLinkedIterator();
    allPass &= testBranchMakeAndPushCommit();
    allPass &= testRepositoryBasics();

    return allPass;
  }

  /**
   * Tests that Commit makes defensive copies of the changedFiles list.
   * @return true if all tests pass
   */
  public static boolean testCommitChangesDefensiveCopy() {
    boolean result = true;

    ArrayList<String> testFiles = new ArrayList<>();
    testFiles.add("main.py");
    
    Commit defendedCommit = new Commit("TEST", testFiles);
    testFiles.add("main.cpp");

    if (defendedCommit.getChangedFiles().size() == testFiles.size()) {
      result = false;
      System.out.println("CONSTRUCTOR DOES NOT MAKE DEFENSIVE COPY"); 
    }
    
    ArrayList<String> getterCopy = defendedCommit.getChangedFiles();
    getterCopy.add("test.elixir");

    if (getterCopy.size() == defendedCommit.getChangedFiles().size()) {
      result = false;
      System.out.println("GETTER DOES NOT MAKE DEFENSIVE COPY");
    }

    return result;
  }

  /**
   * Tests the push and pop methods of CommitStack.
   * @return true if all tests pass
   */
  public static boolean testStackPushPop() {
    boolean result = true;
    // Case 1: Push to an empty stack
    try {
      CommitStack stack = new CommitStack();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      stack.push(commit);
      if (stack.size() != 1) {
        System.out.println("Case 1 failed:");
        System.out.println("Actual Size: " + stack.size());
        System.out.println("Expected Size: 1");
        return false;
      }
      if (!stack.peek().equals(commit)) {
        System.out.println("Case 1 failed:");
        System.out.println("Top element should be the most recently pushed commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 1 failed with exception: " + e);
      return false;
    }
    // Case 2: Push to a non-empty stack
    try {
      CommitStack stack = new CommitStack();

      stack.push(
        new Commit("A Commit", new ArrayList<>(Arrays.asList("main.cpp")))
      );
      stack.push(
        new Commit("Commit", new ArrayList<>(Arrays.asList("main.java")))
      );

      Commit newCommit =  new Commit("1Commit", new ArrayList<>(Arrays.asList("main.py")))
      stack.push(
        newCommit
      );

      if (!stack.peek().equals(newCommit) && stack.size() != 3) {
        System.out.println("Case 2 Failed: invalid top");
        System.out.println("expected " + newCommit.toString());
        System.out.println("but got " + stack.peek().toString());
        
        System.out.println("stack size is " + stack.size());
        result = false;
      }
    } catch (Exception e) {
      System.out.println("Case 2 failed with exception: " + e);
    }
  
    // Case 3: Pop from a non-empty stack
    try {
      CommitStack stack = new CommitStack();
      Commit secondCommit = new Commit(
        "Commit 2",
        new ArrayList<>(Arrays.asList("test.txt"))
      );

      stack.push(
        new Commit("A Commit", new ArrayList<>(Arrays.asList("main.cpp")))
      );
      stack.push(secondCommit);

      Commit commit = stack.pop();
      
      if (!commit.equals(secondCommit)) {
        System.out.println("CASE 3: INVALID TOP");
        result = false;
      }

    } catch (Exception e) {
      System.out.println("Case 3 failed with exception: " + e);
    }
    // Case 4: Pop from an empty stack
    try {
      CommitStack stack = new CommitStack();
      Commit commit = stack.pop();
      if (commit != null) {
        System.out.println("CASE 4: NOT EMPTY STACK");
        result = false;
      }
    } catch (Exception e) {
      System.out.println("Case 4 failed with exception: " + e);
    }
    return result;
  }

  /**
   * Tests the enqueue and dequeue methods of CommitQueue.
   * @return true if all tests pass
   */
  public static boolean testQueueEnqueueDequeue() {
    // Case 1: Enqueue to an empty queue
    try {
      CommitQueue queue = new CommitQueue();
      Commit commit = new Commit("First commit", new ArrayList<>(Arrays.asList("a.java")));
      queue.enqueue(commit);
      if (queue.size() != 1) {
        System.out.println("Case 1 failed:");
        System.out.println("Actual Size: " + queue.size());
        System.out.println("Expected Size: 1");
        return false;
      }
      if (!queue.peek().equals(commit)) {
        System.out.println("Case 1 failed:");
        System.out.println("Front element should be the first enqueued commit");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Case 1 failed with exception: " + e);
      return false;
    }

    
    // Case 2: Enqueue to a non-empty queue
    // Case 3: Dequeue from a non-empty queue
    // Case 4: Dequeue from an empty queue
    return false;
  }

  /**
   * Tests the LinkedIterator class functionality directly.
   * @return true if all tests pass
   */
  public static boolean testLinkedIterator() {
    // TODO
    // This tester should create a chain of linked nodes directly
    // (e.g., by using the constructor of LinkedNode)

    // Case 1: Verify that LinkedIterator visits all nodes
    // in a chain of at least 3 nodes in order
    // Case 2: Verify that hasNext returns false after all nodes visited
    // Case 3: Verify that LinkedIterator works on an empty chain
    // (i.e., null start node)
    
    return false;
  }

  /**
   * Tests makeCommit and pushAllCommits methods of Branch.
   * @return true if all tests pass
   */
  public static boolean testBranchMakeAndPushCommit() {
    // TODO
    // Case 1: Make at least 3 commits without pushing
    // Case 2: Push all commits
    // Case 3: Check that head is the most recently pushed commit
    return false;
  }

  /**
   * Tests basic Repository functionality.
   * @return true if all tests pass
   */
  public static boolean testRepositoryBasics() {
    // TODO
    // Case 1: Verify that active branch is initialized
    // Case 2: Make and push commits to main branch
    // Case 3: Get an existing and nonexistent branch by name
    // Case 4: Create and checkout a new branch
    return false;
  }
}
