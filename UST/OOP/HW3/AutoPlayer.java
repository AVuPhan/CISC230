import java.util.ArrayList;
/**
 * this is the AI player. it is an extention of player but does not ever take user input because it decides for itself.
 * it can invoke the same methods as a UI player but it will have different actions.
 * @author andy1
 *
 */
public class AutoPlayer extends Player{
	/**
	 * arraylist with dice results that will be sent to the automated player to prevent cheating
	 */
	private ArrayList<Integer> arr;
	
	/**
	 * constructor for the automated player. 
	 * @param name is the name for the auto player taken from the super class Player
	 */
	public AutoPlayer(String name) {
		super(name);
	}//end constructor
	
	/**
	 * the auto player is told the number of registered players
	 */
	public void recieveNumberofPlayers(int numOfPlayers) {
		System.out.println(super.getName()+" knows there are "+numOfPlayers+"players.");
	}//end recieveNumberofPlayers
	
	/**
	 * the auto player is told the number of dice used
	 */
	public void reciveDiceInfo(int numOfDice) {
		System.out.println(super.getName()+" knows there are "+numOfDice+"dice in this game.");
	}//end recieveDiceInfo
	
	/**
	 * auto player can told the results of its roll for a certain round. copied into arr
	 */
	public void receiveRollResults(ArrayList<Integer> curDieResults) {
		arr = new ArrayList<Integer>();
		for(int i=0; i<curDieResults.size(); i++) {
			arr.add(i, curDieResults.get(i));
		}
		
		System.out.println(super.getName()+ " has recieved the results.");
	}//end receiveRollResults
	
	/**
	 * This auto player will never guess. It is smart but not that smart
	 * @return always return false
	 */
	public boolean wantsToGuess() {
		//Bot has chance to guess NRD. It will never guess because it is not that intelligent yet.
		System.out.println(super.getName()+" says No to guessing.");
		return false;
	}// end wantToGuess
	
	/**
	 * the auto player is given the opportunity to re-roll any of the dice. 
	 * @param numOfDice is the number of dice. it will be the length for rerolls[]
	 * @return a boolean array with what the auto player wants to reroll. T for yes and false for no. 
	 */
	public boolean[] reroll(int numOfDice) {
		//determine which dice to re-roll
		boolean [] rerolls = new boolean[numOfDice];
		System.out.println("Bot is picking die to reroll...");
		for(int i=0; i<rerolls.length; i++) {
			if(arr.get(i)<3) {						//if the diceroll is less than 3, then reroll that dice
				rerolls[i] = true;
			}
			else {
				rerolls[i] = false;
			}
		}//end loop
		
		return rerolls;								//return the array with all the booleans
	}//end re-rolls

}//end class
