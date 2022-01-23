import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver for the user
 * lets the user decide dimensions and moves the sylus
 * @author andy1
 *
 */
public class DrawingDriver {
	/**
	 * Instance variable scan is used in the getHeight & getWidthmethods 
	 */
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//get dimensions
		DrawingDriver dd = new DrawingDriver();
		int high = dd.getHeight();
		int wide = dd.getWidth();
		//create a board & stylus 
		Board brd = new Board(high,wide);
		Stylus sty = new Stylus(brd);
		brd.printBoard();
		//continu is true if the program can still run. false if the stylus is outta bounds
		boolean continu = true;
		while(continu) {
			try {
				sty.move();
				brd.printBoard();
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Out of bounds. Ending program");
				continu = false;
			}
		}//end loop
	}//end main
	
	/**
	 * Gets the height of the board from the user. Will check if the input is not an int
	 * @return the height
	 */
	private int getHeight() {
		int h=0;
		try {
			System.out.println("Please enter the height of the grid");
			h = scan.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter an int. Try again");
			scan.nextLine();
			getHeight();
		}
		return h;
	}//end getHeight
	
	/**
	 * Gets the height of the board from the user. Will check if the input is not an int
	 * @return the width
	 */
	private int getWidth() {
		int w=0;
		try {
			System.out.println("Please enter the width of the grid");
			w = scan.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter an int. Try again");
			scan.nextLine();
			getWidth();
		}
		return w;
	}//end getWidth

}//end class
