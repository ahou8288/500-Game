import java.util.ArrayList;
import java.util.Collections;


public class Hand {
	public Hand(){
		this.cards=new ArrayList<Card>();
	}
	public ArrayList<Card> cards;
	
	public void sort(){
		Collections.sort(this.cards);
	}
}