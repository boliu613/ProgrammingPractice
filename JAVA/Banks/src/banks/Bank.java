package banks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Bank {
	//instance variables
	private HashMap<Integer, BankAccount> bankAccounts;
	
	private HashMap<Integer, Customer> customers;
	//HashMap models Customer to BankAccount relationship
	private HashMap<Customer, BankAccount> customer2BankAccount;
	private double assets;
   
    //TODO fill this in lecture with methods for
    // 1. opening an account
    // 2. checking balance of an account
    // 3. deposit, withdraw
    // 4. update address 
	
	/**
	 * create a new account for a new customer.
	 * @param name
	 * @param initBal
	 * @return
	 */
	public BankAccount openAccount(String name, double initBal){
		Customer newCustomer = new Customer(name);
		BankAccount newAccount = new BankAccount(newCustomer, initBal);
		customers.put(newCustomer.getCustomerID(), newCustomer);
		bankAccounts.put(newAccount.getAccountNumber(), newAccount);
		assets += initBal;
		customer2BankAccount.put(newCustomer, newAccount);		
		return newAccount;		
	}
	
	public void deposit(int accountNum, double depAmount){
		//find the account
		BankAccount theAccount = bankAccounts.get(accountNum);
		theAccount.deposit(depAmount);
	}
	

	public static void main(String[] args) {
        
    }
	
	public HashMap<Integer, BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(HashMap<Integer, BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
    public HashMap<Integer, Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(HashMap<Integer, Customer> customers) {
		this.customers = customers;
	}
}
