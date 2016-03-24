package application.Model;

import java.sql.Connection;
import java.util.Collection;

import javax.sql.RowSet;

import javafx.collections.ObservableList;

public class FirebirdPersonDAO implements PersonDAO {
	
	Connection conn;
	
	public FirebirdPersonDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertPerson() {
		// TODO Auto-generated method stub
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
