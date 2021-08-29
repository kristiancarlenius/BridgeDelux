package BridgeDelux;

public class Card {
	private char kf;
	private int kt;
	private String kort;
	
	public Card(char kf, int kt) throws IllegalArgumentException{
		
		if ((kf == 'S' || kf == 'H' || kf == 'D' || kf == 'C') && kt >= 2 && kt <= 14) {
			this.kf = kf;
			this.kt = kt;
//Lager kort i fargene og tallene fra to til 14
			
		} else if(kf == 'N' && kt == 0) {
			this.kf = kf;
			this.kt = kt; 
			//ett illigalt kort som brukes for å sjekke luke bort tilfeller der det er blitt forsøkt å legge til kort som ikke er gyldige
			}
		else {
			throw new IllegalArgumentException("Has to be a legal card");
		}
	}
	
	public char getSuit() {
		return this.kf;
	}
	
	public int getFace() {
		return this.kt;
	}
	
	public String toString() {
		this.kort = ("" + kf + kt +"");
		return this.kort;
	}
	
}
