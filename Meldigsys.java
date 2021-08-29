package BridgeDelux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Meldigsys {
	private List<String> Allt = Arrays.asList("C1", "D1", "H1", "S1", "NT1", "C2", "D2", "H2", "S2", "NT2", "C3", "D3", "H3", "S3", "NT3", "C4", "D4", "H4", "S4", "NT4", "C5", "D5", "H5", "S5", "NT5", "C6", "D6", "H6", "S6", "NT6", "C7", "D7", "H7", "S7", "NT7");
	private List <String> turbud = new ArrayList <String>();
	private List <Integer> passrunde = new ArrayList <Integer>();
	private int inn = -1;
	private int turnr = 0;
	private int turnrfok;
	private char tk = 'F';
	private int trenger;
	private String bud;
	private boolean b = true;
	public void startpros() { //legger til 4 tomme budfelt
		turbud.add("");
		turbud.add("");
		turbud.add("");
		turbud.add("");
		
	} 
	public int getturnr() { //brukes for å få åpningshånden
		for(int i = 0; i < 3; i++) {
		turnr += 1;
		if(turnr == 4) {
			turnr = 0;
		}
		}
		return this.turnr;
	}
	public int getturnrfok() { //brukes for å få nr på spilleren med åpene kort
		
		return this.turnrfok;
		
	}
	public boolean sjekkb() { //sjekker at budet er gyldig
		return b;
	}
	public boolean test1() { //sjekker at en trump har blitt satt
		if(this.tk == 'F') {
			return false;
		} else {
			return true;
		}
	}
	public char test2() { //returnerer trump
		if(test1()) {
			return this.tk;
		} else {
			return 'F';
		}
	}
	public String test3() { //returnerer meldingen som spilles
		if(test1()) {
			return turbud.get(this.trenger);
		} else {
			return "Feil har oppstått";
		}
	}
	public void budene(TextArea b1, TextArea b2, TextArea b3, TextArea b4) { //setter budene som vises
		b1.appendText(turbud.get(0));
		b2.appendText(turbud.get(1));
		b3.appendText(turbud.get(2));
		b4.appendText(turbud.get(3));
	}
	public void setbud(TextField bud) { //tar inn budet som spilleren har skrevet
		this.bud = bud.getText().toUpperCase();
	}
	public void setbudstring(String bud) { //identisk med den over men brukes i testene ettersom eclips foretrekker strings
		this.bud = bud.toUpperCase();
	}
	
	public void Meldigsystemet() { //her skjer selve budene, hvis det er gyldig output legger den til og sjekker om budrunden er over(som den er hvis enten tre har passet etter melding, eller fire har passet uten melding)
		passrunde.clear();
		passrunde.add(0);
		passrunde.add(1);
		passrunde.add(2);
		passrunde.add(3);

		int a = 0;
		
		for (int i = 0; i < Allt.size(); i++) {
		if (Allt.get(i).equals(this.bud) || this.bud.equals("PASS")) {
			a = 1;
			
			if(i > inn  && !(this.bud.equals("PASS"))) {
				this.inn = i;
				turbud.set(turnr, (this.bud));
				turnr += 1;
				if(turnr == 4) {
					turnr = 0;
				}
				b = true;
			} else {
				if((this.bud.equals("PASS"))) {
					turbud.set(turnr, this.bud);
					turnr += 1;
					if(turnr == 4) {
						turnr = 0;
					}
					b = true;
					//sjekke om runden har blitt passet og meldingen som skal hentes ut	
					this.trenger = passrunde.get(passrunde.indexOf(turnr));
					passrunde.remove(passrunde.indexOf(turnr));
					int passer = 0;
					for(int b = 0; b < 3; b++) {
						
						if(turbud.get(passrunde.get(b)).equals("PASS")) {
							passer += 1;
						}
					}
					if(passer == 3 && !(turbud.get(this.trenger).isEmpty())) {
						this.tk = turbud.get(this.trenger).charAt(0);
						turnr -= 1;
						if(turnr == -1) {
							turnr = 3;
						}
						turnr -= 1;
						if(turnr == -1) {
							turnr = 3;
						}
					
						this.turnrfok = turnr;
					} else if(passer == 4) {
					System.out.print("Alle passet, start spillet på nytt");
					this.tk = 'P';
				} else {
						passrunde.add(turnr, this.trenger);
					}
					
				} else {
					System.out.println("Må skrive en gyldig melding, som følger riktig rekkefølge");
					b = false;
				}
			}
	
		break;	
		}
		}
		if(a==0) {
			System.out.println("Må skrive en gyldig melding");
			b = false;
		}
	}
}
