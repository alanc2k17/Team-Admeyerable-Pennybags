public class Go extends Landable {
  //default constructor
  public Go() {
    super();
  }
  
  //overloaded constructor
  public Go(String name, String initials) {
    super(name, initials);
  }
  
  public String toString() {
    String returnString = "";
    returnString += "Landed on GO! Awarded $200.";
    return returnString;
  }
  
  
