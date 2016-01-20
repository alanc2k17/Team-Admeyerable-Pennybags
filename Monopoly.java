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

    // clear screen method
    public void clear(){
	System.out.print("\033[H\033[2J");
    }

    // usage: feed Keyboard.readString() into parameter s to allow user to select a number selection
    //        range checks that s is between 1-range, inclusive
    // returns int as the selection number
    // if invalid input, reasks user until valid input
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

    // asks user for number of players, and their names
    // to be called in setup()
    public void setupPlayers(){
	System.out.print("How many players do you wish to have (2-4): ");
	numPlayers = parseInput(Keyboard.readString(), 4);
	
	for (int i = 0; i < numPlayers; i++){ // ask stuff about each player
	    System.out.print("Player " + (i+1) + " name: ");
	    String pName = Keyboard.readString();
	    
	    String[] symbolBank = {"x", "o", "v", "m"};
	    System.out.println("Player " + (i+1) + " symbol:");
	    System.out.println("1:x\t2:o\t3:v\t4:m");
	    // choice corresponds with index in symbol bank -1
	    int symbolChoice = parseInput(Keyboard.readString(), 4);
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
	BrownProperty balctic = new BrownProperty("Baltic Avenue", "BA", true);

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
	
	// initialize board
	// nullls will appear as spaces when board is printed
	board = new Landable[][] 
	    { 
		{ freeparking, kentucky, income, indiana, illinois, borail, atlantic, vetnor, water, marvin, gotojail },
		{ newyork, null, null, null, null, null, null, null, null, null, pacific },
		{ tennessee, null, null, null, null, null, null, null, null, null, northcarolina },
		{ income, null, null, null, null, null, null, null, null, null, income },
		{ stjames, null, null, null, null, null, null, null, null, null, pennsylvania },
		{ pennrail, null, null, null, null, null, null, null, null, null, shortline },
		{ virginia, null, null, null, null, null, null, null, null, null, income },
		{ states, null, null, null, null, null, null, null, null, null, parkplace },
		{ electric, null, null, null, null, null, null, null, null, null, luxury },
		{ stcharles, null, null, null, null, null, null, null, null, null, boardwalk },
		{ jail, connecticut, vermont, income, oriental, reading, income, baltic, income, mediterranean, go }
	    };

    	// initialize players 
	setupPlayers();
    }
    
    
    public void printBoard(){
	clear();
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
			    

    // offers mortgage options and keeps prompting player is done
    // param input is hte player doing the mortgaging
    public void offerMortgageOptions(Player p){
	boolean continueMortgage = true;
	while ( continueMortgage ){
	    ArrayList<Property> propertyList = p.getPropertiesOwned();
	    // print properties
	    for (int i = 0; i < propertyList.size(); i++){
		System.out.println( (i+1+" ") + propertyList.get(i).getName() + " $" + 
				    propertyList.get(i).getMortgageValue() );
	    }
	    // choose property
	    System.out.println("Enter number of property you want to mortgage.");
	    int index = parseInput(Keyboard.readString(), propertyList.size());
	    p.mortgage( propertyList.get(index - 1) );
	    System.out.println(p.getName() + " now has $" + p.getCash());
	    // askt to continue
	    System.out.print("Continue mortgaging? 1:yes\t2:no ");
	    int continueOption = parseInput(Keyboard.readString(), 2);
	    if ( continueOption == 2 )
		continueMortgage = false;
	}	
    }
    
    public void jailTurn(Player p) {
	//check if player can afford bail
	if (p.getCash() >= 50) {
	    //prompt user input
	    System.out.println("Would you like to pay bail? y:1\tn:2");
	    int input = parseInput(Keyboard.readString());
	    //if user would like to pay bail
	    //forced to pay if player has spent more than 3 turns in jail
	    if (input == 1 || p.getJailTurns() > 3) {
		p.setJailTurns(0); 
		p.setJail(false);
		p.charge(50);
	    }
	    else
		p.setJailTurns(p.getJailTurns() + 1);//add one to jailTurns
	}
	//if player cant afford bail
	else {
	    //if player has properties available for mortgage
	    if (p.canMortgage()) {
		if (p.getJailTurns() > 2)
		    System.out.println("You better watch out! Last chance to mortgage before you lose!");
		offerMortgageOptions(p);
	    }
	    p.setJailTurns(p.getJailTurns() + 1);
	    //player is bankrupt :c
	    if (p.getJailTurns() > 3) {
		playerList.remove(p);
	    }
	}
    }

    public void turn(Player p) {
    	if (p.inJail() == true) {
    	    jailTurn(p);
    	}
    	else { //if not in jail
	    p.move(board);

	    if (p.getSquareOn() instanceof GoToJail)
		System.out.println("hi, we need to fix this");
    		//p.setSquareOn( jail, board); 
	    //else if (p._squareOn == Chance || p._squareOn == Community) {
    		//follow instructions on card
	    //}

	    else if (p.getSquareOn() instanceof Chance)
		p.getSquareOn().execute(p, _board);

	    else if (p.getSquareOn() instanceof Property){
		// typecast
		Property squareOn = ((Property) (p.getSquareOn()));
		if (squareOn.getOwner() == null){
		    System.out.println("Do you wish to buy: ");
		    System.out.println("1:yes\t2:no");
		    int choice = parseInput(Keyboard.readString(), 2);
		    if ( choice == 1 ){ //want to buy
			while ( ! p.buy( squareOn ) ){ // if not enough 
			    // offer mortgage options
			    offerMortgageOptions(p);
			    System.out.print("Do you wish to buy the property? 1:yes\t2:no ");
			    int keepGoing = parseInput(Keyboard.readString(), 2);
			    if (keepGoing == 2)
				break;
			}
			System.out.println("Success! You have successfully bought " + squareOn.getName() );
			System.out.println("Your new cash on hand: " + p.getCash() );
		    }
		    else // pass to auction
			System.out.println("IMPLEMENT AUCTION LATER"); //auction
		}
		else // property has an owner
		    p.pay( squareOn ); // pay rent
	    }

	    else if (p.getSquareOn() instanceof Tax)
		p.charge( ((Tax)p.getSquareOn()).getRent() ); // pay tax
	    
	    //offer general options

	    String end = Keyboard.readString();
    	}
    }
    
    public void play(){
	setup();
	printBoard();
	// while there are at least 2 players in the game
	while ( playerList.size() > 1 ){
	    // for each player, call turn
	    for (int i = 0; i < playerList.size(); i++){
		printBoard();
		turn( playerList.get(i) );
	    }
	}
       
	
    }

    public static void main(String[] args){
	Monopoly game = new Monopoly();
	game.play();
    }

}
