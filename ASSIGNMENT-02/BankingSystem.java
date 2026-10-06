/*
* Author: Joseph Carter
* Email: jtcarter5@wisc.edu
* Course: CS 300, Fall 2026
* Assignment: Assignment 03
* Citations: None
*/

import java.util.ArrayList;

/**
 * Main banking system class, mostly manipulating a dynamic array of accounts
*/
public class BankingSystem {
  private ArrayList<BankAccount> accounts;
  
  /**
  * Constructor for banking system
  */
  public BankingSystem() {
    // Initialize accounts dynamic array
    accounts = new ArrayList<BankAccount>();
  }
  
  /**
  * Unique account creation
  * @param accountNumber account number for new account
  * @param name account name for new account
  * @param initialDeposit initial deposit for new account
  */
  public void createAccount(String accountNumber, String name, double initialDeposit) {
    // Try to find account and see if
    // InvalidAccountException gets raised
    try {
      findAccount(accountNumber);
    } catch (InvalidAccountException e) {
      // If raised, then make new account
      accounts.add(new BankAccount(accountNumber, name, initialDeposit));
    } 
    // If not raised, then raise an entirely
    // different InvalidAccountException
    throw new InvalidAccountException("(ERROR) No duplicates");

  }
  
  /**
  * Finding accounts.
  * @param accountNumber desired accountNumber
  * @return desired account with identitical accountNumber
  */
  public BankAccount findAccount(String accountNumber) {
    // Iterate through accounts dynamic array to check for
    // account with identitical accountNumber.

    for (int i = 0; i < accounts.size(); i++) {
      BankAccount currentAccount = accounts.get(i);
      String currentAccountNumber = currentAccount.getAccountNumber();
      if (currentAccountNumber.equals(accountNumber)) {
        return currentAccount;
      }
    }
    
    // If none found, then raise InvalidAccountException
    throw new InvalidAccountException("(ERROR) Account not found");
  }
  
    
  /**
  * Facilitating account transfer
  * @param fromAccount sender accountNumber
  * @param toAccount receiver accountNumber
  * @param amount transaction amount
  */
  public void transferMoney(String fromAccount, String toAccount, double amount) 
      throws InsufficientFundsException {
    // Check if accounts are same, then raise error if true
    if (fromAccount.equals(toAccount)) {
      throw new IllegalArgumentException("(ERROR) Sender and recipient account are the same.");
    }
    // Grab accounts
    BankAccount senderAccount = findAccount(fromAccount);
    BankAccount receiverAccount = findAccount(toAccount);
    
    // Withdraw from sender and deposit into receiver with amount
    senderAccount.withdraw(amount);
    receiverAccount.deposit(amount);
    
  }
   
  /**
  * Display account information
  * @param accountNumber desired account's account number
  */
  public void displayAccountInfo(String accountNumber) {
    // Grab chosen account
    BankAccount chosenAccount = findAccount(accountNumber);
    // Grab stringified info from chosenAccount and print it.
    System.out.println(chosenAccount.toString());
  }

  /**
  * Get sum of ALL account balances in accounts
  * @return sum of ALL account balances
  */
  public double getTotalBankBalance() {
    // Initialize total
    double accountSum = 0.0;
    // Iterate through accounts, adding balances from other accounts
    for (int i = 0; i < accounts.size(); i++) {
      BankAccount currentAccount = accounts.get(i);
      accountSum += currentAccount.getBalance();
    }
    
    // Return sum
    return accountSum;
  }
}


