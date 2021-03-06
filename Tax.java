public class Tax extends Landable{
    protected int _payAmount;
    
    // default constructor
    public Tax() {
	super();
	_payAmount = 100;
    }
  
    // overrided constructor
    // sets _payAmount to parameter value
    public Tax(String name, int payAmount) {
	super(name, "TX");
	_payAmount = payAmount;
    }
  
    // must be implemented
    public String toString() {
	return _name + "amount: " + _payAmount;
    }
  
    //accessor; returns tax amount
    public int getRent() {
	return _payAmount;
    }

} //end Tax
