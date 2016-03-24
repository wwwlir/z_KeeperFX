package application.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirebirdDatabaseDAO implements DatabaseDAO {
	
	Connection conn;
	
	public FirebirdDatabaseDAO(Connection conn){
		this.conn = conn;
	}
	@Override
	public void recreateDatabase() {
		// TODO Auto-generated method stub
		ResultSet rs;
		try {
			Statement stmt = conn.createStatement();
			
			//"select RDB$RELATION_NAME from RDB$RELATIONS where RDB$SYSTEM_FLAG = 0" //Выводит пользовательские таблицы
			//stmt.execute("DROP TABLE ACCOUNTS;");
			//stmt.execute("DROP TABLE PERSONS;");
			stmt.execute("CREATE TABLE ACCOUNTS("
					+ "ID int not null,"
					+ "NAME VARCHAR(50),"
					+ "LOGIN VARCHAR(50),"
					+ "PASSWORD VARCHAR(50),"
					+ "PRIMARY KEY (ID));");
			stmt.execute("CREATE TABLE PERSONS("
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
