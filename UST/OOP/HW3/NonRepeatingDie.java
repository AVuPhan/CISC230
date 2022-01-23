import java.util.ArrayList;
/**
 * class for the NRD. extention of superclass Die. Will overwrite roll() method since it needs more actions.
 * @author andy1
 *
 */
public class NonRepeatingDie extends Die{
	/**
	 * arraylist holding the possible rolls of the certain NRD
	 */
	protected ArrayList<Integer> availableRolls  = new ArrayList<Integer>();
	
	/**
	 * constructor. same as superclass, NRD needs a number of sides and an ID
	 * @param numSides
	 * @param ID
	 */
	public NonRepeatingDie(int numSides, int ID){
		super(numSides, ID);
	}//end constructor
	
	/**
	 * checks for availible sides of die and prints out a non repeating value
	 * @return a randomly generated die value without repeating until all sides have been rolled
	 * 
	 */
	public int roll() {
		//check available sides of dice
		if(availableRolls.size() == 0) {			//if the size of the availibleRolls arrayList is 0 then all have been rolled
			for(int i = 0; i < getSides(); i++) {	//reset the roll history
				availableRolls.add(i+1);			//add dice side i+1 at location i
			}
		}
		
		int rollVal = super.roll();					//invoke the superclass roll (which is to generate a random number within range)
		for(int i = 0; i < availableRolls.size(); i++) {
			//check if the roll is still available on the dice
			if(availableRolls.get(i) == rollVal) {		//if the roll is availible
				availableRolls.remove(i);				//remove the side as a roll option
				return rollVal;							//spit out that side
			}
		}
		//ok, so it didn't find the roll in the side of the die, so now recurse until it gets an available side
		return roll();
	}//end NRD roll method
	
	/**
	 * returns the history of rolls 
	 * @return the arraylist of the availible rolls left
	 */
	public ArrayList<Integer> getHistory(){
		return this.availableRolls;
	}//end getHistory
	
}//end class
