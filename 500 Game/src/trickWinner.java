import java.util.ArrayList;

public class trickWinner {

	public static int findVictor(ArrayList<Card> trickCards, Bid bid) {
		
		//if any trumps are played, highest trump wins
		if (trumpsPlayed(trickCards, bid)) {	
			int suitPlayed = bid.suit;
			return findHighestInSuit(trickCards, suitPlayed); //retrieve location of winning trick from function
		} 
		
		else { 		//if no trumps were played, highest card wins
			int suitPlayed = trickCards.get(0).suit;
			return findHighestInSuit(trickCards, suitPlayed);
		}
		
	}
	
	
	
	//test if any trumps were played in the trick
	//assume 4 cards in a trick
	public static boolean trumpsPlayed(ArrayList<Card> trickCards, Bid bid) {
		int cardsPerTrick = 4;
		int noTrumpsValue = 5;
		
		
		int trumps = bid.suit;
		
		//if we're playing notrumps just always play as if no trumps were played
		if (trumps == noTrumpsValue){
			return false;
		}
		
		//if one of the cards is trumps, then return true
		for (int i=0; i<cardsPerTrick; i++) { 
			int cardSuit = trickCards.get(i).suit;
			if (cardSuit == trumps) {
				return true;
			}
		}
		
		//else return false
		return false;
	}
	
	
	
	//find highest card in a specific suit, given the suit
	public static int findHighestInSuit(ArrayList<Card> trickCards, int suit) {
		
		//default winner is the player who led
		int max = -1;
		int winner = 0;
		
		//go through each card in the trick
		for (int i=0; i<4; i++) {

			int cardSuit = trickCards.get(i).suit;
			int cardValue = trickCards.get(i).value;

			
			//if the card is of the right suit and bigger than all the rest
			if (cardSuit == suit && cardValue >= max) {
				max = cardValue;
				winner = i;
			}
		}
		
		return winner;
	}
	
}