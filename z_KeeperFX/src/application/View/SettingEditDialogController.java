package application.View;

import application.Main;
import application.Model.DAOFactory;
import application.Model.DatabaseDAO;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SettingEditDialogController {
	private Stage dialogStage;
	private Main mainApp;
	private boolean okClicked = false;
	@FXML
	private void initialize(){
		
	}
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	public boolean isOkClicked() {
        return okClicked;
    }
	@FXML
	private void handleOK(){
		okClicked = true;
		dialogStage.close();
	}
	@FXML
	private void handleCansel(){
		okClicked = false;
		dialogStage.close();
	}
	@FXML
	private void clearDatabase(){
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.FIREBIRD);
		DatabaseDAO databaseDAO = factory.getDatabaseDAO();
		databaseDAO.recreateDatabase();
	}
	
}
