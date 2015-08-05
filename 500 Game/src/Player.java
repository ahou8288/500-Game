import java.util.ArrayList;


public class Player {
	//id?
	public Team team;
	
	public static Bid getBid(Hand hand, int[] points, ArrayList<Bid> prevBids) {
		Bid lastBid=prevBids.get(prevBids.size()-1);
		if (lastBid.value<=6){
			if (lastBid.suit<=0){
				return new Bid(0,6); //6 spades
			}
		}
		return new Bid(-1,0); //pass
	}

	public static Hand useKitty(Hand kitty, Hand hands, ArrayList<Bid> prevBids) {
		//Don't use the kitty
		return hands;
	}

	public static Card getCard(ArrayList<Bid> prevBids, Hand hand, ArrayList<Card> trickCards) {
		//Logic, get suit, play that suit else play any card.
		Card choice=hand.cards.get(0); //Pick an invalid card
		hand.cards.remove(0);
		return choice;
	}


}
