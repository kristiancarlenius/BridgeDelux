package BridgeDelux;


import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Play {
	
	private int nr = 0;
	private int egbtur = -1;
	private int turn = 0;
	private int pns = 0;
	private int pew = 0;
	private int starten = 0;
	private List <Card> stikk = new ArrayList <Card>();
	private List <Card> kristian = new ArrayList <Card>();
	private List <Card> viljar = new ArrayList <Card>();
	private List <Card> andreas = new ArrayList <Card>();
	private List <Card> jonatan = new ArrayList <Card>();
	private List <Card> kristianO = new ArrayList <Card>();
	private List <Card> viljarO = new ArrayList <Card>();
	private List <Card> andreasO = new ArrayList <Card>();
	private List <Card> jonatanO = new ArrayList <Card>();
	private List <Card> vise = new ArrayList <Card>();
	private List <List<Card>> turspill = new ArrayList <List<Card>>();
	private List <List<Card>> orgspill = new ArrayList <List<Card>>();
	private Card str;
	private Card vinnerkortet;
	private List <String> turkort = new ArrayList <String>();
	private List <Card> trump = new ArrayList <Card>();
	private char tk;
	
	public String getkstring() { //brukes kun i testene, for å få henende ut som string
		String supstr = kristian.get(0).toString()+", ";
		for(int i = 1; i < kristian.size()-1; i++) {
			supstr = supstr+kristian.get(i).toString()+", ";
		}
		supstr = supstr+kristian.get(kristian.size()-1).toString();
		return supstr;
	}
	public String getvstring() {
		String supstr = viljar.get(0).toString()+", ";
		for(int i = 1; i < viljar.size()-1; i++) {
			supstr = supstr+viljar.get(i).toString()+", ";
		}
		supstr = supstr+viljar.get(viljar.size()-1).toString();
		return supstr;
	}
	public String getastring() {
		String supstr = andreas.get(0).toString()+", ";
		for(int i = 1; i < andreas.size()-1; i++) {
			supstr = supstr+andreas.get(i).toString()+", ";
		}
		supstr = supstr+andreas.get(andreas.size()-1).toString();
		return supstr;
	}
	public String getjstring() {
		String supstr = jonatan.get(0).toString()+", ";
		for(int i = 1; i < jonatan.size()-1; i++) {
			supstr = supstr+jonatan.get(i).toString()+", ";
		}
		supstr = supstr+jonatan.get(jonatan.size()-1).toString();
		return supstr;
	}
	public void setturn(int vsk) { //noe av det viktigste i programmet er at spilleren bak den som har vunnet budrunden begynner med utspill, og at den som ta stikket starter neste runde
		this.turn = vsk;
	}
	public void setstr(TextField txt) { //tar inputet fra brukeren og sjekker om det er gyldig og ikke tomt
		if(!(txt.getText().isEmpty())) {
		try {
		this.str = new Card( txt.getText().toUpperCase().charAt(0), Integer.parseInt(txt.getText().substring(1, txt.getText().length()))) ;
		} catch (IllegalArgumentException e) {
			this.str = new Card( 'N', 0);
		}
		} else {
			this.str = new Card( 'N', 0);
		}
	}
	public void setstrAS(String string) { //i testene bruker vi string istede for textfield ettersom eclips liker dette bedre
		try {
		this.str = new Card(string.toUpperCase().charAt(0), Integer.parseInt(string.substring(1, string.length())));
		} catch(IllegalArgumentException e) {
			this.str = new Card( 'N', 0);
		}
	}
	public Card getvinnerkortet() { //kun brukt i testene for å sjekke at riktig spiller vant
		return this.vinnerkortet;
	}
	public List<Card> getopnk(int tnr){ //brukt for å hente ut den åpene hånden som er den samme(men som naturlig vis endrer seg etter spilt kort) hver runde, ettersom det blir bestemt av budrunden
		this.turspill.clear();
		this.turspill.add(kristian);
		this.turspill.add(viljar);
		this.turspill.add(andreas);
		this.turspill.add(jonatan);
		
		return turspill.get(tnr);
	}
	public List<Card> getvise() { //viser kortene til spilleren
		return this.vise;
	}

	public boolean sjekk() { //sjekker at det som er spilt er lovelig og gyldig input
		this.turspill.clear();
		this.turspill.add(kristian);
		this.turspill.add(viljar);
		this.turspill.add(andreas);
		this.turspill.add(jonatan);
		if(str.toString() != "N0") {
			for(int b = 0; b < turspill.get(turn).size(); b++) {
		if(turspill.get(turn).get(b).toString().equals( str.toString())) {
			if (stikk.size() == 0 ||  stikk.get(0).getSuit() == str.getSuit()) {
			return true; 
			} else {
					for( int i = 0; i < turspill.get(turn).size(); i++) {
						if( turspill.get(turn).get(i).getSuit() == stikk.get(0).getSuit()) {
							System.out.println("Må følge farge");
							return false;
							}
						}
				
					
					return true;
				}
			
		} 
			} 
			
		
		}
		System.out.println("Må fylle ut feltet før du trykker og være et gyldig kort");
		return false;
		
	}
	public void delutkort() { //deler ut kortene til spillerene, definerer orginalkortene og setter viste kort til tomme felt som senere fylles med kortene som er spilt den runden
		if (starten == 0) { //dette er for å passe på at man ikke deler ut kort og så bruker setutkort (eller andre veien)
		new CardDeck(this.kristian, this.viljar, this.andreas, this.jonatan);
		for(int i = 0; i < 13; i++) {
		this.kristianO.add(this.kristian.get(i));
		this.viljarO.add(this.viljar.get(i));
		this.andreasO.add(this.andreas.get(i));
		this.jonatanO.add(this.jonatan.get(i));
		
		}
		this.turkort.clear();
		for(int i = 0; i < 3; i++) {
			turkort.add("");
		}
		this.orgspill.clear();
		this.orgspill.add(kristianO);
		this.orgspill.add(viljarO);
		this.orgspill.add(andreasO);
		this.orgspill.add(jonatanO);
		starten = 1;

		}
	}
	public void setutkort(List<Card> k, List<Card> v, List<Card> a, List<Card> j) { //brukt i importering av spill for å sette kortene
		if (starten == 0) {
			for(int i = 0; i < k.size(); i++) {
			this.kristianO.add(k.get(i));
			this.viljarO.add(v.get(i));
			this.andreasO.add(a.get(i));
			this.jonatanO.add(j.get(i));
			this.kristian.add(k.get(i));
			this.viljar.add(v.get(i));
			this.andreas.add(a.get(i));
			this.jonatan.add(j.get(i));
			}
			
			this.turkort.clear();
			for(int i = 0; i < 3; i++) {
				turkort.add("");
			}
			this.orgspill.clear();
			this.orgspill.add(k);
			this.orgspill.add(v);
			this.orgspill.add(a);
			this.orgspill.add(j);
			starten = 1;
			}
	}
	public void setorg() { //brukt under budrunden for å vise de orginale hendene
		this.egbtur += 1;
		if(this.egbtur == 4) {
			this.egbtur = 0;
		}
		vise = this.orgspill.get(egbtur);
	}
	
	public void setSpiltekort(TextArea ta1, TextArea ta2, TextArea ta3) { //setter tekstareaene til de spilte kortene den runden
		ta1.appendText(turkort.get(0)); 
		ta2.appendText(turkort.get(1));
		ta3.appendText(turkort.get(2));
	}
	public void settrump(char tku) { //setter trump fargen
		this.tk = tku;
	}
	public void spilt() { //her er selve spillingen, bestemmer om det er et brent kort, trump kort eller følger farge, så sjekker om noen har fått stikket og isåfall hvem det var
		
		
		
				if (stikk.size() == 0 ||  stikk.get(0).getSuit() == str.getSuit()) {	
				stikk.add(str);
				for(int g = 0; g < turspill.get(turn).size(); g++) {
					if(turspill.get(turn).get(g).toString().equals( str.toString())) {
						turspill.get(turn).remove(g);
						break;
					}
				}
				this.turn += 1;
				if (turn == 4) {
					turn = 0;
				}
				vise = turspill.get(turn);
				this.nr += 1;
				turkort.add(0, str.toString());
				if (nr == 4) {
					this.nr = 0;
					Card vinnertallet;
					List <Integer> sorterT = new ArrayList<Integer>();
					if (trump.size() == 0) {
					for(int i = 0; i < stikk.size(); i++) {
						sorterT.add(stikk.get(i).getFace());
					}
					Collections.sort(sorterT); 
					vinnertallet = new Card(stikk.get(0).getSuit(), sorterT.get(sorterT.size()-1));
					} else {
						for(int i = 0; i < trump.size(); i++) {
							sorterT.add(trump.get(i).getFace());
						}
						Collections.sort(sorterT); 
						vinnertallet = new Card(trump.get(0).getSuit(), sorterT.get(sorterT.size()-1));
					}
					System.out.println(vinnertallet.toString());
					this.vinnerkortet = vinnertallet;
					for(int a = 0; a < 13; a++) {
					if (kristianO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 0;
						this.pns += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}

						break;
					}
					if (viljarO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 1;
						this.pew += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}

						break;
					}
					if (andreasO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 2;
						this.pns += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}
	
						break;
					}
					if (jonatanO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 3;
						this.pew += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}

						break;
					}
				}
			}
				
			} else { 
				if(str.getSuit() == this.tk) {
					trump.add(str);
				}
				for(int g = 0; g < turspill.get(turn).size(); g++) {
					if(turspill.get(turn).get(g).toString().equals( str.toString())) {
						turspill.get(turn).remove(g);
						break;
					}
				}
				this.turn += 1;
				if (turn == 4) {
					turn = 0;
				}
				
				vise = turspill.get(turn);
				this.nr += 1;
				turkort.add(0, str.toString());
				if (nr == 4) {
					this.nr = 0;
					Card vinnertallet;
					List <Integer> sorterT = new ArrayList<Integer>();
					if (trump.size() == 0) {
					for(int i = 0; i < stikk.size(); i++) {
						sorterT.add(stikk.get(i).getFace());
					}
					Collections.sort(sorterT); 
					vinnertallet = new Card(stikk.get(0).getSuit(), sorterT.get(sorterT.size()-1));
					} else {
						for(int i = 0; i < trump.size(); i++) {
							sorterT.add(trump.get(i).getFace());
						}
						Collections.sort(sorterT); 
						vinnertallet = new Card(trump.get(0).getSuit(), sorterT.get(sorterT.size()-1));
					}
					System.out.println(vinnertallet.toString());
					this.vinnerkortet = vinnertallet;
					for(int a = 0; a < 13; a++) {
					if (kristianO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 0;
						this.pns += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}

						break;
					}
					if (viljarO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 1;
						this.pew += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}
		
						break;
					}
					if (andreasO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 2;
						this.pns += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}
			
						break;
					}
					if (jonatanO.get(a).toString().equals(vinnertallet.toString())) {
						this.turn = 3;
						this.pew += 1;
						stikk.clear();
						trump.clear();
						vise = turspill.get(turn);
						for(int i = 0; i < 3; i++) {
							turkort.set(i, "");
						}
						
						break;
					}
					}
			}
			}

	
	
}
	//disse tre er brukt for å sjekke at riktig score har blitt satt
    public int getsamledepoengscore() {
    	return pns + pew;
    }
    public int getpns() {
    	return pns;
    }
    public int getpew() {
    	return pew;
    }
	
	public boolean runderesten() { //sjekker om det er spillet er ferdig
	
	if ((this.kristian.size() == 0) && (this.viljar.size() == 0) && (this.andreas.size() == 0) && (this.jonatan.size() == 0)) {
		return true;
	} else {
		return false;
	}
	}
	
}