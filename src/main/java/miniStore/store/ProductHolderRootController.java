package miniStore.store;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import miniStore.store.models.Person;
import miniStore.store.models.Product;

public class ProductHolderRootController implements ControllerClass {
	@FXML
	private HBox root;
	@Override
	public void preloadData(Person person) {
		// TODO Auto-generated method stub

	}
	@Override
	public void preloadData(Person person, List<Product> products) {
		
	}

	
	public void preloadData(Person person, List<Product> products,boolean mine) {
//		List<List<Product>> pro = new ArrayList<>();
//		int N  = products.size();
//		for (int i = 0; i<N; i+=3)
//			pro.add(products.subList(i, Math.min(N,i+3)));
		products.forEach(pro->{
			try {
				FXMLLoader loader = new FXMLLoader();
				
				 loader.setLocation(getClass().getResource("/fxml/productHolder.fxml"));
			        
				Pane	box = loader.load();
					
				ProductHolderController	 cm = loader.getController();
				cm.preloadData(person, pro,mine);
				root.getChildren().add(box);
					
			}catch(Exception e)
			{
					e.printStackTrace();
			}
		});
		}

}
