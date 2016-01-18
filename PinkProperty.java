public class PinkProperty extends NormalProperty{
   
    //default constructor
    public PinkProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public PinkProperty(String name, String initials, boolean expensive){
    	super(name, initials, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = new int[] {10, 50, 150, 450, 625, 750}; //virginia ave | states ave
	_rent2 = new int[] {12, 60, 180, 500, 700, 900}; //st. charles place

	// number of other properties in group
	_others = 3;
	    
	// set buy prices
    	_buyPrice1 = 140; 
    	_buyPrice2 = 160; 

	setMortgageValue();
	    
	//set house cost
    	_houseCost = 100;
    }
}
