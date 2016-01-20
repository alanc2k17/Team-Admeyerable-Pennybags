public class Chance extends Landable {
    private int[] _deck = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    
    // default constructor
    public Chance() {
	super();
    }
    
    //overloaded constructor
    public Chance(String name, String initials) {
	super(name, initials);
    }
    
  //randomly rearrange elements of an array (taken [and modified] from InsertionSort.java -- Thanks Mr. Brown!)
    public static void shuffle( int[] a ) {
	int randomIndex;
	for( int i = a.size-1; i > 0; i-- ) {
	    //pick an index at random
	    randomIndex = (int)( (i+1) * Math.random() );
	    //swap the values at position i and randomIndex
	    int tmp = a[i];
	    a[i] = a[randomIndex];
	    a[randomIndex] = tmp; 
	}
    }
    
    //moves first element in _deck to the end of the array
    //effectively moving top card to bottom of deck
  public void moveToBottom() {
      int tmp = a[i];
      a[i] = a[9];
      a[9] = tmp;
  }
    
    //*reads* card and does what it says
    public void execute(int n) {
	if (n < 0 || n > 9)
	    System.out.println("Oops! Something went wrong, we have a team of experienced monkeys on the case!");
	else {
	    if (n == 0) {
        //advance to go
		System.out.println("Advance to Go! (Collect $200)");
		p.give(200);
		p.setSquareOn(go, go); //???? is this a real thing
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
		p.setSquareOn(jail, jail); //???? again, 
	    }
	    if (n == 3) {
		//take a walk on the boardwalk
		System.out.println("Take a walk on the Boardwalk! (Advance token to Boardwalk)");
		p.setSquareOn(boardwalk, boardwalk);
	    }
	    if (n == 4) {
		//you have been elected chairman of the board
		System.out.println("You have been elected Chairman of the Board! (Pay each player $50)");
		int ctr = 0
		    for (Player pl : playerList) {
			pl.give(50);
			ctr++;
		    }
		p.charge(50 * ctr);
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
		System.out.println("Make general repairs on all your property! (For each house pay $25, each hotel $100");
		//need help
	    }
	    if (n == 9) {
		//advance to illinois ave
        System.out.println("Advance to Illinois Ave! (If you pass Go, collect $200");
        if (p._coordinate[0] > 0 && p._coordinate[1] > 4)
	    p.pay(200);
        p.setSquareOn(go, go);
	    }
	}
    }
    