
public class Bid{
	public Bid(int i, int j) {
		this.suit=i;
		this.value=j;
	}
	public int suit;
	public int value;

	public int pointValue() {
	
		//within a trick, suits increment by 20 starting from 40
		int suitPoints = (this.suit*20) + 40;
		
		//within a suit, tricks increment by 100, starting with 6
		int trickPoints = (this.value - 6)*100;
		
		//total points is the sum of the two
		return trickPoints + suitPoints;
	}

	public int compareTo(Bid bid) {
		if (this.value>bid.value){
			return -1; //the bid argument is lower than this bid
		} else if(this.value==bid.value){
			if (this.suit>bid.suit){
				return -1;
			} else if(this.suit==bid.suit){
				return 0;
			} else{
				return 1;
			}
		} else {
			return 1;
		}
	}
}

//Special Bids
//SUIT Rules
//0 spades
//1 clubs
//2 diamonds
//3 hearts
//4 reserved for no trumps
//-1 reserved for pass