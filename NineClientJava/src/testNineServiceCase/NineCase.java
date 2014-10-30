package testNineServiceCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

public class NineCase {

	public NineCase(){
		
	}

	public JsonObject getRequestJsonObjectFromUrl(URL url) {
		try {
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
	
			JsonObject obj = rdr.readObject();
			return obj;
		} catch (IOException e) {
			System.out.println("Something wrong with the line 'InputStream is = url.openStream();'");
			e.printStackTrace();
		}
		return null;
	}
	
	public JsonObject postRequestJsonObjectForUrl(URL url, JsonObject jsonObjectForPostRequest){
		 //Establish URL connection*************************************************************
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			try {
				conn.setRequestMethod("POST"); //use post method
				conn.setDoOutput(true); //we will send stuff
				conn.setDoInput(true); //we want feedback
				conn.setUseCaches(false); //no caches
				conn.setAllowUserInteraction(false);
				conn.setRequestProperty("Content-Type","application/json");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}
			
		    // Open a stream which can write to the URL******************************************
		    OutputStream out = conn.getOutputStream();
		    try {
			    OutputStreamWriter wr = new OutputStreamWriter(out);
			    wr.write(jsonObjectForPostRequest.toString()); //jsonObjectForPostRequest is my JSON object containing the api commands
			    wr.flush();
			    wr.close();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    } finally { //in this case, we are ensured to close the output stream
		    	if (out != null)
		    		out.close();
		    }
		    
		    // Open a stream which can read the server response*********************************
		    InputStream is = conn.getInputStream();
		    JsonReader rdr = Json.createReader(is);
		    JsonObject obj = rdr.readObject();
		    return obj;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public JsonObject createJsonObjectForPostRequest(String nineLetters, ArrayList<String> correctWords) {
		JsonObject jsonObjectForPostRequest;
		if(!correctWords.isEmpty()){
			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
			JsonArrayBuilder jsonArrayBuilder =Json.createArrayBuilder();
			for(String s: correctWords){
				jsonArrayBuilder.add(s);
			}
			jsonObjectForPostRequest = jsonObjectBuilder.add("nineLetters", nineLetters).add("result", jsonArrayBuilder).build();
			return jsonObjectForPostRequest;
		}
		else
			System.out.println("CorrectWords is empty");
		return null;
	}

}