/**
 * These objects have the ability to be put on the board. Stylus is the only boardable object for now
 * @author andy1
 *
 */
public interface Boardable {
	
	/**
	 * returns true if its visible to the user(boardable objects) and false if not (#) 
	 * @return
	 */
	public boolean isVisible();

}//end class
