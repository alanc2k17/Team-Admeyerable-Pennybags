public class BlueProperty extends NormalProperty{
   
    //default constructor
    public BlueProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public BlueProperty(String name, String initials, String coordinate, boolean expensive){
    	super(name, initials, coordinate, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = {35, 175, 500, 1100, 1300, 1500}; //parkplace
    	_rent2 = {50, 200, 600, 1400, 1700, 2000}; //boardwalk
	
	// set buy prices
    	_buyPrice1 = 350; 
    	_buyPrice2 = 400; 
	
	//set house cost
    	_houseCost = 200;
    }
}
