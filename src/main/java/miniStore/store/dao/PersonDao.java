package miniStore.store.dao;

import java.sql.SQLException;
import java.util.Optional;

import miniStore.store.models.Person;

public interface PersonDao {
	
	boolean register(Person person)throws SQLException;
	
	Optional<Person> login(String email , String password)throws SQLException;
	
	Optional<Person> getPersonByEmail(String email)throws SQLException;
	
	boolean UpdatePerson(Person Person)throws SQLException;
}
