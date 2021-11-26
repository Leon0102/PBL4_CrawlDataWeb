package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import Controller.Exchange_Controller;
import Controller.GDTT_Exchange_Controller;
import Controller.JsonReader;
import Controller.Exchange_Controller;
import DAO.Exchange_DAO;
import Model.Exchange;
import javafx.application.Platform;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller_Hose {
	@FXML
	private TableView<Exchange> table;
	@FXML
    private Button Btn_Show;
	@FXML
    private Button Btn_Refill;
	@FXML
	private TableColumn<Exchange, String> colId;
	@FXML
	private TableColumn<Exchange, Double> colTC;
	@FXML
	private TableColumn<Exchange, Double> colTran;
	@FXML
	private TableColumn<Exchange, Double> colSan;
	@FXML
	private TableColumn<Exchange, Double> colGiaMua3;
	@FXML
	private TableColumn<Exchange, Double> colKLMua3;
	@FXML
	private TableColumn<Exchange, Double> colGiaMua2;
	@FXML
	private TableColumn<Exchange, Double> colKLMua2;
	@FXML
	private TableColumn<Exchange, Double> colGiaMua1;
	@FXML
	private TableColumn<Exchange, Double> colKLMua1;
	@FXML
	private TableColumn<Exchange, Double> colUpDown;
	@FXML
	private TableColumn<Exchange, Double> colGiaKL;
	@FXML
	private TableColumn<Exchange, Double> colKL;
	@FXML
	private TableColumn<Exchange, Double> colTongKL;
	@FXML
	private TableColumn<Exchange, Double> colGiaBan1;
	@FXML
	private TableColumn<Exchange, Double> colKLBan1;
	@FXML
	private TableColumn<Exchange, Double> colGiaBan2;
	@FXML
	private TableColumn<Exchange, Double> colKLBan2;
	@FXML
	private TableColumn<Exchange, Double> colGiaBan3;
	@FXML
	private TableColumn<Exchange, Double> colKLBan3;
	@FXML
	private TableColumn<Exchange, Double> colCao;
	@FXML
	private TableColumn<Exchange, Double> colThap;
	@FXML
	private TableColumn<Exchange, Double> colNNMua;
	@FXML
	private TableColumn<Exchange, String> colThoiGian;
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
	@FXML
    private Label responseCode;
    @FXML
    private Circle circle;
    
    public void responseCodeChange() throws IOException {
    	int code = JsonReader.getResponseCode("https://banggia.cafef.vn/stockhandler.ashx?center=1");
    	String kq = String.valueOf(code);
    	responseCode.setText(kq);
    	if(kq.equals("200"))
    	{
    		circle.setFill(Color.GREEN);    		
    	}else {
    		circle.setFill(Color.RED);
    	}
    }
	
	static ObservableList<Exchange> listM = FXCollections.observableArrayList();
	@FXML
    void commitValue(TableColumn.CellEditEvent<Exchange, String> event) {
        System.out.println("Commit: " + event.getNewValue());
    }
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
					} 
	                catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            });
		    }
		},3000,300000);	
	}
	 public static void barchart_show(BarChart bc) {
		 listM = Exchange_DAO.findAll("hose");
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Đầu tư Nước Ngoài");
		 for(Exchange items : listM) {
			 if(items.getTotal_buy()>100)
			 {				 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getTotal_buy()));
			 }
		 }		 
			 bc.getData().add(series);
	 }
	 public static void linechart_show(LineChart lc) {
		 listM = Exchange_DAO.findTop("hose");
		 XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		 series.setName("Đồ thị Tham Chiếu Top 30");
		 for(Exchange items : listM) {			 
				 series.getData().add(new XYChart.Data<String, Double>(items.getId(),items.getRefer()));
		 }
		 lc.getData().add(series);
	 }
	 public void show() {
	    	
	    	try {
	    		responseCodeChange();
	    		
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
	    	
	    	
	    	listM = Exchange_DAO.findAll("hose");
	    	colGiaKL.setCellFactory(new Callback<TableColumn<Exchange,Double>, TableCell<Exchange,Double>>() {
	            public TableCell<Exchange, Double> call(TableColumn<Exchange,Double> param) {
	                return new TableCell<Exchange, Double>() {

	                    @Override
	                    public void updateItem(Double item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (!isEmpty()) {	
//	                        		System.out.println(listM.get(this.getIndex()).getRefer());
	                        		if(item.doubleValue()>listM.get(this.getIndex()).getRefer()) {
	                        			this.setTextFill(Color.GREEN);
	                        			setText(item.toString());                        		
	                        		}
	                        		else {
	                        			this.setTextFill(Color.RED);
	                        			setText(item.toString());
	                        		}
	                        }
	                    }
	                };
	            }
	        });
	    	colKL.setCellFactory(new Callback<TableColumn<Exchange,Double>, TableCell<Exchange,Double>>() {
	            public TableCell<Exchange, Double> call(TableColumn<Exchange,Double> param) {
	                return new TableCell<Exchange, Double>() {

	                    @Override
	                    public void updateItem(Double item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (!isEmpty()) {	
//	                        		System.out.println(listM.get(this.getIndex()).getRefer());
	                        		if(item.doubleValue()>listM.get(this.getIndex()).getRefer()) {
	                        			this.setTextFill(Color.GREEN);
	                        			setText(item.toString());                        		
	                        		}
	                        		else {
	                        			this.setTextFill(Color.RED);
	                        			setText(item.toString());
	                        		}
	                        }
	                    }
	                };
	            }
	        });
	    	colCao.setCellFactory(new Callback<TableColumn<Exchange,Double>, TableCell<Exchange,Double>>() {
	            public TableCell<Exchange, Double> call(TableColumn<Exchange,Double> param) {
	                return new TableCell<Exchange, Double>() {

	                    @Override
	                    public void updateItem(Double item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (!isEmpty()) {	
//	                        		System.out.println(listM.get(this.getIndex()).getRefer());
	                        		if(item.doubleValue()>listM.get(this.getIndex()).getRefer()) {
	                        			this.setTextFill(Color.GREEN);
	                        			setText(item.toString());                        		
	                        		}
	                        		else {
	                        			this.setTextFill(Color.RED);
	                        			setText(item.toString());
	                        		}
	                        }
	                    }
	                };
	            }
	        });
	    	colThap.setCellFactory(new Callback<TableColumn<Exchange,Double>, TableCell<Exchange,Double>>() {
	            public TableCell<Exchange, Double> call(TableColumn<Exchange,Double> param) {
	                return new TableCell<Exchange, Double>() {

	                    @Override
	                    public void updateItem(Double item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (!isEmpty()) {	
//	                        		System.out.println(listM.get(this.getIndex()).getRefer());
	                        		if(item.doubleValue()>listM.get(this.getIndex()).getRefer()) {
	                        			this.setTextFill(Color.GREEN);
	                        			setText(item.toString());                        		
	                        		}
	                        		else {
	                        			this.setTextFill(Color.RED);
	                        			setText(item.toString());
	                        		}
	                        }
	                    }
	                };
	            }
	        });
	    	
	    	table.setItems(listM);
	    	barchart.getData().clear();
	    	barchart_show(barchart);
	    	}catch(Exception e1) {
	    		e1.printStackTrace();
	    	}
	    }
	    public void refill() throws JSONException, IOException {
	    	if(table.getItems().isEmpty())
	    	{
	    		Exchange_Controller.handle("hose");
	    	}
	    	else {
	    		Exchange_Controller.update("hose");
	    	}
	    }
}
