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

public class BookingSuccessController extends bookingdata{	
	
	@FXML
	private Button bookanotherBtn;

	@FXML
    private Button logoutBtn;
	
	
	private Parent root;
    
    private Stage stage;

	private Scene scene;
	
	@FXML
	private Button detailBtn;
    
    @FXML
    private Label typeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label locationLabel;
 
	    public void logout(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    
	    public void bookAnother(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("RoomTypeScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    
		@FXML
		private void initialize() {
			typeLabel.setText("Type: "+ bookingdata.getBooktype());
		 	dateLabel.setText("Date: " + bookingdata.getBookdate());
		 	timeLabel.setText("Time: " + bookingdata.getBooktime());
		 	locationLabel.setText("Location: " + bookingdata.getBooklocation());
		}
		
	    
	    
}