//Brian Nguyen
//Due Date 5/15
package FilesAndExceptions;
public class Account {
	private int acctNumber;
	private String name;
	private double balance;
	
	public Account() {
		acctNumber = 0;
		balance = 0;
	}
	public Account(int acctNumber, String name, double balance) {
		this.acctNumber = acctNumber;
		this.name = name;
		this.balance = balance;
	}
	public String toString(int acctNumber, String name,double balance) {
		return getAcctNumber() + ":" + getName() + ":" + getBalance();
	}
	public void deposit(double amount) {
		if(amount >= 0)
			balance = getBalance() + amount;
	}
	public void withdraw(double amount) {
		if (amount >= 0 && amount <= balance)
			balance = getBalance() - amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public int getAcctNumber() {
		return acctNumber;
	}
}