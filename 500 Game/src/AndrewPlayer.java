import java.util.ArrayList;


public class AndrewPlayer implements PlayerInt{
	//id?
	public Team team;
	public AndrewPlayer(){ //Constructor
		
	}
	
	
	//place the simplest bid
	public Bid getBid(Hand hand, Team[] teams, ArrayList<Bid> prevBids) {
		/*LOGIC;
		Bid 6 of what you have the most of.
		if there is a tie then use the higher numbered suit. (for points)
		*/
		
		//Find which suit you have the most of.
		int[] suitCount = new int[4];
		for (int i=0;i<10;i++){
			suitCount[hand.cards.get(i).suit]++;
		}
		int max=0;
		for (int i=1;i<4;i++){
			if (suitCount[i]>suitCount[max]){
				max=i;
			}
		}
		//Pass if bad cards
		Bid desiredBid;
		if (suitCount[max]>4){
			desiredBid=new Bid(max,6);
		} else {
			desiredBid=new Bid(max,0);
		}
		
		Bid lastNonPass=getUsefulBid(prevBids);
		if (desiredBid.compareTo(lastNonPass)==-1) {
			return desiredBid;
		}
		else {
			return new Bid(-1,0); //pass
		}
	}
	
	private Bid getUsefulBid(ArrayList<Bid> prevBids) {
		for (int i=1;i<4;i++){
			if (prevBids.get(prevBids.size()-i).suit>-1){ //Sort out prevBids.size() //TODO what if first bid, what if prevBids.size()-1<0
				return prevBids.get(prevBids.size()-i);
			}
		}
		return null;
	}


	public Hand useKitty(Hand kitty, Hand myHand, ArrayList<Bid> prevBids) {
		/*LOGIC
		Remove 3 non trumps (lowest cards ideally)
		If no non trumps exist remove the lowest cards
		//TODO short suit
		*/
		myHand.cards.addAll(kitty.cards);
		Bid bidSelected=prevBids.get(prevBids.size()-4);
		int currentSuit=bidSelected.suit;
		myHand.sort();
		if (currentSuit==0){ //Remove a few cards that are not trumps.
			myHand.cards.remove(0);
			myHand.cards.remove(1);
			myHand.cards.remove(2);
		}else{
			myHand.cards.remove(12);
			myHand.cards.remove(11);
			myHand.cards.remove(10);
		}
		return myHand;
	}

	
	//AMMENDED
	public Card getCard(ArrayList<Bid> prevBids, Hand hand, ArrayList<Card> trickCards) {
		//Logic, get suit, play that suit else play any card.
		//Card choice=hand.cards.get(0); //Pick an invalid card
		//hand.cards.remove(0);
		Bid bidSelected=prevBids.get(prevBids.size()-4);
		int location = selectValidCardLocation(hand, trickCards,bidSelected);
		Card choice = hand.cards.get(location);
		hand.cards.remove(location);
		return choice;
	}
	

	//select a valid card from the hand to play
	public static int selectValidCardLocation(Hand hand, ArrayList<Card> trickCards,Bid trumps) {
		
		//play the trump you can
		if (trickCards.size()!=0){ //Don't lead trumps
			for (int i=hand.cards.size()-1; i>=0; i--) { //Trump with low cards
				if (hand.cards.get(i).suit==trumps.suit){
					if (isValidPlay(hand, trickCards, hand.cards.get(i))) {
						return i;
					}
				}
			}
		}
		//if you can't play a trump play something else
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
