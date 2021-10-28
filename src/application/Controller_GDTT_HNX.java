package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONException;

import Controller.GDTT_HNX_Controller;
import Controller.GDTT_Hose_Controller;
import DAO.GDTT_HNX_DAO;
import Model.GDTT_HNX;
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

public class Controller_GDTT_HNX {
	@FXML 
	private TableView<GDTT_HNX> table;
	@FXML
    private Button Btn_Show;
	@FXML
    private Button Btn_Refill;
	@FXML
    private TableColumn<GDTT_HNX, String> colId;
    @FXML
    private TableColumn<GDTT_HNX, Double> colPrice;
    @FXML
    private TableColumn<GDTT_HNX, Integer> colAmount;
    @FXML
    private TableColumn<GDTT_HNX, Double> colValue;
    @FXML
    private TableColumn<GDTT_HNX, String> colTime;
    @FXML
	Label dateTime;
    
    ObservableList<GDTT_HNX> listM = FXCollections.observableArrayList();
    
    public void initialize() {
    	Handle.initClock(dateTime);
	}
    public void show(ActionEvent e) {
    	
    	
    	colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
    	colPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
    	colAmount.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
    	colValue.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getValue()).asObject());
    	colTime.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getTime()));
    	
    	listM = GDTT_HNX_DAO.findAll();
    	
    	table.setItems(listM);
    }
    public void refill(ActionEvent e) throws JSONException, IOException {
    	if(table.getItems().isEmpty())
    	{
    		GDTT_Hose_Controller.handle();
    	}
    	else {
    		GDTT_Hose_Controller.update();
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
}
