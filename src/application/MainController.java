package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	
	public void changetoGDTT_Hose(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GDTT_Hose.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
	}
	public void changetoGDTT_HNX(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GDTT_HNX.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
	}
	public void changetoHose(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Hose.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
	}
	public void changetoHNX(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("HNX.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
	}
	public void changetoUPCOM(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UPCOM.fxml"));
		Parent GDTT_HoseView = loader.load();
		Scene scene = new Scene(GDTT_HoseView);
		stage.setScene(scene);
	}
}
