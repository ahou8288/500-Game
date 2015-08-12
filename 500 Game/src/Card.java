
public class Card implements Comparable<Card>{
	
	public Card(int suit2, int value2) {
		this.suit=suit2;
		this.value=value2;
	}
	
	public int compareTo(Card compareCard) { //This allows the card class to be sorted.
		//Sort by suit then value
		if (this.suit>compareCard.suit){
			return -1;
		} else if(this.suit==compareCard.suit){
			if (this.value>compareCard.value){
				return -1;
			} else if(this.value==compareCard.value){
				return 0;
			} else{
				return 1;
			}
		} else {
			return 1;
		}
	}
	
	public String toString(){
		//determine value
		int valueInt = this.value;
		String valueString = new String();
		
		//for the suit in letters
		String suit = new String();
		
		//find a string to represent the suit
		if(this.suit == 0){
		suit = "s";
		value = valueInt + 1; //bump up the indexes used for spades so it conforms with red system
		}
		else if (this.suit == 1) {suit = "c";} //when the joker is added, clubs will also need to be bumped TODO 
		else if (this.suit == 2) {suit = "d";}
		else {suit = "h";}
		
		//find the string representation of the card value
		
		if (valueInt <=6) { //for values 0-6 (or 1-6 for clubs) use its integer value
			return valueInt + suit; //string + int = string
		}
		
		//if valueInt is greater than 7 then it is a royalty and gets its corresponding letter value
		else if (valueInt == 7) {
			valueString = "J";
		}
		else if (valueInt == 8) {
			valueString = "Q";
		}
		else if (valueInt == 9) {
			valueString = "K";
		}
		else if (valueInt == 10) {
			valueString = "A";
		}
		
		//return the concatonation of value and suit
		return valueString + suit;  
		
		
		/* red and club - 
		4-10 -> 0-6
		j -> 7
		Q -> 8
		K -> 9
		A -> 10

		spade - 
		5-10 -> 0-5
		J -> 6
		Q -> 7
		K -> 8
		A -> 9 */
		
	}
	
	public int suit;
	public int value;
}

//Joker is suit -1 value 0