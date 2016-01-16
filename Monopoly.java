import java.util.ArrayList;

public class Monopoly{
    private int numPlayers;
    private ArrayList<Player> playerList;
    private Landable[][] board;
    private int turns;
    
    public void setup() {
    	
	// instantiate normal properties
	// instatiating brown properties
	BrownProperty mediterranean = new BrownProperty("Mediterranean Avenue", "MD", false);
	BrownProperty baltic = new BrownProperty("Baltic Avenue", "BA", true);
	
	// instantiating light blue properties
	LightBlueProperty oriental = new LightBlueProperty("Oriental Avenue", "OR", false);
	LightBlueProperty vermont = new LightBlueProperty("Vermont Avenue", "VT", false);
	LightBlueProperty connecticut = new LightBlueProperty("Connecticut Avenue", "CT", true);
	
	// instantiating pink properties
	PinkProperty stcharles = new PinkProperty("St. Charles Place", "SC", false);
	PinkProperty states = new PinkProperty("States Avenue", "SA", false);
	PinkProperty virginia = new PinkProperty("Virginia Avenue", "VA", true);

	// instantiating orange properties
	OrangeProperty stjames = new OrangeProperty("St. James Place", "SJ", false);
	OrangeProperty tennessee = new OrangeProperty("Tennessee Avenue", "TN", false);
	OrangeProperty newyork = new OrangeProperty("New York Avenue", "NY", true);

	// instantiating red properties
	RedProperty kentucky = new RedProperty("Kentucky Avenue", "KY", false);
	RedProperty indiana = new RedProperty("Indiana Avenue", "IN", false);
	RedProperty illinois = new RedProperty("Illinois Avenue", "IL", true);

	// instantiating yellow properties
	YellowProperty atlantic = new YellowProperty("Atlantic Avenue", "AL", false);
	YellowProperty vetnor = new YellowProperty("Vetnor Avenue", "VN", false);
	YellowProperty marvin = new YellowProperty("Marvin Gardens", "MG", true);
	
	// instantiating green properties
	GreenProperty pacific = new GreenProperty("Pacific Avenue", "PC", false);
	GreenProperty northcarolina = new GreenProperty("North Carolina Avenue", "NC", false);
	GreenProperty pennsylvania = new GreenProperty("Pennsylvania Avenue", "PA", true);
	
	// instantiating blue properties
	BlueProperty parkplace = new BlueProperty("Park Place", "PK", false);
	BlueProperty boardwalk = new BlueProperty("Boardwalk", "BW", true);

	// instantiate railroads
	Railroad reading = new Railroad("Reading Railroad", "RR");
	Railroad pennrail = new Railroad("Pennsylvania Railroad", "PR");
	Railroad borail =  new Railroad("Body and Odor Railroad", "BO");
	Railroad shortline = new Railroad("Short Line", "SL");
	
	// instantiate utilities
	Utility electric = new Utility("Electric Company", "EC");
	Utility water = new Utility("Water Works", "WW");

	// instantiate tax spaces
	Tax income = new Tax("Income Tax", 200);
	Tax luxury = new Tax("Luxury Tax", 100);

	
	// initialize board
	// nullls will appear as spaces when board is printed
	board = new Landable[][] 
	    { 
		{ null, kentucky, null, indiana, illinois, borail, atlantic, vetnor, marvin, null }
		{ newyork, null, null, null, null, null, null, null, null, null, pacific }
		{ tennessee, null, null, null, null, null, null, null, null, null, northcarolina }
		{ null, null, null, null, null, null, null, null, null, null, null }
		{ stjames, null, null, null, null, null, null, null, null, null, pennsylvania }
		{ pennrail, null, null, null, null, null, null, null, null, null, shortline }
		{ virginia, null, null, null, null, null, null, null, null, null, null }
		{ states, null, null, null, null, null, null, null, null, null, parkplace }
		{ electric, null, null, null, null, null, null, null, null, null, luxury }
		{ stjames, null, null, null, null, null, null, null, null, null, boardwalk }
	    };
       
    	// initialize players and add to list
	// when adding extra player functionality, use loops based on # of players chosen
	numPlayers = 2;
	Player p1 = new Player( "Calvin", "x" );
	Player p2 = new Player( "Alan", "o" );
	playerList.add(p1);
	playerList.add(p2);
    }
   
    public void turn(Player p) {
    	// roll dice
    	// move player
    	// player actions
    }
    
    public void play(){
	// while there are at least 2 players in the game
	while ( playerList.size() > 1 ){
	    // for each player, call turn
	    for (int i = 0; i < playerList.size(); i++){
		turn( playerList.get(i) );
	    }
	}
    }

    public static void main(String[] args){
	Monopoly game = new Monopoly();
	game.play();
    }

}
