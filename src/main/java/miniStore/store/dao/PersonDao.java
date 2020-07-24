package miniStore.store.dao;

import java.util.Optional;

import miniStore.store.models.Person;

public interface PersonDao {
	
	boolean register(Person person);
	
	Optional<Person> login(String email , String password);
	
	boolean checkPersonByEmail(String email);
	
	boolean UpdatePerson(Person Person);
}
