
public class Bid {
	public Bid(int i, int j) {
		this.suit=i;
		this.value=j;
	}
	public int suit;
	public int value;
}

//Special Bids
//suit 4 reserved for no trumps
//suit -1 reserved for pass