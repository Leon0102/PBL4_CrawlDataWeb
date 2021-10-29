package Controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.Hose_DAO;
import Model.Hose;

public class Hose_Controller {
	public static void handle() throws JSONException, IOException {
	    JSONArray json1 = JsonReader.readJsonFromUrlArr("https://banggia.cafef.vn/stockhandler.ashx?center=1");
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	Hose_DAO db = new Hose_DAO();
	    	ArrayList<Hose> listck = new ArrayList<>();
	    	  for(int i =0; i< json1.length(); i++){
	    		  JsonNode jsonNode = mapper.readTree(json1.get(i).toString());
	    		  Hose ck = new Hose();
	    		  ck.setId(jsonNode.get("a").asText());
	    		  ck.setRefer(jsonNode.get("b").asDouble());
	    		  ck.setCeiling(jsonNode.get("c").asDouble());
	    		  ck.setFloor(jsonNode.get("d").asDouble());
	    		  ck.setBuy_Price3(jsonNode.get("e").asDouble());
	    		  ck.setBuy_Amount3(jsonNode.get("f").asDouble());
	    		  ck.setBuy_Price2(jsonNode.get("g").asDouble());
	    		  ck.setBuy_Amount2(jsonNode.get("h").asDouble());
	    		  ck.setBuy_Price1(jsonNode.get("i").asDouble());
	    		  ck.setBuy_Amount1(jsonNode.get("j").asDouble());
	    		  ck.setUpDownOrder(jsonNode.get("k").asDouble());
	    		  ck.setOrder_Price(jsonNode.get("l").asDouble());
	    		  ck.setOrder_Amount(jsonNode.get("m").asDouble());
	    		  ck.setTotalAmount(jsonNode.get("n").asDouble());		  
	    		  ck.setSell_Price1(jsonNode.get("o").asDouble());
	    		  ck.setSell_Amount1(jsonNode.get("p").asDouble());
	    		  ck.setSell_Price2(jsonNode.get("q").asDouble());
	    		  ck.setSell_Amount2(jsonNode.get("r").asDouble());
	    		  ck.setSell_Price3(jsonNode.get("s").asDouble());
	    		  ck.setSell_Amount3(jsonNode.get("t").asDouble());
	    		  ck.setHigh(jsonNode.get("v").asDouble());
	    		  ck.setLow(jsonNode.get("w").asDouble());
	    		  ck.setTime(jsonNode.get("Time").asText());
	    		  ck.setTotal_buy(jsonNode.get("x").asDouble());
	    		  listck.add(ck);
	    		  db.insert(ck);
	    	  }
	    }catch(Exception e) {
			
		}
	}
	public static void update() throws JSONException, IOException {
	    JSONArray json1 = JsonReader.readJsonFromUrlArr("https://banggia.cafef.vn/stockhandler.ashx?center=1");
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	Hose_DAO db = new Hose_DAO();
	    	ArrayList<Hose> listck = new ArrayList<>();
	    	  for(int i =0; i< json1.length(); i++){
	    		  JsonNode jsonNode = mapper.readTree(json1.get(i).toString());
	    		  Hose ck = new Hose();
	    		  ck.setId(jsonNode.get("a").asText());
	    		  ck.setRefer(jsonNode.get("b").asDouble());
	    		  ck.setCeiling(jsonNode.get("c").asDouble());
	    		  ck.setFloor(jsonNode.get("d").asDouble());
	    		  ck.setBuy_Price3(jsonNode.get("e").asDouble());
	    		  ck.setBuy_Amount3(jsonNode.get("f").asDouble());
	    		  ck.setBuy_Price2(jsonNode.get("g").asDouble());
	    		  ck.setBuy_Amount2(jsonNode.get("h").asDouble());
	    		  ck.setBuy_Price1(jsonNode.get("i").asDouble());
	    		  ck.setBuy_Amount1(jsonNode.get("j").asDouble());
	    		  ck.setUpDownOrder(jsonNode.get("k").asDouble());
	    		  ck.setOrder_Price(jsonNode.get("l").asDouble());
	    		  ck.setOrder_Amount(jsonNode.get("m").asDouble());
	    		  ck.setTotalAmount(jsonNode.get("n").asDouble());		  
	    		  ck.setSell_Price1(jsonNode.get("o").asDouble());
	    		  ck.setSell_Amount1(jsonNode.get("p").asDouble());
	    		  ck.setSell_Price2(jsonNode.get("q").asDouble());
	    		  ck.setSell_Amount2(jsonNode.get("r").asDouble());
	    		  ck.setSell_Price3(jsonNode.get("s").asDouble());
	    		  ck.setSell_Amount3(jsonNode.get("t").asDouble());
	    		  ck.setHigh(jsonNode.get("v").asDouble());
	    		  ck.setLow(jsonNode.get("w").asDouble());
	    		  ck.setTime(jsonNode.get("Time").asText());
	    		  ck.setTotal_buy(jsonNode.get("x").asDouble());
	    		  listck.add(ck);
	    		  db.update(ck);
	    	  }
	    }catch(Exception e) {
			
		}
	}
}
