import java.util.ArrayList;
import java.util.Collections;
public class Game {

	public static void main(String[] args) {
		
		int[] points = {0,0};//Game has just begun, nobody has any points
		Player[] players = new Player[4];//Create the players
		
		while(gameNotOver(points)){
			
			Hand[] hands = generateHands(); //hands[4] contains the kitty
			
			DisplayMethods.DispHands(hands);
			
			//Run the bidding
			ArrayList<Bid> prevBids=new ArrayList<Bid>();
			int currentBidPlayer=0; //Begin with the first player
			
			while(!isBiddingOver(prevBids)){
					prevBids.add(Player.getBid(hands[currentBidPlayer],points,prevBids));
					DisplayMethods.DispBid(prevBids.get(prevBids.size()-1),currentBidPlayer);
					currentBidPlayer=(currentBidPlayer+1)%4;
			}
			
			//TODO deal with all pass
			
			
			//Give the player the kitty
			hands[currentBidPlayer]=Player.useKitty(hands[4],hands[currentBidPlayer],prevBids);
			int leadPlayer=currentBidPlayer;
			DisplayMethods.DispKitty(hands[currentBidPlayer]);
			
			//ResetTricks(players); //Set the tricksWon for each team to 0;
			//Start playing cards/ doing tricks
			for (int i=0;i<10;i++){
				ArrayList<Card> trickCards=new ArrayList<Card>();
				for (int j=0; j<4;j++){
					int currentPlayer=(leadPlayer+j)%4; //Player that needs to play a card
					trickCards.add(Player.getCard(prevBids,hands[currentPlayer],trickCards));
				}
				int winner=trickWinner.findVictor(trickCards,prevBids.get(prevBids.size()-3));
				leadPlayer=(leadPlayer+winner)%4; //Figure out who leads the next trick
				//victor.team.tricksWon++;
				//TODO sort out the tricks here
			}
			//TODO sort out the tricks here
			//AllocatePoints(players[currentBidPlayer].team);
		}
	}
	
	private static Hand[] generateHands() {
		ArrayList<Card> deck=new ArrayList<Card>();
		for (int suit =0;suit<4;suit++){
			for(int value=1;value<12;value++){
				if (!(suit>=3&&value==11)){ //NOTE: CHANGE SUIT TO >=2. NO JOKER CURRENTLY //TODO
					deck.add(new Card(suit,value));				}
			}
		}
		//Shuffle the deck
		Collections.shuffle(deck); //Java finally did something right!
		//Add cards to each hand
		Hand[] hands=new Hand[5];
		for (int i=0;i<4;i++){
			hands[i]=new Hand();
			for (int j=0;j<10;j++){
				Card newCard = deck.get(i*10+j);
				hands[i].cards.add(newCard);
			}
		}
		hands[4]=new Hand();
		//Add the kitty
		hands[4].cards.add(deck.get(40));
		hands[4].cards.add(deck.get(41));
		hands[4].cards.add(deck.get(42));
		
		return hands;
	}

	private static boolean gameNotOver(int[] points) {
		if(points[1]>=500||points[0]>=500){
			System.out.println("Game Over, player reached 500.");
			return false;
		}else if(points[1]<=-500||points[0]<=-500){
			System.out.println("Game Over, player reached -500.");
			return false;
		} else {
			return true;
		}
	}

	private static boolean isBiddingOver(ArrayList<Bid> prevBids) {
		int len=prevBids.size();
		if (len<4){
			return false;
		} else if (prevBids.get(len-1).suit==-1&&
				prevBids.get(len-2).suit==-1&&
				prevBids.get(len-3).suit==-1){
			return true;
		}
		return false;
	}

}
