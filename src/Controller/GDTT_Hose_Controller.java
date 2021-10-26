package Controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.GDTT_Hose_DAO;
import DoAn.GDTT_Hose;

public class GDTT_Hose_Controller {
	public static void handle() throws JSONException, IOException {
		JSONObject json = JsonReader.readJsonFromUrl("https://banggia.cafef.vn/TTHandler.ashx?center=1");
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(json.toString());
	        JsonNode arrNode =  jsonNode.get("_1");
	        ArrayList<GDTT_Hose> listCK = new ArrayList<>();
	        if (arrNode.isArray()) {
	            GDTT_Hose_DAO db = new GDTT_Hose_DAO();
	            for (final JsonNode objNode : arrNode) {
	            	GDTT_Hose ck = new GDTT_Hose();
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
