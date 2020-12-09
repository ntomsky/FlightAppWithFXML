package GUI_Control;

import DAO.*;
import static DAO.Customer_dataAccess.*;
import static DAO.DBQueries.*;
import Domain.*;
import Helpers.EntryVerifiers;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {
//    // TEST TABLEVIEW
//    @FXML
//    private TableView <Admin> testTableView;
//    @FXML
//    private TableColumn<Admin, String> firstNameCol;



    //fields from Registration Scene
    @FXML
    private TextField newCustUsername, newCustPassword, newCustFirstName,
    newCustLastName, newCustStreetAddress, newCustCityAddress, newCustState,
    newCustZip, newCustPhoneNumber,newCustEmail, newCustSecretAnswer, newCustSSN, loginUsername,
    loginPwd;

    //securityQuestion ComboBox from Registration screen
    @FXML
    private ComboBox <String> newCustSecQuestion;

    //Registration Screen Button
    @FXML
    private Button goToRegistrationBtn;

    //Back to Login Screen Button
    @FXML
    private Button backToLogInBtn;

    //Button to Main Menu
    @FXML
    private Button toMainMenu;

    //Button to MyFlight Scene
    @FXML
    private Button MyFlightsBtn;
    @FXML
    private Button loginBtn;

    //book new flight button
    @FXML
    private Button bookNewFlightBtn;
    @FXML
    private Button completeRegistration;

    //Book New Flight TableView Elements
    @FXML
    private Button searchFlightButton;
    @FXML
    private TableView <Flight>flightScheduleTableView;
    @FXML
    private TextField filteredField;
    @FXML
    private TableColumn<Flight, String> departCityCol;
    @FXML
    private TableColumn<Flight, String> arrivalTimeCol;
    @FXML
    private TableColumn<Flight, String> arrivalCityCol;
    @FXML
    private TableColumn<Flight, Integer> airlineCol;
    @FXML
    private TableColumn<Flight, Integer> fligthtNumCol;

    //Book New Flight List
    private final ObservableList<Flight> flightScheduleDataList = FXCollections.observableArrayList();

    //User Login from the Login Screen
    public void userLogin() throws IOException, SQLException, ClassNotFoundException {
        if(!isCredentialValid(loginUsername.getText(),USERNAME_VALIDATION))
            System.out.println("invalid username");
        else if (!isCredentialValid(loginPwd.getText(),PASSWORD_VALIDATION))
            System.out.println("invalid password");
        else {
            System.out.println("valid username and password");
            toMainMenu(loginBtn);
        }
    }

    //Registration Scene Method
    public void registrationScene() throws IOException {
        Stage stage = (Stage) goToRegistrationBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Registration_Scene.fxml"));
        primaryStage.setTitle("Registration");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    //Back Login Screen Method
    public void backToLogIn() throws IOException {

        Stage stage = (Stage) backToLogInBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        primaryStage.setTitle("Welcome to Friendly Skies");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    //Call to My Flight Scene
    public void toMyFlightScene() throws IOException {
        Stage stage = (Stage) MyFlightsBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MyFlightScene.fxml"));
        primaryStage.setTitle("My Flights");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    //Call to Book New Flight Scene
    public void toBookNewFlightScene() throws IOException {

        //changing to new Stage
        Stage stage = (Stage) bookNewFlightBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("BookNewFlightScene.fxml"));
        primaryStage.setTitle("Book New Flight");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    //Collecting Data from Registration Scene to create new customer in DB
    public void processRegistration() throws SQLException, ClassNotFoundException, IOException {

        //check if username is available
        if(!isCredentialValid(newCustUsername.getText(),USERNAME_VALIDATION)){
            System.out.println("Username taken");
        }
        //check SSN format
        else if(!EntryVerifiers.isSSN_Valid(newCustSSN.getText()))
             //later alert box , sout to terminal for now
             System.out.println("your SSN format should match xxx-xxx-xxxx, x is a digit");


        //create new Customer entity
        Customer customer = new CustomerBuilder().setUsername(newCustUsername.getText()).setPassword(newCustPassword.getText()).setFirstName(newCustFirstName.getText()).setLastName(newCustLastName.getText()).setSSN(newCustSSN.getText()).setStreetAddress(newCustStreetAddress.getText()).setCityAddress(newCustCityAddress.getText()).setStateAddress(newCustState.getText()).setZipAddress(newCustZip.getText()).setPhoneNumber(newCustPhoneNumber.getText()).setEmail(newCustEmail.getText()).setSecQuestion(newCustSecQuestion.getValue()).setSecretAnswer(newCustSecretAnswer.getText()).createCustomer();

        //insert new customer in DB
        Customer_dataAccess.registerNewCustomer(customer);

        //if success proceed to main menu scene
        toMainMenu(completeRegistration);
    }

    //General Method to call Main Menu
    private void toMainMenu(Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {



    }

}
