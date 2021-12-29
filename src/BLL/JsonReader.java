package BLL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	  public static int getResponseCode(String url) throws IOException {
		  	URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
//			System.out.println("GET Response Code :: " + responseCode);
			return responseCode;
	  }
	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = getResponseCode(url);
		if (responseCode == HttpURLConnection.HTTP_OK)
			{
		    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String jsonText = readAll(rd);
		    JSONObject json = new JSONObject(jsonText);
		    System.out.println("GET DATA");
		    return json;
			} 
		else {
			return null;
			}	
	  }
	  public static JSONArray readJsonFromUrlArr(String url) throws IOException, JSONException {
		  	URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			int responseCode = getResponseCode(url);
			if (responseCode == HttpURLConnection.HTTP_OK)
				{
			    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			    String jsonText = readAll(rd);
			    JSONArray json = new JSONArray(jsonText);
			    System.out.println("GET DATA");
			    return json;
				} 
			else {
				return null;
				}	
		  }
}
