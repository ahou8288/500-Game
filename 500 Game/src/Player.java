import java.util.ArrayList;


public class Player {
	//id?
	
	public static Bid getBid(Hand hand, int[] points, ArrayList<Bid> prevBids) {
		Bid lastBid=prevBids.get(prevBids.size()-1);
		if (lastBid.value<=6){
			if (lastBid.suit<=0){
				return new Bid(0,6); //6 spades
			}
		}
		return new Bid(-1,0); //pass
	}

	public static Hand useKitty(Hand hands2, Hand hands, ArrayList<Bid> prevBids) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Card getCard(ArrayList<Bid> prevBids, Hand hand, ArrayList<Card> trickCards) {
		Card choice=hand.cards.get(0); //Pick an invalid card
		hand.cards.remove(0);
		return choice;
	}


}
