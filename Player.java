import java.util.ArrayList;

public class Player {
  protected String _coordinate;
  protected String _name;
  protected ArrayList<Property>[] _propertiesOwned;
  protected int _cashOnHand;
  protected Property _propertyOn;
  
  public Player() {
    _coordinate = "0,0";
    _name = "nameless";
    _propertiesOwned = new ArrayList<Property>(28);
    _cashOnHand = 1500;
!!  _propertyOn = ???; 
  }
  
  public Player(String newName) {
    _name = newName;
  }
  
  public String toString() {
    String returnString = "";
    
    returnString += _name + "'s current coordinate: " + _coordinate;
    returnString += _name + " currently owns " + _propertiesOwned;
    returnString += _name + " currently has $" + _cashOnHand;
    
    return returnString;
  }
  
  public String getCoords() {
    return _coordinate;
  }
  
  public int getCash() {
    return _cashOnHand;
  }
  
  public ArrayList getPropertiesOwned() {
    return _propertiesOwned;
  }
  
  public Property getPropertyOn() {
    return _propertyOn;
  }
  
  public void setCash(int newCashValue) {
    _cash = newCashValue;
  }
  
  public int charge(int amt) {
    _cash -= amt;
    return _name + " now has $" + _cash;
  }
  
  public int give(int amt) {
    _cash += amt;
    return _name + " now has $" + _cash;
  }
  
  public String move() {
    int diceRoll = (int)(Math.random() * 6) + 1;
    diceRoll += (int)(Math.random() * 6) + 1;
    
    // many if statements
  }
  
  public int buildHouse(Property p, int amt) {
    if (p.buildHouses(amt) == true) 
      return p + " now has " + p._houses + " houses!";
    else
      return "Oh no! Something went wrong: do you have enough money? are there already 5 houses on the property?" 
              + p + " still has " + p._houses + " houses!";
  }
  
  public String buildHotel(Property p) {
  // ???
  }
  
  public mortgage(Property p) {
  // ???
  }
  
  //unsure how to implement pay(Property p)/move()/buildHotel(Property p)
