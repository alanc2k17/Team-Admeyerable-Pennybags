import java.util.ArrayList;

public class Player {
    protected int _coordinate;
    protected String _name;
    protected String _symbol;
    protected ArrayList<Property>[] _propertiesOwned;
    protected int _cashOnHand;
    protected Landable _squareOn;
    
    public Player() {
	_coordinate = 0;
	_name = "nameless";
	_symbol = "D";
	_propertiesOwned = new ArrayList<Property>();
	_cashOnHand = 1500;
	_propertyOn = ???; 
    }
    
    public Player(String newName, String symbol) {
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
    
    private void setCash(int newCashValue) {
	_cash = newCashValue;
    }
    
    public int charge(int amt) {
	setCash( getCash() - amt );
	System.out.println( _name + "now has $" + _cash );
	return getCash();
    }
  
    public int give(int amt) {
	setCash( getCash() + amt );
	System.out.pritnln( _name + " now has $" + _cash );
	return getCash();
    }
    
    public String move() {
	int diceRoll = (int)(Math.random() * 6) + 1;
	diceRoll += (int)(Math.random() * 6) + 1;
	
	// many if statements
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
    
    public String buildHotel(Property p) {
	// ???
    }
    
    public mortgage(Property p) {
	// ???
    }
    
    //unsure how to implement pay(Property p)/move()/buildHotel(Property p)
