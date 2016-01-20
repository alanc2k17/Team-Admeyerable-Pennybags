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
  public void execute(int n) {
    if (n < 0 || n > 9)
      System.out.println("Oops! Something went wrong, we have a team of experienced monkeys on the case!");
    else {
      if (n = 0) {
        //bank error in your favor 
      }
      if (n = 1) {
        //do something
      }
      if (n = 2) {
        //do something
      }
      if (n = 3) {
        //do something
      }
      if (n = 4) {
        //something
      }
      if (n = 5) {
        //something
      }
      if (n = 6) {
        //something
      }
      if (n = 7) {
        //something
      }
      if (n = 8) {
        //something
      }
      if (n = 9) {
        //something
      }
    }
  }
