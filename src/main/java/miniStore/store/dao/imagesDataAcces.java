package miniStore.store.dao;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import miniStore.store.models.Image;

public class imagesDataAcces<T> implements ImagesDao<T> {

	
	
	@Override
	public List<Image> getImages(T o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Image> getImageById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertImage(Image image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteImage(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
