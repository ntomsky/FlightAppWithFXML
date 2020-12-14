package GUI_Control;

import DAO.*;
import static DAO.Customer_dataAccess.*;

import Domain.*;
import GUI_Control.Admin.AdminSceneController;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import Helpers.EntryVerifiers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    //Button to MyFlight Scene
    @FXML
    private Button MyFlightsBtn;
    //book new flight button
    @FXML
    private Button bookNewFlightBtn;

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

    @FXML
    private Button loginBtn;

    @FXML
    private Button completeRegistration;

    //User Login from the Login Screen
    public void userLogin() throws IOException, SQLException, ClassNotFoundException {
        String username = loginUsername.getText();
        String password = loginPwd.getText();
        while(true) {
            //validation steps

            //method to db that returns null, customer or Admin

            if (!isUsernameValid(username)) {
                PopUpAlertBox.display("Invalid username", "Try again or Select Register");
                return;
            } else if (!isPasswordValid(username, password)) {
                PopUpAlertBox.display("Invalid password", "Try again or Select Reset Password");
                return;
            } else if (Admin_dataAccess.isAdmin(username)) {
                //TODO create admin instance here
                transitionToAdminScene();
                break;

            } else {
                Customer activeCustomer = new Customer(username);
                CurrentUser.registerCurrentUser(activeCustomer);
                System.out.println("valid username and password");
                transitionToMainMenu(loginBtn);
                break;
            }
        }
    }

    //Registration Scene Method
    public void registrationScene(javafx.event.ActionEvent event) throws IOException {
        Stage stage;

        if(event.getSource() == goToRegistrationBtn)
        {
        stage = (Stage) goToRegistrationBtn.getScene().getWindow();
        stage.close();
        }

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

    //Collecting Data from Registration Scene to create new customer in DB
    public void processRegistration() throws SQLException, ClassNotFoundException, IOException {

        //check if username is available
        if (isUsernameValid(newCustUsername.getText())) {
            PopUpAlertBox.display("Username Already Exists","Please select a different Username");
            return;
        }
        //check SSN format
         if(!EntryVerifiers.isSSN_Valid(newCustSSN.getText()))
         {
             //later alert box , sout to terminal for now
            PopUpAlertBox.display("Incorrect SSN entry","your SSN format should match xxx-xxx-xxxx, where x is a digit");
            return;
         }

//            //create new Customer entity
            Customer customer = new Customer((
                    EntryVerifiers.SSNtoDigits(newCustSSN.getText())),
                    newCustUsername.getText(),
                    newCustPassword.getText(),
                    newCustFirstName.getText(),
                    newCustLastName.getText(),
                    newCustStreetAddress.getText(),
                    newCustCityAddress.getText(),
                    newCustState.getText(),
                    newCustZip.getText(),
                    newCustPhoneNumber.getText(),
                    newCustEmail.getText(),
                    newCustSecQuestion.getValue(),
                    newCustSecretAnswer.getText());

        //insert new customer in DB
        try{
            Customer_dataAccess.registerNewCustomer(customer);
        }
        catch (Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            return;
        }

        PopUpAlertBox.display("Confirmation", "Thank you for registering with us!");
        //if success proceed to main menu scen

        //Return to login;
        transitionToMainMenu(completeRegistration);
    }

    //to main Menu transition
    public void transitionToMainMenu(Button btn) throws IOException {
        //changing to new Stage
//        MainMenuController.MainMenuInitializer(btn);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
        primaryStage.setTitle("Main Menu");
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
    public void transitionToBookNewFlight() throws IOException {
        //changing to new Stage
        Stage stage = (Stage) bookNewFlightBtn.getScene().getWindow();
        stage.close();
        BookNewFlightController.toBookNewFlightScene();
    }

    public void transitionToAdminScene() throws IOException {
        //changing to new Stage
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
        AdminSceneController.initialize();
    }
//
//    @Override
//    public void initialize(java.net.URL location, ResourceBundle resources) {
//    }

}
