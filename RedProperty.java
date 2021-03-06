public class RedProperty extends NormalProperty{
   
    //default constructor
    public RedProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public RedProperty(String name, String initials, boolean expensive){
    	super(name, initials, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = new int[] {18, 90, 250, 700, 875, 1050}; //kentucky ave | indiana ave
	_rent2 = new int[] {20, 100, 300, 750, 925, 1100}; //illinois ave
	    
	// number of other properties in group
	_others = 3;

	// set buy prices
    	_buyPrice1 = 220; 
    	_buyPrice2 = 240; 

	setMortgageValue();
	    
	//set house cost
    	_houseCost = 150;
    }
}
