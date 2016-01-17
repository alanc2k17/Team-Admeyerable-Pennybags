import java.util.ArrayList;

public abstract class Property extends Landable {
    protected Player _owner;
    protected ArrayList<Player> _playersOnProperty;
    // _groupMembers used to determine monopoly or rent
    protected ArrayList<Property> _groupMembers;
    protected boolean _mortgage;
  
    // default constructor
    public Property() {
	super(); //set default vals for name, initials, coord
	_playersOnProperty = new ArrayList<Player>();
	_groupMembers = new ArrayList<Property>();
	_mortgage = false;
    }
  
    // overloaded constructor
    public Property(String name, String initials) {
	super(name, initials); //use super constructor to set name, inits, coord
	_playersOnProperty = new ArrayList<Player>();
	_groupMembers = new ArrayList<Property>();
	_mortgage = false;
    }
  
    // not all properties have same rent determination process
    public abstract int getRent();

    // abstract b/c in buyPrice is defined differently in its three subclasses
    public abstract int mortgage();

    // abstract b/c in one subclass, it needs to print houses
    // in another, it needs not
    public abstract String toString();
  
    // accessor method to return 
    public Player getOwner() {
	return _owner;
    }

    // mutator method to set owner
    public void setOwner(Player p){
	_owner = p;
    }

    // accessor method to return whether or not Property is mortgaged
    // returns true if mortgaged; false if not
    public boolean isMortgaged(){
	return _mortgage;
    }

    // takes one Property parameter, p, and adds it to _groupMembers
    // _groupMembers used to determine monopoly or rent
    public void addMembers(Property p) {
	_groupMembers.add(p); 
    }
  
}
