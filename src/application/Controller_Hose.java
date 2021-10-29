package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import Controller.GDTT_Hose_Controller;
import Controller.Hose_Controller;
import DAO.Hose_DAO;
import Model.Hose;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Controller_Hose {
	@FXML
	private TableView<Hose> table;
	@FXML
    private Button Btn_Show;
	@FXML
    private Button Btn_Refill;
	@FXML
	private TableColumn<Hose, String> colId;
	@FXML
	private TableColumn<Hose, Double> colTC;
	@FXML
	private TableColumn<Hose, Double> colTran;
	@FXML
	private TableColumn<Hose, Double> colSan;
	@FXML
	private TableColumn<Hose, Double> colGiaMua3;
	@FXML
	private TableColumn<Hose, Double> colKLMua3;
	@FXML
	private TableColumn<Hose, Double> colGiaMua2;
	@FXML
	private TableColumn<Hose, Double> colKLMua2;
	@FXML
	private TableColumn<Hose, Double> colGiaMua1;
	@FXML
	private TableColumn<Hose, Double> colKLMua1;
	@FXML
	private TableColumn<Hose, Double> colUpDown;
	@FXML
	private TableColumn<Hose, Double> colGiaKL;
	@FXML
	private TableColumn<Hose, Double> colKL;
	@FXML
	private TableColumn<Hose, Double> colTongKL;
	@FXML
	private TableColumn<Hose, Double> colGiaBan1;
	@FXML
	private TableColumn<Hose, Double> colKLBan1;
	@FXML
	private TableColumn<Hose, Double> colGiaBan2;
	@FXML
	private TableColumn<Hose, Double> colKLBan2;
	@FXML
	private TableColumn<Hose, Double> colGiaBan3;
	@FXML
	private TableColumn<Hose, Double> colKLBan3;
	@FXML
	private TableColumn<Hose, Double> colCao;
	@FXML
	private TableColumn<Hose, Double> colThap;
	@FXML
	private TableColumn<Hose, Double> colNNMua;
	@FXML
	private TableColumn<Hose, String> colThoiGian;
	@FXML
	private BarChart<String, Double> barchart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	Label dateTime;
	@FXML
	private LineChart<String, Double> linechart;
	
	static ObservableList<Hose> listM = FXCollections.observableArrayList();
	
	public void initialize() {
		Handle.initClock(dateTime);
		new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		       try {
				refill();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		},0,5000);	
		new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		       show();
		    }
		},0,5000);	
    	barchart.getData().clear();
    	barchart_show(barchart);
	}
	 public static void barchart_show(BarChart bc) {
		 listM = Hose_DAO.findAll();
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Đầu tư Nước Ngoài");
		 for(Hose items : listM) {
			 if(items.getTotal_buy()>100)
			 {				 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getTotal_buy()));
			 }
		 }		 
			 bc.getData().add(series);
	 }
	 public static void linechart_show(LineChart lc) {
		 listM = Hose_DAO.findTop();
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Tham Chiếu Top 30");
		 for(Hose items : listM) {			 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getRefer()));
		 }
		 lc.getData().add(series);
	 }
	 public void show() {
	    	
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
	    	
	    	
	    	listM = Hose_DAO.findAll();
	    	
	    	table.setItems(listM);
//	    	barchart.getData().clear();
//	    	barchart_show(barchart);
	    	}catch(Exception e1) {
	    		e1.printStackTrace();
	    	}
	    }
	    public void refill() throws JSONException, IOException {
	    	if(table.getItems().isEmpty())
	    	{
	    		Hose_Controller.handle();
	    	}
	    	else {
	    		Hose_Controller.update();
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
