import java.util.ArrayList;

public abstract class Landable {
    protected int _coordinate;
    protected String _name;
    protected String _initials;
    // last Player in list will be player most recently landed
    protected ArrayList<Player> _playersOnSquare;
  
    // default constructor; written even though class is abstract so it can be inherited
    public Landable() {
	_coordinate = 0;
	_name = "board square";
	_initials = "xD";
	_playersOnSquare = new ArrayList<Player>();
    }
  
    // overloaded constructor; written even though class is abstract so it can be inherited
    public Landable (String name, String initials, int coordinate) {
	this(); //call default constr. to instanitate _playersOnSquare
	_coordinate = coordinate;
	_name = name;
	_initials = initials;
    }
  
    // different subclasses will display themselves differently
    public abstract String toString();
  
    public int getCoordinate() {
	return _coordinate;
    }
    
    // called after a Player lands on this square
    public void setPlayerOnSquare(Player p) {
	_playerOnSquare.add(p);
    }
  
    // called after a Player moves after it is on this square
    public void removePlayerOnSquare(Player p) {
	_playerOnSquare.remove(p);
    }

} //end class Landable
