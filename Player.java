import java.util.ArrayList;

public class Player {
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
    
    public int getJailTurns() {
    	return _jailTurns;
    }
    
    public void setJailTurns(int i) {
    	_jailTurns = i;
    }
    public boolean inJail() {
    	return _inJail;
    }

    public void setJail(boolean b) {
    	_inJail = b;
    }

    // removes Property p from _propertiesOwned
    // returns property removed
    public Property removeProperty(Property p){
	_propertiesOwned.remove(p);
	return p;
    }

    // gives Property p to player
    public void giveProperty(Property p){
	_propertiesOwned.add(p);
    }

    private void setCash(int newCashValue) {
	_cashOnHand = newCashValue;
	System.out.println( _name + " now has $" + getCash());
    }
    
    /*
    // sets the square the player is on
    // used for go to jail and chance cards
    // updates player coordinates
    public void setSquareOn(Landable square, Landable[][] board){
	_squareOn = square;
	// find square
	for (int i = 0; i < board.length; i++){
	    for (int k = 0; k < board[i].length; k++){
		if ( board[i][k] == square ){
		    _coordinate[0] = i; //set coordinates to square location
		    _coordinate[1] = k;
		}
	    }
	}
    }
    */
    
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
	    p.setOwner(this); //set owner to this player
	    this.giveProperty(p);
	    return true;
	}
	return false;
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

    

}

    
