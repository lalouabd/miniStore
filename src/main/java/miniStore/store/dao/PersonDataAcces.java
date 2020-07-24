package miniStore.store.dao;

import java.util.Optional;

import miniStore.store.models.Person;
import miniStore.store.service.JdbcQuerys;

public class PersonDataAcces implements PersonDao {

	private final JdbcQuerys< Person> dbCon;
	
	public PersonDataAcces() {
	
		dbCon = new JdbcQuerys<>();
	}
	
 	@Override
	public boolean register(Person person) {
		
		return false;
	}

	@Override
	public Optional<Person> login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPersonByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdatePerson(Person Person) {
		// TODO Auto-generated method stub
		return false;
	}

}
