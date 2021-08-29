package BridgeDelux;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class txtleser implements Inter{
	private List <Card> kristian = new ArrayList <Card>();
	private List <Card> viljar = new ArrayList <Card>();
	private List <Card> andreas = new ArrayList <Card>();
	private List <Card> jonatan = new ArrayList <Card>();
	private boolean a = true;
	private String hond1;
	private String hond2;
	private String hond3;
	private String hond4;
	//viktig å huske er at den leser ut og lagrer på location posisjonen, i dette tilfelle under mappen minegenkode, hvis det skal lagres eller leses fra andre mapper må dette spesifiseres i filnavnet
	//får ut listene med kort
	public List <Card> retk(){
		return this.kristian;
	}
	public List <Card> retv(){
		return this.viljar;
	}
	public List <Card> reta(){
		return this.andreas;
	}
	public List <Card> retj(){
		return this.jonatan;
	}
	public boolean sjekka() { //sjekker om filene er korrekte og at innholdet i de er kun kort
		return this.a;
	}
	public void txtread(String filnavn) throws FileNotFoundException { //kopierer et spill fra en fil og setter det inn i spiller for å spilles ut
		a = true;
		try {
		File tisp = new File(filnavn);
	    Scanner leser = new Scanner(tisp);
	    hond1 = leser.nextLine();
	    hond2 = leser.nextLine();
	    hond3 = leser.nextLine();
	    hond4 = leser.nextLine();
	    leser.close();
	    long count = hond1.chars().filter(ch -> ch == ',').count();
	    String[] arros1 = hond1.split(", ", (int) count+1);
	    count = hond2.chars().filter(ch -> ch == ',').count();
	    String[] arros2 = hond2.split(", ", (int) count+1);
	    count = hond3.chars().filter(ch -> ch == ',').count();
	    String[] arros3 = hond3.split(", ", (int) count+1);
	    count = hond4.chars().filter(ch -> ch == ',').count();
	    String[] arros4 = hond4.split(", ", (int) count+1);
	    try {
	    	//det kan være aktuelt å legge til hender med ulik størrelse og potensielt også identiske kort når man skal se på og teste en kunstig hånd, dette vil gjøre at spillet ikke kan fullføres men det er heller ikke intensjonen i slike posisjoner
	    for(int i = 0; i< arros1.length; i++) {
	    	kristian.add(new Card(arros1[i].charAt(0), Integer.parseInt(arros1[i].substring(1, arros1[i].length())))); 
	    }
	    for(int i = 0; i< arros2.length; i++) {
	    	viljar.add(new Card(arros2[i].charAt(0), Integer.parseInt(arros2[i].substring(1, arros2[i].length()))));
	    }
	    for(int i = 0; i< arros3.length; i++) {
	    	andreas.add(new Card(arros3[i].charAt(0), Integer.parseInt(arros3[i].substring(1, arros3[i].length()))));
	    }
	    for(int i = 0; i< arros4.length; i++) {
	    	jonatan.add(new Card(arros4[i].charAt(0), Integer.parseInt(arros4[i].substring(1, arros4[i].length()))));
	    } 
	    } catch(IllegalArgumentException e) {
	    	a = false;
	    	System.out.println("Filen må kun bestå av gyldig kort");
	    }
		} catch(FileNotFoundException e) {
			a = false;
		}
	}
	public void txtwrite(Play oj, String filnavn) throws IOException { //kopierer nåværende spill til et egetbestemt filformat (her her jeg erfart at man kan lagre filen som hva enn man vil, txt, jpg, png ect. og den vil fortsatt ha informasjonen lagret i seg og kan fint leses og hentes ut til spill senere)
		a = true;
		try {
		BufferedWriter txt1 = new BufferedWriter(new FileWriter(filnavn));
		for (int a = 0; a < 4; a++) {
			oj.setorg();
		for (int i = 0; i < oj.getvise().size()-1; i++) {
			txt1.write(((String) oj.getvise().get(i).toString()) + ", ");
			}
		txt1.write(((String) oj.getvise().get(oj.getvise().size()-1).toString()));
		txt1.newLine();
		}
		txt1.close();
		} catch(IOException e) {
			a = false;
		}
	}
	@Override
	public void txtles(Play oj, String filnavn) throws FileNotFoundException{
		this.txtread(filnavn);
		if(a) {
		oj.setutkort(retk(), retv(), reta(), retj());
		}
	}
	@Override
	public void txtskriver(Play oj, String filnavn) throws IOException {
		this.txtwrite(oj, filnavn);
		
	}
}
