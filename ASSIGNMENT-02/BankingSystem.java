import java.util.ArrayList;

public class BankingSystem {
  private ArrayList<BankAccount> accounts;

  public BankingSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  // ADD: Exception handling for duplicate accounts
  public void createAccount(
      String accountNumber, String name, double initialDeposit) {
    // TODO: Check if account already exists (throw InvalidAccountException if
    // duplicate)
    // TODO: Handle exceptions thrown by the BankAccount constructor.
    // Handling may involve catching the exception or declaring it in this
    // method’s throws clause. Consider whether this method can resolve an
    // invalid account (catch) or must defer the issue to its caller (throws).
    BankAccount account = new BankAccount(accountNumber, name, initialDeposit);
    accounts.add(account);
  }

  // ADD: Exception throwing for account not found
  public BankAccount findAccount(String accountNumber) {
    // TODO: Throw InvalidAccountException if account not found
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    return null; // Replace this with exception throwing
  }

  // ADD: Exception handling and atomic transactions
  public void transferMoney(
      String fromAccountNum, String toAccountNum, double amount) {
    // TODO: Find both accounts (handle exceptions)
    // TODO: Validate amount is positive
    // TODO: Check for same account transfer
    // TODO: Ensure atomic transaction (both operations succeed or both fail)

    BankAccount fromAccount = findAccount(fromAccountNum);
    BankAccount toAccount = findAccount(toAccountNum);

    fromAccount.withdraw(amount);
    toAccount.deposit(amount);
  }

  public void displayAccountInfo(String accountNumber) {
    // TODO: Handle case when account doesn't exist
    BankAccount account = findAccount(accountNumber);
    System.out.println(account.toString());
  }

  public double getTotalBankBalance() {
    double total = 0;
    for (BankAccount account : accounts) {
      total += account.getBalance();
    }
    return total;
  }
}