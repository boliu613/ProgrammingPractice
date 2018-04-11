package experiments;

public class BankAccount {
    //fill in this in during the lecture
	
	String owner;
	double balance;
	
	BankAccount (String owner, double balance){
		this.owner = owner;
		this.balance = balance;		
	}
	
	//deposit method
	void deposit(double amount){
		this.balance += amount;
		
	}
	
	//withdraw method
	void withdraw(double amount){
		this.balance -= amount;
		
	}
	
	double getBalance(double amount){
		return balance;
	}
	
}
