package miniStore.store.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import miniStore.store.models.Person;
import miniStore.store.models.Product;

public interface ProductDao {
	
	List<Product> getProducts(Person person) throws SQLException;
	
	Optional<Product> getProductbyId(UUID id)throws SQLException;
	
	boolean deleteProductById(UUID id)throws SQLException;
	
	boolean updateProduct(Product product)throws SQLException;
	boolean insertProduct(Product product) throws SQLException;

	List<Product> getProducts();
	
}
