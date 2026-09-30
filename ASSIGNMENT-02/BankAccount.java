public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;

  
  public BankAccount(
    String accountNumber,
    String accountHolderName,
    double initialBalance
  ) {
    if (accountNumber.length() != 8) {
      throw new InvalidAccountException("Invalid account: " + accountNumber);
    }
    if (accountHolderName == null) {
      throw new IllegalArgumentException("Illegal accountHolderName");
    }
    if (accountHolderName.length() == 0) {
      throw new IllegalArgumentException("accountHolderName length is zero");
    }
    
    if (initialBalance < 0.0) {
       throw new IllegalArgumentException("initialBalance is negative");
    }
    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  
  public void deposit(double amount) throws IllegalArgumentException {
    if (amount < 0.0) {
      throw IllegalArgumentException("Negative amount");
    }
    balance += amount;
  }

  
  public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
    if (amount < 0.0) {
      throw IllegalArgumentException("Negative Amount");
    }
    if (amount > this.balance) {
      throw InsufficientFundsException("Insufficient funds for transaction");
    }
    
    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public String toString() {
    return String.format(
      "Account: %s, Holder: %s, Balance: $%.2f",
      accountNumber,
      accountHolderName,
      balance
    );
  }
}
