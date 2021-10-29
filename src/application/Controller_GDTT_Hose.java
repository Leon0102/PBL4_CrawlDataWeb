package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import Controller.GDTT_Hose_Controller;
import DAO.GDTT_Hose_DAO;
import Model.GDTT_Hose;
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

public class Controller_GDTT_Hose {
	@FXML 
	private TableView<GDTT_Hose> table;
	@FXML
    private Button Btn_Show;
	@FXML
    private Button Btn_Refill;
	@FXML
    private TableColumn<GDTT_Hose, String> colId;
    @FXML
    private TableColumn<GDTT_Hose, Double> colPrice;
    @FXML
    private TableColumn<GDTT_Hose, Integer> colAmount;
    @FXML
    private TableColumn<GDTT_Hose, Double> colValue;
    @FXML
    private TableColumn<GDTT_Hose, String> colTime;
    @FXML
	Label dateTime;
    
    ObservableList<GDTT_Hose> listM = FXCollections.observableArrayList();
    
    public void initialize() {
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
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                show();
	            });
		    }
		},1000,10000);	
	}
    public void show() {
    	
    	
    	colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
    	colPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
    	colAmount.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
    	colValue.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getValue()).asObject());
    	colTime.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getTime()));
    	
    	listM = GDTT_Hose_DAO.findAll();
    	table.setItems(listM);
    }
    public void refill() throws JSONException, IOException {
    	if(table.getItems().isEmpty())
    	{
    		GDTT_Hose_Controller.handle();
    	}
    	else {
    		GDTT_Hose_Controller.update();
    	}
    	Handle.infoBox("Completed",null,"Success" );
    }
    public void back(ActionEvent e) throws IOException {
    	Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Main.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
    }
}
