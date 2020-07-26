package miniStore.store;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import miniStore.store.models.Person;
import miniStore.store.models.Product;
import miniStore.store.service.PersonService;
import miniStore.store.service.ProductService;

public class DashboardController implements ControllerClass {
	 	@FXML
	    private Circle profimage;

	    @FXML
	    private Text name;

	    @FXML
	    private Text email;

	    @FXML
	    private Text balance;

	    @FXML
	    private Button myproducts;

	    @FXML
	    private Button withdraw;

	    @FXML
	    private TextField money;

	    @FXML
	    private Button addmoney;

	    @FXML
	    private Button buy;

	    @FXML
	    private Button sell;

	    @FXML
	    private Button logout;

	    @FXML
	    private VBox infbox;
	    @FXML
	    private Text errText;
	    
	    private Person person;
	    
	@Override
	public void preloadData(Person per) {
		person = per;
		balance.setText(String.format("%.2f $", person.getBalance()));
		name.setText(person.getName());
		email.setText(person.getEmail());
		
			try {
	    	Image image = new Image(new FileInputStream(per.getImage().getFile()));
			profimage.setFill(new ImagePattern(image));
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
			addmoney.setOnAction(Event->{
				errText.setText("");
				if (!money.getText().isEmpty())
				{
					try {
						double d = Double.parseDouble(money.getText());
						if (d > 0)
						{
							person.setBalance( person.getBalance() + Math.abs(d));
							balance.setText(String.format("%.2f $", person.getBalance()));
							new PersonService().UpdatePerson(person);
						}
					}catch(Exception e)
					{
						errText.setText("enter a valid number");
					}
				}
			});
			withdraw.setOnAction(event->{
				person.setBalance(0);
				balance.setText(String.format("%.2f $", person.getBalance()));
				try {
					new PersonService().UpdatePerson(person);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			myproducts.setOnAction(event->{
				List<List<Product>> pro = new ArrayList<>();
				List<Product> products;
				try {
					products = new ProductService().getProducts(person);
				
				int N  = products.size();
			for (int i = 0; i<N; i+=3)
					pro.add(products.subList(i, Math.min(N,i+3)));
			pro.forEach(p ->{
				
				FXMLLoader loader = new FXMLLoader();
				
				 loader.setLocation(getClass().getResource("/fxml/productHolderRoot.fxml"));
			        
				HBox box = null;
				try {
					box = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ProductHolderRootController cm=loader.getController();
			
				cm.preloadData(person, p,true);
				if (box != null)
				infbox.getChildren().add(box);
			});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			sell.setOnAction(event->{
				try {
				ControllerClass cn = new DashboardController();
    			SceneChanger sn = new SceneChanger();
					sn.changeScenes(event, "/fxml/addProduct.fxml", "sell",this.person,cn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
			});
			
			buy.setOnAction(event->{
				try {
					ControllerClass cn = new DashboardController();
	    			SceneChanger sn = new SceneChanger();
						sn.changeScenes(event, "/fxml/products.fxml", "search",this.person,cn);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			});
	}

	@Override
	public void preloadData(Person person, List<Product> products) {
		
	}

}
