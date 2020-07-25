package miniStore.store.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import miniStore.store.models.ImageT;

public interface ImagesDao {
	
	List<ImageT> getImages(UUID ownerId) throws SQLException;
	Optional<ImageT> getImageById(UUID id) throws SQLException;
	Optional<ImageT> getImageByOwnerId(UUID ownerId);
	boolean insertImage(ImageT image) throws SQLException;
	boolean deleteImage(String name) throws SQLException;
}
