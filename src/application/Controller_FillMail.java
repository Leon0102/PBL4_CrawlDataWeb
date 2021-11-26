package application;

import DAO.Email_DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_FillMail {
	@FXML
	private TextField txtEmail;
	public void initialize() {
		txtEmail.setText(Email_DAO.GetEmail());
	}
	public void SaveEmail(ActionEvent e)
	{
		Email_DAO.Insert(txtEmail.getText());
		try {
			Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application_FXML/Main.fxml"));
	        Parent studentViewParent = loader.load();
	        Scene scene = new Scene(studentViewParent);
	        scene.getStylesheets().add(getClass().getResource("/application_FXML/application.css").toExternalForm());
	        stage.setScene(scene);
	        stage.centerOnScreen();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}
