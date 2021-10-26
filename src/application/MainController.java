package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONException;

import Controller.Hose_Controller;
import DAO.Hose_DAO;
import Model.Hose;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
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
	
	ObservableList<Hose> listM = FXCollections.observableArrayList();
	
	 public void initialize(URL location, ResourceBundle resources) {
         
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
	    	
	    	
	    	listM = Hose_DAO.findAll();
	    	
	    	table.setItems(listM);
	    	}catch(Exception e1) {
	    		e1.printStackTrace();
	    	}
	    }
	    public void refill(ActionEvent e) throws JSONException, IOException {
	    	Hose_Controller.handle();
	    }

}
