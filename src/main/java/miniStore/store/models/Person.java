package miniStore.store.models;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
	
	private List<UUID> products;
	private String name , email, password;
	private Date dateOfBirth;
	private Image image;
	private double balance;
	private UUID id;
	
	
	
	
	
	public Person(String name ,String email,String password, Date dob)
	{
	
		this(UUID.randomUUID(),name ,email, password, dob,new Image());
	}
	
	public Person(UUID id,String name, String email, String password ,Date dob, Image image)
	{
		this(id ,name, email, password, dob, new ArrayList<UUID>(), image);
	}
	
	public Person(UUID id,String name, String email ,Date dob )
	{
		this(id ,name, email, "", dob, new ArrayList<UUID>(), null);
	}
	public Person( UUID id,String name, String email, String password , Date dob, List<UUID> products,Image image)
	{
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
		setDateOfBirth(dob);
		setProducts(products);
		setImage(image);
	}
	
	public boolean addProduct(UUID id)
	{
		if (id != null)
		{
			if(!products.contains(id))
			{
				products.add(id);
				return true;
			}
		}
		return false;
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

	
	public boolean delProduct(UUID id)
	{
		if (id != null)
		{
			return products.remove(id);
		}
		return false;
	}
	
	public List<UUID> getProducts() {
		return products;
	}
	public void setProducts(List<UUID> products) {
		this.products = products;
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	

}
