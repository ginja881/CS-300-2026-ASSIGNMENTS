/*
* Author: Joseph Carter
* Email: jtcarter5@wisc.edu
* Course: CS 300, Fall 2026
* Assignment: Assignment 03
* Citations: None
*/

/**
* Class that represents a bank account in our banking system
* Accounts have unique account numbers, a holder name, and a balance.
*/
public class BankAccount {

  private String accountNumber;
  private double balance;
  private String accountHolderName;

  /**
  * Constructor for BankAccount
  * @param accountNumber parameter for initial accountNumber value
  * @param accountHolderName parameter for initial accountHolderName value
  * @param initialBalance parameter for initial balance
  * @throws IllegalArgumentException if the name is null, empty, or the 
  * initial balance is negative 
  * @throws InvalidAccountException if the account number's length is not 
  * 8
  */
  public BankAccount(
      String accountNumber,
      String accountHolderName,
      double initialBalance
  ) {
    // Check if length does not equal eight
    if (accountNumber.length() != 8) {
      throw new InvalidAccountException("Invalid account: " + accountNumber);
    }
    // Check if holder name is null, then check if it is empty
    if (accountHolderName == null) {
      throw new IllegalArgumentException("Illegal accountHolderName");
    }
    if (accountHolderName.length() == 0) {
      throw new IllegalArgumentException("accountHolderName length is zero");
    }
    // Check initial balance to see if it is negative
    if (initialBalance < 0.0) {
      throw new IllegalArgumentException("initialBalance is negative");
    }

    // Set attributes
    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  
  /**
  * Method for depositing money into account
  * @param amount amount to deposit, cannot be negative
  * @throws IllegalArgumentException if amount is negative
  */
  public void deposit(double amount) {
    // Check if amount is negative
    if (amount < 0.0) {
      throw new IllegalArgumentException("Negative amount");
    }
    // If not, add to balance
    balance += amount;
  }

    
  /**
  * Method for withdrawing money from account
  * @param amount amount to withdraw, cannot be negative
  * @throws IllegalArgumentException if amount is negative
  * @throws InsufficientFundsException if account does not have enough funds for withdrawal
  */
  public void withdraw(double amount) throws InsufficientFundsException {
    // Check if amount is negative, then check if account has sufficient funds
    if (amount < 0.0) {
      throw new IllegalArgumentException("Negative Amount");
    }
    if (amount > this.balance) {
      throw new InsufficientFundsException("Insufficient funds for transaction");
    }
    
    // Substract from account
    balance -= amount;
  }
  
  /**
  * Getter for balance
  * @return returns the account balance
  */
  public double getBalance() {
    return balance;
  }
  
  /**
  * Getter for account number
  * @return returns the account number string
  */
  public String getAccountNumber() {
    return accountNumber;
  }
  
  /**
  * Getter for account holder name
  * @return returns the account holder name string
  */
  public String getAccountHolderName() {
    return accountHolderName;
  }
  
  /** 
  * Stringify account info 
  * @return string representing account info
  */
  public String toString() {
    return String.format(
      "Account: %s, Holder: %s, Balance: $%.2f",
      accountNumber,
      accountHolderName,
      balance
    );
  }
}
