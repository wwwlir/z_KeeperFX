package application.Model;

import java.io.File;
import java.util.prefs.Preferences;

import application.Main;

public class XMLFileDAOFactory extends DAOFactory {

	@Override
	public PersonDAO getPersonDAO() {
		// TODO Auto-generated method stub
		return new XMLFilePersonDAO(getPersonFilePath());
	}

	@Override
	public DatabaseDAO getDatabaseDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public File getPersonFilePath() {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}

}
