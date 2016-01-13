public abstract class Landable {
  protected int _coordinate;
  protected String _name;
  protected String _initials;
  protected ArrayList<Player> _playerOnSquare
  
  public Landable() {
  }
  
  public Landable (String name, String initials, String coordinate) {
  }
  
  public abstract String toString() {
  }
  
  public int getCoordinate() {
  }
  
  public void setPlayerOnSquare(Player p) {
  }
  
  public void removePlayerOnSquare(Player p) {
  }
} //end class Landable
