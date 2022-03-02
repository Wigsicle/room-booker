package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXML;

public class LoginController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML 
	private Button exitBtn;
	
	@FXML 
	private Button loginBtn;
	
	@FXML
	private Label errMsg;
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField pwd;
	
	@FXML 
	private Label topText;

	public void exit(ActionEvent event) throws IOException {
		Stage stage = (Stage) exitBtn.getScene().getWindow();
		stage.close();
	}
	
	public void login(ActionEvent event) throws IOException {
		
		if ((userName.getText().isBlank() == false) && (pwd.getText().isBlank() == false)) {
			//errMsg.setText("Logging In!");
			
			login log = new login();
			if (log.validateLogin(event, userName.getText(), pwd.getText()) == true) {
		    	errMsg.setText("Logging In!");
				root = FXMLLoader.load(getClass().getResource("RoomTypeScene.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else {
				errMsg.setText("Invalid Credentials!");
			}
			
		} else {
			errMsg.setText("Please Enter Username And Password.");
		}
	}
	
	
}
