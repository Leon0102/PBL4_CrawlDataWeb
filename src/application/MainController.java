package application;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import BLL.ConditionMail_BLL;
import BLL.SendMail_BLL;
import DAO.ConditionMail_DAO;
import DAO.Email_DAO;
import DAO.Exchange_DAO;
import Model.Exchange;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
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
	private Button Btn_Mail;
	@FXML
	private Button Btn_News;
	@FXML
	private Button Btn_Main;
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
	private BarChart<String,Double> barchart2;
	@FXML
	private CategoryAxis x2Axis;
	@FXML
	private NumberAxis y2Axis;
	@FXML
	private ComboBox<String> comb;
	
	static boolean mail = true;
	public void initialize() {
	    Handle.initClock(dateTime);
	    ObservableList<String> list = FXCollections.observableArrayList("HNX","HOSE","UPCOM");
	    comb.setItems(list);
	    if(MainController.mail == true)
    	{
    	new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		    	Platform.runLater(() -> {
	                try {
	                	SendMail_BLL.sendmail(Email_DAO.GetEmail(),ketqua());
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            });
		    }
		},3000,300000);	
    	MainController.mail = false;
    	}   
	}
	public String ketqua() throws SQLException {
		String mck="";
		ObservableList<String> listM = ConditionMail_DAO.GetIdStock();
		for (String item : listM) {
			mck += ConditionMail_BLL.NotifyStock(item,"hose")+"\n"; // NOTE
		}
		return mck;
	}
	public void Cbb_change(ActionEvent e) {
		
		try {
		int s = comb.getSelectionModel().getSelectedIndex();
		switch (s) {
			case 0:
				barchart.getData().clear();
				barchart1.getData().clear();
				barchart2.getData().clear();
				barchart_show(barchart,comb.getSelectionModel().getSelectedItem().toString());
				barchart1_show(barchart1,comb.getSelectionModel().getSelectedItem().toString());
				barchart2_show(barchart2,comb.getSelectionModel().getSelectedItem().toString());
			break;
			case 1:
				barchart.getData().clear();
				barchart1.getData().clear();
				barchart2.getData().clear();
				barchart_show(barchart,comb.getSelectionModel().getSelectedItem().toString());
				barchart1_show(barchart1,comb.getSelectionModel().getSelectedItem().toString());
				barchart2_show(barchart2,comb.getSelectionModel().getSelectedItem().toString());
			break;
			case 2:
				barchart.getData().clear();
				barchart1.getData().clear();
				barchart2.getData().clear();
				barchart_show(barchart,comb.getSelectionModel().getSelectedItem().toString());
				barchart1_show(barchart1,comb.getSelectionModel().getSelectedItem().toString());
				barchart2_show(barchart2,comb.getSelectionModel().getSelectedItem().toString());
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
	public static void barchart2_show(BarChart bc,String name) {
		ObservableList<Exchange> listM = Exchange_DAO.findTopSan(name);
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Giá Sàn Thấp Nhất");
		 for(Exchange items : listM) {			 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getFloor()));
		 }		 
		 bc.getData().add(series);
	 }
	static boolean gdtthose = true;
	public void changetoGDTT_Hose(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/GDTT_Hose.fxml"));
		Btn_GDTT_HOSE.setStyle(" -fx-text-fill: black;");
		Btn_HNX.setStyle(null);
		Btn_HOSE.setStyle(null);
		Btn_UPCOM.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_News.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	static boolean gdtthnx = true;
	public void changetoGDTT_HNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/GDTT_HNX.fxml"));
		Btn_GDTT_HNX.setStyle(" -fx-text-fill: black;");
		Btn_HNX.setStyle(null);
		Btn_HOSE.setStyle(null);
		Btn_UPCOM.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_News.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
		
	}
	static boolean hose = true;
	public void changetoHose(ActionEvent e) throws IOException, InterruptedException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/Hose.fxml"));
		Btn_HOSE.setStyle(" -fx-text-fill: black;");
		Btn_HNX.setStyle(null);
		Btn_UPCOM.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_News.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	static boolean hnx = true;
	public void changetoHNX(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/HNX.fxml"));
		Btn_HNX.setStyle(" -fx-text-fill: black;");
		Btn_HOSE.setStyle(null);
		Btn_UPCOM.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_News.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	static boolean upcom = true;
	public void changetoUPCOM(ActionEvent e) throws IOException, InterruptedException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/UPCOM.fxml"));
		Btn_UPCOM.setStyle(" -fx-text-fill: black;");
		Btn_HOSE.setStyle(null);
		Btn_HNX.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_News.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoMail(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/SendMail.fxml"));
		Btn_Mail.setStyle(" -fx-text-fill: black;");
		Btn_UPCOM.setStyle(null);
		Btn_HOSE.setStyle(null);
		Btn_HNX.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_News.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoNews(ActionEvent e) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/application_FXML/News.fxml"));
		Btn_News.setStyle(" -fx-text-fill: black;");
		Btn_UPCOM.setStyle(null);
		Btn_HOSE.setStyle(null);
		Btn_HNX.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_Main.setStyle(null);
		mainpane.getChildren().removeAll();
		mainpane.getChildren().setAll(fxml);
	}
	public void changetoMain(ActionEvent e) throws IOException {
		Btn_News.setStyle(null);
		Btn_UPCOM.setStyle(null);
		Btn_HOSE.setStyle(null);
		Btn_HNX.setStyle(null);
		Btn_GDTT_HOSE.setStyle(null);
		Btn_GDTT_HNX.setStyle(null);
		Btn_Mail.setStyle(null);
		Btn_Main.setStyle(" -fx-text-fill: black;");
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application_FXML/Main.fxml"));
		Parent MainController = loader.load();
		Scene scene = new Scene(MainController);
		scene.getStylesheets().add(getClass().getResource("/application_FXML/application.css").toExternalForm());
		stage.setScene(scene);
	}
}
