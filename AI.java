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
    
    //checks if player would have monopoly after
    //receiving property p
    //returns true if player will have monopoly
    //false otherwise
    public boolean checkMonopoly(NormalProperty p) {
	Player previousOwner = p.getOwner();
	
	p.setOwner(this);
	if (p.checkMonopoly() == true) {
	    p.setOwner(previousOwner);
	    return true;
	}
	p.setOwner(previousOwner);   
	return false;
    }
    
    //trade algorithm for ai player
    //returns 1 if trade is accepted
    //returns -1 if trade is not accepted
    public int receiveTrade(Player p, ArrayList<Property> want, ArrayList<Property> have) {
	int netWorthWant = 0;
	int netWorthHave = 0;
	boolean willGiveMonopoly = false;
	boolean willGetMonopoly = false;
	
	//determine net worth of recieving properties/will get monopoly
	for (Property pr : want) {
	    if (checkMonopoly((NormalProperty)pr))
		willGetMonopoly = true;
	    netWorthWant += pr.getBuyPrice();
	}
	//determine net worth of giving properties/will give monopoly
	for (Property pr : have) {
	    if (checkMonopoly((NormalProperty)pr))
		willGiveMonopoly = true;
	    netWorthHave += pr.getBuyPrice();	    
	}

	//algorithm to decide
	//if giving ai property = monopoly for other player
	//    if other's property will result in monopoly for me
	//    if other's property given to me >= 2.5 value of my property to other
	//    else deny	
	if (willGiveMonopoly) {
	    if (willGetMonopoly)
		return 1;
	    else if (netWorthWant > 2.5 * netWorthHave)
		return 1;
	    return -1;
	}
	else {
	    if (netWorthWant > netWorthHave)
		return 1;
	}
	return -1;
    }

    //bidding algorithm for aiplayer
    //returns bid if decided to bid
    //-1 otherwise
    public int autoAuction(NormalProperty p, int highestBid) {
	if (highestBid > getCash())
	    return -1;
	while (highestBid <= 1.25 * p.getBuyPrice()) 
	    return highestBid + 5;
	while (checkMonopoly(p) && highestBid >= .9 * getCash()) 
	    return highestBid + 5;
	return -1;
    }

    //bidding algorithm for aiplayer
    //returns bid if decided to bid
    //-1 otherwise
    public int autoAuction(Property p, int highestBid) {
	if (highestBid > getCash())
	    return -1;
	while (highestBid <= 1.25 * p.getBuyPrice()) 
	    return highestBid + 5;
	if (p instanceof NormalProperty) {
	    while (checkMonopoly((NormalProperty)p) && highestBid >= .9 * getCash()) 
		return highestBid + 5;
	}
	return -1;
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
	if (getCash() > p.getBuyPrice())
	    return 1;
	if (p instanceof NormalProperty) {
	    if (checkMonopoly((NormalProperty)p) && totalMortgageValue + getCash() > p.getBuyPrice()) {
		while (getCash() < p.getBuyPrice()) {
		    autoMortgage(p.getBuyPrice());
		}
		return 1;
	    }
	}
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
	return _bankrupt;
    }

    // executes a turn of this player if he is in jail
    public void botJailTurn() {
	//check if player can afford bail
	if (getCash() >= 50) {
	    _jailTurns = 0;
	    _inJail = false;
	    charge(50);
	}
	else // less than $50
	    autoMortgage(50);
    }

    //overridden property action
    public void propertyAction(Property square, Monopoly game){
	if (square.getOwner() == null && square.getOwner() != this) { //unowned
	    if (getCash() > square.getBuyPrice())
		buy(square);
	    else {
		autoMortgage(square.getBuyPrice());
		if (getCash() < square.getBuyPrice())
		    game.auction(square);
	    }
	}
	else if ( square.getOwner() != this ) // property has an owner but not this player
	    pay( square ); // pay rent
	}
}//end class
