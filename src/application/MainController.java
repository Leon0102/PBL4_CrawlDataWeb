package application;

import java.io.IOException;


import DAO.Exchange_DAO;
import Model.Exchange;
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
import javafx.scene.layout.Pane;
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
	private BarChart<String,Double> barchart;
	@FXML
	private BarChart<String,Double> barchart1;
	@FXML
	private CategoryAxis x1Axis;
	@FXML
	private NumberAxis y1Axis;
	@FXML
	private ComboBox<String> comb;
	

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
				barchart.getData().clear();
				barchart1.getData().clear();
				Controller_HNX.linechart_show(linechart);
				barchart_show(barchart,comb.getSelectionModel().getSelectedItem().toString());
				barchart1_show(barchart1,comb.getSelectionModel().getSelectedItem().toString());
			break;
			case 1:
				linechart.getData().clear();
				barchart.getData().clear();
				barchart1.getData().clear();
				Controller_Hose.linechart_show(linechart);
				barchart_show(barchart,comb.getSelectionModel().getSelectedItem().toString());
				barchart1_show(barchart1,comb.getSelectionModel().getSelectedItem().toString());
			break;
			case 2:
				linechart.getData().clear();
				barchart.getData().clear();
				barchart1.getData().clear();
				Controller_UPCOM.linechart_show(linechart);
				barchart_show(barchart,comb.getSelectionModel().getSelectedItem().toString());
				barchart1_show(barchart1,comb.getSelectionModel().getSelectedItem().toString());
			break;
		}
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	public static void barchart_show(BarChart bc,String name) {
		ObservableList<Exchange> listM = Exchange_DAO.findTopFloor(name);
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Giá Trần Cao Nhất");
		 for(Exchange items : listM) {			 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getFloor()));
		 }		 
			 bc.getData().add(series);
	 }
	public static void barchart1_show(BarChart bc,String name) {
		ObservableList<Exchange> listM = Exchange_DAO.findTopKLAll(name);
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Tổng Khớp Lệnh Cao Nhất");
		 for(Exchange items : listM) {			 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getFloor()));
		 }		 
			 bc.getData().add(series);
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
	public void changetoHose(ActionEvent e) throws IOException, InterruptedException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/Hose.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoHNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/HNX.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoUPCOM(ActionEvent e) throws IOException, InterruptedException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/UPCOM.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoMail(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/SendMail.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoNews(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/News.fxml"));
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoMain(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application_FXML/Main.fxml"));
		Parent MainController = loader.load();
		Scene scene = new Scene(MainController);
		stage.setScene(scene);
	}
}
