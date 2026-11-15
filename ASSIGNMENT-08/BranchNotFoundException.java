// PROVIDED FILE

/**
 * Exception thrown when a specific Branch cannot be located in a Repository.
 *
 * This is a checked exception.
 */
public class BranchNotFoundException extends Exception {

  /**
   * Constructs a new BranchNotFoundException with an error message.
   *
   * @param message A descriptive message explaining the failure
   */
  public BranchNotFoundException(String message) {
    super(message);
  }
}
