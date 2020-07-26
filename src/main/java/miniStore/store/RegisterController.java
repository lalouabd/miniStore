package miniStore.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import miniStore.store.models.ImageT;
import miniStore.store.models.Person;
import miniStore.store.service.PersonService;

public class RegisterController {
		@FXML
	    private TextField name;

	    @FXML
	    private TextField email;

	    @FXML
	    private TextField emailcomfiramtion;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private PasswordField passconfirm;

	    @FXML
	    private Text errText;

	    @FXML
	    private Circle profileImage;

	    @FXML
	    private DatePicker dob;

	    @FXML
	    private Button cancel;

	    @FXML
	    private Button signup;
	    private File image;
	    private File defImage;
	
	    @FXML
	    public void initialize() {
	    	defImage = new File("src/main/resources/images/defimage.png");
	    	image = defImage;
	    	setImage(defImage);
	    	dob.setValue(LocalDate.of(1998, 2, 5));
	    	profileImage.setOnMouseEntered(event->{
	    		setImage(new File("src/main/resources/images/uploadimage.png"));
	    	});
	    	profileImage.setOnMouseExited(event->{
	    		setImage(defImage);
	    	});
	    	profileImage.setOnMouseClicked(event->{
	    		image = chooseImage(event);
	    		if(image!= null)
	    		{
	    			setImage(image);
	    			defImage = image;
	    		}
	    	});
	    	signup.setOnAction(event->{
	    		
	    		if (!email.getText().equals(emailcomfiramtion.getText()) || email.getText().isEmpty())
	    		{
	    			SceneChanger.showPopUP("emails don't match", "Error");
	    			return;
	    		}
	    		if (!passconfirm.getText().equals(password.getText()) || password.getText().isEmpty())
	    		{
	    			SceneChanger.showPopUP("passwords don't match", "Error");
	    			return;
	    		}
	    		
	    		if (!email.getText().contains("@"))
	    		{
	    		SceneChanger.showPopUP("invalid email", "Error");
    			return;
	    		}
	    		try {
					if (new PersonService().getPersonbyEmail(email.getText()) != null)
					{
						SceneChanger.showPopUP(" email address is not available", "Error");
		    			return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    			Person per = new Person(name.getText(),
	    				email.getText(),
	    				500,
	    				password.getText(),
	    				java.sql.Date.valueOf(dob.getValue()));
	    		System.out.println( "id ois " +per.getId());
	    		ImageT img = new ImageT(image,per.getId());
	    		per.setImage(img); 
	    		try {
					new PersonService().register(per);
					SceneChanger sc = new SceneChanger();
		    			sc.changeScenes(event, "/fxml/login.fxml", "login");
					
	    		} catch (Exception e) {
					// TODO Auto-generated catch block
					//errText.setText(e.getMessage());
					e.printStackTrace();
				}
	    		
	    	});
	    	cancel.setOnAction(event->{
	    		SceneChanger sc = new SceneChanger();
	    		try {
					sc.changeScenes(event, "/fxml/login.fxml", "login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	});
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
	    
	    public void setImage(File file)
	    {
	    	try {
	    	Image image = new Image(new FileInputStream(file));
			profileImage.setFill(new ImagePattern(image));
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	}
}