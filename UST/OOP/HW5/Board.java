import java.util.ArrayList;
import java.util.HashMap;

/**Board for the game. 2D array of Cells, a nested class, and methods to add or remove from 
 * the hasmaps/arraylists, and a giant move() method called Stylus.move().   
 * @Bugs Stylus still prints itself out after it is moved. the " " is not sticking for some reason. I used my debugging duck and still could not fix it. Yet.
 * @author andy1
 *
 */
public class Board {
	
	/**
	 * Create a 2D array of cells. Cells will hold its positions as well as boardable elements
	 */
	private Cell[][] board;
	/**
	 * height of the board
	 */
	private int height;
	/**
	 * weight of the board
	 */
	private int width;
	/**
	 * hashmap of boardable elements and cells
	 */
	private HashMap<Boardable, Cell> elementPlace = new HashMap<Boardable, Cell>();
	/**
	 * currentRow for stylus
	 */
	private int curRow=0;
	/**
	 * current Col for stylus
	 */
	private int curCol=0;
	
	/**
	 * Constructor for the board. Will also check the inputed dimensions if theyre valid (Greater than 0 & less than 100)
	 * @param h is height
	 * @param w is width
	 */
	public Board(int h, int w) {
		if(h < 1 || h > 100) {
			throw new IllegalArgumentException();
		}
		
		if(w < 1 || w > 100) {
			throw new IllegalArgumentException();
		}
		height = h;
		width = w;
		board = new Cell[height][width];
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				board[i][j] = new Cell(i,j);
			}
		}
	}//end constructor
	
	
	
	//********************************************************************************************************************
	
	/**
	 * Nested class in Board. A Cell has a position in the 2d Array. yeah thats about it i guess
	 * @author andy1
	 *
	 */
	public class Cell implements Boardable{
		/**
		 * row
		 */
		private int row;
		/**
		 * col
		 */
		private int col;
		/**
		 * specificies whether the cell is visible. false = # or null. true if " " or stylus *
		 */
		private boolean isVisible = false;
		/**
		 * list of differnt elements for each individual cell. Either empty or * in this case
		 */
		ArrayList<Boardable> elements = new ArrayList<>();
		
		/**
		 * constructor for a Cell
		 * @param row is row
		 * @param col is col
		 */
		public Cell(int row, int col){
			this.row = row;
			this.col = col;
		}//end constructor
		
		/**
		 * adds boardable elements onto the board
		 * @param elem
		 */
		public void addElement(Boardable elem) {
			elements.add(elem);
		}//end addElement
		
		/**
		 * removes boardable elemtns form the baord
		 * @bug also im pretty sure it doesnt work like its supposed
		 * @param elem
		 * @return
		 */
		public boolean removeElement(Boardable elem) {
			elements.remove(elements.size()-1);
			return false;
		}//end removeElement
		
		/**
		 * prints out what is in a cell based on what boardable elements are on it and whether its visible or not.
		 * Pretty sure this is the area of the bug where it only prints * after you move the stylus.
		 */
		public String toString() {
			if(!isVisible) {
				return "#";
			}
			else {
				if(elements.size()==0) {
					return " ";
				}
				else {
					return this.elements.get(elements.size()-1).toString();
				}
			}
			
		}//end toString

		/**
		 * determines whther a element should be visible or not
		 */
		@Override
		public boolean isVisible() {
			// TODO Auto-generated method stub
			//board[][]has # or null false
			//empy string or * then true
			
			if(board[row][col].equals("#")) {
				this.isVisible = false;
				return false;
			}
			else if(board[row][col].equals(" ")||board[row][col].equals("*")) {
				this.isVisible = true;
			}
			return isVisible;
		}
	}//end nested class
	
	//********************************************************************************************************************
	
	/**
	 * Keeps track of stylus
	 * @param dir is the users direction input
	 * @param elem is what boardable elemt is being moved
	 * @return true if its a valid move and false otherwise
	 */
	public boolean move(Direction dir, Boardable elem) {
		int holdRow = curRow;
		int holdCol = curCol;
		board[curRow][curCol].isVisible = true;
		switch(dir) {
			case UP_LEFT:
				placeElement(elem, curRow--, curCol--);
				return true;
				
			case UP:
				placeElement(elem, curRow--, curCol);
				return true;
				
			case UP_RIGHT:
				placeElement(elem, curRow--, curCol++);
				return true;
				
			case LEFT:
				placeElement(elem, curRow, curCol--);
				return true;
				
			case RIGHT:
				placeElement(elem, curRow, curCol++);
				return true;
				
			case DOWN_LEFT:
				placeElement(elem, curRow++, curCol--);
				return true;
				
			case DOWN:
				placeElement(elem, curRow++, curCol);
				return true;
				
			case DOWN_RIGHT:
				placeElement(elem, curRow++, curCol++);
				return true;
		}//end switch
		
		board[holdRow][holdCol].removeElement(elem);
		
		board[holdRow][holdCol].isVisible = false;
		
		return false; //ret false if no move was made
	}//end move
	
	/**
	 * Places a boardable element on the board at a specific spot
	 * @param elem is the baordable elemt
	 * @param row is the row
	 * @param col is the col
	 * @return true if its successfully placed
	 */
	public boolean placeElement(Boardable elem, int row, int col) {
		//add illegalArgexception if it tries to be added outside the board
		boolean isPlaced;
		if(row>height||row<0||col<0||col>width) {
			throw new IllegalArgumentException("Out of bounds");
		}
		isPlaced = false;
		
		elementPlace.put(elem, board[row][col]);
		
		board[row][col].isVisible = true;
		
		try {
			board[row][col].addElement(elem);
			isPlaced = true;
		}
		catch(Exception e) {
			System.out.println("exception");
		}
		
		return isPlaced;
	}//end placeElement
	
	/**
	 * prints the board by printing the cell 
	 */
	public void printBoard() {
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				
				System.out.print(board[i][j].toString());
				
			}//end loop for col
			System.out.println("");
		}//end loop for rows
	}

}//end class
