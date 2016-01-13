import java.util.ArrayList;

public abstract class Landable {
  protected int _coordinate;
  protected String _name;
  protected String _initials;
  protected ArrayList<Player> _playerOnSquare
  
  public Landable() {
    _coordinate = 0;
    _name = "board square";
    _initials = "xD";
    _playerOnSquare = new ArrayList<Player>();
  }
  
  public Landable (String name, String initials, int coordinate) {
    this();
    _coordinate = coordinate;
    _name = name;
    _initials = initials;
  }
  
  public abstract String toString() {
  }
  
  public int getCoordinate() {
    return _coordinate;
  }
  
  public void setPlayerOnSquare(Player p) {
    _playerOnSquare.add(p);
  }
  
  public void removePlayerOnSquare(Player p) {
    _playerOnSquare.remove(p);
  }
} //end class Landable
