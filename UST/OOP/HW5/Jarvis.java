//ASSIGNMENT 5
public class Jarvis extends Mobile{
	
	
	public Jarvis(Board brd) {
		super(brd);
		
		// TODO Auto-generated constructor stub
	}

	private void layTrap() {
		//create new trap
		//randomly pick a adjacent cell
		//if it comes back false, wait and try again
		//cant place on player
		//cant place on itself
		//cant place on another trap
		
		
	}
	
	protected void move() {
		//check all adjacent cells
		//if edge, no move
		//yes if trap
		//no if player
	}
	
	public void run() {
		
	}
	
	public boolean share(Boardable elem) {
		//setHugged
		//if jarvis shares cell with player, sethugged is set to true
		return false;
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}
