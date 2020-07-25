package miniStore.store;

import java.util.List;

import miniStore.store.models.Person;
import miniStore.store.models.Product;

public interface ControllerClass {
	
	void preloadData(Person person);
	void preloadData(Person person , List<Product> products);
	
	

}
