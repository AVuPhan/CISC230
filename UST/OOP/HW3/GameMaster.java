import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
/**
 * Controls the important aspects of the game.
 * it will register players, create the dice, the players win status, 
 * copying & broadcasting die results & scores, and launch the game
 * @author andy1
 *
 */
public class GameMaster {
	/**
	 * arraylist that holds all the registered players in the game
	 */
	private ArrayList<Player> gamePlayers = new ArrayList<>();
	/**
	 * random generator
	 */
	private Random rand;
	/**
	 * array of all the dice used in the game 
	 */
	private Die[] dices;
	/**
	 * position of the 1st non repeating die in the array
	 */
	private int nrdPos1;
	/**
	 * position of the 2nd non repeating die in the array
	 */
	private int nrdPos2;
	/**
	 * array holding the running scores for each player.
	 */
	private int[] playerScores;
	/**
	 * array with the status of the players ability to win. true if they still have the potential to win. 
	 * false if they arent able to win anymore created in gamemaster so player cannot manipulate it
	 */
	private boolean[] winStatus;
	
	/**
	 * registers the player and adds them into the gamePlayers arraylist
	 * @param curPlayer
	 */
	public void registerPlayer(Player curPlayer) {
		this.gamePlayers.add(curPlayer);
	}//end registerPlayer
	
	/**
	 * will randomly create 4-9 die with 3-11 random sides. 2 of the die are special non repeating. all the dice are placed in the dices[] array
	 */
	public void createDie() {
		rand = new Random();
		int numDice = rand.nextInt(9-4)+4;	//randomly create 4-9 dice
		dices = new Die[numDice];
		nrdPos1 = rand.nextInt(numDice)+1;	//pick a random position for the 1st NRD
		do {
			nrdPos2 = rand.nextInt(numDice)+1;	//pick a random position for the 2nd NRD. will keep picking a new position if its the same as the 1st 
		}
		while(nrdPos2==nrdPos1);
		
		//System.out.println("nrd pos 1 & 2: "+nrdPos1+", "+nrdPos2+"\n");
		
		for(int i = 0; i<dices.length; i++) {			//populate the dices array. put regular dice in places where there isnt any NRD 
			int numOfSides = rand.nextInt(11-3)+3;
			if(i!=nrdPos1 || i!=nrdPos2) {
				//create regular dice
				dices[i] = new Die(numOfSides, i+1);
			}
			else {//at pos of NRD
				dices[i] = new NonRepeatingDie(numOfSides, i+1);
			}
		}//end loop
	}//end createDie
	
	
	private void resetWinStatus() {
		for(int i=0;i<winStatus.length; i++) {
			winStatus[i] = true;
		}
	}//end resetWinStatus
	
	/**
	 * create a copy and print out that copy so no people can cheat
	 * @param curDieResults is the arraylist of the current die results for the certain round
	 */
	private void broadcastDieRolls(ArrayList<Integer> curDieResults) {
		final ArrayList<Integer> copy = new ArrayList<Integer>();
		for(int i = 0; i<dices.length; i++) {
			copy.add(curDieResults.get(i));
		}
		//print out every die value in copy
		System.out.println("Die Results:");
		for(int j=0;j<copy.size(); j++) {
			System.out.println(copy.get(j));
		}
	}//end broadcastDieRolls
	
	/**
	 * takes the players scores and create a copy to print out
	 * @param scores is the current player scores at the end of the round
	 * @return an int[] array copy of the scores
	 */
	private int[] copyPlayerScore(int[] scores) {
		final int[] copy = new int[gamePlayers.size()]; 
		for(int i=0; i<scores.length; i++) {
			copy[i] = scores[i]; 
		}		
		return copy;
	}//end copyPlayerScore
	
	/**
	 * Runs the game. Uses nested for loop for rounds and players and runs through choices for every player and displays scores
	 * For each round,{
	 * 		for each player,{
	 * 			prompt if wanna guess or nah
	 * 				if yes, do the guess. else, move on
	 * 			roll all the die
	 * 			display die values
	 * 			prompt reroll for each die
	 * 				if yes, reroll die
	 * 			display die values
	 * 			sum the total
	 * 		}
	 * 		display all the players score @ end of each round
	 * }
	 * determine the winner
	 */
	public void playGame() {				
		System.out.println("Starting the game... ");
		System.out.println("There are "+gamePlayers.size()+" players in this game\n");
		playerScores = new int[gamePlayers.size()];
		winStatus = new boolean[gamePlayers.size()];
		resetWinStatus();	//boolean arrays are defaulted to false. @ beginning of game everyone has the ability to win
		
		for(int r=1; r<=5; r++) {	//goes through all 5 rounds
			System.out.println("Round "+r);
			
			for(int p=0; p<gamePlayers.size(); p++) {//goes through all actions for each player
				System.out.println(gamePlayers.get(p).getName() +"'s turn.");
				
				//prompts user if they wanna guess the NonRepeating Dice
				if(gamePlayers.get(p).wantsToGuess()) {//if true
					int[]playerGuesses = gamePlayers.get(p).guess();
					if(playerGuesses[0]==nrdPos1 || playerGuesses[0]==nrdPos2 && playerGuesses[1]==nrdPos1 ||playerGuesses[1]==nrdPos2) {
						//player wins if winstatus is still true
						if(winStatus[p]) {
							//end the game
							winner(gamePlayers.get(p));
						}
					}
					else {
						//player loses for good. winstatus goes false for that player
						System.err.println("What a shame.\n");
						winStatus[p] = false;
					}
					
				}//end player wantsToGuess
				
				//now the GameMaster rolls the dice for each player.get(p)
				ArrayList<Integer>currentDieResults = new ArrayList<Integer>();		//store in this arraylist
				System.out.println("Rolling the dice...");
				for(Die d : dices) {												//roll every dice
					currentDieResults.add(d.roll());
				}
				
				//now display rolls / receive roll result
				broadcastDieRolls(currentDieResults);								//results are printed out
				gamePlayers.get(p).receiveRollResults(currentDieResults);			//send the roll results to the player (mainly for auto players)
				
				//prompt for a re-roll
				boolean[] rollAgain = new boolean[dices.length];					//get the re-roll choices
				rollAgain = gamePlayers.get(p).reroll(dices.length);
				for(int b=0; b<rollAgain.length; b++) {								//re-roll the dice that is needed to be re-rolled
					if(rollAgain[b]) {
						currentDieResults.set(b, dices[b].roll());
					}
				}

				//display die results
				broadcastDieRolls(currentDieResults);
				
				//sum die total score for the person's turn
				int currentRoundDieTotal = 0;		//set or reset the players die total for the round
				int totalIdenticalDice = 1;			//counter for identical dice
				
				for(int i=1;i<dices.length; i++) {	//if all the dice are identical
					if(currentDieResults.get(i)==currentDieResults.get(0)) {
						totalIdenticalDice++;
					}
				}
				
				if(winStatus[p]&&totalIdenticalDice==dices.length) {		//player wins if their winStat is still true and all dice are identical
					winner(gamePlayers.get(p));
				}
				
				//sum the die total
				for(int i = 0; i < currentDieResults.size(); i++) {
					currentRoundDieTotal = currentRoundDieTotal + currentDieResults.get(i);
					
				}
				playerScores[p] = playerScores[p] + currentRoundDieTotal;	//add the round totals into the running score
				
				System.out.println("Total Score for player "+gamePlayers.get(p).getName()+" in round "+r+" is "+currentRoundDieTotal+"\n");
				
			}//end loop for player actions******************************************************************
			
			//display the score after the round ends
			int[] scoreAtEndOfRound = new int[gamePlayers.size()];
			scoreAtEndOfRound = copyPlayerScore(playerScores);			//copy the player score so no one cheats 
			
			//display relevent info at the end of the round
			System.out.println("At the end of round "+r+" here are all of the players' scores:");
			for(int k=0; k<gamePlayers.size(); k++) {
				System.out.println("\t"+gamePlayers.get(k).getName()+ " scored "+scoreAtEndOfRound[k]+" points");
			}
			System.out.println("\n");
			
		}//end loop for rounds**********************************************************************************
		
		//code for end of game
		//if the score is greater than 200 then thats a shame
		for(int p=0; p<gamePlayers.size(); p++) {
			if(playerScores[p] > 200) {
				winStatus[p] = false;		// all players with scores > 200 will lose
			}
		}
		//create a player that is the winning player. Must be empty
		Player currentWinningPlayer = null;
		//find the score closest to 200. Im using min to find the difference
		int min = Integer.MAX_VALUE;
				
		for(int w=0; w<winStatus.length; w++) {
			if(winStatus[w] && Math.abs(playerScores[w] - 200) < min ) {	//if a player is still capable of winning and their score < MAX_VLUE
				min = Math.abs(playerScores[w]-200);						//update the min
				currentWinningPlayer = gamePlayers.get(w);					//put the player with the min into the currentWinningPlayer
			}
		}
		
		if(currentWinningPlayer == null) {									//if no winning player is assigned/is still empty
			draw();															//no one wins
		}
		else {
			winner(currentWinningPlayer);									//send the winning player to the winscreen
		}
	}//end playGame
	
	/**
	 * displays the winner and ends the game
	 * @param dude is the winning player
	 */
	private void winner(Player dude) {
		System.out.println(dude.getName()+" wins!");
		System.exit(0);
	}
	
	/**
	 * no one wins so the game ends
	 */
	private void draw() {
		System.out.println("Game is a draw. No one wins");
		System.exit(0);
	}
}//end class
