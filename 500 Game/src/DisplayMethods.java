
public class DisplayMethods {
	public static void DispHand(Hand hand) {
			for (int j=0;j<hand.cards.size();j++){
				System.out.printf("(%d,%d)",hand.cards.get(j).suit,hand.cards.get(j).value);
			}
			System.out.println();
	}
	
	public static void DispHands(Hand[] hands) {
		System.out.println("\n\nNew Game:");
		for (int i=0; i<hands.length;i++){
			System.out.printf("%d - ",i); //Say which players' hand it is
			DispHand(hands[i]);
		}
	}

	public static void DispBid(Bid bid, int currentBidPlayer) {
		if (bid.suit==-1){
			System.out.printf("%d - Pass\n",currentBidPlayer);
		} else {
			System.out.printf("%d - (%d,%d)\n",currentBidPlayer,bid.suit,bid.value); //Show a bid
		}
	}

	public static void DispKitty(Hand hand) {
		System.out.printf("\nHand after Kitty - ");
		DispHand(hand);
	}
}
