import java.util.ArrayList;
import cs1.Keyboard;

public class Player implements UserInput{
    protected int[] _coordinate;
    protected String _name;
    protected String _symbol;
    protected ArrayList<Property> _propertiesOwned;
    protected int _cashOnHand;
    protected Landable _squareOn;
    protected int _diceRoll;
    protected boolean _inJail;
    protected int _jailTurns;
    
    public Player() {
	_coordinate = new int[] { 10, 10 };
	_name = "nameless";
	_symbol = "D";
	_propertiesOwned = new ArrayList<Property>();
	_cashOnHand = 1500;
    }
    
    public Player(String newName, String symbol) {
	this();
	_name = newName;
	_symbol = symbol;
    }
    
    public String toString() {
	String returnString = "";
	
	returnString += _name + "'s current coordinate: " + _coordinate;
	returnString += _name + " currently owns " + _propertiesOwned;
	returnString += _name + " currently has $" + _cashOnHand;
	
	return returnString;
    }

    public String getName(){
	return _name;
    }

    public String getSymbol(){
	return _symbol;
    }

    public int[] getCoords() {
	return _coordinate;
    }
    
    public int getCash() {
	return _cashOnHand;
    }
    
    public ArrayList<Property> getPropertiesOwned() {
	return _propertiesOwned;
    }
    
    public Landable getSquareOn() {
	return _squareOn;
    }
    
    public int getDiceRoll(){
	return _diceRoll;
    }

    public boolean inJail(){
	return _inJail;
    }
    
    public int jailTurns(){
	return _jailTurns;
    }

    public void setJail(boolean b){
	_inJail = b;
    }

    public void setJailTurns(int n){
	_jailTurns = n;
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

    // removes Property p from _propertiesOwned
    // returns property removed
    public Property removeProperty(Property p){
	_propertiesOwned.remove(p);
	p.setOwner(null);
	return p;
    }

    // gives Property p to player
    public void giveProperty(Property p){
	_propertiesOwned.add(p);
	p.setOwner(this);
    }

    private void setCash(int newCashValue) {
	_cashOnHand = newCashValue;
	System.out.println( _name + " now has $" + getCash());
    }
    
    // coord details new coordinates
    public void setSquareOn(int[] coord, Landable[][] board) {
	// remove player from old square
	board[ _coordinate[0] ][ _coordinate[1] ].removePlayerOnSquare(this);
	// update Player's _squareOn
	_squareOn = board[coord[0]][coord[1]];
	// add player to new square
	board[ coord[0] ][ coord[1] ].setPlayerOnSquare(this);
	_coordinate = coord; // update player coordinates
	
    }
	
    // takes amt of money away from player
    public int charge(int amt) {
	setCash( getCash() - amt );
	return getCash();
    }
  
    // gives amt of money to p;layer
    public int give(int amt) {
	setCash( getCash() + amt );
	return getCash();
    }
    
    // moves player around the board, adjusting coordinates as necessary
    // coordinates corresponds with the indicies in board
    // also gives pass go cash to player
    public void move( Landable[][] board ) {
	if (_squareOn != null)
	    _squareOn.removePlayerOnSquare(this);

	_diceRoll = (int)(Math.random() * 6) + 1;
	_diceRoll += (int)(Math.random() * 6) + 1;
	// translate dice roll into change in coordinates, corresponding w/ board
	if ( _coordinate[0] == 0 ){ //top row
	    _coordinate[1] += _diceRoll;
	    if ( _coordinate[1] > 10 ){ //player leaves top row to right column
		_coordinate[0] += _coordinate[1] - 10;
		_coordinate[1] = 10;
	    }
	    if ( _coordinate[0] > 10 ){ // player leaves right column to bottom row
		_coordinate[1] -= (_coordinate[0] - 10);
		_coordinate[0] = 10;
		give( 200 ); // pass Go cash
	    }
	}
	else if ( _coordinate[1] == 10 ){ //right column
	    _coordinate[0] += _diceRoll;
	    if ( _coordinate[0] > 10 ){ // player leaves right column to bottom row
		_coordinate[1] -= (_coordinate[0] - 10);
		_coordinate[0] = 10;
		give( 200 ); // pass Go cash
	    }
	    if ( _coordinate[1] < 0 ){ //player leaves bottom row to left column
		_coordinate[0] += _coordinate[1];
		_coordinate[1] = 0;
	    }
	}
	else if ( _coordinate[0] == 10 ){ //bottom row
	    _coordinate[1] -= _diceRoll;
	    if ( _coordinate[1] < 0 ){ // player leaves bottom row to left column
		_coordinate[0] += _coordinate[1];
		_coordinate[1] = 0;
	    }
	    if ( _coordinate[0] < 0 ){ //player leaves left column to top row
		_coordinate[1] -= _coordinate[0];
		_coordinate[0] = 0;
	    }
	}
	else if ( _coordinate[1] == 0 ){ //left row
	    _coordinate[0] -= _diceRoll;
	    if ( _coordinate[0] < 0 ){ //player leaves left column to top row
		_coordinate[1] -= _coordinate[0];
		_coordinate[0] = 0;
	    }
	    if ( _coordinate[1] > 10 ){ //player leaves top row to right column
		_coordinate[0] += _coordinate[1] - 10;
		_coordinate[1] = 10;
	    }
	}
	System.out.println( _name + " has rolled a: " + _diceRoll);
	_squareOn = board[ _coordinate[0] ][ _coordinate[1] ];
	_squareOn.setPlayerOnSquare(this);
	System.out.println( _name + " has landed on: " + _squareOn );
    }

    // used to pay rent
    public void pay(Property p) {
    	int fee = p.getRent();
    	System.out.println(_name + "has paid " + fee + " to " + p.getOwner().getName());
    	this.charge(fee);
    	p.getOwner().give(fee);
    }

    // used to buy a property at list price
    // only called if p is ownerless
    // returns true if purchase successful; false otherwise
    public boolean buy(Property p){
	// enough money to pay
	if ( getCash() >= p.getBuyPrice() ){
	    charge( p.getBuyPrice() ); //charge player
	    this.giveProperty(p); //give property and set new owner
	    return true;
	}
	return false;
    }

    // executes a turn of this player if he is in jail
    public void jailTurn() {
	//check if player can afford bail
	System.out.println("Jail Turn: " + _jailTurns );
	if (getCash() >= 50) {
	    //prompt user input
	    System.out.println("Would you like to pay bail? y:1\tn:2");
	    int input = parseInput(Keyboard.readString(), 2);
	    //if user would like to pay bail
	    //forced to pay if player has spent more than 3 turns in jail
	    if (input == 1 || _jailTurns >= 3) {
		_jailTurns = 0;
		_inJail = false;
		charge(50);
	    }
	    else
		_jailTurns += 1;//add one to jailTurns
	    
	}
	else if ( getCash() < 50 ){
	    System.out.println("You do not have enough money to bail out. Would you like to mortgage property to do so? If this is your third turn, you will be forced to chose yes. 1:yes\t2:no");
	    int input = parseInput(Keyboard.readString(), 2);
	    if ( input == 1 || _jailTurns >= 3 ){
		System.out.println("Mortgage until you get at least $50. If you do not do so or cannot do so and end mortgaging, you will lose.");
		offerMortgageOptions(0);
		if ( getCash() < 50 )
		    return;
	    }
	}
	else
	    _jailTurns += 1;
    }

    // executes a player turn
    // params: Landable[][] board represents the Monopoly board
    //         Monopoly game represents the Monopoly object being played
    //              needed for executeLandOn()
    public void turn(Landable[][] board, Monopoly game) {
    	if (_inJail){
    	    jailTurn();
    	}
    	else { //if not in jail
	    move(board);
	    executeLandOn(board, game); // determine course of action based on what player landed on
	    playerOptions(game); //offer general options
	}
    }

    // determines course of action based on what player landed on
    // params: Landable[][] board represents the Monopoly board
    //         Monopoly game represents Monopoly object being played
    //               needed for propertyAction()
    public void executeLandOn(Landable[][] board, Monopoly game){
	if ( _squareOn instanceof GoToJail )
	    goToJailAction( (GoToJail)(_squareOn), board );

	else if ( _squareOn instanceof Chance )
	    chanceAction( (Chance)(_squareOn), board, game) ;

	else if ( _squareOn instanceof Community )
	    communityAction( (Community)(_squareOn), board, game );

	else if ( _squareOn instanceof Property )
	    propertyAction( (Property)(_squareOn), game );

	else if ( _squareOn instanceof Tax )
	    taxAction( (Tax)(_squareOn) );
    }

    // performs necessary actions if player lands on go to jail square
    // takes 2 params: the GoToJail instance landed on, and the Monopoly board
    public void goToJailAction( GoToJail square, Landable[][] board ){
	setSquareOn( new int[] {0,10}, board); // move to jail
	square.processVictim(this, board); // send to jail
    }
    
    // triggers actions on this player within chance object
    // takes 3 params: the Chance instance landed on, the  Monpoly board, and the Monopoly game
    public void chanceAction( Chance square, Landable[][] board, Monopoly game ){
	System.out.println(square);
	square.execute(this, board, game.getPlayerList()); // execute action based on chance card
    }

    // triggers actions on this player within chance object
    // takes 2 params: the Community instance landed on, Monoboly board, and the Monopoly game
    public void communityAction( Community square, Landable[][] board, Monopoly game ){
	System.out.println(square);
	square.execute(this, board, game.getPlayerList()); // execute action based on community card
    }

    // performs necessary actions for player landing on a property
    // e.g. buying, renting, etc.
    // params: typecasted Property square: property landed on
    //         Monopoly game represents game being played
    //             needed to reference Monopoly's auction method
    public void propertyAction(Property square, Monopoly game){
	if (square.getOwner() == null && square.getOwner() != this){ //unowned
	    System.out.println("Do you wish to buy " + square.getName() + " for " + square.getBuyPrice() + " (current cash: " + getCash() + ")");
	    System.out.println("1:yes\t2:no");
	    int choice = parseInput(Keyboard.readString(), 2);
	   
	    if ( choice == 1 ){ //want to buy
		while ( ! buy( square ) ){ // if not enough 
		    // offer mortgage options
		    System.out.println("You do not have enough cash to pay. Offering mortgage options.");
		    offerMortgageOptions(0);
		    System.out.print("Do you wish to buy the property? 1:yes\t2:no ");
		    int keepGoing = parseInput(Keyboard.readString(), 2);
		    if (keepGoing == 2) // if stop mortgaging
			break; // exit loop
		}
		System.out.println("Success! You have successfully bought " + square.getName() );
		System.out.println("Your new cash on hand: " + getCash() );
	    }
	    else // pass buying, go to auction
		game.auction( square );
	}
	else // property has an owner
	    pay( square ); // pay rent
    }
    
    // charges the player for the tax value of the tax square landed on
    // takes 1 param, typecasted Tax instance landed on
    public void taxAction(Tax square){
	charge( square.getRent() ); // pay tax
    }
    
    // offers player options at the end of turn
    // takes one param: Monopoly game, the monopoly object being played
    //                    used to remove player from Monopoly's playerList if he is bankrup
    public void playerOptions(Monopoly game){
	System.out.println("1. Build houses");
	System.out.println("2. Sell houses");
	System.out.println("3. Morgage property");
	System.out.println("4. Unmortgage property");
	System.out.println("5. End turn");
	System.out.println("6. Initiate a trade. NOT IMPLEMENTED");
	System.out.println("7. Save and exit game. NOT IMPLEMENTED");
	if ( getCash() < 0 )
	    System.out.println("WARNING. YOU ARE IN DEBT. IF YOU DO NOT ACHIEVE A NON-NEGATIVE BALANCE AND END YOUR TURN, YOU WILL FORFEIT. IF YOU CANNOT REPAY YOUR DEBTS IN THIS TURN, YOU LOSE. Have a nice day.");
	int choice = parseInput(Keyboard.readString(), 7);

	if ( (choice == 1 || choice == 2) ) // if sell houses or build houses
	    offerBuildOptions(choice-1);
	
	else if (choice == 3 || choice == 4) // mortgage or unmortgage
	    offerMortgageOptions(choice-3);
       
	else if (choice == 5){ // done
	    if ( getCash() < 0 ) // if bankrupt
		game.getPlayerList().remove(this); // remove player from game
	    return; // stop playerOptions
	}
	playerOptions(game); // call player options again
    }

    // builds amt number of houses on NormalProperty p
    // prints out confirmation/denial message for user
    // returns new number of houses on NormalProperty p
    public int buildHouse(NormalProperty p, int amt) {
	// if can build houses
	if ( p.buildHouses(amt) == true )
	    // houses already built in conditional statement
	    System.out.println( p.getName() + " now has " + p.getHouses() + " houses!" );
	else
	    System.out.println( "Oh no! Something went wrong: do you have enough money? Are there already 5 houses on the property?" 
				+ p.getName() + " still has " + p.getHouses() + " houses!" );
	return p.getHouses();
    }

    // sells amt number of houses on NormalProperty p
    // prints out confirmatin/denial message
    // returns new number of houses on NormalProperty p
    public int sellHouse(NormalProperty p, int amt) {
	// if can build houses
	if ( p.sellHouses(amt) == true )
	    // houses already built in conditional statement
	    System.out.println( p.getName() + " now has " + p.getHouses() + " houses!" );
	else
	    System.out.println( "Oh no! Something went wrong: Do you have enough houses to sell?" 
				+ p.getName() + " still has " + p.getHouses() + " houses!" );
	return p.getHouses();
    }

    // offers build/sell options
    // params: int opt 0 if player is building, 1 if selling
    public void offerBuildOptions(int opt){
	if ( getPropertiesOwned().size() == 0 ) // no build/sell if no properties
	    System.out.println("You do not have any properties yet.");

	else{
	    // print info about properties to build houses on
	    boolean atLeastOne = false; // true if there is at least 1 property to build a house on
	   
	    for ( int i = 0; i < getPropertiesOwned().size(); i++ ){
		if ( getPropertiesOwned().get(i) instanceof NormalProperty){
		    NormalProperty tmp = (NormalProperty)(getPropertiesOwned().get(i));
		    if ( tmp.checkMonopoly() ){ //print info if monopoly b/c then can build house
			atLeastOne = true;
			System.out.print( i + 1 );
			System.out.print( ". " + tmp.getName() );
			System.out.print( "\tCost: " + tmp.getHouseCost() );
			System.out.print( "\tHouses: " + tmp.getHouses() + "\n");
		    }
		}
	    } // close all printing
	    System.out.println();
	    
	    if ( atLeastOne ){ // only offer choices if player can build at least one house
		System.out.print("Which property do you want to build on? ");
		int propChoice = parseInput(Keyboard.readString(), getPropertiesOwned().size()) - 1;
		if ( getPropertiesOwned().get(propChoice) instanceof NormalProperty && 
		     ((NormalProperty)(getPropertiesOwned()).get(propChoice)).checkMonopoly() ){
		    
		    NormalProperty property = (NormalProperty) ( getPropertiesOwned().get(propChoice) );
		    
		    if (opt==0){ //build
			System.out.print("How many houses do you want to build? ");
			int houseNum = parseInput(Keyboard.readString(), 5);
			buildHouse( property, houseNum );
		    }
		    else if (opt==1){ //sell
			System.out.print("How many hosues do you want to sell? ");
			int houseNum = parseInput(Keyboard.readString(), 5);
			sellHouse( property, houseNum);
		    }
		} // close if property is part of monopoly
	    } // close if at least one
	    
	    else // invalid input
		System.out.println("You do not have any properties that you can build on.");
	} // closes else
    }
    

    public boolean mortgage(Property p) {
	return p.mortgage();
    }
    
    public boolean unMortgage(Property p) {
	return p.unMortgage();
    }

    // returns true if player still has properties he can mortgage
    public boolean canMortgage(){
	for ( Property prop : getPropertiesOwned() ){
	    if (! prop.isMortgaged() )
		return false;
	}
	return true;
    }

    // offers mortgage options and keeps prompting player until done
    // params: int opt 0 if player is mortgaging, 1 if unmortgaging
    public void offerMortgageOptions(int opt){
	if ( getPropertiesOwned().size() == 0 ){
	    System.out.println("No properties to work with.");
	    return;
	}
	boolean continueMortgage = true;
	while ( continueMortgage ){
	    // print properties
	    for (int i = 0; i < _propertiesOwned.size(); i++){
		String retStr = "";
		retStr += i + 1 + " "; // choice number
		retStr += _propertiesOwned.get(i).getName();
		retStr += " $" + _propertiesOwned.get(i).getMortgageValue() + " ";
		if ( _propertiesOwned.get(i).isMortgaged() )
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
	    int index = parseInput(Keyboard.readString(), _propertiesOwned.size());

	    if ( opt == 0 ) //mortgage option
		mortgage( _propertiesOwned.get(index - 1) );
	    else // unmortgage option
		unMortgage( _propertiesOwned.get(index - 1) );

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
        public void trade(Player p) {
	ArrayList<NormalProperty> want, have;
	boolean anotherOne = true;

	//prompt user to choose properties wanted
	System.out.println("Please choose which properties you would like from " + p._name);
	System.out.println(p._propertiesOwned);
	while (anotherOne == true) {
	    int input = parseInput(Keyboard.readString(), p._propertiesOwned.size());
	    //check if property is already chosen
	    if (want.contains(p._propertiesOwned(input)))
		System.out.println("This property has already been chosen! Please choose another property.");
	    else {
		//confirm property
		System.out.println("You have chosen " + p._propertiesOwned.get(input)
				   + "Are you sure this is the property you want? y:1\tn:2");
		int confirm = parseInput(Keyboard.readString(), 2);
		if (confirm == 1)
		    want.add(p._propertiesOwned.get(input));
		//would user like to make another choice
		System.out.println("Would you like to make another choice? y:1\tn:2");
		int another = parseInput(Keyboard.readString(), 2);
		if (another == 2)
		    anotherOne == false;
	    } //end else
	} // end while | easter egg found! (3 of 3)
	anotherOne == true;
	
	//promt user to choose properties to exchange
	System.out.println("Please choose which properties you would like to give " + p._name);
	System.out.println(this._propertiesOwned);
	while (anotherOne == true) {
	    int input = parseInput(Keyboard.readString(), this._propertiesOwned.size());
	    if (want.contains(p._propertiesOwned(input)))
		System.out.println("This property has already been chosen! Please choose another property.");
	    else {
		//confirm property
		System.out.println("You have chosen " + this._propertiesOwned.get(input)
				   + "Are you sure this is the property you want to give? y:1\tn:2");
		int confirm = parseInput(Keyboard.readString(), 2);
		if (confirm == 1)
		    have.add(this._propertiesOwned.get(input));
		//would user like to make another choice
		System.out.println("Would you like to make another choice? y:1\tn:2");
		int another = parseInput(Keyboard.readString(), 2);
		if (another == 2)
		    anotherOne == false;
	    } //end else 
	} //end while
	
	//print out trade
	System.out.println( this._name + " would like to trade with " + p._name + "!" );
	System.out.println( this._name + " offers " + have + " for " + p._name + "'s " + want);
	System.out.println( "Does " + this._name + " agree to the trade?" );
	int agree1 = parseInput(Keyboard.readString(), 2);
	System.out.println( "Does " + p._name + " agree to the trade?" );
	int agree2 = parseInput(Keybaord.readString(), 2);
	//if both players agree to trade
	if (agree1 == 1 && agree2 == 1) {
	    System.out.println("Both players agreed to the trade!");
	    for (Property pr : want) {
		this._propertiesOwned.add(pr);
		p._propertiesOwned.remove(pr);
	    }
	    for (Property pr : have) {
		this._propertiesOwned.remove(pr);
		p._propertiesOwned.add(pr);
	    }
	    System.out.println( this._name + " now owns: " + this._propertiesOwned);
	    System.out.println( p._name + " now owns: " + p._propertiesOwned);
	}
	//if one/both players do not agree to the trade
	else 
	    System.out.println("Oh no! One (or more) of the parties declined the trade! :C");
    } //end trade

}

    
