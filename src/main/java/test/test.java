package test;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import miniStore.store.models.Person;
import miniStore.store.models.Product;
import miniStore.store.service.ProductService;

public class test {

	public static void main(String[] args) {
		ProductService pr = new ProductService();
		try {
			pr.insertProduct(new Product("test", 20, "test","test&te", 10));
			pr.getProducts(new Person(UUID.randomUUID(),
					"john", "test&te", Date.valueOf(LocalDate.now()), 500))
			.forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
