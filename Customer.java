//Brian Nguyen
//Due Date 5/15
package FilesAndExceptions;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Scanner;
public class Customer extends Account {
	public static void main(String args[]){
	String[] acctNumberArray = new String[6];
	String[] nameArray = new String[6];
	String[] balanceArrayString = new String[6];
	int[] accountNumber = new int[6];
	double[] balanceArray = new double[6];
	Account account = null;
		try {
			account = fillArrayList(accountNumber,balanceArray, acctNumberArray, nameArray, balanceArrayString);
		} catch (FileNotFoundException ex) {
			System.out.println("No File Found");
			System.exit(0);
		}
		try {
			actions(account);
		} catch(Exception ex) {
			System.out.println("Error");
		}
	} 
public static Account fillArrayList(int[] accountNumber,double balanceArray[], String[] acctNumberArray, String[] nameArray, String[] balanceArrayString) throws FileNotFoundException {
	java.io.File file = new java.io.File("accounts.dat");
	Scanner fileInput = new Scanner(file);
	fileInput.useDelimiter(":|\n");
	for(int i = 0; i < 6; i++){
		acctNumberArray[i] = fileInput.next();
		nameArray[i] = fileInput.next();
		balanceArrayString[i] = fileInput.next();
	}
	fileInput.close();
	accountNumber = getIntArray(acctNumberArray);
	balanceArray = getDoubleArray(balanceArrayString);
	ArrayList<Account> accountList = getArrayList(accountNumber, nameArray, balanceArray);
	return getAccount(accountList, accountNumber);
}
public static int[] getIntArray(String[] acc) {
	int[] account = new int[6];
	for(int i = 0;i < 6; i++) {
	account[i] = Integer.parseInt(acc[i]);
	}
	return account;
}
public static double[] getDoubleArray(String[] acc) {
	double[] account = new double[6];
	for(int i = 0;i < 6; i++) {
	account[i] = Double.parseDouble(acc[i]);
	}
	return account;
}
public static ArrayList<Account> getArrayList(int[] acctNumberArray, String[] nameArray, double[] balanceArray){
	ArrayList<Account> arrayList = new ArrayList<Account>();
	for(int j = 0; j < 6; j++) {
		arrayList.add(new Account(acctNumberArray[j] , nameArray[j], balanceArray[j]));
	}
	return arrayList;
}
public static Account getAccount(ArrayList<Account> arrayList, int[] accountNumber) {
	Scanner input = new Scanner(System.in);
	int arrayIndex = -1;
	boolean done = false;
	while(done == false) {
		System.out.print("Enter your account number: ");
		String acc = input.next();
		if(validAcctNumber(acc)) {
			for(int i = 0; i< 6 ; i++) {
				if(Integer.parseInt(acc) == accountNumber[i]) {
					arrayIndex = i;
					done = true;
				}
				if(i == 5 && arrayIndex == -1) {
					System.out.println(acc + " is not a valid account number.");
					done = false;
				}
			}
		}
		else done = false;
	}
	return arrayList.get(arrayIndex);
}
public static void actions(Account account) throws NoSuchElementException {
	System.out.println("Hello, " + account.getName());
	System.out.println("Your current balance is $" + account.getBalance());
	Scanner sc = new Scanner(System.in);
	boolean breakb = false;
	while(breakb == false) {
	System.out.print("D)eposit, W)ithdraw, or F)inish? ");
	char letter = sc.next().charAt(0);
	if(letter == 'd' || letter == 'D') {
		System.out.print("Enter amount to deposit: $");
		String deposit = sc.next();
		if(validDeposit(deposit, account) == false) {
			continue;
		}
		else account.deposit(Double.parseDouble(deposit));
		System.out.println("Your current balance is $" + account.getBalance());
	}
	else if(letter == 'w' || letter == 'W') {
		System.out.print("Enter amount to withdraw: $");
		String withdraw = sc.next();
		if(validWithdraw(withdraw, account) == false) {
			continue;
		}
		else account.withdraw(Double.parseDouble(withdraw));
		System.out.println("Your current balance is $" + account.getBalance());
	}
	else if(letter == 'f' || letter == 'F')
		breakb = true;
	else System.out.println("Not an option");
	}
	System.out.println("Goodbye, " + account.getName() + ".");
	sc.close();
}
public static boolean validDeposit(String deposit, Account account) {
	try {
		double number = Double.parseDouble(deposit);
		if(number < 0) {
		System.out.println("You cannot deposit a negative amount.");
		return false;
		}
		else return true;
	} catch(Exception ex) {
		System.out.println(deposit + " is not a number.");
		return false;
	}
}
public static boolean validWithdraw(String withdraw, Account account) {
	try {
		double number = Double.parseDouble(withdraw);
		if(number < 0) {
			System.out.println("You cannot withdraw a negative amount.");
			return false;
		}
		else if (number > account.getBalance()) {
			System.out.println("You cannot withdraw more than you have");
			return false;
		}
		else return true;
	} catch(Exception ex) {
		System.out.println(withdraw + " is not a number.");
		return false;
	}
}
public static boolean validAcctNumber(String acc) {
	try {
		Integer.parseInt(acc);
		return true;
	} catch(Exception ex) {
		System.out.println(acc + " is not a number.");
		return false;
	}
}
}