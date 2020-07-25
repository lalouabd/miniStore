package miniStore.store.models;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageT {
	private UUID id;
	private UUID ownerID;
	private String Path;
	private String name;
	private File file;
	
	public ImageT(File img,UUID ownerId )
	{
		setId(UUID.randomUUID());
		setOwnerID(ownerID);
		setPath(img.getAbsolutePath());
		setName(img.getName());
		setFile(img);
		
	}
	public ImageT(UUID id,String path,String name,UUID ownerId )
	{
		setId(id);
		setOwnerID(ownerID);
		setPath(path);
		setName(name);
		setFile(new File(path));
		
	}
	public void copyToLocal()
	{
		 Path sourcePath = file.toPath();
	        
	        
	        Path targetPath = Paths.get("/images/"+getName());
	        try {
	        Files.copy(sourcePath, targetPath,StandardCopyOption.REPLACE_EXISTING);
	        file = new File(targetPath.toString());
	        Path = targetPath.toString();
	        }catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
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
