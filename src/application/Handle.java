package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Handle {
	static void initClock(Label d) {
	    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        d.setText(LocalDateTime.now().format(formatter));
	    }), new KeyFrame(Duration.seconds(1)));
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	}
	public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
