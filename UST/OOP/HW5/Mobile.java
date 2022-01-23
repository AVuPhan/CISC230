
public abstract class Mobile implements Boardable, Runnable{
	
	protected Board brd;
	
	public Mobile(Board brd) {
		this.brd = brd;
	}
	
	protected abstract boolean move();
	
	
}//end class
