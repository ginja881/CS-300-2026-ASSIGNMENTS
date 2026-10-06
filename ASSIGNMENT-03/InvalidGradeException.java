/**
 * Custom checked exception thrown when an invalid grade value is provided.
 * Extends Exception to ensure proper handling of grade validation errors.
 */
public class InvalidGradeException extends Exception {
  
  /**
   * Constructs a new InvalidGradeException with the specified detail message.
   *
   * @param message the detail message explaining the cause of the exception
   */
  public InvalidGradeException(String message) {
    super(message);
  }
}