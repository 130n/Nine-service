package testNineServiceCase;

import java.net.MalformedURLException;
import java.net.URL;

import javax.json.JsonObject;


public class NineLetters extends NineCase{
	private JsonObject jsonObject;
	private String letters;
	NineLetters(){
		
		try {
			this.letters = getRequestJsonObjectFromUrl(new URL("http://localhost:8080/nine/getnine.json") ).getString("letters");
		} catch (MalformedURLException e) {
			System.out.println("Something went wrong with the url: http://localhost:8080/nine/getnine.json");
			e.printStackTrace();
		}
	}
	
	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}
	
}
