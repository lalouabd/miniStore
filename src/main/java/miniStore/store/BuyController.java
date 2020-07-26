package miniStore.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miniStore.store.models.Person;
import miniStore.store.models.Product;
import miniStore.store.service.ProductService;

public class BuyController implements ControllerClass {
    @FXML
    private TextField searchbar;

    @FXML
    private Button search;

    @FXML
    private Button back;

    @FXML
    private VBox infbox;
    private List<Product> products;

	@Override
	public void preloadData(Person person) {
		ProductService productService = new ProductService();
		
		search.setOnAction(event->{
			infbox.getChildren().removeIf(e ->e.isVisible());
			try {
				
				products = productService.getProducts(searchbar.getText());
				List<List<Product>> pro = new ArrayList<>();
				System.out.println(products.size());
				
									
				int N  = products.size();
			for (int i = 0; i<N; i+=6)
					pro.add(products.subList(i, Math.min(N,i+6)));
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
				
				ProductHolderRootController cm = loader.getController();
			
				cm.preloadData(person, p,false);
			
				infbox.getChildren().add(box);
				
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		back.setOnAction(event->{
			ControllerClass cn = new DashboardController();
			SceneChanger sn = new SceneChanger();
		
				try {
					sn.changeScenes(event, "/fxml/Dashboard.fxml", "dashboard",person,cn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		});
	}

	@Override
	public void preloadData(Person person, List<Product> products) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preloadData(Person person, Product product, boolean mine) {
		// TODO Auto-generated method stub
		
	}

}
