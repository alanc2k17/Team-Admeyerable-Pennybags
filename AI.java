import cs1.Keyboard;
import java.util.ArrayList;

public class AI extends Player {
    boolean _bankrupt = false;
    
    //default constructor
    public AI() {
	super();
    }

    //overloaded constructor
    public AI(String name, String symbol) {
	super(name, symbol);
	_name = name;
	_symbol = symbol;
    }

    //buying algorithm for aiplayer
    //returns 1 if decided to buy
    //returns -1 if decided not to buy
    public int autoBuy(Property p) {
	int totalMortgageValue = 0;
	//determines totalMortgageValue
	for (Property pr : _propertiesOwned) 
	    totalMortgageValue += pr.getMortgageValue();

	//determine if bot would like to buy

	if ( buy(p) ){ // buys property at full price, if successfully bought...
	    System.out.println("Property bought!");
	    return 1;
	}
	else if (p instanceof NormalProperty) {
	    // if has potential to buy with mortgages...
	    if (checkMonopoly((NormalProperty)p) && (totalMortgageValue + getCash()) > p.getBuyPrice()) {
		while (getCash() < p.getBuyPrice()) {
		    autoMortgage(p.getBuyPrice()); //mortgage until you can buy
		}
		buy(p);
		System.out.println("Property bought!");
		return 1;
	    }
	}
	System.out.println( getName() + " did not buy this property. Going into auction.");
	return -1;
    }
    
    // executes a player turn
    // params: Landable[][] board represents the Monopoly board
    //         Monopoly game represents the Monopoly object being played
    //              needed for executeLandOn()
    // returns true if Player is alive after turn
    // returns false if Player is bankrupt
    public boolean botTurn(Landable[][] board, Monopoly game) {
    	if (_inJail)
    	    botJailTurn();   	
    	else { //if not in jail
	    move(board);
	    executeLandOn(board, game); // determine course of action based on what player landed on
	}
	if (getCash() < 0 && canMortgage() == false)
	    _bankrupt = true;
	System.out.println("Type any key then enter to end AI's turn");
	Keyboard.readString();
	return ! _bankrupt;
    }

    // executes a turn of this player if he is in jail
    public void botJailTurn() {
	//check if player can afford bail
	if (getCash() >= 50) {
	    if (_jailTurns >= 3) {
		_jailTurns = 0;
		_inJail = false;
		charge(50);
	    }
	    else
		_jailTurns += 1;//add one to jailTurns
	}
	else { // less than $50
	    if (_jailTurns >= 3 ){
	        autoMortgage(50); //mortage until at least $50 is achieved
		charge(50);
	    }
	    else
		_jailTurns += 1;
	}
    }

    //overridden property action
    public void propertyAction(Property square, Monopoly game){
	if (square.getOwner() == null && square.getOwner() != this) { //unowned
	    if ( autoBuy(square) == -1 ) //AI did not buy
		game.auction(square); // auction property
	}
	else if ( square.getOwner() != this ) // property has an owner but not this player
	    pay( square ); // pay rent
	}
}//end class


