
public class Card implements Comparable<Card>{
	
	public Card(int suit2, int value2) {
		this.suit=suit2;
		this.value=value2;
	}
	
	public int compareTo(Card compareCard) {
		//Sort by suit then value
		if (this.suit>compareCard.suit){
			return 1;
		} else if(this.suit==compareCard.suit){
			if (this.value>compareCard.value){
				return 1;
			} else if(this.value==compareCard.value){
				return 0;
			} else{
				return -1;
			}
		} else {
			return -1;
		}
	}
	public int suit;
	public int value;
	public String name;
}

//Joker is suit -1 value 0