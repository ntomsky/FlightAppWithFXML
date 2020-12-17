package GUI_Control;

import DAO.*;
import static DAO.Customer_dataAccess.*;

import Domain.*;
import GUI_Control.Admin.AdminSceneController;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import static Helpers.EntryVerifiers.*;
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

    //Main Menu Button
    @FXML private Button MyFlightsBtn;
    @FXML private Button bookNewFlightBtn;
    @FXML private Button logOutBtn;
    @FXML private Button deleteMyAccountBtn;


    //fields from Registration Scene
    @FXML
    private TextField newCustUsername, newCustPassword, newCustFirstName,
    newCustLastName, newCustStreetAddress, newCustCityAddress, newCustState,
    newCustZip, newCustPhoneNumber,newCustEmail, newCustSecretAnswer, newCustSSN, loginUsername,
    loginPwd;

    //securityQuestion ComboBox from Registration screen
    @FXML private ComboBox <String> newCustSecQuestion;

    //Registration Screen Button
    @FXML
    private Button goToRegistrationBtn;

    //Back to Login Screen Button
    @FXML private Button backToLogInBtn;

    //Login Menu Button
    @FXML private Button loginBtn;
    @FXML private Button resetPassord;

    //Submit registration button
    @FXML private Button completeRegistration;

    //Button handler
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws Exception {

        if(actionEvent.getSource() == MyFlightsBtn) {
            //toMyflights page
            MyFlightSceneController.startMyFlight(MyFlightsBtn);
        }
        //go to password reset
        else if(actionEvent.getSource() == resetPassord) {
            PasswordResetController.startPasswordReset(resetPassord, "PasswordResetScene.fxml");
        }
        //logout function
        else if(actionEvent.getSource() == logOutBtn) {

            //set logged in user to null
            CurrentUser.registerCurrentUser(null);

            //closing scene
            Stage stage = (Stage) logOutBtn.getScene().getWindow();
            stage.close();

            //move to Login page screen
            Stage primaryStage = new Stage();
            new Main().start(primaryStage);

        }
            else
            try{
                //delete account
                Customer_dataAccess.deleteCustomer(((Customer)CurrentUser.getCurrentUser()).getCustomer_id());
                PopUpAlertBox.display("Confirmation", "Your account has been deleted");
                Stage primaryStage = new Stage();
                new Main().start(primaryStage);
            }
            catch (Exception ex){
                ex.getMessage();
            }
    }

    //User Login from the Login Screen
    public void userLogin() throws IOException, SQLException, ClassNotFoundException {
        String username = loginUsername.getText();
        String password = loginPwd.getText();

            //validation steps

            //method to db that returns null, customer or Admin
            if (!isUsernameValid(username)) {
                PopUpAlertBox.display("Invalid username", "Try again or Select Register");
                return;
            }
            // call to db to check password based on username
            else if (!isPasswordValid(username, password)) {
                PopUpAlertBox.display("Invalid password", "Try again or Select Reset Password");
                return;
            }
            //if above true, checks if admin
            else if (Admin_dataAccess.isAdmin(username)) {
                //TODO create admin instance here
                //Creates an Global oob of CurrentUSer from Admin
                Admin activeAdmin = new Admin(username);
                CurrentUser.registerCurrentUser(activeAdmin);
                transitionToAdminScene();

            }
            //if above false, creates a customer instance
            else {
                Customer activeCustomer = new Customer(username);
                try {
                    //this pulls ID from DB and then initiates a global Current customer obj
                    CurrentUser.registerCurrentUser(Customer_dataAccess.initializeCustomer(activeCustomer));
                }
                catch (Exception ex){
                    ex.getMessage();
                    System.out.println("error in initializing active customer");
                    PopUpAlertBox.display("oops","something went wrong");
                    return;
                }
                System.out.println("valid username and password");
                System.out.println(((Customer)CurrentUser.getCurrentUser()).getCustomer_id());
                transitionToMainMenu(loginBtn);
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
        primaryStage.setTitle("Let us get to know you");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    //Back Login Screen Method
    public void backToLogIn() throws IOException {

        Stage stage = (Stage) backToLogInBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        primaryStage.setTitle("Welcome to Friendly Skies");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    //Collecting Data from Registration Scene to create new customer in DB
    public void processRegistration(javafx.event.ActionEvent event) throws Exception {

        //check if username is available
        if (isUsernameValid(newCustUsername.getText())) {
            PopUpAlertBox.display("Username Already Exists", "Please select a different Username");
            return;
        }

        //check SSN format
        if (!EntryVerifiers.isSSN_Valid(newCustSSN.getText())) {
            //Popup on wrong input
            PopUpAlertBox.display("Incorrect SSN entry", "your SSN format should match xxx-xxx-xxxx, where x is a digit");
            return;
        }
        //checks if this SSN already exists
        else if (isSSN_Unique(SSNtoDigits(newCustSSN.getText()))) {
            PopUpAlertBox.display("This SSN already exists ", "Please check your SSN, if correct, reset your password");
            return;
        }

        //create new Customer entity
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
        try {
            Customer_dataAccess.registerNewCustomer(customer);
        } catch (Exception ex) {
            PopUpAlertBox.display("Error", "Please fill out all information");
            ex.getMessage();
            return;
        }

        //closing scene
        Stage stage = (Stage) completeRegistration.getScene().getWindow();
        stage.close();


        //select next scene and message based on type of active user

        //if admin
        if ((CurrentUser.getCurrentUser() instanceof Admin)) {
            PopUpAlertBox.display("Registration Confirmed", "" + customer.getUserName() + " has been added");
            AdminSceneController.initialize();
        }
        //if customer
        else {
            PopUpAlertBox.display("Confirmation", "Thank you for registering with us!");
            //start the application from the Login screen using
            // the Main class to let use login with  created login and password
            Stage primaryStage = new Stage();
            new Main().start(primaryStage);

        }
    }

    //to main Menu transition
    public void transitionToMainMenu(Button btn) throws IOException {
        //changing to new Stage
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 500, 600));
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
}
