import java.util.ArrayList;
/**
 * recives the file info from filereader
 * creates questions from the lines of the file
 * @author andy1
 *
 */
public class QuestionMaker {
	
	/**
	 * create a file reader
	 */
	private FileReader fr;
	/**
	 * create an arraylist of Questions
	 */
	private ArrayList studyQuestions;
	
	/**
	 * getQuestions will create and store all questions in an arraylist 
	 * @return an arrayList of all the questions
	 */
	public ArrayList<Question> getQuestions() {
		fr = new FileReader();			//instantiate the file reader
		studyQuestions = new ArrayList<Question>();		//instantiate the list
		int lineCtr=0;					//counter for the lines that the filereader is able to scan
		//this loop will go through every single line of a text file
		while(fr.fileHasNextLine()) {
			String curLine = fr.getNextLineOfFile();					//looks at the current line
			String[]div = curLine.split(";");							//chop up that line at every semicolon
			
			if(div[0].toUpperCase().equals("TF")) {						//if the first element in the array is TF then create a new TF question
				String quest = "True/False: "+div[1];					//isolate the question body
				String ans = div[div.length-2];							//isolate the answer body
				int pts = Integer.parseInt(div[div.length-1]);			//isolate the number of points this question is worth
				Question newQuest = new QuestionTF(quest, ans, pts);	//throw everything together to make a new TF question
				studyQuestions.add(newQuest);							//put it into the arraylist
			}
			else if(div[0].toUpperCase().equals("FB")) {				//if the 1st element is FB then create a new fill in blank question
				String quest = "Fill in the blank: "+div[1];			//isolate the question body
				String ans = div[div.length-2];							//isoalte the answer body
				int pts = Integer.parseInt(div[div.length-1]);			//isolate the number of points
				Question newQuest = new QuestionFB(quest, ans, pts);	//throw everything together to make a new TF question
				studyQuestions.add(newQuest);							//put it into the arraylist
			}
			else if(div[0].toUpperCase().equals("MC")) {				//if the 1st element is MC then create new mult choice question
				String quest = "Multiple choice: "+div[1];				//isolate the question body
				String ans = div[div.length-2];							//isolate the answer body
				int pts = Integer.parseInt(div[div.length-1]);			//isolate the points
				int numOfChoices = Integer.parseInt(div[2]);			//find out how many possible answers there are
				for(int i=3; i<numOfChoices+3; i++) {					//isolate all possible answers. for each possible answer, stick them on the back of question 
					quest = quest +"\n\t"+div[i];
				}	
				Question newQuest = new QuestionFB(quest, ans, pts);	//throw everything together to make a new TF question
				studyQuestions.add(newQuest);							//put it into the arraylist
			}
			else {														//hasNextLine did not find any tags, so skip the current line
				System.out.println("Did not find a valid question on line "+lineCtr);
			}
			
			lineCtr++;
		}//end loop
		return studyQuestions;
	}//end getQuestions
	
}//end class
