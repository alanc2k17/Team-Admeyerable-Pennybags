public abstract class Property extends Landable {
    protected Player _owner;
    protected ArrayList<Player> _playersOnProperty;
    protected ArrayList<Property> _groupMembers;
    protected boolean _mortgage;
  
    public Property() {
	super();
	_owner = ;
    }
  
    public Property(String name, String initials, String coordiante) {
    }
  
    public abstract int getRent() {
    }
  
    public String toString() {
    }
  
    public int getBuyPrice() {
    }
  
    public Player getOwner() {
    }
  
    public void addMembers(Property p) {
    }  
  
    public int mortgage() {
    }
}
