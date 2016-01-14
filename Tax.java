public class Tax extends Landable{
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
	return "";
    }
  
    //accessor; returns tax amount
    public int getTax() {
	return _payAmount;
    }

} //end Tax
