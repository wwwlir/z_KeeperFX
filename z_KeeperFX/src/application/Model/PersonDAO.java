package application.Model;

import java.util.Collection;

import javax.sql.RowSet;

import javafx.collections.ObservableList;

public interface PersonDAO {
	public int insertPerson(Person person);
	public boolean deletePerson(Person person);
	public Person findPerson(Person person);
	public boolean updatePerson(Person person);
	public RowSet selectPersonRS();
	public Collection selectPersonTO();
	public void printPersons();
	public ObservableList<Person> getPersonData();
}
