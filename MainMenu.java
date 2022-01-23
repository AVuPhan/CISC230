/**
 * User interface for the user to control the program. contains a main menu and branching methods for each of the 10 options
 * @author andy phan
 */
import java.util.Scanner;
public class MainMenu {
	
	/**
	 * This scanner exists from the beginning of time and is present throughout this entire class
	 * All branching void methods will use this scanner to take in user input
	 */
	final private static Scanner scan = new Scanner(System.in);
	
	/**
	 * Menu prints out the menu for the user. User inputs a value x
	 */
	public static void menu() {
		prnt("\nPlease select a number from the following options:");
		prnt("1) Add new Stock Index Card\r\n" + 
				"2) Remove Stock Index Card\r\n" + 
				"3) Increase Stock by SIC-ID\r\n" + 
				"4) Decrease Stock by SIC-ID\r\n" + 
				"5) Display Stock Index Card by SIC-ID\r\n" + 
				"6) Display Stock Index Card by Author\r\n" + 
				"7) Display Stock Index Card by Title\r\n" + 
				"8) Display All Stock Index Cards\r\n" +
				"9) Change price by SIC-ID\r\n"+
				"10) Quit");
		int x = scan.nextInt();
		whatToDo(x);
	}//end menu
	
	/**
	 * whatToDo determines what to do with x and based on that input it will branch to the manager or
	 * as default it will reprompt and go back to the menu 
	 * Each case will go to a specific void method to get user input and then to the InventoryManager for additional instructions
	 * Then after accomplishing the task, it will return to the menu again and end the case.
	 * Case 10 is the only one that doesnt take user input nor returns to the menu.
	 * @param x user input, corresponds to one of the 10 actions. Will go back to menu and reprompt if x is not <=10 && >=1 
	 */
	public static void whatToDo(int x) {
		switch(x) {									//switch statement for x, will go to either 1-10 or default
			case 1:
				//Add new Stock Index Card
				addCardOP();
				menu();
				break;
			case 2:
				//Remove Stock Index Card
				removeOP();
				menu();
				break;
			case 3:
				//Increase Stock by SIC-ID
				increaseStockOP();
				menu();
				break;
			case 4:
				//Decrease Stock by SIC-ID
				decreaseStockOP();
				menu();
				break;
			case 5:
				//Display Stock Index Card by SIC-ID
				displayByID();
				menu();
				break;
			case 6:
				//Display Stock Index Card by Author
				displayByAuthor();
				menu();
				break;
			case 7:
				//Display Stock Index Card by Title
				displayByTitle();
				menu();
				break;
			case 8:
				//Display all Stock Index Cards
				displayAllOP();
				menu();
				break;
			case 9:
				//Change price by SIC-ID
				changePriceOP();
				menu();
				break;
			case 10:
				//Quit
				prnt("Goodbye");
				break;
			default:
				//Typed in anything besides 1-10
				prnt("Please enter an int between 1 and 10\n");
				menu();
				break;
		}//end switch
	}//end whatToDo
	
	/**
	 * prompts user for the new info needed to create a new card
	 */
	private static void addCardOP() {
		int i;
		String t;
		String a;
		double d;
		int q;
		
		prnt("Making a new card:");
		prnt("Please enter new SIC-ID: ");
		i = scan.nextInt();							//takes in the new ID
		scan.nextLine();							//kills the \n from the previous line
		prnt("Enter the title of the book: ");
		t = scan.nextLine();						//takes in new title
		
		prnt("Enter the author of the book: ");
		a = scan.nextLine();						//takes in new author
		
		prnt("Enter the price of the book: ");
		d = scan.nextDouble();						//takes in new price
		
		prnt("Enter the number of books in stock: ");
		q = scan.nextInt();							//takes in new stock
		
		Manager.addCard(i,t,a,d,q);					//sends all the new info to the manager
	}//end addCardOP
	
	/**
	 * Prompts user for the id of the card that is to be removed. 
	 */
	public static void removeOP() {
		prnt("Enter the ID of the card you wish to remove");
		int killID = scan.nextInt();
		if(Manager.removeCard(killID)==true) {
			prnt("Successfully removed card");
		}
		else {
			System.err.println("Error: Cannot find ID");
		}
	}//end removeOP
	
	/**
	 * prompt user for the ID of the card that will add more to the current stock 
	 */
	public static void increaseStockOP() {
		prnt("Please enter the ID of the card you wish to increase");
		int id = scan.nextInt();
		prnt("Enter the amount you want to increase by");
		int amount;
		do {
			amount = scan.nextInt();
		}
		while(amount<0);											//must ad a positive amount
		if(Manager.increaseCardQuantity(id,amount)==true) {			//id and additional amount is sent to the manager
			prnt("Successfully increased stock by "+amount);
		}
		else {
			System.err.println("Error: Cannot find ID");
		}
	}//end increaseStockOP
	
	/**
	 * prompts user for the ID of the card and amount that will be subtracted from the current stock 
	 */
	public static void decreaseStockOP() {
		prnt("Please enter the ID of the card you wish to decrease");
		int id = scan.nextInt();
		prnt("Enter the amount you want to decrease by");
		int amount;
		do {
			amount = scan.nextInt();
		}
		while(amount<0);											//must subtract a positive amount
		if(Manager.decreaseCardQuantity(id,amount)==true) {
			prnt("Successfully decreased stock by "+amount);
		}
		else {
			System.err.println("Error: Cannot find ID or amount is greather than current stock");
		}
	}
	
	/**
	 * prompts the user for the id and sends it to the manager
	 * prints out the stock card for that ID
	 */
	private static void displayByID() {
		//Scanner scan = new Scanner(System.in);
		prnt("Please enter the ID");
		int search = scan.nextInt();
		prnt(Manager.getInfoByID(search));
		//scan.close();
	}//end displayByID
	
	/**
	 * prompts the user for the authors name and sends it to the manager
	 * prints out the stock card for that author
	 */
	private static void displayByAuthor() {
		//Scanner scan = new Scanner(System.in);
		scan.nextLine();
		prnt("Please enter the name of the author");
		String search = scan.nextLine();
		prnt(Manager.getInfoByAuthor(search));
		//scan.close();
	}//end displayByAuthor
	
	/**
	 * prompts the user for the title of a book and sends it to the manager
	 * prints out the stock card info for that title
	 */
	private static void displayByTitle() {
		//Scanner scan = new Scanner(System.in);
		scan.nextLine();
		prnt("Please enter the title of the book");
		String search = scan.nextLine();
		prnt(Manager.getInfoByTitle(search));
		//scan.close();
	}//end displayByTitle
	
	/**
	 * Prints every StockCard in the arraylist.  
	 */
	private static void displayAllOP() {
		prnt("Displaying all cards: ");
		prnt(Manager.goToString());
	}
	
	/**
	 * prompts the user for the id and the new price and sends it to messenger
	 * prints out whether it was changed or not
	 */
	private static void changePriceOP() {
		prnt("Enter the ID of the card");
		int id = scan.nextInt();
		prnt("Enter the new price");
		double newP;
		do {
			newP = scan.nextDouble();
		}
		while(newP<0);										//price must be greater than 0
		if(Manager.changeCardPrice(id,newP)==true) {
			prnt("Price successfully changed");
		}
		else {
			System.err.println("Error: Cannot find ID");
		}
	}//end changePriceOP
	
	/**
	 * Shortcut for System.out.println
	 * @param s takes in a string
	 * @return the printed string s
	 */
	public static String prnt(String s) {
		System.out.println(s);
		return s;
	}
	
}//end class
