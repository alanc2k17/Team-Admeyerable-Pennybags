public class Jail extends Landable {
    //default constructor
    public Jail() {
	super();
    }
  
    //overloaded constructor
    public Jail(String name, String initials) {
	super(name, initials);
    }
    
    //overloaded toString
    public String toString() {
	String returnString = "";
	returnString += "Just visiting! Don't worry... YET.";
	return returnString;
    }
    
}
