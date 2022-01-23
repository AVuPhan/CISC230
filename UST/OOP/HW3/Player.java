import java.util.Scanner;
import java.util.ArrayList;

/**
 * Player super class makes a player with a name. the player can interact in the 
 * game by guessing the NRD and selecting certain die to re-roll. 
 * @author andy1
 *
 */
public class Player {
	/**
	 * This is a String called name that is given to a player.
	 */
	private String name;
	/**
	 * The scanner is used throughout this class to get the user's input
	 */
	private Scanner scan;
	/**
	 * this Arraylist is the copy that the player receives to prevent cheating
	 */
	private ArrayList<Integer> arr;
	
	/**
	 * Constructor for Player class. Only needs a name
	 * @param name takes in a name and assigns it to the Player's name
	 */
	public Player(String name) {
		this.name = name;
	}//end constructor
	
	/**
	 * This method tells the player the total number of players registered to play
	 * @param numOfPlayers is the number of players
	 */
	public void recieveNumberofPlayers(int numOfPlayers) {
		System.out.println("There are "+numOfPlayers+"players.");
	}//end recieveNumberofPlayers
	
	/**
	 * this method tells the player the (random) number of dice the game master is using
	 * @param numOfDice
	 */
	public void reciveDiceInfo(int numOfDice) {
		System.out.println("There are "+numOfDice+"dice in this game.");
	}//end recieveDiceInfo
	
	/**
	 * This method tells the player the results of the dice rolls for a given round.
	 * creates a copy of the results and stored in arr
	 * @param curDieResults is the current die results
	 */
	public void receiveRollResults(ArrayList<Integer> curDieResults) {
		arr = new ArrayList<Integer>(); 
		for(int i=0; i<curDieResults.size(); i++) {
			arr.add(i, curDieResults.get(i));
		}
	}//end receiveRollResults
	
	/**
	 * prompts the user if they want to guess the NRD
	 * @return true if they want to guess and false if they do not.
	 */
	public boolean wantsToGuess() {
		scan = new Scanner(System.in);	//make a new scanner
		System.out.println("Would you like to guess the position of the NonRepeatingDie? (Y/N)");
		String ans;
		while(true) {
			ans = scan.nextLine();		//takes the user input
			ans = ans.toUpperCase();	//make it uppercase
			if(ans.equals("Y")) {
				return true;			//yes they want to guess, return true
			}
			else if(ans.equals("N")) {	//no they dont want to guess, return false
				return false;
			}
			else {						//did not enter y, n, Y, or N. will loop back
				System.err.println("Please enter y or n");
				continue;
			}
		}
	}// end wantToGuess
	
	/**
	 * take in the 2 guesses of the player
	 * @return an int[] with the 2 guesses
	 */
	public int[] guess(){
		scan = new Scanner(System.in);				//make a new scanner
		int[] guesses = new int[2];
		System.out.println("Enter your 1st guess of the NonRepeatingDie's position.");
		int guessPos1 = scan.nextInt();				//gets the users 1st input
		int guessPos2;
		while(true) {
			System.out.println("Enter your 2nd guess");
			guessPos2 = scan.nextInt();				//gets the users 2nd input
			if(guessPos2 == guessPos1) {			//the guess is the same as the first
				System.err.println("2nd guess cannot be the same as your 1st guess. Try again");
				continue;
			}
			else {
				break;
			}
		}
		guesses[0] = guessPos1;						//put both guesses in the int[] array and return that array
		guesses[1] = guessPos2;
		return guesses;
	}//end guess
	
	/**
	 * gets the users input whether they want to re-roll a certain die or not. 
	 * true if they do want to and false if they don't
	 * @param numOfDice is the number of dice, which is to be the size of the boolean[] array. 
	 * @return a boolean array with t and f indicating the action for a certain die
	 */
	public boolean[] reroll(int numOfDice) {
		scan = new Scanner(System.in);
		boolean[] rerolls = new boolean[numOfDice];	//boolean [] array is same length as dice collection
		for(int i=0; i<rerolls.length; i++) {
			while(true) {
				System.out.println("Reroll dice number "+(i+1)+"? (Y/N)");
				String ans;
				ans = scan.nextLine();				//gets users input
				ans = ans.toUpperCase();
				if(ans.equals("Y")) {				//chooses to reroll the current die
					rerolls[i] = true;
					break;
				}
				else if(ans.equals("N")) {			//chooses not to reroll the currnet die
					rerolls[i] = false;
					break;
				}
				else {								//did not enter y, n, Y, or N. -->reprompt
					System.err.println("Please enter y or n");
					continue;
				}
			}
		}//end loop
		return rerolls;								//return the boolean[] array
	}//end re-roll
	
	/**
	 * spits out the players name
	 * @return the string which is also the name
	 */
	public String getName() {
		return name;
	}//end getName

}//end class
