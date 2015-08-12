import java.util.ArrayList;


public class DisplayMethods {
	public static void DispHand(Hand hand) {
			for (int j=0;j<hand.cards.size();j++){
				System.out.printf("(%d,%d)",hand.cards.get(j).suit,hand.cards.get(j).value);
			}
	}
	
	public static String HandToString(Hand hand) {
		String returnVal="";
		for (int j=0;j<hand.cards.size();j++){
			returnVal=returnVal.concat(String.format("(%d,%d)",hand.cards.get(j).suit,hand.cards.get(j).value));
		}
		return returnVal;
	}
	
	public static void DispHands(Hand[] hands) {
		System.out.println("-------------------New Round:------------------------------------------------------------------------");
		for (int i=0; i<hands.length;i++){
			System.out.printf("%d - ",i); //Say which players' hand it is
			DispHand(hands[i]);
			System.out.println();
		}
	}

	public static void DispBid(Bid bid, int currentBidPlayer) {
		if (bid.suit==-1){
			System.out.printf("%d - Pass\n",currentBidPlayer);
		} else {
			System.out.printf("%d - (%d,%d)\n",currentBidPlayer,bid.suit,bid.value); //Show a bid
		}
	}

	public static void DispAfterKitty(Hand hand) {
		System.out.printf("Hand after Kitty - ");
		DispHand(hand);
		System.out.println();
	}

	public static void DispTrick(ArrayList<Card> trickCards, int winner,int startPlayerIndex) {
		if (winner%2==0){
			System.out.printf("X ");
		} else {
			System.out.printf("O ");
		}
		
		//Put trickCards into a hand
		Hand displayHand = new Hand();//Easier to display as a hand
		for (int i=0;i<4;i++){
			displayHand.cards.add(trickCards.get((i-startPlayerIndex+4)%4));
		}
		//displayHand.cards.addAll(trickCards);
		System.out.printf("The cards this trick were");
		DispHand(displayHand);
		System.out.printf(". The winner was %d.",winner+1); // This does not start at 0 for the display
		System.out.printf(" %d lead with (%d,%d).\n",startPlayerIndex+1,trickCards.get(0).suit,trickCards.get(0).value);
	}

	public static void DispTrickCount(Team[] teams, int bidWinnerTeam, Bid bidSelected) {
		System.out.printf("Team %d has made %d tricks. They needed to win %d tricks.\n",bidWinnerTeam,teams[bidWinnerTeam].tricksWon,bidSelected.value);
	}

	public static void DispTeamPoints(Team team0, Team team1) {
		// TODO Auto-generated method stub
		System.out.printf("Team 0 has %d points.\nTeam 1 has %d points.\n",team0.points,team1.points);
	}
}

/*TODO;
 *Add player naming capability (Randomly choose from default computer player name list?) 
 *Add automatic Team names (players are not shown as numbers)
 *Create a display which shows partners on opposite sides of a square, and shows the tricks in the middle
 */