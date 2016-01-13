public class BrownProperty extends NormalProperty{
   
    // pre-set rent prices, index corresponds w/ # of houses
    protected final int[] RENT1 = {2, 10, 30, 90, 160, 250}; //mediterranean
    protected final int[] RENT2 = {4, 20, 60, 180, 320, 450}; //baltic

    // pre-set buy prices
    protected final int BUYPRICE1 = 60; //mediterranean
    protected final int BUYPRICE2 = 60; //baltic
    
    //pre-set house cost and mortgage value
    protected final int HOUSECOST = 50;
    protected final int MORTGAGEVALUE1 = 30;
    protected final int MORTGAGEVALUE2 = 30;

    //default constructor
    public BrownProperty(){
	this();	
    }

    //overloaded constructor
    public BrownProperty(String name, String initials, String coordinate, boolean expensive){
	this(name, initials, coordinate, expensive);
    }
}

    