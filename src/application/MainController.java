package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.UPCOM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private Pane mainpane;
	@FXML
	Label dateTime;
	@FXML
	private LineChart<String, Double> linechart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private ComboBox comb;

	public void initialize() {
	    Handle.initClock(dateTime);
	    ObservableList<String> list = FXCollections.observableArrayList("HNX","HOSE","UPCOM");
	    comb.setItems(list);
	}
	public void Cbb_change(ActionEvent e) {
		
		try {
		int s = comb.getSelectionModel().getSelectedIndex();
		switch (s) {
			case 0:
				linechart.getData().clear();
				Controller_HNX.linechart_show(linechart);
			break;
			case 1:
				linechart.getData().clear();
				Controller_Hose.linechart_show(linechart);
			break;
			case 2:
				linechart.getData().clear();
				Controller_UPCOM.linechart_show(linechart);
			break;
		}
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	public void changetoGDTT_Hose(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/GDTT_Hose.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoGDTT_HNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/GDTT_HNX.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoHose(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/Hose.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoHNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/HNX.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoUPCOM(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/UPCOM.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoMail(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/SendMail.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
}
