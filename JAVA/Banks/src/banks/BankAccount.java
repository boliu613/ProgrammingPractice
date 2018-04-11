package banks;

/**
 * A class representing a bankaccount.
 * Every bankaccount is associated with a single customer.
 * Every bankaccount has an account number that is auto generated.
 * @author Arvind
 *
 */
public class BankAccount {
    
    private static int counter = 86000;
    private Customer customer;
    private double balance;
    private int accountNumber;
    
    /**
     * Given a customer and a start balance create a new account
     * @param customer
     * @param balance
     */
    public BankAccount(Customer customer, double balance) {
        this.customer = customer;
        this.balance = balance;
        this.accountNumber = counter + 1;
    }
    
    /**
     * deposit to this account
     * @param amount - the amount to be deposited
     */
    public void deposit(double amount) {
        this.balance += amount;
    }
   
    /**
     * withdraw from this account
     * @param amount - the amount to be withdrawn
     */
    public void withdraw(double amount) {
        if (amount > balance)
            throw new RuntimeException("insufficient funds");
        this.balance -= amount;
    }
    
    /**
     * get the current balance
     * @return the current balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * get the account number. 
     * Each bank account has a unique number.
     * This number cannot be set directly
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

}
