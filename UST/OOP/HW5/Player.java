import java.util.InputMismatchException;
import java.util.Scanner;
//ASSINGMENT 5
public class Player extends Mobile{

	private long time;
	private Scanner input;
	
	public Player(Board brd) {
		super(brd);
		this.brd.placeElement(this, 0, 0);
	}
	
	protected boolean move() {
		//get from stylus
		input = new Scanner(System.in);
		String m = null;
		//System.out.println("Enter your move");
		try {
			m = input.nextLine();
		}
		catch(InputMismatchException e) {//this is useless because everything can still pass into it
			System.out.println("Plz enter a string");
			scan.nextLine();
			move();
		}
		switch(m) {//goes through all the possible moves associated with the enums. passes the enum and itself to Board.move()
			case "q":
				return brd.move(Direction.UP_LEFT,this);
			case "w":
				return brd.move(Direction.UP, this);
			case "e":
				return brd.move(Direction.UP_RIGHT, this);
			case "a":
				return brd.move(Direction.LEFT, this);
			case "d":
				return brd.move(Direction.RIGHT, this);
			case "z":
				return brd.move(Direction.DOWN_LEFT, this);
			case "x":
				return brd.move(Direction.DOWN, this);
			case "c":
				return brd.move(Direction.DOWN_RIGHT, this);
			case "s":
				return brd.move(Direction.STAY, this);
			default:
				return false;
		}//end switch
		
	}//end move
	
	public void run() {
		
	}
	
	public void setDelayTime(long delay) {
		this.time = delay;
	}
	
	public void delay() {
		try {
			Thread.sleep(time);
			System.in.read(new byte[System.in.available()]);
		}
		catch(Exception e) {
			//Thread.currentThread.interupt();
		}
		time = 0;
	}
	
	public boolean share(Boardable elem) {
		//called on element already in the cell
		//establish king of the hill
		//thing comes into the cell by move()
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	
}//end class
