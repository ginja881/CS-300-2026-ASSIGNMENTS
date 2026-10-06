/**
 * Custom checked exception thrown when attempting operations that require grades
 * but no grades are available. Extends Exception to ensure proper handling of
 * missing grade scenarios.
 */
public class NoGradesException extends Exception {
  
  /**
   * Constructs a new NoGradesException with the specified detail message.
   *
   * @param message the detail message explaining the cause of the exception
   */
  public NoGradesException(String message) {
    super(message);
  }
}