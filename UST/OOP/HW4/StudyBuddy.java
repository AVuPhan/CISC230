import java.util.*;
/**
 * StudBud will help the user study for the exam. it handles all the exceptions that are thrown
 * @author andy1
 *
 */
public class StudyBuddy {
	
	/**
	 * Create and instantiate a new scanner for the user
	 */
	private Scanner scan = new Scanner(System.in);
	/**
	 * number of questions the user wants to answer
	 */
	private static int numOfQuest=0;
	/**
	 * keeps track of the user's total score
	 */
	private int userPoints = 0;
	/**
	 * arraylist of questions the StudBud will take from
	 */
	private static ArrayList<Question>list = new ArrayList<Question>();

	/**
	 * main method. has the groundwork for the setting up the Studdy buddy
	 * @param args is something important the code needs to execute
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudyBuddy sb = new StudyBuddy();					//create a new instance of itself
		QuestionMaker qm = new QuestionMaker();				//make a new quesitonmaker
		
		System.out.println("Welcome to StudyBuddy! Hit Enter to continue");
		try{
			System.in.read();
		}
		catch(Exception e){}								//hit enter to continue
		System.out.println("Please select a file");
		list = qm.getQuestions();							//goes to the question maker to fetch the list of questions. Stored in list 
		System.out.println("Loading questions...\n\n");
		
		numOfQuest = sb.getNumOfQuest();					//get the number of questions the user wants
		while(numOfQuest>list.size()) {						//check how many number of quesitons. cannot study more than the amount of loaded questions
			System.out.println("Sorry but you have only loaded "+list.size()+" questions.");
			numOfQuest = sb.getNumOfQuest();
		}
		
		sb.study();											//launch the study sesh
		
	}//end main
	
	//****************************************************************divider******************************************************************************
	/**
	 * Gets the users input of how many quesitons they wanna study
	 * @return an int of how many quesitons you wanna study
	 */
	public int getNumOfQuest() {
		int qs = 0;
		System.out.println("How many questions would you like to answer?");
		try {
			qs = scan.nextInt();							//get the users input
			scan.nextLine();								//clear the newline
		}
		catch(InputMismatchException e) {					//if its not an int then catch the exception and the user has to try again
			System.out.println("Please enter an int.");
			scan.nextLine();
			getNumOfQuest();
		}
		return qs;
	}//end getNumOfQuest
	
	//****************************************************************diviver*****************************************************************************
	
	/**
	 * checks the users input to make sure its a string
	 * @return the user's input in all caps so its easy to compare later
	 */
	public String validInput() {
		String ret="";
		try{
			ret = scan.nextLine();							//scans for a stirng
		}
		catch(InputMismatchException e) {					//if its nto a string then the exception is caught and this method is called again
			System.out.println("Please enter your answer in the form of a string.");
			scan.nextLine();
			validInput();
		}
		return ret.toUpperCase();
	}//end isValidInput
	
	//****************************************************************divider*******************************************************************
	
	/**
	 * code used to branch to the end the program.
	 * I could have used System.exit(); to end the code and crash the testing framework but because im a nice guy im not going to :)  
	 */
	public void endOfStudySesh() {
		System.out.println("You have earned a total of "+userPoints+" points.");
		if(userPoints<0) {
			System.out.println("Better luck next time!");
		}
		else {
			System.out.println("Great Job!");
		}
	}//end of endOfStudySesh
	
	//********************************************************************************************************************************************
	
	/**
	 * runs through all the questions for the user
	 */
	public void study() {
		int correct = 0;													//the number of quesitons answered correctly 
		for(int i=0; i<numOfQuest; i++) {									//loop until you've done as many questions as the user wants 
			try {
				System.out.println("Points: "+list.get(i).getPoints());		//display how many points the quesiton is worth
				System.out.println(list.get(i).getQuestion());				//display the question for the user
			}
			catch(IndexOutOfBoundsException e) {							//if there are no questions in the list, then catch the exception and end the study sesh
				System.out.println("You have no questions left");
				endOfStudySesh();
			}
			String userInput = validInput();								//get the users input
			//System.out.println("You entered: "+userInput);//echo input
			
			if(userInput.equals("PASS")) {									//if user input equals pass then remove it from the arraylist
				System.out.println("Ok, lets skip this one\n");
				list.remove(i);
			}
			else if(userInput.equals("DELAY")) {							//if the user input equals delay then remove it from its current location and slap it on the end of the list 
				System.out.println("Ok, I will ask that one later.\n");
				Question temp = list.get(i);								//create temp and it gets the quesiton at i. 
				list.remove(i);												//then remove it 
				list.add(temp);												//add temp at the end
				i = i - 1;													//so you dont skip a question because everything in the list shifts up 1 slot 
			}
			else if(userInput.equals( list.get(i).getCorrectAns().toUpperCase() ) ) {		//if the user input is equal to the correct answer then add points and increment the number of correctly answered questions
				System.out.println("Correct! You earned "+list.get(i).getPoints()+" points.\n");
				correct = correct + 1;
				userPoints = userPoints + list.get(i).getPoints();
			}
			else {															//incorrect answer, display the correct answer and how many points is lost
				System.out.println("Incorrect, the answer was "+list.get(i).getCorrectAns()+". You lost "+list.get(i).getPoints()+" points.\n");
				userPoints = userPoints - list.get(i).getPoints();
			}
			
		}//end loop
		
		//at the end of the all the questions give the user an overview
		System.out.println("Of the "+numOfQuest+" questions you attempted, you got "+correct+" correct.");
		endOfStudySesh();
		
	}//end study
	
}//end class
