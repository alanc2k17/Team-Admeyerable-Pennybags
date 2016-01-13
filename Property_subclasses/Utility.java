public class Utility extends Property{
    
    protected final int BUYPRICE = 200;
    
    protected int _mortgageValue; //not inherited from Property b/c some classes under property have 2 mortgage vals

    //default constructor
    public Utility(){
	super();
	setMortgageValue();
    }

    //overloaded constructor
    public Utility(String name, String initials, String coordinate){
	super(name, initials, coordinate);
	setMortgageValue();
    }

    //override toString; used to print Object to board
    public String toString(){
	return "";
    }

    //sets mortgage value based on BUYPRICE
    //calld in constructor
    public void setMortgageValue(){
	_mortgageValue = (int) (.5 * BUYPRICE);
    }

    //returns int detailing number of Utilities _owner owns
    public int railRoadsOwned(){
	int numUtilities = 0;
	ArrayList<Property> ownedProperties = getOwner().getPropertiesOwned();
	for ( int i = 0; i < ownedProperties.size(); i++){ //loop through owner's properties
	    if ( ownedProperties.get(i) instanceof Utility ){ //if that property is railroad
		numUtilitys += 1;
	    }
	}
	return numUtilities;
    }

    public int getDiceRoll(){
	
    }

    //returns int detailing rent if player lands on property
    //based off of how many other railraods owned
    public int getRent(){
	
    }

}