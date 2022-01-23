/**
 * abstract interface for Question. Itself its too vague so it is implemented by QuestionTF, QuestionFB, and QuestionMC
 * sets up all the methods for the questions 
 * @author andy1
 *
 */
public abstract interface Question {	
	
	/**
	 * Gets the question for the user
	 * @return the question in the form of a string
	 */
	public String getQuestion();
	
	/**
	 * gets the correct answer for the user.
	 * @return the correct answer in the form of a string
	 */
	public String getCorrectAns();
	
	/**
	 * checks if the user entered the correct answer
	 * @param ans is the user's answer
	 * @return true if it matches the question's answer
	 */
	public boolean isCorrect(String ans);
	
	/**
	 * Gets the amounto f points the current question is worth 
	 * @return an int of the points
	 */
	public int getPoints();
	
}//end class
