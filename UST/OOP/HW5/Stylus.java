import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Stylus acts like the pen on the sketchpad. takes in user input and decides where to move the pen from there
 * is a implementation of boardable (can be placed on the board)
 * @author andy1
 *
 */
public class Stylus implements Boardable{
	/**
	 * Takes in the board its being placed on
	 */
	private Board board;
	/**
	 * Scanner for user inputed moves
	 */
	private Scanner scan;
	
	/**
	 * constructor for a stylus. 
	 * stylus is placed at the upper left hand corner at [0][0]
	 * @param board is the board its writing on
	 */
	public Stylus(Board board) {
		this.board = board;
		this.board.placeElement(this, 0, 0);
	}//end constructor

	/**
	 * takes usre input and checks to see if its a possible move and sends that moveset to the move() in Board to calculate the positioning
	 * @return true if its a possbile move and false if not.
	 */
	public boolean move() {
		//telling the Board to move the stylus. take input from user. 
		//ret true if valid move (q,w,e,a,d,z,x, or c) and ret false if not
		scan = new Scanner(System.in);
		String m = null;
		//System.out.println("Enter your move");
		try {
			m = scan.nextLine();
		}
		catch(InputMismatchException e) {//this is useless because everything can still pass into it
			System.out.println("Plz enter a string");
			scan.nextLine();
			move();
		}
		switch(m) {//goes through all the possible moves associated with the enums. passes the enum and itself to Board.move()
			case "q":
				return board.move(Direction.UP_LEFT,this);
			case "w":
				return board.move(Direction.UP, this);
			case "e":
				return board.move(Direction.UP_RIGHT, this);
			case "a":
				return board.move(Direction.LEFT, this);
			case "d":
				return board.move(Direction.RIGHT, this);
			case "z":
				return board.move(Direction.DOWN_LEFT, this);
			case "x":
				return board.move(Direction.DOWN, this);
			case "c":
				return board.move(Direction.DOWN_RIGHT, this);
			default:
				return false;
		}//end switch
		
	}//end move
	
	/**
	 * The stylus is always visible
	 * @ returns true
	 */
	public boolean isVisible() {
		return true;
	}
	
	/**
	 * Stylus is always defined as  an asterisk. 
	 */
	public String toString() {
		return "*";
	}//end toString
	
}//end class

