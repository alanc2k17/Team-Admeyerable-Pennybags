import java.util.ArrayList;

public class Railroad extends Property{
    
    protected final int[] RENT = {25, 50, 100, 200}; //index corresponds w/ number of railroads
    protected final int BUYPRICE = 200;
    
    protected int _mortgageValue; //not inherited from Property b/c some classes under property have 2 mortgage vals

    //default constructor
    public Railroad(){
	super();
	setMortgageValue();
    }

    //overloaded constructor
    public Railroad(String name, String initials){
	super(name, initials);
	setMortgageValue();
    }

    //override toString; used to print Object to board
    public String toString(){
	return "";
    }

    // accessor method to get buy price
    public int getBuyPrice(){
	return BUYPRICE;
    }

    //sets mortgage value based on BUYPRICE
    //calld in constructor
    public void setMortgageValue(){
	_mortgageValue = (int) (.5 * BUYPRICE);
    }

    //mortgages property
    //gives owner money equal to mortgage value
    //returns true if mortgage successful, false otherwise
    public boolean mortgage(){
	if ( ! isMortgaged() ){ //don't mortgage if already mortgaged
	    Player owner = getOwner();
	    owner.give( _mortgageValue ); 
	    _mortgage = true; 
	    return true;
	}
	return false;
    }

    //unmortgages property
    //charges owner money equal to 110% of mortgage value
    //returns true if mortgage successful, false otherwise
    public boolean unMortgage(){
	Player owner = getOwner();
	int unMortgagePrice = (int) (1.1 * _mortgageValue );
	// check for enough money and not already unmortgaged
	if ( isMortgaged() && owner.getCash() >= unMortgagePrice ){
	    owner.charge(unMortgagePrice); 
	    _mortgage = false;
	    return true;
	}
	return false;
    }

    //returns int detailing number of Railroads _owner owns
    public int railRoadsOwned(){
	int numRailroads = 0;
	ArrayList<Property> ownedProperties = getOwner().getPropertiesOwned();
	for ( int i = 0; i < ownedProperties.size(); i++){ //loop through owner's properties
	    if ( ownedProperties.get(i) instanceof Railroad ){ //if that property is railroad
		numRailroads += 1;
	    }
	}
	return numRailroads;
    }

    //returns int detailing rent if player lands on property
    //based off of how many other railraods owned
    public int getRent(){
	if ( isMortgaged() ) return 0; // no rent with mortgage
	return RENT[ railRoadsOwned() ]; //# of railrds. corresponds w/ index of RENT
    }

}