package BridgeDelux;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Inter {
//bruken av interface kan virke overfl�dig og det er fordi det er det, da dette ikke egentlig var n�dvendig for mitt program og grunnstenene i programmet mitt var lagd f�r vi hadde l�rt om interfaces 
	public void txtles(Play oj, String filnavn) throws FileNotFoundException;
	public void txtskriver(Play oj, String filnavn) throws IOException;
}
