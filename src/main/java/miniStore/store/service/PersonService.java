package miniStore.store.service;

import java.sql.SQLException;

import miniStore.store.dao.ImagesDao;
import miniStore.store.dao.PersonDao;
import miniStore.store.dao.PersonDataAcces;
import miniStore.store.dao.imagesDataAcces;
import miniStore.store.models.Person;

public class PersonService {
	private final PersonDao personDao;
	private final ImagesDao imagedao;
	
	public PersonService()
	{
		personDao = new PersonDataAcces();
		imagedao = new imagesDataAcces();
	
	}
	

	public boolean UpdatePerson(Person Person) throws SQLException{
	
		return personDao.UpdatePerson(Person);	
	}
	
	public Person login(String email,String password) throws SQLException
	{
		
		return personDao.login(email, password).orElse(null);
	}
	
	public boolean register(Person person)throws SQLException
	{
		imagedao.insertImage(person.getImage());
		return personDao.register(person);
	}
}