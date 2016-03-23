package application.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FirefirdDAOFactory extends DAOFactory {

	public static final String DRIVER = "org.firebirdsql.jdbc.FBDriver";
	public static final String DBURL = "jdbc:firebirdsql:embedded:C:\\FirebirdDatabase\\FDBT.FDB";
	
	public static Connection createConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBURL, "SYSDBA", "masterkey");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	public PersonDAO getPersonDAO() {
		// TODO Auto-generated method stub
		return new FirebirdPersonDAO(createConnection());
	}

}
