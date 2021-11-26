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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller_GDTT_Hose {
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
	Label dateTime;
    @FXML
    private Label responseCode;
    @FXML
    private Circle circle;
    
    public void responseCodeChange() throws IOException {
    	int code = JsonReader.getResponseCode("https://banggia.cafef.vn/TTHandler.ashx?center=1");
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
    	
    	listM = GDTT_Exchange_DAO.findAll("hose");
    	table.setItems(listM);
    }
    public void refill() throws JSONException, IOException {
    	if(table.getItems().isEmpty())
    	{
    		GDTT_Exchange_Controller.handle("hose");
    	}
    	else {
    		GDTT_Exchange_Controller.update("hose");
    	}
    }
    public void back(ActionEvent e) throws IOException {
    	Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Main.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
    }
    public static Object getValueAt(TableView<GDTT_Exchange> table, int column, int row) {
        return table.getColumns().get(column).getCellObservableValue(row).getValue();
    }
}
