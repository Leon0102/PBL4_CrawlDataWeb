package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONException;

import Controller.UPCOM_Controller;
import DAO.UPCOM_DAO;
import Model.UPCOM;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Controller_UPCOM {
	@FXML
	private TableView<UPCOM> table;
	@FXML
    private Button Btn_Show;
	@FXML
    private Button Btn_Refill;
	@FXML
	private TableColumn<UPCOM, String> colId;
	@FXML
	private TableColumn<UPCOM, Double> colTC;
	@FXML
	private TableColumn<UPCOM, Double> colTran;
	@FXML
	private TableColumn<UPCOM, Double> colSan;
	@FXML
	private TableColumn<UPCOM, Double> colGiaMua3;
	@FXML
	private TableColumn<UPCOM, Double> colKLMua3;
	@FXML
	private TableColumn<UPCOM, Double> colGiaMua2;
	@FXML
	private TableColumn<UPCOM, Double> colKLMua2;
	@FXML
	private TableColumn<UPCOM, Double> colGiaMua1;
	@FXML
	private TableColumn<UPCOM, Double> colKLMua1;
	@FXML
	private TableColumn<UPCOM, Double> colUpDown;
	@FXML
	private TableColumn<UPCOM, Double> colGiaKL;
	@FXML
	private TableColumn<UPCOM, Double> colKL;
	@FXML
	private TableColumn<UPCOM, Double> colTongKL;
	@FXML
	private TableColumn<UPCOM, Double> colGiaBan1;
	@FXML
	private TableColumn<UPCOM, Double> colKLBan1;
	@FXML
	private TableColumn<UPCOM, Double> colGiaBan2;
	@FXML
	private TableColumn<UPCOM, Double> colKLBan2;
	@FXML
	private TableColumn<UPCOM, Double> colGiaBan3;
	@FXML
	private TableColumn<UPCOM, Double> colKLBan3;
	@FXML
	private TableColumn<UPCOM, Double> colCao;
	@FXML
	private TableColumn<UPCOM, Double> colThap;
	@FXML
	private TableColumn<UPCOM, Double> colNNMua;
	@FXML
	private TableColumn<UPCOM, String> colThoiGian;
	@FXML
	private BarChart<String, Double> barchart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	
	
	ObservableList<UPCOM> listM = FXCollections.observableArrayList();
	
	 public void initialize(URL location, ResourceBundle resources) {
         
	    }
	 public void barchart_show() {
		 listM = UPCOM_DAO.findAll();
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Đầu tư Nước Ngoài");
		 for(UPCOM items : listM) {
			 if(items.getTotal_buy()>1000)
			 {				 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getTotal_buy()));
			 }
		 }
		 barchart.getData().add(series);
	 }
	 public void show(ActionEvent e) {
	    	
	    	try {
	    		
	    	colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
	    	colTC.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getRefer()).asObject());
	    	colTran.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getCeiling()).asObject());
	    	colSan.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getFloor()).asObject());
	    	colGiaMua3.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getBuy_Price3()).asObject());
	    	colKLMua3.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getBuy_Amount3()).asObject());
	    	colGiaMua2.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getBuy_Price2()).asObject());
	    	colKLMua2.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getBuy_Amount2()).asObject());
	    	colGiaMua1.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getBuy_Price1()).asObject());
	    	colKLMua1.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getBuy_Amount1()).asObject());
	    	colUpDown.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getUpDownOrder()).asObject());
	    	colGiaKL.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getOrder_Price()).asObject());
	    	colKL.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getOrder_Amount()).asObject());
	    	colTongKL.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getTotalAmount()).asObject());
	    	colGiaBan1.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getSell_Price1()).asObject());
	    	colKLBan1.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getSell_Amount1()).asObject());
	    	colGiaBan2.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getSell_Price2()).asObject());
	    	colKLBan2.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getSell_Amount2()).asObject());
	    	colGiaBan3.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getSell_Price3()).asObject());
	    	colKLBan3.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getSell_Amount3()).asObject());
	    	colCao.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getHigh()).asObject());
	    	colThap.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getLow()).asObject());
	    	colNNMua.setCellValueFactory(cellData ->new SimpleDoubleProperty(cellData.getValue().getTotal_buy()).asObject());
	    	colThoiGian.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getTime()));
	    	
	    	
	    	listM = UPCOM_DAO.findAll();
	    	
	    	table.setItems(listM);
	    	barchart_show();
	    	}catch(Exception e1) {
	    		e1.printStackTrace();
	    	}
	    }
	    public void refill(ActionEvent e) throws JSONException, IOException {
	    	UPCOM_Controller.handle();
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
