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

    //true if this is expensive property of color group
    protected boolean _expensive; 

    protected int _mortgageValue1;
    protected int _mortgageValue2;

    // default constructor
    // also sets expensive boolean to default (false)
    public NormalProperty(){
	super();
	_expensive = false;
    }

    // overloaded constructor
    // also sets expensive boolean to last input param
    public NormalProperty(String name, String initials, int coordinate, boolean expensive){
	super(name, initials, coordinate);
	_expensive = expensive;
    }
    
    // override toString to return info about his class
    public String toString(){
	return "";
    }

    // accessor; returns # of houses
    public int getHouses(){
	return _houses;
    }

    // returns an int equal to rent on this property
    // uses _houses inst. var. to determine rent
    public int getRent(){
	if ( isMortgaged() ) return 0; //no rent if mortgaged
	if (expensive)
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
    public boolean sellHouse(int h){
	Player owner = getOwner();
	if ( (_houses - h) >= 0 ){ //enough houses to sell
	    _houses -= h; //sell
	    owner.charge( ((int) (.5 * h * _houseCost)) ); //refund player
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
	Player ownerProperties = getOwner().getPropertiesOwned();

	// for each color group member property
	for (int i = 0; i < _groupMembers.size(); i++){
	    // if this color group property not owned
	    if (! propertyIn( _groupMembers.get(i), ownerProperties) ){
		return false; 
	    }
	}
	return true;
    }

    // mortgages the property
    // gives player the mortgage money
    // returns true if mortgage is successful
    // returns false otherwise
    public boolean mortgage(){
	if (! isMortgaged() ){ //don't mortgage if already mortgaged
	    Player owner = getOwner();
	    if ( _expensive ) // use expensive mortgage value
		owner.give( _mortgageValue2 ); 
	    else // use cheap mort. val
		owner.give( _mortgageValue1 ); 
	    _mortgage = true;
	    return true;
	}
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
	return false;
    }
	    
}