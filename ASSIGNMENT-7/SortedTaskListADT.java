/**
 * An abstract data type representing a priority-organized list of tasks.
 * Implementations of this interface maintain tasks in priority order:
 * all HIGH priority tasks come first, then MEDIUM, then LOW.
 *
 * <p>Within each priority group, tasks are maintained in the order they were
 * inserted (newest tasks at the end of their priority group).</p>
 *
 * @param <T> the type of elements stored in this task list
 */
public interface SortedTaskListADT<T> {

  /**
   * Returns the number of tasks currently in the list.
   *
   * @return the size of the list
   */
  public int size();

  /**
   * Returns true if and only if the list contains no tasks.
   *
   * @return true if the list is empty; false otherwise
   */
  public boolean isEmpty();

  /**
   * Adds a new task to the list in the correct position based on its priority.
   * HIGH priority tasks are placed before MEDIUM, which are placed before LOW.
   * Within a priority group, the new task is added at the END of that group.
   *
   * @param task the task to add
   * @throws IllegalArgumentException if task is null
   */
  public void addTask(T task);

  /**
   * Returns the task at the given index in the list, without removing it.
   * Index 0 is the front of the list (the highest-priority, earliest-added task).
   *
   * @param index the zero-based index of the task to retrieve
   * @return the task at the given index
   * @throws IndexOutOfBoundsException if index is not in the range [0, size()-1]
   */
  public T get(int index);

  /**
   * Returns true if the list contains a task equal to the given task
   * (as determined by the task's equals() method).
   *
   * @param task the task to search for
   * @return true if the task is found in the list; false otherwise
   */
  public boolean contains(T task);

  /**
   * Removes and returns the task at the given index in the list.
   * Index 0 is the front of the list.
   *
   * @param index the zero-based index of the task to remove
   * @return the task that was removed
   * @throws IndexOutOfBoundsException if index is not in the range [0, size()-1]
   */
  public T remove(int index);

  /**
   * Removes all tasks from the list, resetting it to an empty state.
   */
  public void clear();
}
