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
    	_rent1 = {26, 130, 390, 900, 1100, 1275}; //pacific place
    	_rent2 = {26, 130, 390, 900, 1100, 1275}; //north carolina ave
	    _rent3 = {28, 150, 450, 1000, 1200, 1400}; //penn ave
	    
	// set buy prices
    	_buyPrice1 = 300; 
    	_buyPrice2 = 300; 
	    _buyPrice3 = 320; 
	    
	//set house cost and mortgage value
    	_houseCost = 200;
    	_mortgageValue1 = (int) (.5 * _buyPrice1);
    	_mortgageValue2 = (int) (.5 * _buyPrice2);
    	_mortgageValue3 = (int) (.5 * _buyPrice3);

    }
}
