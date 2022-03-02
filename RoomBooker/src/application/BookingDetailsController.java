package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookingDetailsController extends database implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML 
	private Label myLabel;
	
	@FXML
	private ChoiceBox<String> timeChoiceBox;
	
	@FXML 
	private DatePicker myDatePicker;
	
	@FXML
	private Label myLabel1;
	

    @FXML
    private Label typeTxt;
	
	@FXML
	private TableView<ModelTable> tableView;
	
	@FXML
	private TableColumn<ModelTable, String> bookDate;
	
	@FXML
	private TableColumn<ModelTable, String> bookingTime;
	
	@FXML
	private TableColumn<ModelTable, String> bookerName;
	
	@FXML
	private TableColumn<ModelTable, String> location;
	
	//ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
	
	private String time;
	private LocalDate date;
	private String blocation;
	private String myPersons;
	private String myGroup;
	
	@FXML
	private Button bookBtn; 
	
    @FXML
    private Label errMsg;
    
    @FXML
    private ChoiceBox<String> personChoiceBox;

    @FXML
    private ChoiceBox<String> groupChoiceBox;
    
    @FXML
    private ChoiceBox<String> locationChoiceBox;
    
    @FXML
    private Button detailBtn;
    
    private boolean success = false;
    
	database db1 = new database(); //Create object of database class
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	typeTxt.setText(bookingdata.getBooktype());
    	if (bookingdata.getBooktype().equals("Gym")) {
    		bookingdata.setTable("gym");
    	} else if (bookingdata.getBooktype().equals("Lab")) {
    		bookingdata.setTable("lab");
    	} else if (bookingdata.getBooktype().equals("Meeting Room")) {
    		bookingdata.setTable("meetingroom");
    	}
    	setChoiceBoxLocations();
    	
    	tableView.setItems(db1.db(bookingdata.getTable()));
    	
		//Set columns
		bookDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		bookingTime.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
		bookerName.setCellValueFactory(new PropertyValueFactory<>("bookerName"));
		location.setCellValueFactory(new PropertyValueFactory<>("location"));
		
		timeChoiceBox.getItems().addAll("8:00","10:00","12:00","14:00","16:00");
		timeChoiceBox.setOnAction(this::getTimings);
		
		personChoiceBox.getItems().addAll("2","5","10");
		personChoiceBox.setOnAction(this::getPersons);
		
		groupChoiceBox.getItems().addAll("2","5");
		groupChoiceBox.setOnAction(this::getGroup);
		
		setCellValueFromTableToFields();
	}
	
	protected void setChoiceBoxLocations() {
		if (bookingdata.getTable().equals("gym")) {
			locationChoiceBox.getItems().addAll("Gym-1","Gym-2");
			locationChoiceBox.setOnAction(this::getLocation);
   	 	} else if (bookingdata.getTable().equals("lab")) {
   	 		locationChoiceBox.getItems().addAll("Lab-1","Lab-2","Lab-3","Lab-4","Lab-5");
   	 		locationChoiceBox.setOnAction(this::getLocation);
   		} else if (bookingdata.getTable().equals("meetingroom")) {
   			locationChoiceBox.getItems().addAll("Meeting Room-1","Meeting Room-2","Meeting Room-3","Meeting Room-4","Meeting Room-5", "Meeting Room-6","Meeting Room-7","Meeting Room-8","Meeting Room-9","Meeting Room-10");
   			locationChoiceBox.setOnAction(this::getLocation);
   		}
	}
	
	 protected void getTimings(ActionEvent event) {
		time = timeChoiceBox.getValue();
		bookingdata.setBooktime(time);
	}
	
	 public void getDate(ActionEvent event) {
		date = myDatePicker.getValue();
		bookingdata.setBookdate(date.toString());
	}
	
	 protected void getLocation(ActionEvent event) {
		blocation = locationChoiceBox.getValue();
		bookingdata.setBooklocation(blocation);
	}
	
	 protected void getPersons(ActionEvent event) {
		myPersons = personChoiceBox.getValue();
	}
	
	 protected void getGroup(ActionEvent event) {
		myGroup = groupChoiceBox.getValue();
	}
	//Allowed number of persons.
	 protected boolean checkPersons() {
		boolean personCheck = false;
		switch(bookingdata.getTable()) {
			  case "gym":
				  if (myPersons.equals("2")) {
					  personCheck = true;
					}
			    break;
			  case "meetingroom":
				  if (myPersons.equals("2")||myPersons.equals("5")) {
					  personCheck = true;
					}
			    break;
			  case "lab":
				  if (myPersons.equals("2")||myPersons.equals("5")||myPersons.equals("10")) {
					  personCheck = true;
					}	
				break;
			  default:
			    personCheck = false;
			}
		return personCheck;
	}
	
	 protected boolean checkGroup() {
		boolean groupCheck = false;
		switch(bookingdata.getTable()) {
		  case "gym":
			  if (myGroup.equals("2")) {
					groupCheck = true;
				}
		    break;
		  case "meetingroom":
			  if (myGroup.equals("2")||myGroup.equals("5")) {
					groupCheck = true;
				}
		    break;
		  case "lab":
			  if (myGroup.equals("2")||myGroup.equals("5")) {
					groupCheck = true;
				}	
			break;
		  default:
		    groupCheck = false;
		}
		return groupCheck;
	}

	 
	 protected void checkFields() throws IOException {
		 if (date == null) {
			 errMsg.setText("No Date Selected!");
		 } else if (time == null) {
			 errMsg.setText("No Time Slot Selected!");
		 } else if (blocation == null) {
			 errMsg.setText("Location Not Selected!");
		 } else if (myPersons == null) {
			 errMsg.setText("Number Of Persons Not Selected!");
		 } else if (myGroup == null) {
			 errMsg.setText("Groupings Not Selected!");
		 } else if (checkPersons() == false) {
			errMsg.setText("Not Adhering To Covid Persons Guidelines!");
		 } else if (checkGroup() == false) {
			errMsg.setText("Not Adhering To Covid Groupings Guidelines!");
		 } else {
			 if(db1.checkDuplicateEntry() == true) {
				 success = false;
				 errMsg.setText("Room Already Booked, Please Select A Different Date Or Time.");
			 } else {
				 errMsg.setText("Room Booked!");
				 success = true;
			 }
		 }	 
	 }
	 
	  protected void updateTable() {
		tableView.getItems().clear();
		db1.update();
	 }
	 
	  protected void setCellValueFromTableToFields() {
		 tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				ModelTable click = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
				myDatePicker.setPromptText(click.bookDate);
				timeChoiceBox.setValue(click.bookingTime);
				locationChoiceBox.setValue(click.location);
		 	}
		 });
	 }
	 
	 
	  public void bookClick(ActionEvent event) throws IOException  {
		 checkFields();
		 if (success == true) {
			updateTable();
		 }
	    }
	 
	 
	  public void detailClick(ActionEvent event) throws IOException {
		 	if (success == false) {
		 		errMsg.setText("No Details To Display!");
		 	} else {
		 	root = FXMLLoader.load(getClass().getResource("BookingSuccess.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		 	}
	    }
	 
	  public void switchToRoomTypeScene(ActionEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("RoomTypeScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
}
