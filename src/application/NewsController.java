package application;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import DAO.Exchange_DAO;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;

public class NewsController {
	@FXML private ProgressBar progress;
	@FXML private Button btnSearch;
	@FXML private Button btnBack;
	@FXML private Button btnForward;
	@FXML private Button btnHistory;
	@FXML private ComboBox<String> cbbIdStock;
	@FXML private ComboBox<String> cbbExchange;
	@FXML private WebView webView;
	
	private WebEngine engine;
	private WebHistory history;
	
    public void Load() {
    	progress.setVisible(true);
    	engine = webView.getEngine();
    	loadPage();
    	
    	progress.progressProperty().bind(engine.getLoadWorker().progressProperty());
    	
    	engine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) ->{
    		if(newValue == Worker.State.SUCCEEDED) {
    			System.out.println("Page has been loaded");
    			progress.setVisible(false);
    		}else if (newValue == Worker.State.FAILED) {
    			System.out.println("Failed");
    		}
    	});
    }
    public void loadPage() {
    	String idstock = cbbIdStock.getSelectionModel().getSelectedItem().toString();
    	engine.load("https://cafef.vn/search/"+idstock+".chn");
    }
	public void displayHistory() {
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();	
		for(WebHistory.Entry entry : entries) {
			System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
		}
	}
public void back() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(-1);
	}
	
	public void forward() {
		
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(1);
	}
	
	public void initialize() {
		cbbExchange.setItems(Exchange_DAO.GetExchangeName());
		cbbExchange.setPromptText(Exchange_DAO.GetExchangeName().get(0));
		cbbIdStock.setItems(Exchange_DAO.GetStockId("Hose"));
		cbbIdStock.setPromptText(Exchange_DAO.GetStockId("Hose").get(0));
		progress.setVisible(false);
    }
	public void changeCbbExchange() {
		try {
			int s = cbbExchange.getSelectionModel().getSelectedIndex();
			switch (s) {
				case 0:
					cbbIdStock.setItems(Exchange_DAO.GetStockId("Hose"));
					cbbIdStock.setPromptText(Exchange_DAO.GetStockId("Hose").get(0));
				break;
				case 1:
					cbbIdStock.setItems(Exchange_DAO.GetStockId("HNX"));
					cbbIdStock.setPromptText(Exchange_DAO.GetStockId("HNX").get(0));
				break;
				case 2:
					cbbIdStock.setItems(Exchange_DAO.GetStockId("UPCOM"));
					cbbIdStock.setPromptText(Exchange_DAO.GetStockId("UPCOM").get(0));
				break;
			}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
	}
}
