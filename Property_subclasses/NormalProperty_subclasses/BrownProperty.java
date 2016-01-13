public class BrownProperty extends NormalProperty{
   
    //default constructor
    public BrownProperty(){
	super();
	setInfo();
    }

    //overloaded constructor
    public BrownProperty(String name, String initials, String coordinate, boolean expensive){
	super(name, initials, coordinate, expensive);
	setInfo();
    }

    //set rent prices, buy prices, house costs, and mortgage values
    public void setInfo(){
	// set rent prices, index corresponds w/ # of houses
	_rent1 = {6, 30, 90, 270, 400, 550}; //mediterranean
	_rent2 = {8, 40, 100, 300, 450, 600}; //baltic
	
	// set buy prices
	_buyPrice1 = 100; 
	_buyPrice2 = 120; 
	
	//set house cost and mortgage value
	_houseCost = 50;
	_mortgageValue1 = (int) (.5 * _buyPrice1);
	_mortgageValue2 = (int) (.5 * _buyPrice2);

    }
}

    