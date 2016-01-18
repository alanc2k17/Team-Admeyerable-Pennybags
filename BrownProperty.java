public class BrownProperty extends NormalProperty{
   
    //default constructor
    public BrownProperty(){
	super();
	setInfo();
    }

    //overloaded constructor
    public BrownProperty(String name, String initials, boolean expensive){
	super(name, initials, expensive);
	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
	_rent1 = new int[] {6, 30, 90, 270, 400, 550}; //mediterranean
	_rent2 = new int[] {8, 40, 100, 300, 450, 600}; //baltic

	// number of other properties in group
	_others = 2;
	
	// set buy prices
	_buyPrice1 = 100; 
	_buyPrice2 = 120; 
	
	//set house cost
	_houseCost = 50;
    }
}

    
