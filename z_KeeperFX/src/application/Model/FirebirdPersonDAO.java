package application.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.sql.RowSet;

import javafx.collections.ObservableList;

public class FirebirdPersonDAO implements PersonDAO {
	
	Connection conn;
	
	public FirebirdPersonDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertPerson(Person person) {
		// TODO Auto-generated method stub
		String strSQL = "insert into persons (fname, lname, address, phonenumbers, birthday) values ("
				+"'"+person.getFirstName()+"',"
				+"'"+person.getLastName()+"',"
				+"'"+person.getAddress()+"',"
				+"'"+person.getPhoneNumbers()+"',"
				+"'"+person.getBirthday()+"'"
				+ ")";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(strSQL);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean deletePerson() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person findPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePerson() {
		// TODO Auto-generated method stub
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
