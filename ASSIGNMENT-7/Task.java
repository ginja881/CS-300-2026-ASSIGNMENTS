/**
 * Represents a single task in our task management system. Each Task has a unique
 * auto-generated ID, a description, a priority level, and a completion status.
 *
 * <p>Tasks are compared to one another based on their priority level using the
 * compareTo(Task) method, which allows the TaskList to maintain ordering
 * by priority.</p>
 */
public class Task implements Comparable<Task> {

  /** Counter for generating unique task IDs, starting at 1. */
  private static int idGenerator = 1;

  /** The unique identifier for this task. */
  private final int ID;

  /** A brief description of this task. */
  private final String description;

  /** The priority level of this task. */
  private final TaskPriority priority;

  /** Whether this task has been marked as completed. */
  private boolean isComplete;

  /**
   * Creates a new Task with the given description and priority.
   * The task is initially NOT complete. The ID is auto-generated starting at 1.
   *
   * @param description a brief description of this task; must not be null or blank
   * @param priority the priority level of this task; must not be null
   * @throws IllegalArgumentException if description is null or blank, or priority is null
   */
  public Task(String description, TaskPriority priority) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Task description cannot be null or blank");
    }
    if (priority == null) {
      throw new IllegalArgumentException("Task priority cannot be null");
    }
    this.ID = idGenerator++;
    this.description = description;
    this.priority = priority;
    this.isComplete = false;
  }

  /**
   * Returns the unique ID of this task.
   *
   * @return the ID of this task
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Returns the description of this task.
   *
   * @return the description of this task
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns the priority level of this task.
   *
   * @return the priority of this task
   */
  public TaskPriority getPriority() {
    return this.priority;
  }

  /**
   * Returns whether this task has been completed.
   *
   * @return true if this task is complete; false otherwise
   */
  public boolean isComplete() {
    return this.isComplete;
  }

  /**
   * Marks this task as complete.
   */
  public void markComplete() {
    this.isComplete = true;
  }

  /**
   * Compares this task to another task based on priority.
   * The comparison is by priority ordinal: HIGH (0) < MEDIUM (1) < LOW (2).
   * A negative return value means this task's priority comes before the other
   * task's priority in the list ordering.
   *
   * @param other the other Task to compare to
   * @return a negative integer if this task should come before other,
   *         zero if they have the same priority, or a positive integer if this
   *         task should come after other
   */
  @Override
  public int compareTo(Task other) {
    if (other == null) {
      throw new NullPointerException("Cannot compare Task to null");
    }
    return Integer.compare(this.priority.ordinal(), other.priority.ordinal());
  }

  /**
   * Returns a String representation of this task in the format:
   * PRIORITY: description (ID)
   * If the task is complete, " [DONE]" is appended. For example:
   * HIGH: Fix login bug (3) [DONE]
   *
   * @return a String representation of this task
   */
  @Override
  public String toString() {
    String result = this.priority + ": " + this.description + " (" + this.ID + ")";
    if (this.isComplete) {
      result += " [DONE]";
    }
    return result;
  }

  /**
   * Two tasks are considered equal if they have the same description and priority,
   * regardless of ID or completion status. This is useful for checking whether a
   * task with the same content already exists in the list.
   *
   * @param o the object to compare against
   * @return true if the other object is a Task with the same description and priority
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Task)) {
      return false;
    }
    Task other = (Task) o;
    return this.description.equals(other.description) && this.priority == other.priority;
  }

  /**
   * Resets the ID generator to 1. Call this at the beginning of each test method
   * to ensure predictable ID values.
   */
  public static void resetIDGenerator() {
    idGenerator = 1;
  }
}
