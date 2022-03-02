package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoomTypeController {
	
	@FXML
    private Label topText;

    @FXML
    public Button cancelBtn;

    @FXML
    public Button nextBtn;

    @FXML
    public Button labBtn;

    @FXML
    public Button meetingroomBtn;

    @FXML
    public Button gymBtn;
    
    private Parent root;
    
    private Stage stage;

	private Scene scene;
	
    @FXML
    private Label errMsg;
    
    private boolean buttonClicked = false;
    
    public void cancel(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    

    @FXML
    public void ongymClick(ActionEvent event) throws IOException {
    	buttonClicked = true;
    	bookingdata.setBooktype(gymBtn.getText());
    }	

    @FXML
    public void onlabClick(ActionEvent event) throws IOException {
    	buttonClicked = true;
    	bookingdata.setBooktype(labBtn.getText());
    }

    @FXML
    public void onmrClick(ActionEvent event) throws IOException {
    	buttonClicked = true;
    	bookingdata.setBooktype(meetingroomBtn.getText());
    }

    @FXML
    public void onnextClick(ActionEvent event) throws IOException {
    	if (buttonClicked == false) {
    		errMsg.setText("Please select a room type");
    	} else {
    		Parent root = FXMLLoader.load(getClass().getResource("BookingDetails.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
    }

}
