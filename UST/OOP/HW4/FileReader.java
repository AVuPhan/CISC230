
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
/**
 * Filereader will scan through a selected file and check to make sure it is read properly
 * @author Sawin
 */
public class FileReader {
	/**
	 * create a new class variable of a Scanner to look at files 
	 */
	private Scanner fileScanner;
	
	/**
	 * Constructor for FileReader. 
	 */
	public FileReader(){
		JFileChooser chooser = new JFileChooser();//create a new file chooser   
		try {               
			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION){
				throw new Error("Input file not selected");//if a file is not chosen then display this error
			}            
			File questionFile = chooser.getSelectedFile(); //open the new file           
			fileScanner = new Scanner(questionFile);       //initialize the scanner for the file
		} catch (FileNotFoundException e) {
			System.err.println("Data file not found.");    //the file could not be found
		} catch (Exception e) {
			System.err.println("A mysterious error occurred.");//any other exception or error is thrown 
			e.printStackTrace(System.err);
		}
	}//end constructor
	
	/**
	 * boolean method determines whether the file has a line after the current one
	 * @return true if it does. false otherwise
	 */
	public boolean fileHasNextLine(){
		return this.fileScanner.hasNextLine();
	}//end fileHasNextLine
	
	/**
	 * goes to the next line in the file
	 * @return the next line of the file
	 */
	public String getNextLineOfFile(){
		return this.fileScanner.nextLine();
	}//end getNextLineOfFile
	
	/**
	 * Close the scanner and thriw an exception if its unable too
	 */
	public void finalize(){
		try{
			this.fileScanner.close();
		}catch(Exception ex){ 
			System.err.println("A mysterious error occurred on closing Scanner.");
			ex.printStackTrace(System.err);
		}
	}//end finalize
	
}//end class
