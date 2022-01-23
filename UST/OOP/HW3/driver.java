/**
 * Driver class with main method for the dice game. It will initialize a game master,
 * create & register players, create the die, and run the game. 
 * @author andy1
 *
 */
public class driver {
	public static void main(String[] args) {
		//create a game master
		GameMaster gm = new GameMaster();
		
		//create UI players & AI players
		Player p1 = new Player("Howard Stark");
		//Player p2 = new Player("Tony Stark");
		//Player p3 = new Player("Peter Parker");
		AutoPlayer cpu1 = new AutoPlayer("JarvisBot");
		//AutoPlayer cpu2 = new AutoPlayer("FridayBot");
		//AutoPlayer cpu3 = new AutoPlayer("EdithBot");
		
		//register players
		gm.registerPlayer(p1);
		//gm.registerPlayer(p2);
		//gm.registerPlayer(p3);
		gm.registerPlayer(cpu1);
		//gm.registerPlayer(cpu2);
		//gm.registerPlayer(cpu3);
		
		//create dice & run the game
		gm.createDie();
		gm.playGame();
		
	}//end main
}//end class
