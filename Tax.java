public class Tax {
    protected int _payAmount;
    
    // default constructor
    public Tax() {
	_payAmount = 100;
    }
  
    // overrided constructor
    // sets _payAmount to parameter value
    public Tax(int payAmount) {
	_payAmount = payAmount;
    }
  
    // must be implemented
    public String toString() {

	String line1 = "------\n";
	String line2 = "| TAX|\n";
	String line3 = "|$" + _payAmount + "|\n";

	
	//line four holds symbols of all players on property
	String line4 = "|";
	//print out all Players on this square onto line4
	for (int i = 0; i < _playersOnSquare.size(); i++){ 
	    line4 += _playersOnSquare.get(i);
	}
	line4 += "|\n";
		
	String line5 = "------";

	return line1 + line2 + line3 + line4 + line5;
    }
  
    //accessor; returns tax amount
    public int getTax() {
	return _payAmount;
    }

} //end Tax
