public class Go extends Landable {
    //default constructor
    public Go() {
	super("Go", "GO"); //call super's overloaded constr.
    }
    
    public String toString() {
	String returnString = "";
	returnString += "Landed on GO! Awarded $200.";
	return returnString;
    }
}  

