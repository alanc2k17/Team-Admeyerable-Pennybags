import java.util.ArrayList;

public class Player {
    protected int[] _coordinate;
    protected String _name;
    protected String _symbol;
    protected ArrayList<Property> _propertiesOwned;
    protected int _cashOnHand;
    protected Landable _squareOn;
    protected int _diceRoll;

    public Player() {
	_coordinate = new int[] { 0, 0 };
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

    public int getCoords() {
	return _coordinate;
    }
    
    public int getCash() {
	return _cashOnHand;
    }
    
    public ArrayList getPropertiesOwned() {
	return _propertiesOwned;
    }
    
    public Landable getSquareOn() {
	return _squareOn;
    }
    
    public int getDiceRoll(){
	return _diceRoll;
    }

    private void setCash(int newCashValue) {
	_cashOnHand = newCashValue;
	System.out.println( _name + " now has $" + getCash());
    }
    
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
    
    // takes amt of money away from player
    public int charge(int amt) {
	setCash( getCash() - amt );
	System.out.println( _name + "now has $" + _cashOnHand );
	return getCash();
    }
  
    // gives amt of money to p;layer
    public int give(int amt) {
	setCash( getCash() + amt );
	System.out.println( _name + " now has $" + _cashOnHand );
	return getCash();
    }
    
    // moves player around the board, adjusting coordinates as necessary
    // coordinates corresponds with the indicies in board
    public void move( Landable[][] board ) {
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
		_coordinate[1] -= (coordinate[0] - 10);
		_coordinate[0] = 10;
	    }
	}
	else if ( _coordinate[1] == 10 ){ //right column
	    _coordinate[0] += _diceRoll;
	    if ( _coordinate[0] > 10 ){ // player leaves right column to bottom row
		_coordinate[1] -= (coordinate[0] - 10);
		_coordinate[0] = 10;
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
	_squareOn = board[ _coordinate[0], _coordinate[1] ];
	System.out.println( _name + " has landed on: " + _squareOn );
    }

    public void pay(Property p) {
    	int fee = p.getRent();
    	System.out.println(_name + "has paid " + fee + " to " + p.getOwner());
    	this.charge(fee);
    	p.getOwner().give(fee);
    }

    public boolean buy(Property p){
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
    
    public boolean mortgage(Property p) {
	return p.mortgage();
    }
    
    public boolean unMortgage(Property p) {
	return p.unMortgage();
    }
}
    //METHODS LEFT: move(); buildHotel(); requestTrade(); trade()
    
