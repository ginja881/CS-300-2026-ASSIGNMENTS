import java.util.ArrayList;

public class BankSystem {
  private ArrayList<BankAccount> accounts;

  public BankSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  public void createAccount(String accountNumber, String name, double initialDeposit) throws InvalidAccountException {
    for (int i = 0; i < accounts.size(); i++) {
      BankAccount currentAccount = accounts.get(i);
      String currentAccountNumber = currentAccount.getAccountNumber();
      if (currentAccountNumber.equals(accountNumber) == 0) {
        throw new InvalidAccountException("(ERROR) accountNumber taken");
      }
    }
    
    if (initialDeposit < 0.0) {
      throw new InvalidAccountException("(ERROR) initialDeposit is 0.0, invalid account");
    }

    accounts.add(new BankAccount(accountNumber, name, initialDeposit));
  }
  public BankAccount findAccount(String accountNumber) throws InvalidAccountException {
    for (int i = 0; i < accounts.size(); i++) {
      BankAccount currentAccount = accounts.get(i);
      String currentAccountNumber = currentAccount.getAccountNumber();
      if (currentAccountNumber.equals(accountNumber) == 0) {
        return currentAccount;
      }
    }

    throw new InvalidAccountException("(ERROR) Account not found");
  }

  public void transferMoney(String fromAccount, String toAccount, double amount) throws InvalidArgumentException{
    if (fromAccount.equals(toAccount) == 0) {
      throw new InvalidArgumentException("(ERROR) Sender and recipient account are the same.");
    }
    BankAccount senderAccount = findAccount(fromAccount);
    BankAccount destinationAccount = findAccount(toAccount);

    senderAccount.withdraw(amount);
    destinationAccount.deposit(amount);
    
  }

  public void displayAccountInfo(String accountNumber) {
    BankAccount chosenAccount = findAccount(accountNumber);
    System.out.println(chosenAccount.toString());
  }

  public double getTotalBalance() {
    double accountSum = 0.0;
    for (int i = 0; i < accounts.size(); i++) {
       BankAccount currentAccount = accounts.get(i);
       accountSum += currentAccount.getBalance();
    }

    return accountSum;
  }
}


