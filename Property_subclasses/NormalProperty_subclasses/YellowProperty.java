public class YellowProperty extends NormalProperty{
   
    //default constructor
    public YellowProperty(){
    	super();
    	setInfo();
    }

    //overloaded constructor
    public YellowProperty(String name, String initials, String coordinate, boolean expensive){
    	super(name, initials, coordinate, expensive);
    	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
    	_rent1 = {22, 110, 330, 800, 975, 1150}; //atlantic ave
    	_rent2 = {22, 110, 330, 800, 975, 1150}; //ventor ave
	    _rent3 = {24, 120, 360, 850, 1025, 1200}; //marvin gardens
	    
	// set buy prices
    	_buyPrice1 = 260; 
    	_buyPrice2 = 260; 
	    _buyPrice3 = 280; 
	    
	//set house cost and mortgage value
    	_houseCost = 150;
    	_mortgageValue1 = (int) (.5 * _buyPrice1);
    	_mortgageValue2 = (int) (.5 * _buyPrice2);
    	_mortgageValue3 = (int) (.5 * _buyPrice3);

    }
}
