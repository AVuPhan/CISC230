/**
 * Manager contains methods to edit/manipulate the inventory of StockIndexCards
 * @author andy phan
 */
import java.util.ArrayList;
public class Manager {
	
	/**
	 * This arraylist is, and ever shall be, of type StockIndexCard and is called the inventory
	 */
	final private static ArrayList<StockIndexCard> inventory = new ArrayList<StockIndexCard>();
	
	/**
	 * This method adds a new card into the arraylist
	 * will check for duplicate IDs
	 * @param id is the new id
	 * @param t is the new title
	 * @param a is the new author
	 * @param p is the original price
	 * @param q is the current stock
	 */
	public static void addCard(int id,String t, String a, double p, int q) {
		if(!hasCard(id)) {														//check to make sure no card with that id currently exists
			StockIndexCard newCard = new StockIndexCard(id, t, a, p, q);		//create a new card
			inventory.add(newCard);												//add the card into the list
			System.out.println("Successfully added card");
		}
		else {
			System.err.println("Error: SIC-ID "+id+" already exists.");
		}
	}//end addCard
	
	/**
	 * this will remove a card from the list
	 * @param id of the card you wish to remove
	 * @return true if it was successfully removed and false if the card does't exist
	 */
	public static boolean removeCard(int id) {
		for(int i=0;i<inventory.size(); i++) {
			if(inventory.get(i).getSicID() == id) {
				inventory.remove(i);
				return true;
			}
		}
		return false;
	}//end removeCard
	
	/**
	 * this will add more to the current stock of a StockCard
	 * @param id of the card you want to add to
	 * @param amt the amount youre adding 
	 * @return true if the amount is added. False if you cant find the ID
	 */
	public static boolean increaseCardQuantity(int id, int amt) {
		int curStock;											//current stock
		for(int i=0;i<inventory.size(); i++) {
			curStock = inventory.get(i).getQuantity();			//current stock gets the stock current quantity
			if(inventory.get(i).getSicID() == id) {
				inventory.get(i).setQuantity(curStock+amt);		//add the amount and it becomes the new quantity
				return true;
			}
		}
		return false;											//cannot find ID
	}//end increaseCardQuantity
	
	/**
	 * this will subtract the current stock of StockCard
	 * @param id of the card you want to subtract from
	 * @param amt the amount youre subtracting
	 * @return true of the amount is subtracted. False if the id is not found
	 */
	public static boolean decreaseCardQuantity(int id, int amt) {
		int curStock;											//current stock
		for(int i=0;i<inventory.size(); i++) {					//currentStock gets the stock's current quantity
			curStock = inventory.get(i).getQuantity();
			if(inventory.get(i).getSicID() == id) {				
				
				if(inventory.get(i).getQuantity()<amt) {		//check to make sure the amount were subtracting is less than the current stock
					return false;								//amount to decrease stock is greater than stock. Bad.
				}
				else {
					inventory.get(i).setQuantity(curStock-amt);	//subtract amount from current stock and that becoems the new current quantity
					return true;
				}
			}
		}
		return false;											//cannot find ID
	}//end decreaseCardQuantity
	
	/**
	 * this will loop until it finds the ID and returns the StockCard info for that ID
	 * @param id the user inputed to find
	 * @return all the Card info for that specific ID
	 */
	public static String getInfoByID(int id) {
		String ret="";											//create a return string for the String
		for(int i=0;i<inventory.size(); i++) {					//loop thru the list
			if(inventory.get(i).getSicID() == id) {				//if the target id matches an id, then
				ret=ret+inventory.get(i).toString();			//put the card info on the ret string
			}
		}
		if(ret=="") {											//so if it found nothing, then ret is an empty string
			ret=ret+"The ID "+id+" does not exist";				//therefore no card with that ID exists
		}
		return ret;
	}//end getInfoByID
	
	/**
	 * this will loop until it finds the authors name and return the StockCard info for that name
	 * @param auth is the user inputed target name
	 * @return all the card info for that specific name
	 */
	public static String getInfoByAuthor(String auth) {
		String ret="";											//create a return string
		for(int i=0;i<inventory.size(); i++) {					//loop
			if(inventory.get(i).getAuthor().equals(auth)) {		//if it finds a match, then 
				ret=ret+inventory.get(i).toString();			//it puts the info on the ret string
			}
		}
		if(ret==" ") {
			ret = ret +"The author "+auth+" does not exist";	//if it found nothing, then it says No card wit that author exists
		}
		return ret;
	}//end getInfoByAuth
	
	/**
	 * this will loop until it finds the title of a book and returns the card info for that book
	 * @param tit is the target title of the book. User inputed
	 * @return a string with the card info for that book
	 */
	public static String getInfoByTitle(String tit) {
		String ret="";											//create a return string
		for(int i=0;i<inventory.size(); i++) {					//loop
			if(inventory.get(i).getTitle().equals(tit)) {		//if it find a match, then
				ret = ret+inventory.get(i).toString();			//put the card info on that string
			}
		}
		if(ret=="") {
			ret = ret +"The title "+tit+" does not exist";		//if there is not info then the title doesn't exist
		}
		return ret;
	}//end getInfoByTit
	
	/**
	 * finds the card that is going to have its price updated
	 * @param id is the target card ID
	 * @param d is the new price
	 * @return true if the price is successfully changed
	 */
	public static boolean changeCardPrice(int id, double d) {
		for(int i=0;i<inventory.size(); i++) {					//loop
			if(inventory.get(i).getSicID() == id) {				//if it finds a match, then
				inventory.get(i).setPrice(d);					//update the price
				return true;
			}
		}
		return false;											//cannot find ID
	}//end changeCardPrice
	
	/**
	 * loops through the list to find matching IDS
	 * @param id the target ID
	 * @return true if it found a duplicate ID. We dont want duplicate IDs
	 */
	public static boolean hasCard(int id) {
		for(int i=0;i<inventory.size(); i++) {
			if(inventory.get(i).getSicID() == id) {
				return true;
			}
		}
		return false;
	}//end hasCard
	
	/**
	 * prints out every card in the list and also every element of each cards
	 * boolean printMe solved a bunch of errors so as long as that is there this code runs. I have yet to determine why 
	 * @return a string that is the list of cards and their IDs, titles, authors, prices, and quantities
	 */
	public static String goToString() {
		String ret="";
		boolean printMe = true;
		for(int i=0;i<inventory.size(); i++) {
			if(printMe==true) {
				ret = ret+inventory.get(i).toString();
			}
		}
		return ret;
	}//end toString
}//end class
