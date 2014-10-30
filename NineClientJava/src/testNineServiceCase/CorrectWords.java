package testNineServiceCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class CorrectWords extends NineCase {
	String nineLetters;
	ArrayList<String> correctWords;
	CorrectWords(JsonArray jsonArray, String nineLetters){
		this.nineLetters = nineLetters;
		correctWords = new ArrayList<String>();
		for(int i = 0; i < jsonArray.size() ; i++){
			
			String string = jsonArray.getJsonString(i).getString();
			
			if(!string.contains(nineLetters.subSequence(4, 5) ) )
				continue;
			if(string.trim().length() < 3)
				continue;
			
			StringBuilder stringBuilder = new StringBuilder(string);
			for(int j = 0; j < nineLetters.length() ; j++){
				
				int index = stringBuilder.indexOf(nineLetters.substring(j, j+1) );
				if( index > -1 ){
					stringBuilder.deleteCharAt(index);
					
				}
				
			}
			if(stringBuilder.length() == 0)
				correctWords.add(string);
		}
	}
	
	public boolean validate(){
		try {
			JsonObject response = postRequestJsonObjectForUrl(new URL("http://localhost:8080/nine/validate.json")
																, createJsonObjectForPostRequest(nineLetters, correctWords));
			JsonArray jsonArrayMissedWords = response.getJsonArray("missedWords");
			JsonArray jsonArrayFalseWords = response.getJsonArray("falseWords");
			return (jsonArrayMissedWords.isEmpty() && jsonArrayFalseWords.isEmpty());
		} catch (MalformedURLException e) {
			System.out.println("Something wrong with the url: http://localhost:8080/nine/validate.json");
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String> getCorrectWords() {
		return correctWords;
	}

	public void setCorrectWords(ArrayList<String> correctWords) {
		this.correctWords = correctWords;
	}
}
