import java.util.ArrayList;


public class DumbPlayer implements PlayerInt{
	//id?
	public Team team;
	public DumbPlayer(){ //Constructor
		
	}
	
	
	//place the simplest bid
	public Bid getBid(Hand hand, Team[] teams, ArrayList<Bid> prevBids) {
		//if no one else has bid
		if (prevBids.size() == 0) {
			return new Bid(0,6); //bid 6 spades
		}
		else { //if anyone else has already bid - they will have bid 6 spades
			return new Bid(-1,0); //pass
		}
	}

	
	
	public Hand useKitty(Hand kitty, Hand hands, ArrayList<Bid> prevBids) {		
		//Don't use the kitty
		return hands;
	}

	
	//AMMENDED
	public Card getCard(ArrayList<Bid> prevBids, Hand hand, ArrayList<Card> trickCards) {
		//Logic, get suit, play that suit else play any card.
		//Card choice=hand.cards.get(0); //Pick an invalid card
		//hand.cards.remove(0);
		
		int location = selectValidCardLocation(hand, trickCards);
		Card choice = hand.cards.get(location);
		hand.cards.remove(location);
		return choice;
	}
	

	//select a valid card from the hand to play
	public static int selectValidCardLocation(Hand hand, ArrayList<Card> trickCards) {
		
		//play the first card in your hand that is valid
		for (int i=0; i<hand.cards.size(); i++) {
			if (isValidPlay(hand, trickCards, hand.cards.get(i))) {
				return i;
			}
		}
		
		//it should never get to here, there should always be a valid card in your hand
		//just in case
		return 0;
	}
	
	
	
	//determine whether a card play is valid
	public static boolean isValidPlay(Hand hand, ArrayList<Card> trickCards, Card play) {
		
		//if it was the first play of the trick it is valid
		if (trickCards.size() == 0) {
			return true;
		}
		
		//determine whether any the hand has any of the led suit
		int ledSuit = trickCards.get(0).suit;
		boolean hasLed = false;
		for (int i=0; i<hand.cards.size(); i++) {
			if (hand.cards.get(i).suit == ledSuit) {
				hasLed = true;
			}
		}
		
		//if they have the led suit it must be played, else anything goes
		//TODO joker and bowers case
		if (hasLed) {
			if (play.suit == ledSuit){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
	}


}
