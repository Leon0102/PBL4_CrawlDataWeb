package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import Controller.SendMail_Controller;
import DAO.Hose_DAO;
import Model.Hose;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class SendMailController {
	@FXML
	private TextField txtNhapMail;
	@FXML
	private Button btnReceiveMail;
	
	ObservableList<Hose> listM = FXCollections.observableArrayList();
	
	public void sendMail(ActionEvent e) throws IOException {
		listM = Hose_DAO.findTop();
		SendMail_Controller.sendmail(txtNhapMail.getText(),listM);
	}
}
