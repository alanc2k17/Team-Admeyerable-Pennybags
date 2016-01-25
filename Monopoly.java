import java.util.ArrayList;
import cs1.Keyboard;

public class Monopoly implements UserInput{
    private int numPlayers;
    private ArrayList<Player> playerList;
    private Landable[][] board;

    // constructor; sets default values
    public Monopoly(){
	numPlayers = 0;
	playerList = new ArrayList<Player>();
    }

    // accessor to get playerList
    // used in chance and auctioning methods
    public ArrayList<Player> getPlayerList(){
	return playerList;
    }

    // clear screen method
    public void clear(){
	System.out.print("\033[H\033[2J");
    }


    // usage: feed Keyboard.readString() into parameter s to allow user to select a number selection
    // returns int as the selection number
    // if invalid input, reasks user until valid input
    public int parseInput(String s){
	int retInt = 0;
	try{
	    retInt = Integer.parseInt(s);
	}
	catch (Exception e){ // if invalid input
	    System.out.println("Invalid input! Please try again.");
	    return parseInput(Keyboard.readString()); //reprompt user
	}
	return retInt;
    }

    // same usage as parseInput(String s), except additional int param range asks user to select a number
    // between 0 and range inclusive, and reasks until valid input
    public int parseInput(String s, int range){
	int retInt = 0;
	try{
	    retInt = Integer.parseInt(s);
	}
	catch (Exception e){ // if invalid input
	    System.out.println("Invalid input! Please try again.");
	    return parseInput(Keyboard.readString(), range); //prompt user for another input, and parse it
	}
	
	if (retInt < 1 || retInt > range){ // if out of bounds
	    System.out.println("Invalid range! Please try again.");
	    return parseInput(Keyboard.readString(), range); //prompt user for another input, and parse it
	}  
	return retInt;
    }

    // takes data from the game and writes data to a file called savefile.txt
    public void saveData(){
	String playerInfo = "";
	String propertyInfo = "";

	// gather player info; loop through playerList
	for ( int i = 0; i < playerList.size(); i++ ){
	    String line = "";
	    Player p = playerList.get(i);
	    line += p.getName() + ",";
	    line += p.getSymbol() + ",";
	    line += p.getCoords()[0] + ",";
	    line += p.getCoords()[1] + ",";
	    line += p.getCash() + ",";
	    line += p.inJail() + ",";
	    line += p.jailTurns() + "|";
	    playerInfo += line;
	}

	// gather property info; loo through board to get properties
	for ( int row = 0; row < board.length; row++ ){
	    for ( int col = 0; col < board[row].length; col++ ){
		if ( board[row][col] instanceof Property && ((Property) (board[row][col])).getOwner() != null ){ // if owned property
		    Property pr = (Property)(board[row][col]); //typecast to access vars
		    String line = "";
		    line += pr.getInitials() + ",";
		    line += row + ",";
		    line += col + ",";
		    line += pr.getOwner().getSymbol() + ",";
		    line += pr.isMortgaged() + ",";
		    
		    if ( board[row][col] instanceof NormalProperty )
			line += ((NormalProperty) board[row][col] ).getHouses() + "|";
		    else
			line += 0 + "|"; //if instance of railroad or utility, no houses

		    propertyInfo += line;
		}
	    }
	} // close propert loop

	Savefile.writeInfo(propertyInfo + "&&&" + playerInfo);		
    }


    // reads data from the savefile.txt and sets up the game accordingly
    // returns true if data successfully loaded
    // false otherwise
    public boolean loadFile(){
	String contents = Savefile.readInfo();
	if ( contents.equals("bad") ) // no existing savefile
	    return false;
	String propertyInfo = contents.substring(0, contents.indexOf("&&&"));
	String playerInfo = contents.substring(contents.indexOf("&&&")+3, contents.length());
	return true;
    }
	
	    
    // asks user for number of players, and their names
    // to be called in setup()
    public void setupPlayers(){
	String[] symbolBank = {"x", "o", "v", "m"};	

	System.out.print("How many players do you wish to have (1-4): ");
	numPlayers = parseInput(Keyboard.readString(), 4);

	//instantiate bots if less than 2 players
        if (numPlayers < 2) {
	    System.out.print("Player " + 1 + " " + symbolBank[0] + " name: ");
	    String pName = Keyboard.readString();
	    playerList.add(new Player(pName, symbolBank[0]));
	    System.out.println("How many bots would you like?");
	    int numBots = parseInput(Keyboard.readString(), 3);
	    for (int i = 1; i < numBots + 1; i++) 
		playerList.add(new AI("Bot " + i, symbolBank[i]));
	}
	else {
	    //instantiate other players if more than 1
	    for (int i = 0; i < numPlayers; i++){ // ask stuff about each player

		System.out.print("Player " + (i+1) + " " + symbolBank[i] + " name: ");
		String pName = Keyboard.readString();
	    
		// call constructor and add to playerList
		playerList.add( new Player(pName, symbolBank[i]) );
	    }
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

	// instantiate jail space
	Jail jail = new Jail();
	
	// instantiate go to jail space
	GoToJail gotojail = new GoToJail();
	
	// instantiate go space
	Go go = new Go();

	// instantiate free parking
	FreeParking freeparking = new FreeParking();
	
	// instantiate chance spaces
	Chance chance1 = new Chance();
	Chance chance2 = new Chance();
	Chance chance3 = new Chance();
	Community community1 = new Community();
	Community community2 = new Community();
	Community community3 = new Community();
	

	// initialize board
	// nullls will appear as spaces when board is printed
	board = new Landable[][] 
	    { 
		{ freeparking, kentucky, chance2, indiana, illinois, borail, atlantic, vetnor, water, marvin, gotojail },
		{ newyork, null, null, null, null, null, null, null, null, null, pacific },
		{ tennessee, null, null, null, null, null, null, null, null, null, northcarolina },
		{ community2, null, null, null, null, null, null, null, null, null, community3 },
		{ stjames, null, null, null, null, null, null, null, null, null, pennsylvania },
		{ pennrail, null, null, null, null, null, null, null, null, null, shortline },
		{ virginia, null, null, null, null, null, null, null, null, null, chance3 },
		{ states, null, null, null, null, null, null, null, null, null, parkplace },
		{ electric, null, null, null, null, null, null, null, null, null, luxury },
		{ stcharles, null, null, null, null, null, null, null, null, null, boardwalk },
		{ jail, connecticut, vermont, chance1, oriental, reading, income, baltic, community1, mediterranean, go }
	    };

    	// initialize players 
	setupPlayers();
	// testing purposes only
	/*
	playerList.get(0).buy(newyork);
	playerList.get(0).buy(tennessee);
	playerList.get(0).buy(stjames);
	*/
    }
    
    
    public void printBoard(){
	for ( int row = 0; row < board.length; row++ ){
	    // loop through each row 3 times, since each element consumes 3 lines
	    for ( int count = 0; count < 4; count++ ){ 
		for ( int col = 0; col < board[row].length; col++ ){
		    // if element is last in row, print newline
		    // if not, print nothing
		    String newLine = "";
		    if ( col == board[row].length-1 )
			newLine = "\n";

		    // if element is null, print 9 empty spaces
		    if ( board[row][col] == null && count != 0){
			if ( col == 0 )
			    System.out.print("|"); //border
			System.out.print("         " + newLine);
		    }
		    else{ //element is an instance of Landable
			// if in first column or directly after a null...
			if ( (col == 0 || (board[row][col-1] == null)) && count != 0 ) 
			    System.out.print("|"); //border
			
			if ( (count == 0) && (row == 0 || row == 10) )// && board[row-1][col] == null) ) ) //
			    System.out.print("---------" + newLine); //border

			if ( count == 1 ){ // on first count, print initials, owner, and houses
			    String initials = board[row][col].getInitials() + ":";
			    String symbol = " ";
			    String houseString = "    ";
			    if ( board[row][col] instanceof Property ){
				Property prop = (Property)(board[row][col]); //typecast
				if ( prop.getOwner() != null )
				    symbol = prop.getOwner().getSymbol();
				if ( prop instanceof NormalProperty ){
				    int houses = ( (NormalProperty)(prop) ).getHouses();
				    if ( houses != 0 )
					houseString = "(" + houses + "h)";
				}				
			    }
			    System.out.print(initials + symbol + houseString + "|" + newLine);
			} //close count 1
			    
			if (count == 2){ // on second count, print players on square
			    String printStr = ""; 
			    // list of players on square
			    ArrayList<Player> playersOn = board[row][col].getPlayersOn();
			    // for each player on square, print its symbol
			    for ( int i = 0; i < playersOn.size(); i++ )
				printStr += playersOn.get(i).getSymbol();
			    //print added spaces to keep padding
			    for ( int i = playersOn.size(); i < 8; i++ )
				printStr += " ";
			    System.out.print(printStr + "|" + newLine);
			} // close count 2
			
			if ( count == 3 ) // on third count 
			    System.out.print("---------" + newLine); //border
		    } // close else instance of Landable
		} // close loop through row 
	    } // close loop 3 times
	} // close top level loop
    } // close method


    // performs auctioning between every player in game
    public void auction(Property p) {
	// to avoid null pointer exception, quit auction if no one has money
	boolean allNegative = true;
	for ( int i = 0; i < playerList.size(); i++ ){
	    if ( playerList.get(i).getCash() > 0 )
		allNegative = false;
	}
	if (allNegative == true) { return; }

	// copy contents of playerList into bidderList
	// cannot use assignment operator, b/c then an alias would be created
	System.out.println("Starting auction!");
	ArrayList<Player> bidderList = new ArrayList<Player>();
	for ( int i = 0; i < playerList.size(); i++ ){
	    bidderList.add( playerList.get(i) );
	}
	int currentBid = 0;
	Player highestBidder = new Player("no one", "d");
	int dropOuts = 0;

	while (bidderList.size() - dropOuts > 1) { // while there is more tha one bidder
	    for ( int i = 0; i < bidderList.size(); i++ ){ // for each bidder
		if ( bidderList.get(i) != null ) { // if not a dropped out bidder
		    Player pl = bidderList.get(i);
		    
		    if (currentBid >= pl.getCash()){ //if current player cannot afford to bid
			bidderList.set(i, null);
			dropOuts += 1;
			System.out.println( pl.getName() + " has dropped out!" );
			if ( dropOuts == bidderList.size() - 1 )
			    break;
		    }

		    else { //if current player can afford to bid
			System.out.println("People are bidding on " + p.getName() + "! The highest bid is " + currentBid + ". " + pl.getName() + ": Would you like to bid? y:1\tn:2");
			int input = parseInput(Keyboard.readString(), 2);
			//if player wants to bid, determine how much
			if (input == 1) { // yes to bid
			    if ( highestBidder == null ) // you are first bidder
				System.out.println("Start bidding. " + pl.getName() + ": How much would you like to bid?");
			    else
				System.out.println("Current bid: " + currentBid + " by " + highestBidder.getName() + ". " + pl.getName() + ": How much would you like to bid?");

			    int inputBid = parseInput(Keyboard.readString()); // needs new parseInput
				while (inputBid <= currentBid || inputBid > pl.getCash()) {
				    System.out.println("Invalid bid! Try again.");
				    inputBid = parseInput(Keyboard.readString());
				}
			    // set new bid to max bid so far
			    currentBid = inputBid;
			    highestBidder = pl;
			}

			else { // no to bidding --> drop out
			    bidderList.set(i, null); //set to null value as a placeholder
			    dropOuts += 1;
			    System.out.println( pl.getName() + " has dropped out!" );
			    if ( dropOuts == bidderList.size() - 1 )
				break;
			}
		    } // close player can afford to bid
		} // close if not a dropped out bidder
	    } // bid turn done
	} // close while loop

	highestBidder.giveProperty(p); //highest bidder wins property
	highestBidder.charge(currentBid);
	System.out.println("Congrats! " + highestBidder.getName() + " won the bid for " + currentBid + "!");
    }		   
    
    public void play(){
	setup();
	// while there are at least 2 players in the game
	while ( playerList.size() > 1 ){
	    // for each player, call turn
	    int maxTurns = playerList.size();
	    int turnNumber = 0;
	    while ( turnNumber < playerList.size() ){
		if ( turnNumber >= playerList.size() ) //guard against out of bounds error
		    break;
		if ( playerList.get(turnNumber) instanceof AI) {  
		    ((AI)playerList.get(turnNumber)).botTurn(board, this); //if player is not bankrupt after turn
		    turnNumber += 1;
		}
		else {
		    if ( playerList.get(turnNumber).turn(board, this) )
			turnNumber += 1;
		}
		// if player is bankrupt, do not increment, b/c next-in-line player shifts left to fill the spot
	    }    
	}
	System.out.println("Congratulations to our filthy rich winner: " + playerList.get(0).getName());
       	
    }

    public static void main(String[] args){
	Monopoly game = new Monopoly();
	game.play();
    }

}
