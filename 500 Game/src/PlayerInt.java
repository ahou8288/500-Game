import java.util.ArrayList;

public interface PlayerInt {
	public Bid getBid(Hand hand, Team[] teams, ArrayList<Bid> prevBids);
	public Hand useKitty(Hand kitty, Hand hands, ArrayList<Bid> prevBids);
	public Card getCard(ArrayList<Bid> prevBids, Hand hand, ArrayList<Card> trickCards);
}