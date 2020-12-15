package GUI_Control;

import DAO.Customer_dataAccess;
import Domain.Admin;
import Domain.CurrentUser;
import Domain.Customer;
import GUI_Control.Admin.AdminSceneController;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordResetController {

    //buttons
    @FXML private Button getSecretQBtn;
    @FXML private Button backBtn;
    @FXML private Button submitAnswerBtn;

    @FXML private TextField usernameField;
    @FXML private TextField answerField;


    //starts Password Reset Screen
    public static void startPasswordReset(Button btn, String FXMLfile) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(MyFlightSceneController.class.getResource(FXMLfile));
        primaryStage.setTitle("Password Reset");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws Exception {

        if (actionEvent.getSource() == getSecretQBtn) {
            try {
                    //run a query to DB based on username
                    String secretQ = Customer_dataAccess.getSecurityQ(usernameField.getText());

                    //store username
                    if(secretQ != null) {
                        Customer activeCustomer = new Customer(usernameField.getText());

                        CurrentUser.registerCurrentUser(Customer_dataAccess.initializeCustomer(activeCustomer));
                    }

                    //display the secret question
                    PopUpAlertBox.display("Here's your secret question",secretQ);

                    //to the next scene to get the answer
                   startPasswordReset(getSecretQBtn,"PasswordResetStep2.fxml");


            } catch (Exception ex) {
                PopUpAlertBox.display("Error", "This user doesnt not exist, please try again");
            }
        }
        else if(actionEvent.getSource() == backBtn) {
            //Closing current stage
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.close();

            //start the application from the Login screen using the Main class
            Stage primaryStage = new Stage();
            new Main().start(primaryStage);
        }
        else if(actionEvent.getSource() == submitAnswerBtn){

            if(((Customer)CurrentUser.getCurrentUser()).getSecurityAnswer().equals(answerField.getText())){

                PopUpAlertBox.display("Here's your password", (CurrentUser.getCurrentUser()).getUserPassword());

                //logout
                CurrentUser.registerCurrentUser(null);

                //closing scene
                Stage stage = (Stage) submitAnswerBtn.getScene().getWindow();
                stage.close();

                //start the application from the Login screen using
                // the Main class to let user login
                Stage primaryStage = new Stage();
                new Main().start(primaryStage);

                }
            else {

                //no match. try again error
                PopUpAlertBox.display("Error", "Your answer is incorrect, try again");

                //logout
                CurrentUser.registerCurrentUser(null);
                startPasswordReset(submitAnswerBtn, "PasswordResetScene.fxml");
            }

        }

    }
}
