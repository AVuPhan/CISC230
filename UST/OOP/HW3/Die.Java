import java.util.Random;
/**
 * This class represents the die that is used in the game. It can be any set number of sides and contains an ID.
 * the die is a super class for the NRD. 
 * @author andy1
 *
 */
public class Die {
	/**
	 * amount of sides on a die
	 */
	private int sides;
	/**
	 * create a random generatator
	 */
	private Random rand;
	/**
	 * id for the die
	 */
	private int ID;
	
	/**
	 * constructor gives a die its total number of sides as well as an ID
	 * @param s is sides
	 * @param id is ID
	 */
	public Die(int s, int id) {
		sides = s;
		ID = id;
	}//end constructor
	
	/**
	 * number of sides on a certain die
	 * @return the number of sides on a given die
	 */
	public int getSides() {
		return sides;
	}//end getSides
	
	/**
	 * the die's id number
	 * @return the id number
	 */
	public int getID() {
		return ID;
	}//end getID
	
	/**
	 * generates random number based on the number of sides in the die
	 * @return the random number 
	 */
	public int roll() {
		rand = new Random();
		int result = rand.nextInt(this.sides)+1;	//so the dice sides cant be 0. starts at 1 instead
		return result;
	}//end roll
	
	/**
	 * spits out info of the certain die
	 */
	public String toString() {
		return "Num of sides: "+getSides()+"\nID: "+getID();
	}//end toString
}//end class
