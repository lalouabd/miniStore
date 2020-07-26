package miniStore.store;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import miniStore.store.models.Person;
import miniStore.store.models.Product;

public class ProductHolderController  {

    @FXML
    private ImageView image;

	@FXML
    private Pane root;

    @FXML
    private Text name;

    @FXML
    private Text price;
    
	public void preloadData(Person person, Product product, boolean mine) {
		image.setImage(new Image(product.getImages().get(0).getPath()));
		
		name.setText(product.getName());
		price.setText(product.getPrice() + " $");
		root.setOnMouseClicked(event->{
			if (mine)
			{
				
			}else {
				
			}
			
		});
	}

}
