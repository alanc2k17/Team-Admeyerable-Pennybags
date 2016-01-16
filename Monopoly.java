import java.util.ArrayList;

public class Monopoly{
    private int numPlayers;
    private ArrayList<Player> playerList;
    private Landable[][] board;
    private int turns;
    
    public void setup() {
    	// initialize board
    	board = new Landable[11][11];
    	// initialize properties
    	
    	// initialize players
    }
    	
    public void turn() {
    	// roll dice
    	// move player
    	// player actions
    }
    
    public static void main(String[] args){
	Monopoly game = new Monopoly();
	game.play();
    }

}
