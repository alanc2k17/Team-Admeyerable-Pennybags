public class GreenProperty extends NormalProperty{
   
    //default constructor
    public GreenProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public GreenProperty(String name, String initials, String coordinate, boolean expensive){
    	super(name, initials, coordinate, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = {26, 130, 390, 900, 1100, 1275}; //pacific place | north carolina ave
	_rent2 = {28, 150, 450, 1000, 1200, 1400}; //penn ave
	    
	// set buy prices
    	_buyPrice1 = 300; 
    	_buyPrice2 = 320; 
	    
	//set house cost
    	_houseCost = 200;
    }
}
