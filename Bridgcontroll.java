package BridgeDelux;



import java.io.FileNotFoundException;
import java.io.IOException;

//import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
public class Bridgcontroll {
	@FXML private TextField kortspilt;
	@FXML private TextArea minekort;
		 
		  private Play spill = new Play();
		  private Meldigsys melding = new Meldigsys();
		  private txtleser txtbehandler = new txtleser();
		  
	@FXML private Button btdok;
	@FXML private Button copybt;
    @FXML private TextArea tur1;
	@FXML private TextArea tur2;
	@FXML private TextArea tur3;
	@FXML private TextArea nord;
	@FXML private TextArea ost;
	@FXML private TextArea syd;
	@FXML private TextArea vest;
	@FXML private TextArea id1;
	@FXML private TextArea id4;
	@FXML private TitledPane startplan;
	@FXML private Button strkn;
	@FXML private TextField dok;
	@FXML private TextField pgbt;
	@FXML public void copy() throws IOException { //kopierer nåværende spill til egendefinert fil
		txtbehandler.txtwrite(spill, pgbt.getText());
		if(txtbehandler.sjekka()) {
		copybt.setDisable(true);
		copybt.setVisible(false);
		pgbt.setDisable(true);
		pgbt.setVisible(false);
		}
	}
	@FXML public void btdok() throws FileNotFoundException { //henter ut egendefinert fil til å spilles
		txtbehandler.txtles(spill, dok.getText());
		if(txtbehandler.sjekka()) {
		melding.startpros();
		spill.setorg();
		for (int i = 0; i < spill.getvise().size()-1; i++) {
			this.minekort.appendText(((String) spill.getvise().get(i).toString()) + ", ");
			}
			this.minekort.appendText(((String) spill.getvise().get(spill.getvise().size()-1).toString()));
		strkn.setDisable(true);
		startplan.setVisible(false);
		id1.setVisible(false);
		
		id4.setVisible(false);
		tur3.setVisible(false);
		tur2.setVisible(false);
		tur1.setVisible(false);
		dok.setDisable(true);
		}
	}
	@FXML public void startknappen() { //starter ett orginalt spill
		spill.delutkort();
		spill.setorg();
		for (int i = 0; i < spill.getvise().size()-1; i++) {
			this.minekort.appendText(((String) spill.getvise().get(i).toString()) + ", ");
			}
			this.minekort.appendText(((String) spill.getvise().get(spill.getvise().size()-1).toString()));
		melding.startpros();
		strkn.setDisable(true);
		startplan.setVisible(false);
		id1.setVisible(false);
		
		id4.setVisible(false);
		tur3.setVisible(false);
		tur2.setVisible(false);
		tur1.setVisible(false);
		
		
	}
	@FXML public void spiltkort() { //inneholder både budrunde og spillrunde
		
		if((melding.test1())) { //spillrunde
			this.minekort.clear();
			
		
		spill.setstr(kortspilt);
		spill.settrump(melding.test2());
	
		if (spill.sjekk()) {
			
			
			
			
			spill.spilt();
			
			//setter den åpene hånden
			this.id1.clear();
			if(spill.getopnk(melding.getturnrfok()).size() != 0) {//passer på at etter siste kortet spilt, at den ikke prøver å kjøre denne koden ettersom det vil sende feilmelding
			for (int i = 0; i < spill.getopnk(melding.getturnrfok()).size()-1; i++) {
					this.id1.appendText(((String) spill.getopnk(melding.getturnrfok()).get(i).toString()) + ", ");
					}
					this.id1.appendText(((String) spill.getopnk(melding.getturnrfok()).get(spill.getopnk(melding.getturnrfok()).size()-1).toString()));
			}
			
			this.minekort.clear();
			if (spill.getvise().size() != 0) {//passer på at etter siste kortet spilt, at den ikke prøver å kjøre denne koden ettersom det vil sende feilmelding
			for (int i = 0; i < spill.getvise().size()-1; i++) { //setter den viste hånden
				this.minekort.appendText(((String) spill.getvise().get(i).toString()) + ", ");
				}
				this.minekort.appendText(((String) spill.getvise().get(spill.getvise().size()-1).toString()));
			}
				this.kortspilt.clear();
				this.tur1.clear();
				this.tur2.clear();
				this.tur3.clear();
				spill.setSpiltekort(tur1, tur2, tur3);
				if(spill.runderesten()) {
					this.minekort.appendText("N-S:" + spill.getpns() +"  E-W:" + spill.getpew());
				}
		} else { 
			if (spill.getvise().size() != 0) {//passer på at etter siste kortet spilt, at den ikke prøver å kjøre denne koden ettersom det vil sende feilmelding
			for (int i = 0; i < spill.getvise().size()-1; i++) {//setter den viste hånden
				this.minekort.appendText(((String) spill.getvise().get(i).toString()) + ", ");
				}
				this.minekort.appendText(((String) spill.getvise().get(spill.getvise().size()-1).toString()));
			}
		}
		
		
		}
		else {//budrunden
			
			melding.setbud(kortspilt);
			melding.Meldigsystemet();
			if( melding.sjekkb()) {
				nord.clear();
				ost.clear();
				syd.clear();
				vest.clear();
			melding.budene(nord, ost, syd, vest);
			spill.setorg();
			this.minekort.clear();
			for (int i = 0; i < spill.getvise().size()-1; i++) {
				this.minekort.appendText(((String) spill.getvise().get(i).toString()) + ", ");
				}
				this.minekort.appendText(((String) spill.getvise().get(spill.getvise().size()-1).toString()));
			if(melding.test1()) {
				if(melding.test2() == 'P') { //stopper spillet hvis det blir passrunde(dvs at fire spillere passer uten en melding)
					kortspilt.setDisable(true);
					strkn.setDisable(true);
				} else {
				spill.setturn(melding.getturnr());
				spill.setorg();
				this.minekort.clear();
				for (int i = 0; i < spill.getvise().size()-1; i++) {
					this.minekort.appendText(((String) spill.getvise().get(i).toString()) + ", ");
					}
					this.minekort.appendText(((String) spill.getvise().get(spill.getvise().size()-1).toString()));
					
		
				nord.setVisible(false);
				ost.setVisible(false);
				syd.setVisible(false);
				vest.setVisible(false);
				id1.setVisible(true);
				id4.setVisible(true);
				tur3.setVisible(true);
				tur2.setVisible(true);
				tur1.setVisible(true);
				id4.appendText(melding.test3());
				
			
				}
				}
			} else {
				System.out.println("skriv inn melding som ikke er spilt før og med gyldig input");
			}
		}
	}

}