public class GoToJail extends Landable {
    //default constructor
    public GoToJail() {
	super("Go To Jail", "GJ"); //call super's overloaded constr
    }
    
    //overloaded toString
    public String toString() {
	return "Looks like you got caught by the po-po! Peace out, dawg.";
    }

    public void processVictim(Player p, Landable[][] board) {
	p.setJail(true);
	p.setSquareOn( new int[] {10,0}, board );
    }
}
