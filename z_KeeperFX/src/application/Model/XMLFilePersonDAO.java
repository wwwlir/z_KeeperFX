package application.Model;

import java.io.File;
import java.util.Collection;
import java.util.prefs.Preferences;

import javax.sql.RowSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class XMLFilePersonDAO implements PersonDAO {
	
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private File file;
	
	public XMLFilePersonDAO(File file) {
		// TODO Auto-generated constructor stub
		this.file = file;
		if (file != null) {
	        loadPersonDataFromFile(file);
	    }
	}

	@Override
	public int insertPerson(Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deletePerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person findPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePerson(Person person) {
		// TODO Auto-generated method stub
		if (file != null) {
	        loadPersonDataFromFile(file);
	        return true;
	    }else{
	    	return false;
	    }		
	}

	@Override
	public RowSet selectPersonRS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectPersonTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printPersons() {
		// TODO Auto-generated method stub

	}

	@Override
	public ObservableList<Person> getPersonData() {		
		return personData;
	}
	
	public void loadPersonDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(PersonListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

	        personData.clear();
	        personData.addAll(wrapper.getPersons());

	        // Save the file path to the registry.
	        setPersonFilePath(file);

	    } catch (Exception e) { // catches ANY exception
	        Dialogs.create()
	                .title("Error")
	                .masthead("Could not load data from file:\n" + file.getPath())
	                .showException(e);
	    }
	}
	public void savePersonDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(PersonListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        PersonListWrapper wrapper = new PersonListWrapper();
	        wrapper.setPersons(personData);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        setPersonFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Dialogs.create().title("Error")
	                .masthead("Could not save data to file:\n" + file.getPath())
	                .showException(e);
	    }
	}
	public void setPersonFilePath(File file) {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());

	        // Update the stage title.
	        //primaryStage.setTitle("AddressApp - " + file.getName());
	    } else {
	        prefs.remove("filePath");

	        // Update the stage title.
	        //primaryStage.setTitle("AddressApp");
	    }
	}

}
