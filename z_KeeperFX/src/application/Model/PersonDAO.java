package application.Model;

import java.util.Collection;

import javax.sql.RowSet;

import javafx.collections.ObservableList;

public interface PersonDAO {
	public int insertPerson();
	public boolean deletePerson();
	public Person findPerson();
	public boolean updatePerson();
	public RowSet selectPersonRS();
	public Collection selectPersonTO();
	public void printPersons();
	public ObservableList<Person> getPersonData();
}
