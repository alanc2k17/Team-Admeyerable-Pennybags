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
	    parseInput(Keyboard.readString(), range); //prompt user for another input, and parse it
	}
	
	if (retInt < 1 || retInt > range){ // if out of bounds
	    System.out.println("Invalid range! Please try again.");
	    parseInput(Keyboard.readString(), range); //prompt user for another input, and parse it
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
	//mediterranean.addMember( baltic );
	//baltic.addMember( meditteranean );

	// instantiating light blue properties
	LightBlueProperty oriental = new LightBlueProperty("Oriental Avenue", "OR", false);
	LightBlueProperty vermont = new LightBlueProperty("Vermont Avenue", "VT", false);
	LightBlueProperty connecticut = new LightBlueProperty("Connecticut Avenue", "CT", true);
	//oriental.addMember(vermont); oriental.addMember(connecticut);
	//vermont.addMember(oriental); vermont.addMember(connecticut);
	//connecticut.addMember(oriental); connecticut.addMember(vermont);

	// instantiating pink properties
	PinkProperty stcharles = new PinkProperty("St. Charles Place", "SC", false);
	PinkProperty states = new PinkProperty("States Avenue", "SA", false);
	PinkProperty virginia = new PinkProperty("Virginia Avenue", "VA", true);
	//stcharles.addMember(states); stcharles.addMember(virginia);
	//states.addMember(stcharles); states.addMember(virginia);
	//virginia.addMember(stcharles); virginia.addMember(states);

	// instantiating orange properties
	OrangeProperty stjames = new OrangeProperty("St. James Place", "SJ", false);
	OrangeProperty tennessee = new OrangeProperty("Tennessee Avenue", "TN", false);
	OrangeProperty newyork = new OrangeProperty("New York Avenue", "NY", true);
	//stjames.addMember(tennessee); stjames.addMember(newyork);
	//tennessee.addMember(stjames); tennessee.addMember(newyork);
	//newyork.addMember(st.james); newyork.addMember(tennessee);

	// instantiating red properties
	RedProperty kentucky = new RedProperty("Kentucky Avenue", "KY", false);
	RedProperty indiana = new RedProperty("Indiana Avenue", "IN", false);
	RedProperty illinois = new RedProperty("Illinois Avenue", "IL", true);
	//kentucky.addMember(indiana); kentucky.addMember(illinois);
	//indiana.addMember(kentucky); indiana.addMember(illinois);
	//illinois.addMember(indiana); illinois.addMember(kentucky);

	// instantiating yellow properties
	YellowProperty atlantic = new YellowProperty("Atlantic Avenue", "AL", false);
	YellowProperty vetnor = new YellowProperty("Vetnor Avenue", "VN", false);
	YellowProperty marvin = new YellowProperty("Marvin Gardens", "MG", true);
	//atlantic.addMember(vetnor); atlantic.addMember(marvin);
	//vetnor.addMember(atlantic); vetnor.addMember(marvin);
	//marvin.addMember(atlantic); marvin.addMember(vetnor);

	// instantiating green properties
	GreenProperty pacific = new GreenProperty("Pacific Avenue", "PC", false);
	GreenProperty northcarolina = new GreenProperty("North Carolina Avenue", "NC", false);
	GreenProperty pennsylvania = new GreenProperty("Pennsylvania Avenue", "PA", true);
	//pacific.addMember(northcarolina); pacific.addMember(pennsylvania);
	//northcarolina.addMember(pacific); northcarolina.addMember(pennsylvania);
	//pennsylvania.addMember(pacific); pennsylvania.addMember(northcarolina);

	// instantiating blue properties
	BlueProperty parkplace = new BlueProperty("Park Place", "PK", false);
	BlueProperty boardwalk = new BlueProperty("Boardwalk", "BW", true);
	//parkplace.addMember(boardwalk);
	//boardwalk.addMember(parkplace);

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
		{ stjames, null, null, null, null, null, null, null, null, null, boardwalk },
		{ jail, connecticut, vermont, income, oriental, reading, income, baltic, income, mediterranean, go }
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
			    

    // offers mortgage options and keeps prompting player is done
    // param input is hte player doing the mortgaging
    public void offerMortgageOptions(Player p){
	boolean continueMortgage = true;
	while ( continueMortgage ){
	    ArrayList<Property> propertyList = p.getPropertiesOwned();
	    // print properties
	    for (int i = 0; i < propertyList.size(); i++){
		System.out.println( (i+1+"") + propertyList.get(i).getName() + " $" + 
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

    public void turn(Player p) {
    	//check if player is in jail
    	if (p.getSquareOn() instanceof Jail){ //!!!!!
	    System.out.println("you're in jail"); //WE NEED MORE STUFF HERE
	}
    	else { //if not in jail
	    p.move(board);

	    if (p.getSquareOn() instanceof GoToJail)
		System.out.println("hi, we need to fix this");
    		//p.setSquareOn( jail, board); 
	    //else if (p._squareOn == Chance || p._squareOn == Community) {
    		//follow instructions on card
	    //}
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
	    

	    /*
	    else {	// if rent is more than amt of cash player has
    		if (p._squareOn.getRent() > p._cashOnHand) {
		    // if player has properties available for rent
		    if (_propertiesOwned.isEmpty() == true) {
			// offer mortgage options
		    }
		    else {
			// take player out of game
		    }
    		}
    		else {	//pay owner of property
		    p.pay(p._squareOn);
		    //offer more options to player
		    //build house/hotel
		    //trade/mortgage
    		}
	    }
	    */
    	}
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
