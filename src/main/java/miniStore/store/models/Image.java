package miniStore.store.models;

import java.io.File;
import java.util.UUID;

public class Image {
	private UUID id;
	private UUID ownerID;
	private String Path;
	private String name;
	private File file;
	
	private Image(File img,UUID ownerId )
	{
		setId(UUID.randomUUID());
		setOwnerID(ownerID);
		setPath(img.getAbsolutePath());
		setName(img.getName());
		setFile(img);
		
	}
	public void copyToLocal()
	{
		
	}
	
	public void delImage()
	{
		if (file.exists())
		{
			file.delete();
		}
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(UUID ownerID) {
		this.ownerID = ownerID;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
