package miniStore.store;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import miniStore.store.models.Person;
import miniStore.store.models.Product;
import miniStore.store.service.PersonService;
import miniStore.store.service.ProductService;

public class PreviewController  implements ControllerClass{
	  @FXML
	    private Text title;

	    @FXML
	    private Button back;

	    @FXML
	    private Text price;

	    @FXML
	    private Button buyNow;

	    @FXML
	    private TextField quantity;

	    @FXML
	    private Text AvQua;

	    @FXML
	    private TextArea dis;

	    @FXML
	    private Button pre;

	    @FXML
	    private ImageView Image;

	    @FXML
	    private Button next;
	    private IntegerPropertyBase pointer ;
	    
	    @Override
	    public void preloadData(Person person, Product product, boolean mine) {
	    	price.setText(String.format("%.2f $",product.getPrice()));
	    	title.setText(product.getName());
	    	dis.setText(product.getDetails());
	    	
	    	
	    buyNow.setDisable(mine);
	    if (person.getEmail().equals(product.getOwnerEmail()))
	    	buyNow.setDisable(true);
	    
	    	quantity.setText("1");
	    	AvQua.setText(product.getQuantity() + "");
	    	pointer = new  SimpleIntegerProperty();
		
	    	pointer.set(0);
	    	dis.setEditable(false);
	    	pointer.addListener((observable, oldValue,newValue)-> {
				if (newValue.intValue() >= product.getImages().size())
					pointer.set(product.getImages().size() -1);
				if(newValue.intValue() < 0)
					pointer.set(0);
			});
	    	try {
				Image.setImage(new Image(new FileInputStream(product.getImages().get(0).getFile())));
				}catch(Exception e)
				{
					e.printStackTrace();
				}
	    	back.setOnAction(event->{
	    		ControllerClass cn = new DashboardController();
				SceneChanger sn = new SceneChanger();
				try {
					if (mine)
					sn.changeScenes(event, "/fxml/Dashboard.fxml", "dashboard",person,cn);
					else
						sn.changeScenes(event, "/fxml/products.fxml", "Search",person,cn);	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	});
	    	pre.setOnAction(event->{
				if(pointer.intValue() > 0)
				{
				pointer.set(pointer.intValue() -1);
			
				try {
					Image.setImage(new Image(new FileInputStream(product.getImages().get(pointer.intValue()).getFile())));
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				});
			next.setOnAction(event->{
				if (pointer.intValue() < product.getImages().size()  -1)
				{
					pointer.set(pointer.intValue() +1);
				
				try {
				Image.setImage(new Image(new FileInputStream(product.getImages().get(pointer.intValue()).getFile())));
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				}
			});
			buyNow.setOnAction(event->{
				
				if (quantity.getText().isEmpty() )
				{
					SceneChanger.showPopUP("invalid quantity", "Error");
					return ;
					
				}
				if (Integer.parseInt(quantity.getText()) > product.getQuantity())
				{
					SceneChanger.showPopUP("invalid quantity", "Error");
					return ;
				}
				int q =  Integer.parseInt(quantity.getText());
				if (person.getBalance() <  q * product.getPrice())
				{
					SceneChanger.showPopUP("you don't have enough money", "Error");
					return ;
				}
				
				PersonService ps = new PersonService();
				ProductService Pser = new ProductService();
				
				product.setQuantity(product.getQuantity() - q);
				
				person.setBalance(person.getBalance() - (product.getPrice() * q));
				try {
//					if (product.getQuantity() <= 0)
//						Pser.deleteProduct(product);
//					else
						Pser.updateProduct(product);
					
					Person ow = ps.getPersonbyEmail(product.getOwnerEmail());
					ow.setBalance(ow.getBalance() + (product.getPrice() * q));
					ps.UpdatePerson(ow);
					ps.UpdatePerson(person);
					SceneChanger.showPopUP("thanks for buying .", "Message ");
					ControllerClass cn = new DashboardController();
					SceneChanger sn = new SceneChanger();
					try {
						sn.changeScenes(event, "/fxml/Dashboard.fxml", "dashboard",person,cn);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	    	
	    }

		@Override
		public void preloadData(Person person) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void preloadData(Person person, List<Product> products) {
			// TODO Auto-generated method stub
			
		}
}
