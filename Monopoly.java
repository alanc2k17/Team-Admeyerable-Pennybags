import java.util.ArrayList;
import cs1.Keyboard;

public class Monopoly{
    private int numPlayers;
    private ArrayList<Player> playerList;
    private Landable[][] board;
    private int turns;

    // constructor; sets default values
    public Monopoly(){
	numPlayers = 0;
	playerList = new ArrayList<Player>();
	turns = 0;
    }

    // usage: feed Keyboard.readString() into parameter to allow user to select a number selection
    // returns int as the selection number
    // if invalid input, reasks user until valid input
    public int parseInput(String s){
	int retInt = 0;
	try{
	    retInt = Integer.parseInt(s);
	}
	catch (Exception e){ // if invalid input
	    System.out.println("Invalid input! Please try again.");
	    parseInput(Keyboard.readString()); //prompt user for another input, and parse it
	}
	return retInt;
    }

    // asks user for number of players, and their names
    // to be called in setup()
    public void setupPlayers(){
	System.out.print("How many players do you wish to have (2-4): ");
	numPlayers = parseInput(Keyboard.readString());
	
	for (int i = 0; i < numPlayers; i++){ // ask stuff about each player
	    System.out.print("Player " + (i+1) + " name: ");
	    String pName = Keyboard.readString();
	    
	    String[] symbolBank = {"x", "o", "v", "m"};
	    System.out.println("Player " + (i+1) + " symbol:");
	    System.out.println("1:x\t2:o\t3:v\t4:m");
	    // choice corresponds with index in symbol bank -1
	    int symbolChoice = parseInput(Keyboard.readString());
	    String pSymbol = symbolBank[symbolChoice-1];

	    // call constructor and add to playerList
	    playerList.add( new Player(pName, pSymbol) );
	}
    }
	
    // sets up the game; instantiates properites, sets up boards and players
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
		{ income, kentucky, income, indiana, illinois, borail, atlantic, vetnor, water, marvin, income },
		{ newyork, null, null, null, null, null, null, null, null, null, pacific },
		{ tennessee, null, null, null, null, null, null, null, null, null, northcarolina },
		{ income, null, null, null, null, null, null, null, null, null, income },
		{ stjames, null, null, null, null, null, null, null, null, null, pennsylvania },
		{ pennrail, null, null, null, null, null, null, null, null, null, shortline },
		{ virginia, null, null, null, null, null, null, null, null, null, income },
		{ states, null, null, null, null, null, null, null, null, null, parkplace },
		{ electric, null, null, null, null, null, null, null, null, null, luxury },
		{ stjames, null, null, null, null, null, null, null, null, null, boardwalk },
		{ income, connecticut, vermont, income, oriental, reading, income, baltic, income, mediterranean, income }
	    };

    	// initialize players 
	setupPlayers();
    }
    
    
    public void printBoard(){
	for ( int row = 0; row < board.length; row++ ){
	    // loop through each row 5 times, since each element consumes 5 lines
	    for ( int count = 0; count < 5; count++ ){ 
		for ( int col = 0; col < board[row].length; col++ ){
		    // if element is last in row, print newline
		    // if not, print nothing
		    String newLine = "";
		    if ( col == board[row].length-1 )
			newLine = "\n";

		    // if element is null, print 5 empty spaces
		    if ( board[row][col] == null ){
			if ( col == 0 )
			    System.out.print("|"); //border
			System.out.print("     " + newLine);
		    }
		    else{ //element is an instance of Landable
			// if in first column or directly after a null...
			if ( col == 0 || (board[row][col-1] == null) ) 
			    System.out.print("|"); //border
			
			if ( count == 0 ) // on first count
			    System.out.print("-----" + newLine); //border

			else if ( count == 1 ) // on second count, print initials
			    System.out.print(" " + board[row][col].getInitials() + " |" + newLine);

			else if ( count == 2){ // on third count, print houses if applicable 
			    if ( board[row][col] instanceof NormalProperty ){
				// typecast to access getHouses() method
				NormalProperty element = (NormalProperty)(board[row][col]);
				if ( element.getHouses() == 5 )
				    System.out.print("  H |" + newLine); //print hotel
				else{
				    String printStr = "";
				    // print proper num of houses
				    for (int h = 0; h < element.getHouses(); h++)
					printStr += "h";
				    // print added spaces to keep padding
				    while ( printStr.length() < 4 )
					printStr += " ";
				    System.out.print(printStr + "|" + newLine);
				} // close if houses == 5
			    } // close if normalproperty
			    else // element is a railroad, utilty, or nonproperty
				System.out.print("    |" + newLine); //print blank spaces (no houses)
			} // close count 2
			
			else if (count == 3){ // on fourth count, print players on
			    String printStr = ""; 
			    // list of players on square
			    ArrayList<Player> playersOn = board[row][col].getPlayersOn();
			    // for each player on square, print its symbol
			    for ( int i = 0; i < playersOn.size(); i++ )
				printStr += playersOn.get(i).getSymbol();
			    //print added spaces to keep padding
			    for ( int i = playersOn.size(); i < 4; i++ )
				printStr += " ";
			    System.out.print(printStr + "|" + newLine);
			} // close count 3
			
			else if ( count == 4 ) // on fifth count 
			    System.out.print("-----" + newLine); //border
		    } // close else instance of Landable
		} // close loop through row 
	    } // close loop 5 times
	} // close top level loop
    } // close method
			    

    public void turn(Player p) {
    	// roll dice
    	// move player
    	// player actions
    }
    
    public void play(){
	setup();
	printBoard();
	// while there are at least 2 players in the game
	/*
	while ( playerList.size() > 1 ){
	    // for each player, call turn
	    for (int i = 0; i < playerList.size(); i++){
		turn( playerList.get(i) );
	    }
	}
	*/
	
    }

    public static void main(String[] args){
	Monopoly game = new Monopoly();
	game.play();
    }

}
