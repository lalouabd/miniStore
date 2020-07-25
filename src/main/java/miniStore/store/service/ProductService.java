package miniStore.store.service;

import java.sql.SQLException;
import java.util.List;

import miniStore.store.dao.ImagesDao;
import miniStore.store.dao.ProductDao;
import miniStore.store.dao.ProductDataAcces;
import miniStore.store.dao.imagesDataAcces;
import miniStore.store.models.Person;
import miniStore.store.models.Product;

public class ProductService {

	private final ProductDao productDao;
	private final ImagesDao  imageDao;
	public ProductService()
	{
		productDao =  new ProductDataAcces();
		imageDao = new imagesDataAcces();
	}

	public boolean insertProduct(Product product) throws SQLException
	{
		product.getImages().forEach(img->{
			try {
				imageDao.insertImage(img);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return productDao.insertProduct(product);
	}
	
	public List<Product> getProducts(Person person) throws SQLException
	{
		List<Product> list;
		list = productDao.getProducts(person);
		
		list.forEach(pro ->{
			try {
				pro.setImages(imageDao.getImages(pro.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return list;
	}
	
	public List<Product> getProducts(String name) throws SQLException
	{
		List<Product> list;
		list = productDao.getProducts(name);
		
		list.forEach(pro ->{
			try {
				pro.setImages(imageDao.getImages(pro.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return list;
	}
	
	public boolean updateProduct(Product product) throws SQLException
	{
		return productDao.updateProduct(product);
	}
	
	public boolean deleteProduct(Product product) throws SQLException
	{
		product.getImages().forEach(img -> {
			try {
				imageDao.deleteImage(img.getName());
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		});
		
		return productDao.deleteProductById(product.getId());
	}
}