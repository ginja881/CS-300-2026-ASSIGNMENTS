/*
 * Author: Joseph Carter
 * Email: jtcarter5@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Programming Assignment 07
 * Citations: none 
 */ 


// Tester 
public class TaskListTester {

  
  public static boolean testLinkedTask() {
    boolean result = true;
    
    try {
      LinkedTask task = new LinkedTask(null);
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      result = false; 
    }
    
    Task taskA = new Task("A", TaskPriority.HIGH);
    Task taskB = new Task("B", TaskPriority.MEDIUM);
    Task taskC = new Task("C", TaskPriority.LOW);

    LinkedTask filledTask = new LinkedTask(
      taskA, 
      new LinkedTask(taskB, null)
    );
    LinkedTask singleTask = new LinkedTask(
      taskC
    );

    String filledString = filledTask.toString();
    String desiredFilledString = String.format(
        "%s -> ", 
	taskA.toString()
    );
    
    String singleString = singleTask.toString();
    String desiredSingleString = String.format(
	"%s -> END", 
	taskC.toString()
    );

    if (!filledString.equals(desiredFilledString)) {
      System.out.println("TASK FORMAT ISSUE\n" + filledString + "\n" + "\n" + desiredFilledString);
      result = false;
    }

    if (!singleString.equals(desiredSingleString)) {
      System.out.println("TASK FORMAT ISSUE" + singleString + "\n" + "\n" + desiredSingleString);
      result = false;
    }
    

    return result;
  }

  public static boolean testAddTask() {
    Task.resetIDGenerator();
    boolean result = true;
    TaskList taskList = new TaskList();
    
    try {
      taskList.addTask(null);
      System.out.println("NO NULL SHOULD BE ACCEPTED");
      result = false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      result = false;
    }


    taskList.addTask(new Task("Deploy to production", TaskPriority.MEDIUM));                  
    taskList.addTask(new Task("Fix login bug", TaskPriority.HIGH));                           
    taskList.addTask(new Task("Update README", TaskPriority.LOW));                            
    taskList.addTask(new Task("Code review PR #42", TaskPriority.MEDIUM));                    
    taskList.addTask(new Task("Patch security vuln", TaskPriority.HIGH));     
    
    int expectedCount = 5;
    int count = 0;
    try {
    
      for (int i = 0; i < expectedCount; i++) {
	if (i < expectedCount - 1) {
	  Task currentTask = taskList.get(i);
	  Task nextTask = taskList.get(i+1);
	  if (currentTask.compareTo(nextTask) == 1) {
	    String error = String.format("FAIL, NODES AT %d and %d are out of order", i, i+1);
	    System.out.println(error);
	    result = false;
	    break;
	  }
	}

	count++;
      } 
      
      if (count != expectedCount) {
	System.out.println("LIST ONLY HAS " + count + " NODES");
	result = false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      result = false;
    }
    return result;
  }


  public static boolean testRemove() {
    Task.resetIDGenerator();
    boolean result = true;

    TaskList taskList = new TaskList();

    taskList.addTask(new Task("Deploy to production", TaskPriority.MEDIUM));
    taskList.addTask(new Task("Fix login bug", TaskPriority.HIGH));
    taskList.addTask(new Task("Update README", TaskPriority.LOW));
    taskList.addTask(new Task("Code review PR #42", TaskPriority.MEDIUM));
    taskList.addTask(new Task("Patch security vuln", TaskPriority.HIGH));
    
    int expectedSize = taskList.size();

    try {
      taskList.remove(expectedSize*2);
      System.out.println("FAIL INVALID INDEX");
      result = false;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("PASS");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      result = false;
    }

    Task oldFirstTask = taskList.get(0);

    taskList.remove(0);

    if (oldFirstTask == taskList.get(0) && taskList.size() != expectedSize - 1) {
      System.out.println("FAIL REMOVAL OF FIRST");
      System.out.println("SIZE: " + taskList.size());
      result = false;
    }
    expectedSize -= 1;

    Task oldLastTask = taskList.get(expectedSize -1);
    taskList.remove(expectedSize - 1);

    if (oldLastTask == taskList.get(expectedSize - 1) && taskList.size() != expectedSize - 1) {
      System.out.println("FAIL REMOVAL OF LAST");
      System.out.println("SIZE: " + taskList.size());
      result = false;
    }
    expectedSize -= 1;
    

    Task oldMiddleTask = taskList.get(1);

    taskList.remove(1);

    if (oldMiddleTask == taskList.get(1) && taskList.size() != expectedSize - 1) {
      System.out.println("FAIL REMOVAL OF MIDDLE");
      System.out.println("SIZE: " + taskList.size());
      result = false;
    }

    return result;
  }

  // ---- PROVIDED METHODS (do not modify) ----

  /**
   * Calls all test methods and returns true if and only if all tests pass.
   *
   * @return true if every test method returns true; false otherwise
   */
  public static boolean runAllTests() {
    boolean allPass = true;
    allPass &= testLinkedTask();
    allPass &= testAddTask();
    allPass &= testRemove();
    return allPass;
  }

  /**
   * Demonstrates the functionality of the TaskList with sample data.
   * See the assignment specification for expected output.
   */
  public static void demo() {
    Task.resetIDGenerator();
    TaskList list = new TaskList();

    list.addTask(new Task("Deploy to production", TaskPriority.MEDIUM));
    list.addTask(new Task("Fix login bug", TaskPriority.HIGH));
    list.addTask(new Task("Update README", TaskPriority.LOW));
    list.addTask(new Task("Code review PR #42", TaskPriority.MEDIUM));
    list.addTask(new Task("Patch security vuln", TaskPriority.HIGH));

    System.out.println("List Before Completion:");
    System.out.println(list);
    System.out.println("Size: " + list.size());

    list.get(0).markComplete();
    list.get(2).markComplete();
    list.get(4).markComplete();

    System.out.println("\nList After Completion:");
    System.out.println(list);

    list.remove(4);
    list.remove(2);
    list.remove(0);

    System.out.println("\nList After Deletion:");
    System.out.println(list);
  }

  /**
   * Main method. Runs all tests and prints results, then runs the demo.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("testLinkedTask: " + testLinkedTask());
    System.out.println("testAddTask: " + testAddTask());
    System.out.println("testRemove: " + testRemove());
    System.out.println("runAllTests: " + runAllTests());

    System.out.println("\n========== DEMO ==========\n");
    demo();
  }
}
