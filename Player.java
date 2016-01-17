import java.util.ArrayList;

public class Player {
    protected int _coordinate;
    protected String _name;
    protected String _symbol;
    protected ArrayList<Property> _propertiesOwned;
    protected int _cashOnHand;
    protected Landable _squareOn;
    protected int _diceRoll;

    public Player() {
	_coordinate = 0;
	_name = "nameless";
	_symbol = "D";
	_propertiesOwned = new ArrayList<Property>();
	_cashOnHand = 1500;
	
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
    
    public int charge(int amt) {
	setCash( getCash() - amt );
	System.out.println( _name + "now has $" + _cashOnHand );
	return getCash();
    }
  
    public int give(int amt) {
	setCash( getCash() + amt );
	System.out.println( _name + " now has $" + _cashOnHand );
	return getCash();
    }
    
    public void move() {
	_diceRoll = (int)(Math.random() * 6) + 1;
	_diceRoll += (int)(Math.random() * 6) + 1;
	System.out.println( _name + " has rolled a: " + getDiceRoll());
    }

    public void pay(Property p) {
    	int fee = p.getRent();
    	this.charge(fee);
    	p.getOwner().give(fee);
    	System.out.println(_name + "has paid " + fee + " to " + p.getOwner());
    	System.out.println(_name + " now has " + getCash());
    	System.out.println(p.getOwner() + " now has " + p.getOwner().getCash());
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
    
