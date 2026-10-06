/**
 * Custom checked exception thrown when attempting to add a student with a duplicate ID.
 * Extends Exception to ensure proper handling of duplicate student scenarios.
 */
public class DuplicateStudentException extends Exception {
  
  /**
   * Constructs a new DuplicateStudentException with the specified detail message.
   *
   * @param message the detail message explaining the cause of the exception
   */
  public DuplicateStudentException(String message) {
    super(message);
  }
}