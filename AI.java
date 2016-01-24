import java.util.ArrayList;

public class AI extends Player {

    //checks if player would have monopoly after
    //receiving property p
    //returns true if player will have monopoly
    //false otherwise
    public boolean checkMonopoly(Property p) {
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
    //returns 2 if trade is not accepted
    public int receiveTrade(Player p, ArrayList<Property> want, ArrayList<Property> have) {
	int netWorthWant = 0;
	int netWorthHave = 0;
	boolean willGiveMonopoly = false;
	boolean willGetMonopoly = false;
	
	//determine net worth of properties
	for (Property pr : want) {
	    netWorthWant += pr.getBuyPrice();
	}
	for (Property pr : have) {
	    netWorthHave += pr.getBuyPrice();
	}
	//determine if giving properties will give monopoly to other player
	//implementation here
	
	//if giving ai property = monopoly for other player
	//    if other's property will result in monopoly for me
	//    if other's property given to me >= 2.5 value of my property to other
	//    else deny
	
	if (netWorthWant > netWorthHave)
	    return 1;
    }

    //bidding algorithm for aiplayer
    //returns bid if decided to bid
    //-1 otherwise
    public int autoAuction(Property p, int highestBid) {
	if (highestBid > getCash())
	    return -1;
	while (highestBid <= 1.25 * p.getBuyPrice()) 
	    return highestBid + 5;
	while (checkMonopoly(p) && highestBid >= .9 * getCash()) 
	    return highestBid + 5;
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
	if (checkMonopoly(p) && totalMortgageValue + getCash() > p.getBuyPrice()) {
	    while (getCash() < p.getBuyPrice()) {
		autoMortgage(p.getBuyPrice());
	    }
	    return 1;
	}
	return -1;
    }

    //mortgage algorithm for aiplayer
    //triggered when _cash < 0 or trying to buy property under certain conditions
    //chooses properties based on least valuable to most valuable
    public void autoMortgage(int targetCash) {
	Property leastValuable = _propertiesOwned.get(0);
	int cantMortgageCTR = 0;
	
	while (getCash() < targetCash) {
	    //determine least valuable property
	    for (Property pr : _propertiesOwned) {
		if (pr.canMortgage() == true && cantMortgageCTR != getPropertiesOwned().size() ) {
		    if (pr.getBuyPrice() < leastValuable.getBuyPrice()) {
			leastValuable = pr;
		    }
		}
		else
		    return;
	    mortgage(pr);
	    }
	}
    }
}//end class
