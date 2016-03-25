package application.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FirebirdDatabaseDAO implements DatabaseDAO {
	
	public final String DRIVER = "org.firebirdsql.jdbc.FBDriver";
	public final String DBURL = "jdbc:firebirdsql:embedded:C:\\FirebirdDatabase\\FDBT.FDB";
	
	public Connection createConnection(){
		Connection conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DBURL, "SYSDBA", "masterkey");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	@Override
	public void recreateDatabase() {
		// TODO Auto-generated method stub
		try {
			Connection conn = createConnection();
			Statement stmt = conn.createStatement();
			
			//"select RDB$RELATION_NAME from RDB$RELATIONS where RDB$SYSTEM_FLAG = 0" //Выводит пользовательские таблицы
			//stmt.execute("DROP TABLE ACCOUNTS;");
			//stmt.execute("DROP TABLE PERSONS;");
			stmt.executeUpdate("CREATE TABLE ACCOUNTS("
					+ "ID int not null,"
					+ "NAME VARCHAR(50),"
					+ "LOGIN VARCHAR(50),"
					+ "PASSWORD VARCHAR(50),"
					+ "PRIMARY KEY (ID));");
			stmt.executeUpdate("CREATE TABLE PERSONS("
					+ "ID int not null,"
					+ "FNAME VARCHAR(50),"
					+ "LNAME VARCHAR(50),"
					+ "ADDRESS VARCHAR(50),"
					+ "PHONENUMBERS VARCHAR(50),"
					+ "BIRTHDAY DATE,"
					+ "PRIMARY KEY (ID));");
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public double getSizeDatabase() {
		// TODO Auto-generated method stub
		return 0;
	}

}
