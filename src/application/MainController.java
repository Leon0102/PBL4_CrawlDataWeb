package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Button Btn_GDTT_HOSE;
	@FXML
	private Button Btn_HOSE;
	@FXML
	private Button Btn_GDTT_HNX;
	@FXML
	private Button Btn_HNX;
	@FXML
	private Button Btn_UPCOM;
	@FXML
	private StackPane mainpane;
	
	
	public void changetoGDTT_Hose(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("GDTT_Hose.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoGDTT_HNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("GDTT_HNX.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoHose(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("Hose.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoHNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("HNX.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoUPCOM(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("UPCOM.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
}
