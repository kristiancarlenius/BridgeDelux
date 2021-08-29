package BridgeDelux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//Har identisk navn med oppgave tiligere gitt, men er helt unik ettersom den legger til kort anneledes, stokker de om annerledes, sorterer de etter farge og gir de tilbake
public class CardDeck {
		private List <Card> spiller1 = new ArrayList <Card>();
		private List <Card> spiller2 = new ArrayList <Card>();
		private List <Card> spiller3 = new ArrayList <Card>();
		private List <Card> spiller4 = new ArrayList <Card>();
	

	public CardDeck(List <Card> name1, List <Card> name2, List <Card> name3, List <Card> name4) {
		CardDeckready();
		Collections.sort(spiller1, Comparator.comparing(Card::getSuit)); //sorterer etter farge
		Collections.sort(spiller2, Comparator.comparing(Card::getSuit));
		Collections.sort(spiller3, Comparator.comparing(Card::getSuit));
		Collections.sort(spiller4, Comparator.comparing(Card::getSuit));
		for(int i = 0; i < 13; i++) {
		name1.add(spiller1.get(i)); 	//deler ut kortene 
		name2.add(spiller2.get(i));
		name3.add(spiller3.get(i));
		name4.add(spiller4.get(i));
		}
	}
	public void CardDeckready(){
		 List<Card> totdeck = new ArrayList<Card>();
		 List<Card> randdeck = new ArrayList<Card>();
		 char[] totchar = {'S', 'H', 'D', 'C'};
		 
		for( int a = 0; a < totchar.length; a++) { //lager en ny kortstokk
			for( int b = 2; b < 15; b++) {
				totdeck.add(new Card(totchar[a], b));
		}
		
			
		}
		while (totdeck.size() > 0){
			int indx = (int) ((Math.random() * ((totdeck.size()-1) - 0)) + 0);
			randdeck.add(totdeck.remove(indx)); //her shuffles de random
			}
		
		
		for( int a = 0; a < 13; a++) {
			this.spiller1.add(randdeck.remove(0)); //deler ut de tilfeldige kortene til spilleren
		}
		
		for( int a = 0; a < 13; a++) {
			this.spiller2.add(randdeck.remove(0));
		}
		for( int a = 0; a < 13; a++) {
			this.spiller3.add(randdeck.remove(0));
		}
		for( int a = 0; a < 13; a++) {
			this.spiller4.add(randdeck.remove(0));
		}
	}
}
