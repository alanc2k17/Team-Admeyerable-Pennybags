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
    }
    
    
    public void printBoard(){
	clear();
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
		    if ( board[row][col] == null ){
			if ( col == 0 )
			    System.out.print("|"); //border
			System.out.print("         " + newLine);
		    }
		    else{ //element is an instance of Landable
			// if in first column or directly after a null...
			if ( col == 0 || (board[row][col-1] == null) ) 
			    System.out.print("|"); //border
			
			if ( count == 0 ) /*(row == 0 || row == 10)*/ //
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
			    

    // offers mortgage options and keeps prompting player is done
    // params: Player p player doing the mortgaging
    //         int opt  0 if player is mortgaging, 1 if unmortgaging
    public void offerMortgageOptions(Player p, int opt){
	boolean continueMortgage = true;
	while ( continueMortgage ){
	    ArrayList<Property> propertyList = p.getPropertiesOwned();
	    // print properties
	    for (int i = 0; i < propertyList.size(); i++){
		String retStr = "";
		retStr += i + 1 + " "; // choice number
		retStr += propertyList.get(i).getName();
		retStr += " $" + propertyList.get(i).getMortgageValue() + " ";
		if ( propertyList.get(i).isMortgaged() )
		    retStr += "already mortgaged";
		else
		    retStr += "not mortgaged.";
		System.out.println( retStr );
	    }

	    // choose property
	    if ( opt == 0 ) //mortgage option
		System.out.println("Enter number of property you want to mortgage.");
	    else // unmortgage option
		System.out.println("Enter number of property you want to unmortgage.");
	    int index = parseInput(Keyboard.readString(), propertyList.size());

	    if ( opt == 0 ) //mortgage option
		p.mortgage( propertyList.get(index - 1) );
	    else // unmortgage option
		p.unMortgage( propertyList.get(index - 1) );

	    // askt to continue
	    if ( opt == 0 )
		System.out.print("Continue mortgaging? 1:yes\t2:no ");
	    else
		System.out.print("Continue unmortgaging? 1:yes\t2:no ");
	    int continueOption = parseInput(Keyboard.readString(), 2);
	    if ( continueOption == 2 )
		continueMortgage = false;
	}	
    }

    // offers player options at the end of turn
    public void playerOptions(Player p){
	System.out.println("1. Build houses");
	System.out.println("2. Sell houses");
	System.out.println("3. Morgage property");
	System.out.println("4. Unmortgage property");
	System.out.println("5. End turn");
	System.out.println("6. Initiate a trade. NOT IMPLEMENTED");
	System.out.println("7. Save and exit game. NOT IMPLEMENTED");
	if ( p.getCash() < 0 )
	    System.out.println("WARNING. YOU ARE IN DEBT. IF YOU DO NOT ACHIEVE A NON-NEGATIVE BALANCE AND END YOUR TURN, YOU WILL FORFEIT. IF YOU CANNOT REPAY YOUR DEBTS IN THIS TURN, YOU LOSE. Have a nice day.");
	int choice = parseInput(Keyboard.readString(), 7);

	if (choice == 1 || choice == 2){ // if sell houses or build houses
	    // print info about properties to build houses on
	    for ( int i = 0; i < p.getPropertiesOwned().size(); i++ ){
		if ( p.getPropertiesOwned().get(i) instanceof NormalProperty){
		    NormalProperty tmp = (NormalProperty)(p.getPropertiesOwned().get(i));
		    if ( tmp.checkMonopoly() ){

			System.out.print( i + 1 );
			System.out.print( ". " + tmp.getName() );
			System.out.print( "\tCost: " + tmp.getHouseCost() );
			System.out.print( "\tHouses: " + tmp.getHouses() + "\n");
		    }
		}
	    } // close all printing
	    System.out.println();
	    
	    System.out.print("Which property do you want to build on? ");
	    int propChoice = parseInput(Keyboard.readString(), p.getPropertiesOwned().size()) - 1;
	    if ( p.getPropertiesOwned().get(propChoice) instanceof NormalProperty && 
		 ((NormalProperty)(p.getPropertiesOwned()).get(propChoice)).checkMonopoly() ){
		
		NormalProperty property = (NormalProperty) ( p.getPropertiesOwned().get(propChoice) );
		if (choice==1){
		    System.out.print("How many houses do you want to build? ");
		    int houseNum = parseInput(Keyboard.readString(), 5);
		    p.buildHouse( property, houseNum );
		}
		else if (choice==2){
		    System.out.print("How many hosues do you want to sell? ");
		    int houseNum = parseInput(Keyboard.readString(), 5);
		    p.sellHouse( property, houseNum);
		}
	    } // close if property is part of monopoly  
	    else // invalid input
		System.out.println("You chose a property that you cannot build/sell one.");
	    
	} // closes if choice1 or choice2
	
	else if (choice == 3){ // mortgage
	    offerMortgageOptions(p, 0);
	}
	else if (choice == 4){
	    offerMortgageOptions(p, 1);
	}
	else if (choice == 5){
	    if ( p.getCash() < 0 )
		playerList.remove(p); // remove player from game
	    return;
	}
	playerOptions(p); // call player options again
    }

    public void auction(Property p) {
	// copy contents of playerList into bidderList
	// cannot use assignment operator, b/c then an alias would be created
	ArrayList<Player> bidderList = new ArrayList<Player>();
	for ( int i = 0; i < playerList.size(); i++ ){
	    bidderList.add( playerList.get(i) );
	}
	int currentBid = 0;
	Player highestBidder = null;
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
			    while (inputBid <= currentBid) {
				System.out.println("Too low! Cheapskate! Bid again with an amount greater than: " + currentBid);
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
		    
    public void jailTurn(Player p) {
	//check if player can afford bail
	System.out.println("Jail Turn: " + p.getJailTurns());
	if (p.getCash() >= 50) {
	    //prompt user input
	    System.out.println("Would you like to pay bail? y:1\tn:2");
	    int input = parseInput(Keyboard.readString(), 2);
	    //if user would like to pay bail
	    //forced to pay if player has spent more than 3 turns in jail
	    if (input == 1 || p.getJailTurns() >= 3) {
		p.setJailTurns(0); 
		p.setJail(false);
		p.charge(50);
	    }
	    else
		p.setJailTurns(p.getJailTurns() + 1);//add one to jailTurns
	    
	}
	else if ( p.getCash() < 50 ){
	    System.out.println("You do not have enough money to bail out. Would you like to mortgage property to do so? If this is your third turn, you will be forced to chose yes. 1:yes\t2:no");
	    int input = parseInput(Keyboard.readString(), 2);
	    if ( input == 1 || p.getJailTurns() >= 3 ){
		System.out.println("Mortgage until you get at least $50. If you do not do so or cannot do so and end mortgaging, you will lose.");
		offerMortgageOptions( p, 0 );
		if ( p.getCash() < 50 )
		    return;
	    }
	}
	else
	    p.setJailTurns(p.getJailTurns() + 1);
    }
    

    public void turn(Player p) {
    	if (p.inJail() == true) {
    	    jailTurn(p);
    	}
    	else { //if not in jail
	    p.move(board);

	    if (p.getSquareOn() instanceof GoToJail){
		// typecast p.getSquareOn() to access specific methods
		GoToJail square = (GoToJail) (p.getSquareOn());
		p.setSquareOn( new int[] {0,10}, board); 
		square.processVictim(p, board); // send to jail
	    }
		    
	    else if (p.getSquareOn() instanceof Chance ){
		System.out.println( p.getSquareOn() );
		// typecast to access specific methods
		Chance square = (Chance) (p.getSquareOn());
		square.execute(p, board, playerList);
	    }
	    else if (p.getSquareOn() instanceof Community){
		System.out.println( p.getSquareOn() );
		// typecast to access specific methods
		Community square = (Community) (p.getSquareOn());
		square.execute(p, board, playerList);
	    }
	    
	    else if (p.getSquareOn() instanceof Property){
		// typecast
		Property squareOn = ((Property) (p.getSquareOn()));
		if (squareOn.getOwner() == null && squareOn.getOwner() != p){
		    System.out.println("Do you wish to buy: (current cash: " + p.getCash() + ")");
		    System.out.println("1:yes\t2:no");
		    int choice = parseInput(Keyboard.readString(), 2);
		    if ( choice == 1 ){ //want to buy
			while ( ! p.buy( squareOn ) ){ // if not enough 
			    // offer mortgage options
			    System.out.println("You do not have enough cash to pay. Offering mortgage options.");
			    offerMortgageOptions(p, 0);
			    System.out.print("Do you wish to buy the property? 1:yes\t2:no ");
			    int keepGoing = parseInput(Keyboard.readString(), 2);
			    if (keepGoing == 2)
				break;
			}
			System.out.println("Success! You have successfully bought " + squareOn.getName() );
			System.out.println("Your new cash on hand: " + p.getCash() );
		    }
		    else // pass to auction
			auction(squareOn);
		}
		else // property has an owner
		    p.pay( squareOn ); // pay rent
	    }

	    else if (p.getSquareOn() instanceof Tax)
		p.charge( ((Tax)p.getSquareOn()).getRent() ); // pay tax
    	}
	playerOptions( p );
	//offer general options
    }
    
    public void play(){
	setup();
	printBoard();
	// while there are at least 2 players in the game
	while ( playerList.size() > 1 ){
	    // for each player, call turn
	    for (int i = 0; i < playerList.size(); i++){
		turn( playerList.get(i) );
		printBoard();
	    }
	}
       
	
    }

    public static void main(String[] args){
	Monopoly game = new Monopoly();
	game.play();
    }

}
