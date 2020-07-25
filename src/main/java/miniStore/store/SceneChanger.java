package miniStore.store;

import java.io.IOException;
import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import miniStore.store.models.Person;
import miniStore.store.models.Product;

public class SceneChanger {
	public void changeScenes(Event event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
       // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //get the stage from the event that was passed in
        scene.getStylesheets().add(MainApp.class.getResource("/styles/Styles.css").toExternalForm());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        
        stage.show();
    
}
	
	public void changeScenes(Event event, String viewName, String title, Person person, ControllerClass controllerClass) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(MainApp.class.getResource("/styles/Styles.css").toExternalForm());
        //access the controller class and preloaded the volunteer data
        controllerClass = loader.getController();
        controllerClass.preloadData(person);
        
      //  scene.getStylesheets().add("./src/application/application.css");
        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        
        stage.show();
    }
	public void changeScenes(Event event,
			String viewName,
			String title,
			Person person,
			List<Product> products,
			ControllerClass controllerClass) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(MainApp.class.getResource("/styles/Styles.css").toExternalForm());
        //access the controller class and preloaded the volunteer data
        controllerClass = loader.getController();
        controllerClass.preloadData(person, products);
        
      //  scene.getStylesheets().add("./src/application/application.css");
        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        
        stage.show();
    }

}
