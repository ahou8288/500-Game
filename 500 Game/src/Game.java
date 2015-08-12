import java.util.ArrayList;
import java.util.Collections;
public class Game {

	public static void main(String[] args) {
		//Stores an instance of each of the players
		PlayerInt[] players = makePlayers();//Create the players
		
		//player 0 and 2 and part of team 0
		//player 1 and 3 are part of team 1
		Team team0 = new Team(players[0], players[2]);
		Team team1 = new Team(players[1], players[3]);
		Team[] teams = {team0, team1};
		
		while(gameNotOver(teams)){//Check if the points cause the game to end
			
			//Deal the cards
			Hand[] hands = generateHands(); //hands[4] contains the kitty
			
			//Show the programmer what is in everyones hand
			DisplayMethods.DispHands(hands);
			//GuiDisp.FormHands(hands); //This is a different display
			
			//Run the bidding
			System.out.println("Bidding;");
			ArrayList<Bid> prevBids=new ArrayList<Bid>(); //Create an Arraylist of all the bids ever
			int currentBidPlayer=0; //Begin with the first player
			
			
			while(!isBiddingOver(prevBids)){
					Bid tempBid=players[currentBidPlayer].getBid(hands[currentBidPlayer],teams,prevBids); //Collects the players bid
					prevBids.add(tempBid); //Stores the players bid
					DisplayMethods.DispBid(tempBid,currentBidPlayer); //Display the bid
					currentBidPlayer=(currentBidPlayer+1)%4; //Cycle around the players
			}
			
			if (AllPlayersPassed(prevBids)){
				System.out.println("All players passed. Ending game.");
				System.exit(0);
			}
			//Determine which player won the bidding
			int leadPlayer=currentBidPlayer; //The bidding when with a round of passes and returned to the person who won the bidding.
			
			int bidWinner=leadPlayer; //Stored for point scoring purposes
			int bidWinnerTeam=bidWinner%2;
			Bid bidSelected=prevBids.get(prevBids.size()-4);
			int selectedBidValue=bidSelected.pointValue();
			
			//Give the player the kitty
			hands[leadPlayer]=players[leadPlayer].useKitty(hands[4],hands[leadPlayer],prevBids); //Give the player the kitty to use
			DisplayMethods.DispAfterKitty(hands[leadPlayer]); //Display the new hand of after the kitty was used
			
			//TODO reset the number of tricks won at this point
			
			System.out.println("Tricks;");//Start playing cards/ doing tricks
			team0.resetTricks();
			team1.resetTricks();
			for (int i=0;i<10;i++){ //Run exactly 10 rounds
				
				ArrayList<Card> trickCards=new ArrayList<Card>(); //Create a new blank ArrayList to store the cards of this trick.
				for (int j=0; j<4;j++){ //Loop through each player
					int currentPlayer=(leadPlayer+j)%4; //Player that needs to play a card
					Card tempCard =players[currentPlayer].getCard(prevBids,hands[currentPlayer],trickCards);//Get a card from the player
					trickCards.add(tempCard); //Store the card
				}
				
				int winner=trickWinner.findVictor(trickCards,bidSelected); //Find the winner of the last trick
				winner=(winner+leadPlayer)%4; //convert to actual player index
				
				int winningTeam = winner%2;
				teams[winningTeam].addTrick();
				
				DisplayMethods.DispTrick(trickCards,winner,leadPlayer); //Show what happened in the past trick
				leadPlayer=(leadPlayer+winner)%4; //Figure out who leads the next trick
			}
			
			DisplayMethods.DispTrickCount(teams,bidWinnerTeam,bidSelected);
			
			int otherTeamIndex=(bidWinnerTeam+1)%2;
			if (bidSelected.value>teams[bidWinnerTeam].tricksWon){ //if the bid was not reached //TODO if 10 is reach round up to 250
				teams[bidWinnerTeam].points-=selectedBidValue;
			} else {
				teams[bidWinnerTeam].points+=selectedBidValue;
			}
			teams[otherTeamIndex].points+=10*teams[otherTeamIndex].tricksWon;
			
			DisplayMethods.DispTeamPoints(team0,team1);
		}
	}
	
	private static PlayerInt[] makePlayers() { //Here we can change some code to decide who is each player
		PlayerInt[] players = new PlayerInt[4];
		players[0]=new DumbPlayer();
		players[1]=new AndrewPlayer();
		players[2]=new DumbPlayer();
		players[3]=new AndrewPlayer();
		return players;
	}

	private static boolean AllPlayersPassed(ArrayList<Bid> prevBids) {
		// TODO Auto-generated method stub
		return false;
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
			hands[i].sort();
		}
		hands[4]=new Hand();
		//Add the kitty
		hands[4].cards.add(deck.get(40));
		hands[4].cards.add(deck.get(41));
		hands[4].cards.add(deck.get(42));
		
		return hands;
	}

	private static boolean gameNotOver(Team[] teams) {
		
		if(teams[1].points>=500||teams[0].points>=500){
			System.out.println("Game Over, player reached 500.");
			return false;
		}else if(teams[1].points<=-500||teams[0].points<=-500){
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
