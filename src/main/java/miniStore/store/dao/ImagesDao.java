package miniStore.store.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import miniStore.store.models.Image;

public interface ImagesDao<T> {
	
	List<Image> getImages(T o);
	Optional<Image> getImageById(UUID id);
	boolean insertImage(Image image);
	boolean deleteImage(String name);
}
