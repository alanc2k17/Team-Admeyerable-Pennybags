public class Community extends Landable {
  private int[] _deck = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
  
  // default constructor
  public Community() {
	  super("Community", "CM");
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
    
  //moves first element in _deck to the end of the array
  //effectively moving top card to bottom of deck
  public void moveToBottom() {
	  int tmp = a[0];
	  a[0] = a[9];
	  a[9] = tmp;
  }
  
  //*reads* card and does what it says
  public void execute(Player p, Landable[][] board) {
    if (n < 0 || n > 9)
      System.out.println("Oops! Something went wrong, we have a team of experienced monkeys on the case!");
    else {
      if (n = 0) {
        //bank error in your favor 
       	System.out.println("Bank error in your favor! (Collect $200)");
       	p.give(200);
      }
      if (n = 1) {
        //doctors pees
        System.out.println("Doctor's fees! (Pay $50)");
        p.charge(50);
      }
      if (n = 2) {
        //from sale of stock
        System.out.println("From sale of stock you get $50!");
        p.give(50);
      }
      if (n = 3) {
        //grand opera night
        System.out.println("Grand Opera Night! (Collect $50 from every player for opening night seats)");
        //should landable extend monopoly?
      }
      if (n = 4) {
        //holiday fund matures
        System.out.println("Holiday Fund matures! (Collect $100)");
        p.give(100);
      }
      if (n = 5) {
        //income tax refund
        System.out.println("Income Tax Refund! (Collect $20)");
        p.give(20);
      }
      if (n = 6) {
        //birthday!
        System.out.println("Feliz Cumpleanos! (Collect $10)");
        p.give(10);
      }
      if (n = 7) {
        //life insurance matures
        System.out.println("Life Insurance Matures! (Collect $100)");
        p.give(100);
      }
      if (n = 8) {
        //pay hospital fees
        System.out.println("Pay Hospital Fees! (Pay $100)");
        p.charge(100);
      }
      if (n = 9) {
        //school tax
        System.out.println("Time To Send The Kids To College! (Pay $150)");
        p.charge(150);
      }
    }
  }
