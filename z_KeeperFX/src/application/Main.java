package application;
	
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import application.Model.Person;
import application.Model.PersonListWrapper;
import application.View.MainLayoutController;
import application.View.PersonEditDialogController;
import application.View.PersonViewController;
import application.View.RootLayoutController;
import application.View.SettingEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	Stage primaryStage;
	BorderPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	
	{
		personData.add(new Person("Last name", "First name"));
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Title");
		this.primaryStage.getIcons().add(new Image("file:resources/images/Address_Book.png"));
		try {
			initRootLayout();
			showMainLayout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initRootLayout() throws IOException{
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
	        rootLayout = (BorderPane) loader.load();

	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);

	        RootLayoutController controller = loader.getController();
	        controller.setMainApp(this);

	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void showMainLayout() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("View/MainLayout.fxml"));
		AnchorPane mainLayout = (AnchorPane)loader.load();
		rootLayout.setCenter(mainLayout);
		
		MainLayoutController mainLController = loader.getController();
		mainLController.setMainApp(this);
	}
	public ObservableList<Person> getPersonData() {
        return personData;
    }
	public Stage getPrimaryStage() {
        return primaryStage;
    }
	public void showPersonOverview(){
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/PersonView.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            //rootLayout.clearConstraints(rootLayout);
            rootLayout.setCenter(personOverview);
            
            // Give the controller access to the main app.
            PersonViewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	    // Загружаем файл при открытии страницы контактов
	    File file = getPersonFilePath();
	    if (file != null) {
	        loadPersonDataFromFile(file);
	    }
	}
	public boolean showPersonEditDialog(Person person) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/PersonEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        PersonEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPerson(person);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean showSettingEditDialog(){
		
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/SettingEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        SettingEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getPersonFilePath() {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setPersonFilePath(File file) {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());

	        // Update the stage title.
	        primaryStage.setTitle("AddressApp - " + file.getName());
	    } else {
	        prefs.remove("filePath");

	        // Update the stage title.
	        primaryStage.setTitle("AddressApp");
	    }
	}
	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
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

	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
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
	public void loadPersonDataFromFirebird(){
		
	}
	public void savePersonDataToFirebird(){
		
	}
}
