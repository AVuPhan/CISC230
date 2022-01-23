/**
 * Specified object of Question, for true or false questions
 * @author andy1
 *
 */
public class QuestionTF implements Question{
	/**
	 * question is the string that holds the question body
	 */
	private String question;
	/**
	 * answer is the string that holds the answer body
	 */
	private String answer;
	/**
	 * points is the number of points a certain quesiton is worth
	 */
	private int points;
	
	/**
	 * Constructor for a true/false question
	 * @param question is the question
	 * @param ans is the answer
	 * @param pts is the points
	 */
	public QuestionTF(String question, String ans, int pts) {
		this.question = question;
		answer = ans;
		points = pts;
	}//end constructor
	
	/**
	 * Gets the question for the user
	 * @return the question in the form of a string
	 */
	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return question;
	}//end getQuestion
	
	/**
	 * gets the correct answer for the user.
	 * @return the correct answer in the form of a string
	 */
	@Override
	public String getCorrectAns() {
		// TODO Auto-generated method stub
		return answer;
	}//end getCorrectAns
	
	/**
	 * checks if the user entered the correct answer
	 * @param ans is the user's answer
	 * @return true if it matches the question's answer
	 */
	@Override
	public boolean isCorrect(String ans) {
		// TODO Auto-generated method stub
		if(getCorrectAns().equals(ans)) {
			return true;
		}
		return false;
	}//end isCorrect
	
	/**
	 * Gets the amounto f points the current question is worth 
	 * @return an int of the points
	 */
	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}//end getPoints


}//end class
