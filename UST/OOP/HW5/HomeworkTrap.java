//ASSIGNMENT 5
public class HomeworkTrap implements Boardable{
	//calls setDelay -> 5000ms
	//set to 0 again

	private Board brd;
	
	HomeworkTrap(Board brd){
		this.brd = brd;
	}
	
	public boolean share(Boardable elem) {
		
		//wtf is this supposed to do???
		//false if  instanceof homeworktrap
		//true of  instance of jarvis
		//if instance of player, setdelay 5000ms
		//remove itself from board
		//return true
		
		return false;
	}//end share
	
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}// end class
