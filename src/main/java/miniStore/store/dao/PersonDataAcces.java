package miniStore.store.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import miniStore.store.models.ImageT;
import miniStore.store.models.Person;
import miniStore.store.service.JdbcQuerys;

public class PersonDataAcces implements PersonDao {

	private final JdbcQuerys< Person> dbCon;
	private final ImagesDao imagedao;
	public PersonDataAcces() {
	
		dbCon = new JdbcQuerys<>();
		imagedao = new imagesDataAcces();

	}
	
 	@Override
	public boolean register(Person person)throws SQLException {
 		String sql = "insert into person("
 				+ "name,"
 				+ "email,"
 				+ "password,"
 				+ "dob,"
 				+ "balance,"
 				+ "id)values(?,?,?,?,?,?)";
 		
		
		return  dbCon.update(
				sql,
				person.getName(),
				person.getEmail(),
				person.getPassword(),
				person.getDateOfBirth().toString(),
				person.getBalance(),
				person.getId());
	}

	@Override
	public Optional<Person> login(String email, String password) throws SQLException{
		Optional<Person> op ;
		String sql = "select * from person where email=?";
		op = dbCon.queryForObject(sql,result->{
			try {
				if (result.getString("password").equals(password))
				{
					
						Person per = new Person(
								UUID.fromString(result.getString("id")),
								result.getString("name"),
								result.getString("email"),
								java.sql.Date.valueOf(result.getString("dob")),
								Double.parseDouble(result.getString("balance"))
								);
						per.setImage(
								imagedao.getImageByOwnerId(per.getId())
								.orElse(
										new ImageT(
												new File("src/main/resources/images/defimage.png"),
												per.getId())));
						return per;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}, email);
		return op;
	}

	@Override
	public boolean checkPersonByEmail(String email) throws SQLException{
		String sql = "select * from person where email=?";
		Optional<Person > op = dbCon.queryForObject(sql,
				result->{
			try {
				
				
					
						Person per = new Person(
								UUID.fromString(result.getString("id")),
								result.getString("name"),
								result.getString("email"),
								result.getDate("dob"),
								Double.parseDouble(result.getString("balance"))
								);
				return per;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}, email);
		return !op.isPresent();
	}

	@Override
	public boolean UpdatePerson(Person person) throws SQLException{
		String sql = "update person set name=?"
				+ ",dob=?"
				+ ",balance=? where email=?";
		
		return dbCon.update(sql, 
				person.getName()
				,person.getDateOfBirth().toString()
				,person.getBalance()
				,person.getEmail());
	}

}
