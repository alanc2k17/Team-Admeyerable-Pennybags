import java.util.ArrayList;

public abstract class Landable {
    protected String _name;
    protected String _initials;
    // last Player in list will be player most recently landed
    protected ArrayList<Player> _playersOnSquare;
  
    // default constructor; written even though class is abstract so it can be inherited
    public Landable() {
	_name = "board square";
	_initials = "xD";
	_playersOnSquare = new ArrayList<Player>();
    }
  
    // overloaded constructor; written even though class is abstract so it can be inherited
    public Landable (String name, String initials) {
	this(); //call default constr. to instanitate _playersOnSquare
	_name = name;
	_initials = initials;
    }
  
    // different subclasses will display themselves differently
    public abstract String toString();

    // accessor method to return name
    public String getName(){
	return _name;
    }
    
    // accessor method to return initials
    public String getInitials(){
	return _initials;
    }

    // accessor to get list of players on square
    public ArrayList<Player> getPlayersOn(){
	return _playersOnSquare;
    }

    // called after a Player lands on this square
    public void setPlayerOnSquare(Player p) {
	_playersOnSquare.add(p);
    }
  
    // called after a Player moves after it is on this square
    public void removePlayerOnSquare(Player p) {
	_playersOnSquare.remove(p);
    }

} //end class Landable
