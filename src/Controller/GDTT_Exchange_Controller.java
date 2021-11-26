package Controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.GDTT_Exchange_DAO;
import Model.GDTT_Exchange;

public class GDTT_Exchange_Controller {
	public static void handle(String exchangeName) throws JSONException, IOException {
		JSONObject json;
		if(exchangeName=="hose")json = JsonReader.readJsonFromUrl("https://banggia.cafef.vn/TTHandler.ashx?center=1");
		else  //exchangeName= "hnx"
			json = JsonReader.readJsonFromUrl("https://banggia.cafef.vn/TTHandler.ashx?center=2");
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(json.toString());
	        JsonNode arrNode =  jsonNode.get("_1");
	        ArrayList<GDTT_Exchange> listCK = new ArrayList<>();
	        if (arrNode.isArray()) {
	            GDTT_Exchange_DAO db = new GDTT_Exchange_DAO();
	            for (final JsonNode objNode : arrNode) {
	            	GDTT_Exchange ck = new GDTT_Exchange();
	            	ck.setId(objNode.get("e").asText());
	            	ck.setPrice(objNode.get("f").asDouble());
	            	ck.setAmount(objNode.get("g").asInt());
	            	ck.setValue(objNode.get("h").asDouble());
	            	ck.setTime(objNode.get("i").asText());
	            	listCK.add(ck);
	            	db.insert(ck,exchangeName);
	            }
	        }
		} catch(Exception e) {
			
		}
	}
	
	public static void update(String exchangeName) throws JSONException, IOException {
		JSONObject json;
		if(exchangeName=="hose")json = JsonReader.readJsonFromUrl("https://banggia.cafef.vn/TTHandler.ashx?center=1");
		else //exchangeName= "hnx"
			json = JsonReader.readJsonFromUrl("https://banggia.cafef.vn/TTHandler.ashx?center=2");
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(json.toString());
	        JsonNode arrNode =  jsonNode.get("_1");
	        ArrayList<GDTT_Exchange> listCK = new ArrayList<>();
	        if (arrNode.isArray()) {
	            GDTT_Exchange_DAO db = new  GDTT_Exchange_DAO();
	            for (final JsonNode objNode : arrNode) {
	            	GDTT_Exchange ck = new GDTT_Exchange();
	            	ck.setId(objNode.get("e").asText());
	            	ck.setPrice(objNode.get("f").asDouble());
	            	ck.setAmount(objNode.get("g").asInt());
	            	ck.setValue(objNode.get("h").asDouble());
	            	ck.setTime(objNode.get("i").asText());
	            	listCK.add(ck);
	            	db.update(ck,exchangeName);
	            }
	        }
		} catch(Exception e) {
			
		}
	}
}
