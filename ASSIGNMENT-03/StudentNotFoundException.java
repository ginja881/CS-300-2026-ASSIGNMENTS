/**
 * Custom checked exception thrown when a requested student cannot be found
 * in the gradebook. Extends Exception to ensure proper handling of
 * student lookup failures.
 */
public class StudentNotFoundException extends Exception {
  
  /**
   * Constructs a new StudentNotFoundException with the specified detail message.
   *
   * @param message the detail message explaining the cause of the exception
   */
  public StudentNotFoundException(String message) {
    super(message);
  }
}