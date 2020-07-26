package miniStore.store;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import miniStore.store.models.Person;
import miniStore.store.models.Product;
import miniStore.store.service.ProductService;

public class addProductsController implements ControllerClass {

	  	@FXML
	    private TextField title;

	    @FXML
	    private Button Cancel;

	    @FXML
	    private Button save;

	    @FXML
	    private TextField quantity;

	    @FXML
	    private TextField price;

	    @FXML
	    private TextArea disc;

	    @FXML
	    private Button addImage;

	    @FXML
	    private Button delImage;

	    @FXML
	    private Button pre;

	    @FXML
	    private ImageView slider;

	    @FXML
	    private Button next;
	    private List<File> files;
	    
	    private File defImage;
	    private IntegerPropertyBase pointer ;
	@Override
	public void preloadData(Person person) {
		files = new ArrayList<>();
		pointer = new  SimpleIntegerProperty();
		pointer.set(0);
		defImage = new File("src/main/resources/images/defimage.png");
		
		try {
			slider.setImage(new Image(new FileInputStream(defImage)));
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		pointer.addListener((observable, oldValue,newValue)-> {
				if (newValue.intValue() >= files.size())
					pointer.set(files.size() -1);
				if(newValue.intValue() <0)
					pointer.set(0);
			});
		
		delImage.setOnAction(event->{
			files.remove(pointer.intValue());
			try {
				File image = defImage;
				pointer.set(pointer.intValue() -1);
				
				if (pointer.intValue() >= 0 && files.size() > 0)
					image = files.get(pointer.intValue());
				slider.setImage(new Image(new FileInputStream(image)));
				}catch(Exception e)
				{
					e.printStackTrace();
				}
		});
		addImage.setOnAction(event->{
			File file = chooseImage(event);
		
			if(file != null)
			{
				files.add(file);
				pointer.setValue(files.size() -1);
				try {
					slider.setImage(new Image(new FileInputStream(files.get(pointer.intValue()))));
					}catch(Exception e)
					{
						e.printStackTrace();
					}
			}
		});
		Cancel.setOnAction(event->{
			try {
			ControllerClass cn = new DashboardController();
			SceneChanger sn = new SceneChanger();
			sn.changeScenes(event, "/fxml/Dashboard.fxml", "dashboard",person,cn);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			});
		save.setOnAction(event->{
			if(title.getText().isEmpty())
			{
				SceneChanger.showPopUP(" title is missing ", "Error!");
				return;
			}
			if(price.getText().isEmpty())
			{
				SceneChanger.showPopUP(" title is missing", "Error!");
				return;
			}
			if(disc.getText().isEmpty())
			{
				SceneChanger.showPopUP("description must not be empty", "Error!");
				return ;
			}
			if(quantity.getText().isEmpty() )
			{
				SceneChanger.showPopUP(" Quantity is missing", "Error!");
				return;
			}
		
			try {
				Product pro  = new Product(
					title.getText(),
					Double.parseDouble(price.getText()),
					disc.getText(),
					person.getEmail(),
					Integer.parseInt(quantity.getText()));
			if (files.size() == 0)
				pro.addImage(defImage);
				else
				{
					files.forEach(im-> pro.addImage(im));
				}
			try {
				new ProductService().insertProduct(pro);
				ControllerClass cn = new DashboardController();
				SceneChanger sn = new SceneChanger();
				sn.changeScenes(event, "/fxml/Dashboard.fxml", "dashboard",person,cn);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}catch(Exception e)
			{
				SceneChanger.showPopUP("Price or Quantity are not valid", "Error!");
			}
			
			
		});
		pre.setOnAction(event->{
			if(pointer.intValue() > 0)
			{
			pointer.set(pointer.intValue() -1);
		
			try {
				slider.setImage(new Image(new FileInputStream(files.get(pointer.intValue()))));
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			});
		next.setOnAction(event->{
			if (pointer.intValue() < files.size()  -1)
			{
				pointer.set(pointer.intValue() +1);
			
			try {
			slider.setImage(new Image(new FileInputStream(files.get(pointer.intValue()))));
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			});
		
	}

	@Override
	public void preloadData(Person person, List<Product> products) {
		
	}
	private File chooseImage(Event event) {
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setTitle("choose image");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		ExtensionFilter ex = new ExtensionFilter("*.png", "*.png");
		ExtensionFilter ex2 = new ExtensionFilter("*.jpeg", "*.jpeg");
		ExtensionFilter ex3 = new ExtensionFilter("*.jpg", "*.jpg");
		ExtensionFilter ex4 = new ExtensionFilter("*.gif", "*.gif");
		fileChooser.getExtensionFilters().addAll(ex,ex2,ex3,ex4);
	
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		return selectedFile;
	}
}
