public class OrangeProperty extends NormalProperty{
   
    //default constructor
    public OrangeProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public OrangeProperty(String name, String initials, String coordinate, boolean expensive){
    	super(name, initials, coordinate, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = {14, 70, 200, 550, 700, 900}; //st. james place | tennessee ave
	_rent2 = {16, 80, 220, 600, 800, 1000}; //ny ave
	    
	// set buy prices
    	_buyPrice1 = 180; 
    	_buyPrice2 = 200; 
	    
	//set house cost
    	_houseCost = 100;
    }
}
