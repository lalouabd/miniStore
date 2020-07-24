package miniStore.store.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public  class Product {
	private UUID id;
	private  String name;
	private double price;
	private List<File> images;
	private  String details;
	private String ownerEmail;
	private int quantity;

	


	


	public Product(String name, double price, String details,String ownerEmail,int quantity ) {
			this.id  =UUID.randomUUID();
			this.name = name;
			this.price = price;
			this.ownerEmail = ownerEmail;
			this.details = details;
			setQuantity(quantity);
			images = new ArrayList<>();
	}

	
	public boolean addImage(File image) {
			if (image != null)
			{
				if (image.exists())
				{
					Optional<File > op = images.stream()
					.filter(img->img.getName().equals(image.getName())).findFirst();
					if (!op.isPresent())
						images.add(image);
					else 
						return false;
					return true;
				}
			}
			return false;
	}
	public boolean removeImage(File image) {
		if (image != null)
		{
			if (image.exists())
			{
				images.remove(image);
				return true;
			}
		}
		return false;
	}
	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		if (quantity > 0)
		this.quantity = quantity;
		else if (this.quantity <= 0)
			this.quantity = 0;
	}
	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getOwnerEmail() {
		return ownerEmail;
	}


	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}


	public void setImages(List<File> images) {
		this.images = images;
	}
	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}

	
	public List<File> getImages() {
		return images;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString()
	{
		return String.format(" Owner %s : \n name : %s\t"
				+ "price %.2f \n details: \n %s",
				this.getOwnerEmail(),
				this.getName(),
				this.getPrice(),
				this.getDetails());
	}
}
