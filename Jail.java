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
	returnString += "Oh man! You are currently in the gallows, What should you do?";
	return returnString;
    }
    
}
