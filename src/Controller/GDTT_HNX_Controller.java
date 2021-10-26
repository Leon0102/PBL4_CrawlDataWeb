package Controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.GDTT_HNX_DAO;
import DAO.GDTT_Hose_DAO;
import Model.GDTT_HNX;
import Model.GDTT_Hose;

public class GDTT_HNX_Controller {
	public static void handle() throws JSONException, IOException {
		JSONObject json = JsonReader.readJsonFromUrl("https://banggia.cafef.vn/TTHandler.ashx?center=2");
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(json.toString());
	        JsonNode arrNode =  jsonNode.get("_1");
	        ArrayList<GDTT_HNX> listCK = new ArrayList<>();
	        if (arrNode.isArray()) {
	        	GDTT_HNX_DAO db = new GDTT_HNX_DAO();
	            for (final JsonNode objNode : arrNode) {
	            	GDTT_HNX ck = new GDTT_HNX();
	            	ck.setId(objNode.get("e").asText());
	            	ck.setPrice(objNode.get("f").asDouble());
	            	ck.setAmount(objNode.get("g").asInt());
	            	ck.setValue(objNode.get("h").asDouble());
	            	ck.setTime(objNode.get("i").asText());
	            	listCK.add(ck);
	            	db.insert(ck);
	            }
	        }
		} catch(Exception e) {
			
		}
	}
}
