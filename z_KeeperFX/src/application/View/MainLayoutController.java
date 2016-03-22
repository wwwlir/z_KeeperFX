package application.View;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainLayoutController {
	private Main mainApp;
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
	@FXML
	private void inicialize(){
		
	}
	@FXML
	private void showPersonView(){
		mainApp.showPersonOverview();
	}
}
