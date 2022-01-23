/**
 * Testbench for the Etch a Sketch code
 * Tests the max and min grid limits (cant be <0 or >100)
 * Tests the boundaries for every possible move
 * @author andy1
 *
 */
public class TestDriver {
	
	/**
	 * Main method. creates a bunch of boards and styluses to test. 
	 * Checks for max and min board sizes
	 * All enum tests have correct for loop sizes to test board limits 
	 * @param args is a string array of args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Testing huge dimentions...");
		try {
			Board brd = new Board(101,101);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Testing negative dimentions...");
		try {
			Board brd = new Board(-10,-10);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the RIGHT...");
		Board brd0 = new Board(10,10);
		Stylus sty0 = new Stylus(brd0);
		try {
			for(int i=0; i<=11; i++) {
				brd0.move(Direction.RIGHT, sty0);
			}
			brd0.printBoard();//if this prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the DOWN_RIGHT...");
		Board brd1 = new Board(10,10);
		Stylus sty1 = new Stylus(brd1);
		try {
			for(int i=0; i<=11; i++) {
				brd1.move(Direction.DOWN_RIGHT, sty1);
			}
			brd1.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the DOWN...");
		Board brd2 = new Board(10,10);
		Stylus sty2 = new Stylus(brd2);
		try {
			for(int i=0; i<=11; i++) {
				brd2.move(Direction.DOWN, sty2);
			}
			brd2.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the DOWN_LEFT...");
		Board brd3 = new Board(10,10);
		Stylus sty3 = new Stylus(brd3);
		try {
			for(int i=0; i<=1; i++) {
				brd3.move(Direction.DOWN_LEFT, sty3);
			}
			brd3.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the LEFT...");
		Board brd4 = new Board(10,10);
		Stylus sty4 = new Stylus(brd4);
		try {
			for(int i=0; i<=1; i++) {
				brd4.move(Direction.LEFT, sty4);
			}
			brd4.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the UP_LEFT...");
		Board brd5 = new Board(10,10);
		Stylus sty5 = new Stylus(brd5);
		try {
			for(int i=0; i<=1; i++) {
				brd5.move(Direction.UP_LEFT, sty5);
			}
			brd5.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the UP...");
		Board brd6 = new Board(10,10);
		Stylus sty6 = new Stylus(brd6);
		try {
			for(int i=0; i<=1; i++) {
				brd6.move(Direction.UP, sty6);
			}
			brd6.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.println("Creating a 10x10 board. Testing grid boundaries on the UP_RIGHT...");
		Board brd7 = new Board(10,10);
		Stylus sty7 = new Stylus(brd7);
		try {
			for(int i=0; i<=1; i++) {
				brd7.move(Direction.UP_RIGHT, sty7);
			}
			brd7.printBoard();//if the board prints, it does not pass the test
			System.err.println("Fail");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass");
		}
		
		System.out.print("Testing is finished");
		
	}//end main
}//end class
