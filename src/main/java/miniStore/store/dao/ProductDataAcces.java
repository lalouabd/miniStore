package miniStore.store.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import miniStore.store.models.Person;
import miniStore.store.models.Product;
import miniStore.store.service.JdbcQuerys;

public class ProductDataAcces implements ProductDao{
	
	private final JdbcQuerys<Product> dbCon;
	
	public ProductDataAcces() {
			dbCon = new JdbcQuerys<>();
	}

	@Override
	public List<Product> getProducts(Person person) throws SQLException {
		String sql = "select * from products where ownerEmail=?";
		return dbCon.queryForList(sql, Result->{
			Product pro = null;
			try {
				pro = new Product(UUID.fromString(Result.getString("id")),
						Result.getString("name"),
						Result.getDouble("price"),
						Result.getString("details"),
						Result.getString("ownerEmail"),
						Result.getInt("quantity")
						);
			}catch(Exception e)
			{	
				e.printStackTrace();
			}
			return pro;
		},person.getEmail());
	}

	@Override
	public Optional	<Product> getProductbyId(UUID id) throws SQLException{
		String sql = "select * from products where id=?";
		return dbCon.queryForObject(sql, result->{
			Product pro = null;
			try {
				pro = new Product(
						result.getString("name"),
						Double.parseDouble(result.getString("price")),
						result.getString("details"),
						result.getString("ownerEmail"),
						result.getInt("quantity")
						);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return pro;
		},
				id);
	}

	@Override
	public boolean deleteProductById(UUID id) throws SQLException{
		// TODO Auto-generated method stub
		String sql = "delete from products where id=?";
		
		return dbCon.update( sql, id);
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		String sql = "update products set name=? "
				+ ", price=? "
				+ ", details=? "
				+ ", ownerEmail=? "
				+ ", quantity=?"
				+ "where id=?";
		
		return dbCon.update(
				sql,
				product.getName(),
				product.getPrice(),
				product.getDetails(),
				product.getOwnerEmail(),
				product.getQuantity(),
				product.getId()
				);
	}

	@Override
	public boolean insertProduct(Product product) throws SQLException {
		String sql= "insert into products("
				+ "name,"
				+ "price,"
				+ "details,"
				+ "ownerEmail,"
				+ "quantity,"
				+ "id) Values(?,?,?,?,?,?)";
		return dbCon.update(
				sql,
				product.getName(),
				product.getPrice(),
				product.getDetails(),
				product.getOwnerEmail(),
				product.getQuantity(),
				product.getId()
				);
	}

	@Override
	public List<Product> getProducts(String name) throws SQLException {
		String sql="select * from produckts where name like ?";
		
		// TODO Auto-generated method stub
		return dbCon.queryForList(sql, result->{
			Product pro = null;
			try {
				pro = new Product(
						result.getString("name"),
						Double.parseDouble(result.getString("price")),
						result.getString("details"),
						result.getString("ownerEmail"),
						result.getInt("quantity")
						);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return pro;
		}, name+"%");
	}

}
