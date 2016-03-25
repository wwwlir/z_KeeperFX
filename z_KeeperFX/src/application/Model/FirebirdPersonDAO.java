package application.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;


import javax.sql.RowSet;

import javafx.collections.ObservableList;

public class FirebirdPersonDAO implements PersonDAO {
	
	int setIDPerson;
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
	public int insertPerson(Person person) {
		// TODO Auto-generated method stub
//		LocalDate ld = person.getBirthday();
//		Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
//		Date getBirthday = (Date) Date.from(instant);
		Date getBirthday = Date.valueOf(person.getBirthday());
		String strSQL = "insert into persons (fname, lname, address, phonenumbers, birthday) values (?,?,?,?,?)";
		try {
			Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(strSQL);
			stmt.setString(1, person.getFirstName());
			stmt.setString(2, person.getLastName());
			stmt.setString(3, person.getAddress());
			stmt.setString(4, person.getPhoneNumbers());
			stmt.setDate(5, getBirthday);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean deletePerson(Person person) {
		// TODO Auto-generated method stub
		findPerson(person);
		String strSQL = "delete from PERSONS where id=?";
		try {
			Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(strSQL);
			stmt.setInt(1, setIDPerson);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Person findPerson(Person person) {
		// TODO Auto-generated method stub
		String strSQL = "select ID from PERSONS where fname=? and lname=?";
		try {
			Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(strSQL);
			stmt.setString(1, person.getFirstName());
			stmt.setString(2, person.getLastName());
			ResultSet res =  stmt.executeQuery();
			res.next();
			int type = res.getInt("ID");
			this.setIDPerson = type;
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updatePerson(Person person) {
		// TODO Auto-generated method stub
		Date getBirthday = Date.valueOf(person.getBirthday());
		String strSQL = "update PERSONS set lname=?, fname=?, address=?, phonenumbers=?, birthday=? where id=?";
		try {
			Connection conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(strSQL);
			stmt.setString(1, person.getFirstName());
			stmt.setString(2, person.getLastName());
			stmt.setString(3, person.getAddress());
			stmt.setString(4, person.getPhoneNumbers());
			stmt.setDate(5, getBirthday);
			stmt.setInt(6, setIDPerson);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
