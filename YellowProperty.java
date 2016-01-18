public class YellowProperty extends NormalProperty{
   
    //default constructor
    public YellowProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public YellowProperty(String name, String initials, boolean expensive){
    	super(name, initials, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = new int[] {22, 110, 330, 800, 975, 1150}; //atlantic ave | ventor ave
	_rent2 = new int[] {24, 120, 360, 850, 1025, 1200}; //marvin gardens

	// number of other properties in group
	_others = 3;
	    
	// set buy prices
    	_buyPrice1 = 260; 
    	_buyPrice2 = 280; 
	    
	//set house cost
    	_houseCost = 150;
    }
}
