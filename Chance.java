import java.util.ArrayList;

public class Chance extends Landable {
    private static int[] _deck = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    // default constructor
    public Chance() {
	super("Chance", "CH");
	shuffle();
    }
    
    //randomly rearrange elements of an array (taken [and modified] from InsertionSort.java -- Thanks Mr. Brown!)
    public void shuffle() {
	int randomIndex;
	for( int i = _deck.length-1; i > 0; i-- ) {
	    //pick an index at random
	    randomIndex = (int)( (i+1) * Math.random() );
	    //swap the values at position i and randomIndex
	    int tmp = _deck[i];
	    _deck[i] = _deck[randomIndex];
	    _deck[randomIndex] = tmp; 
	}
    }
    
    public String toString(){
	return "You landed on Chance!";
    }

    //moves first element in _deck to the end of the array
    //effectively moving top card to bottom of deck
    public void moveToBottom() {
	int tmp = _deck[0];
	for ( int i = 1; i < _deck.length; i ++ ){
	    _deck[i-1] = _deck[i];
	}
	_deck[_deck.length-1] = tmp;
    }
    
    //*reads* card and does what it says
    public void execute(Player p, Landable[][] board, ArrayList<Player> playerList) {
	int n = _deck[0];

	if (n < 0 || n > 9)
	    System.out.println("Oops! Something went wrong, we have a team of experienced monkeys on the case!");
	else {
	    if (n == 0) {
        //advance to go
		System.out.println("Advance to Go! (Collect $200)");
		p.setSquareOn(new int[] {10,10}, board);
	    }
	    if (n == 1) {
		//bank pays you dividend of $50
		System.out.println("Bank pays you dividend of $50");
		p.give(50);
	    }
	    if (n == 2) {
		//go to jail
		System.out.println("Go to Jail! :(");
		p.setJail(true);
		p.setSquareOn(new int[] {0,10}, board);
	    }
	    if (n == 3) {
		//take a walk on the boardwalk
		System.out.println("Take a walk on the Boardwalk! (Advance token to Boardwalk)");
		p.setSquareOn(new int[] {9,10}, board);
	    }
	    if (n == 4) {
		//you have been elected chairman of the board
		System.out.println("You have been elected Chairman of the Board! (Pay each player $50)");
		for (int i = 0; i < playerList.size(); i++){ // for each player
		    playerList.get(i).give(50); // give 50
		}
		p.charge(50 * playerList.size());
	    }
	    if (n == 5) {
		//your building and loan matures
		System.out.println("Your building {and} loan matures! (Collect $150)");
		p.give(150);
	    }
	    if (n == 6) {
		//pay poor tax
		System.out.println("Fined for doing 56 in a 55! (Fined $15) - Drive safe!");
		p.charge(15);
	    }
	    if (n == 7) {
		//win a crossword competition
		System.out.println("You have won a crossword competition! (Collect $100)");
		p.give(100);
	    }
	    if (n == 8) {
		//make general repairs on buildings
		System.out.println("Make general repairs on all your property! (For each house pay $25)");
		//need help
		int houseCount = 0;
		ArrayList<Property> properties = p.getPropertiesOwned();
		for (int i = 0; i < properties.size(); i++){ // for each owner property
		    if (properties.get(i) instanceof NormalProperty)
			houseCount += ( (NormalProperty) (properties.get(i)) ).getHouses();
		}
		
		p.charge( 25 * houseCount ); //charge 25 per house
	    }
	    if (n == 9) {
		//advance to illinois ave
		System.out.println("Advance to Illinois Ave! (If you pass Go, collect $200");
		if (p.getCoords()[0] >= 0 && p.getCoords()[1] > 4)
		    p.give(200);
		p.setSquareOn(new int[] {0,4}, board );
	    }

	    moveToBottom(); // move drawn card to bottom
	}
    }
}    