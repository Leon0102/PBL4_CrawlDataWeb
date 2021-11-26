package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import Controller.GDTT_Exchange_Controller;
import Controller.JsonReader;
import DAO.GDTT_Exchange_DAO;
import Model.GDTT_Exchange;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller_GDTT_HNX {
	@FXML 
	private TableView<GDTT_Exchange> table;
	@FXML
    private Button Btn_Show;
	@FXML
    private Button Btn_Refill;
	@FXML
    private TableColumn<GDTT_Exchange, String> colId;
    @FXML
    private TableColumn<GDTT_Exchange, Double> colPrice;
    @FXML
    private TableColumn<GDTT_Exchange, Integer> colAmount;
    @FXML
    private TableColumn<GDTT_Exchange, Double> colValue;
    @FXML
    private TableColumn<GDTT_Exchange, String> colTime;
    @FXML
    private Label dateTime;
    @FXML
    private Label responseCode;
    @FXML
    private Circle circle;
    
    public void responseCodeChange() throws IOException {
    	int code = JsonReader.getResponseCode("https://banggia.cafef.vn/TTHandler.ashx?center=2");
    	String kq = String.valueOf(code);
    	responseCode.setText(kq);
    	if(kq.equals("200"))
    	{
    		circle.setFill(Color.GREEN);    		
    	}else {
    		circle.setFill(Color.RED);
    	}
    }
    
    ObservableList<GDTT_Exchange> listM = FXCollections.observableArrayList();
    
    public void initialize() throws IOException {
    	Handle.initClock(dateTime);
    	show();
    	new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		    	Platform.runLater(() -> {
	                try {
	                	refill();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	                catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            });
		    }
		},1000,300000);	
	}
    public void show() throws IOException {
    	responseCodeChange();
    	
    	colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
    	colPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
    	colAmount.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
    	colValue.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getValue()).asObject());
    	colTime.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getTime()));
    	
//    	colPrice.setCellFactory(new Callback<TableColumn<GDTT_Exchange,Double>, TableCell<GDTT_Exchange,Double>>() {
//            public TableCell<GDTT_Exchange, Double> call(TableColumn<GDTT_Exchange,Double> param) {
//                return new TableCell<GDTT_Exchange, Double>() {
//
//                    @Override
//                    public void updateItem(Double item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (!isEmpty()) {
//                        	for(int i = 0;i<listM.size();i++) {		
////                        		System.out.println(listM.get(i).getPrice());
//                        		if(item.doubleValue()>listM.get(i).getPrice()) {
//                        			this.setTextFill(Color.GREEN);
//                        			setText(item.toString());                        		
//                        		}
//                        		else {
//                        			this.setTextFill(Color.RED);
//                        			setText(item.toString());
//                        		}
//                        	}
//                        }
//                    }
//                };
//            }
//        });
    	
    	listM = GDTT_Exchange_DAO.findAll("hnx");
    	
    	table.setItems(listM);
    }
    public  void refill() throws JSONException, IOException {
    	if(table.getItems().isEmpty())
    	{
    		GDTT_Exchange_Controller.handle("hnx");
    	}
    	else {
    		GDTT_Exchange_Controller.update("hnx");
    	}
    }
}
