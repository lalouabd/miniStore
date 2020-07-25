package miniStore.store.models;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
	
//	private List<UUID> products;
	private String name , email, password;
	private Date dateOfBirth;
	private ImageT image;
	private double balance;
	private UUID id;
	
	
	
	
	
	
	
	public Person(UUID id,String name, String email,double bal, String password ,Date dob, ImageT image)
	{
		this(id ,name, email, password, bal,dob, image);
	}
	
	public Person(UUID id,String name, String email ,Date dob ,double bal)
	{
		this(id ,name, email, "",bal, dob, null);
	}
	public Person( UUID id,String name, String email, String password ,double bal, Date dob,ImageT image)
	{
		setBalance(bal);
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
		setDateOfBirth(dob);
		
		setImage(image);
	}
	
	
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	

	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public ImageT getImage() {
		return image;
	}
	public void setImage(ImageT image) {
		this.image = image;
	}
	

}
