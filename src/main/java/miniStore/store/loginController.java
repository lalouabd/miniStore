package miniStore.store;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import miniStore.store.models.Person;
import miniStore.store.service.PersonService;

public class loginController {
	@FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private Button login;

    @FXML
    private Text errText;
    
  

    @FXML
    public void initialize() {
    
    	login.setOnAction(event->{
    		errText.setText("");
    		try {
    		Person per = new PersonService().login(username.getText(), password.getText());
    		if (per != null)
    		{
    			ControllerClass cn = new DashboardController();
    			SceneChanger sn = new SceneChanger();
    			sn.changeScenes(event, "/fxml/Dashboard.fxml", "dashboard",per,cn);
    			
    		}
    		else
    			errText.setText("invalid email or password");
    		}catch (Exception e)
    		{
    			e.printStackTrace();
    			errText.setText(e.getMessage());
    		}
    		});
    	
    	register.setOnAction(event->{
    		
			SceneChanger sn = new SceneChanger();
			try {
				sn.changeScenes(event, "/fxml/register.fxml", "Register");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	});
    }
 }
