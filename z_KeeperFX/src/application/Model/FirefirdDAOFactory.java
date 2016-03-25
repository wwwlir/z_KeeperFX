package application.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FirefirdDAOFactory extends DAOFactory {

	
	public PersonDAO getPersonDAO() {
		// TODO Auto-generated method stub
		return new FirebirdPersonDAO();
	}
	public DatabaseDAO getDatabaseDAO(){
		return new FirebirdDatabaseDAO();
	}
}
