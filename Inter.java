package BridgeDelux;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Inter {
//bruken av interface kan virke overflødig og det er fordi det er det, da dette ikke egentlig var nødvendig for mitt program og grunnstenene i programmet mitt var lagd før vi hadde lært om interfaces 
	public void txtles(Play oj, String filnavn) throws FileNotFoundException;
	public void txtskriver(Play oj, String filnavn) throws IOException;
}
