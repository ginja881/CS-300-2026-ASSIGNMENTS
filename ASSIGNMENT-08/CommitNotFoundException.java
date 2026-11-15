// PROVIDED FILE

/**
 * Exception thrown when a specific commit cannot be located in a data structure.
 *
 * This is a checked exception typically used by lookup or removal operations.
 */
public class CommitNotFoundException extends Exception {

  /**
   * Constructs a new CommitNotFoundException with an error message.
   *
   * @param message A descriptive message explaining the failure
   */
  public CommitNotFoundException(String message) {
    super(message);
  }
}
