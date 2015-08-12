
public class Team {
	DumbPlayer p1;
	DumbPlayer p2;
	int points;
	int tricksWon;
	
	public Team(PlayerInt players, PlayerInt players2) {
		points = 0;
		tricksWon = 0;
	}
	
	public void addTrick(){tricksWon += 1; }
	
	public void resetTricks(){
		tricksWon=0;
	}
}
