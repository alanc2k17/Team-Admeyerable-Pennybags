import java.util.ArrayList;

public class NormalProperty extends Property{
    // MOST OF THE VARS ARE NOT INITIALIZED IN THIS CLASS
    // THEY ARE HERE SO THEY CAN BE USED IN METHOD DEFINITIONS

    // 1 refers to data for cheaper two properties of the color group
    // 2 refers to data for more expensive property of the color group
    
    //index corresponds w/ rent for number of houses
    protected int[] _rent1; 
    protected int[] _rent2; 

    protected int _buyPrice1; 
    protected int _buyPrice2; 

    protected int _houses;
    protected int _houseCost;
    //true if this's owner has monopoly on color group
    protected boolean _monopoly; 
    //number of other properties with same color group needed for monopoly
    protected int _others;

    //true if this is expensive property of color group
    protected boolean _expensive; 

    protected int _mortgageValue1;
    protected int _mortgageValue2;

    // default constructor
    // also sets expensive boolean to default (false)
    public NormalProperty(){
	super();
	_expensive = false;
	_houses = 0;
	setMortgageValue();
    }

    // overloaded constructor
    // also sets expensive boolean to last input param
    public NormalProperty(String name, String initials, boolean expensive){
	super(name, initials);
	_expensive = expensive;
	_houses = 0;
	setMortgageValue();
    }
    
    // override toString to return info about this class
    public String toString(){
	String returnString = "";
	if ( _owner == null ){
	    returnString += "owner of " + _name + "(" + _initials + ") = No Owner\n";
	    returnString += "buy price: " + getBuyPrice();
	}
	else{
	    returnString += "owner of " + _name + "(" + _initials + ") " + _owner.getName() + "\n"; 
	    returnString += "does the current owner have a monopoly in this color group?" + checkMonopoly() + "\n";
	    returnString += "number of houses on this property: " + _houses + "\n";
	    returnString += "current rent is: " + getRent() + "\n";
	}
	returnString += "mortgage value is: " + getMortgageValue();
	return returnString;
    }

    // accessor; returns # of houses
    public int getHouses(){
	return _houses;
    }

    // accessor; returns cost of hosues
    public int getHouseCost(){
	return _houseCost;
    }

    // accessor; gets buy price
    public int getBuyPrice(){
	if (_expensive)
	    return _buyPrice2;
	else
	    return _buyPrice1;
    }

    // returns an int equal to rent on this property
    // uses _houses inst. var. to determine rent
    public int getRent(){
	if ( isMortgaged() ) return 0; //no rent if mortgaged
	if (_expensive)
	    // use _rent2 data if prop. is expensive
	    return _rent2[ _houses ];
	else
	    // use _rent1 data if prop. is cheap
	    return _rent1[ _houses ];
    }

    // takes one int param, h, and adds h to # of houses
    // returns true if houses sucessfully built
    //         false otherwise (ie. not enough money, max houses)
    public boolean buildHouses(int h){
	Player owner = getOwner();
	// sufficient funds & enough space
	if ( owner.getCash() >= (h * _houseCost) &&  (_houses + h) < 5 ){
	    _houses += h; //build
	    owner.charge( h * _houseCost ); // charge player
	    return true;
	}
	return false;
    }

    // takes one input param, h, and subtracts h from # of houses
    // returns true if houses successfully sold
    //         false otherwise (ie. not enough houses)
    public boolean sellHouses(int h){
	Player owner = getOwner();
	if ( (_houses - h) >= 0 ){ //enough houses to sell
	    _houses -= h; //sell
	    owner.give( ((int) (.5 * h * _houseCost)) ); //refund player
	    return true;
	}
	return false;
    }

    // helper function to be used in checkMonopoly()
    // takes two params, a Property and an ArrayList<Property>
    // returns true if an alias of Property is in ArrayList; false otherwise
    public boolean propertyIn(Property p, ArrayList<Property> list){
	for ( int i = 0; i < list.size(); i++){ //for each Property in list
	    if (p == list.get(i)){ //if alias found
		return true;
	    }
	}
	return false;
    }

    // returns true if Player (owner) has a monopoly on this color
    // false otherwise
    public boolean checkMonopoly(){
	int count = 0;
	for (int i = 0; i < getOwner().getPropertiesOwned().size(); i++){ // loop through owner properties
	    if ( this.getClass().equals( getOwner().getPropertiesOwned().get(i) ) ) //if same class
		count += 1;
	}
	return count == _others;
	    
	/*
	ArrayList<Property> ownerProperties = getOwner().getPropertiesOwned();

	// for each color group member property
	for (int i = 0; i < _groupMembers.size(); i++){
	    // if this color group property not owned
	    if (! propertyIn( _groupMembers.get(i), ownerProperties) ){
		return false; 
	    }
	}
	return true;
	*/
    }

    // returns the mortgage value of property, depending on whether it is expensive or not
    public int getMortgageValue(){
	if ( _expensive ) // use expensive mortgage value
	    return _mortgageValue2;
	else
	    return _mortgageValue1;
    }

    // sets mortgage value, equal to 1/2 buy price
    public void setMortgageValue(){
	_mortgageValue1 = (int)(_buyPrice1 * .5);
	_mortgageValue2 = (int)(_buyPrice2 * .5);
    }

    // mortgages the property
    // gives player the mortgage money
    // returns true if mortgage is successful
    // returns false otherwise
    public boolean mortgage(){
	if (! isMortgaged() ){ //don't mortgage if already mortgaged
	    Player owner = getOwner();
	    owner.give( getMortgageValue() );
	    _mortgage = true;
	    return true;
	}
	System.out.println("Sorry! This was already mortgaged. :( ");
	return false;
    }

    // unmortgages property
    // charges owner money equal to 110% of mortgage value
    // returns true if mortgage successful, false otherwise
    public boolean unMortgage(){
	Player owner = getOwner();
	int unMortgagePrice = 0;
	if ( _expensive ) //use expensive mort. val
	    unMortgagePrice = (int) (1.1 * _mortgageValue2);
	else //use cheap mort. val
	    unMortgagePrice = (int) (1.1 * _mortgageValue1);
	
	//check if enough money and not already mortgaged
	if ( isMortgaged() && owner.getCash() >= unMortgagePrice ){
	    owner.charge(unMortgagePrice);
	    _mortgage = false;
	    return true;
	}
	System.out.println("Yay! There's no need to unmortgage this property, or you do not have enough money.");
	return false;
    }
	    
}
