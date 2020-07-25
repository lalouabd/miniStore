package miniStore.store.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.ImageIcon;

import miniStore.store.models.ImageT;
import miniStore.store.service.JdbcQuerys;

public class imagesDataAcces implements ImagesDao {

	public final JdbcQuerys<ImageT> dbcon;
	
	public imagesDataAcces()
	{
		dbcon = new JdbcQuerys<>();
	}
	
	
	
	@Override
	public List<ImageT> getImages(UUID ownerID) throws SQLException {
		String sql = "select * from images where ownerid=?";
		
		return dbcon.queryForList(
				sql,
				result->{
					ImageT im = null;
					try {
						im = new ImageT(
								UUID.fromString(result.getString("id")),
								result.getString("path"),
								result.getString("name"),
								UUID.fromString(result.getString("ownerid")));
					}catch (Exception e)
					{
					
						e.printStackTrace();
						
					}
					return im;
				},
				ownerID);
	}

	
	@Override
	public Optional<ImageT> getImageById(UUID id) throws SQLException{
		String sql = "select * from  images where id=?";
		return dbcon.queryForObject(sql, result->{
			ImageT im = null;
			try {
				im = new ImageT(
						UUID.fromString(result.getString("id")),
						result.getString("path"),
						result.getString("name"),
						UUID.fromString(result.getString("ownerid")));
			}catch (Exception e)
			{
				
				e.printStackTrace();
				
			}
			return im;
			
		}, id);
	}

	@Override
	public boolean insertImage(ImageT image) throws SQLException{
		String sql = "insert into images(id,path,name,ownerid) values(?,?,?,?)";
		
		return dbcon.update(sql,
				image.getId(),
				image.getPath(),
				image.getName(),
				image.getOwnerID());
		
	}

	@Override
	public boolean deleteImage(String name) throws SQLException {
		String sql = "delete from images where name=?";
		
		return dbcon.update(sql, name);
	}


	@Override
	public Optional<ImageT> getImageByOwnerId(UUID ownerId) {
		
		return null;
	}

}
