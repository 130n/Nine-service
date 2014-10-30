package testNineServiceCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;


public class WordList extends NineCase {
	private JsonArray jsonArray;
	
	WordList(){
		try {
			jsonArray = getRequestJsonObjectFromUrl(new URL("http://localhost:8080/nine/wordlist.json") ).getJsonArray("wordList");
			
		} catch (MalformedURLException e) {
			System.out.println("Something went wrong with the url: http://localhost:8080/nine/wordlist.json");
			e.printStackTrace();
		}
		
	}

	public JsonArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JsonArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	
}
