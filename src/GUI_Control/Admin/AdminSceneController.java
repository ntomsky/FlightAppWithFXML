package GUI_Control.Admin;



import GUI_Control.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class AdminSceneController {

    //Main Menu buttons
    @FXML Button userManagementBtn;
    @FXML Button manageFlightsBtn;
    @FXML Button logOutBtn;
    @FXML Button exitBtn;



    public static void initialize() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(AdminSceneController.class.getResource("AdminScene.fxml"));
        primaryStage.setTitle("Admin View");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void handleMenuButtonAction(javafx.event.ActionEvent actionEvent) throws Exception {


        if(actionEvent.getSource() == userManagementBtn){
            //close and transition to User management
            Stage stage = (Stage) userManagementBtn.getScene().getWindow();
            stage.close();
            UserManagementController.startUserManagement();
            //call to start User Mgt scene
        }
        else if (actionEvent.getSource() == manageFlightsBtn){
            //close Load FlightsManagement FXML tied to FlightsManagement controller
            Stage stage = (Stage) manageFlightsBtn.getScene().getWindow();
            stage.close();
            //call to start Flights management
            FlightsManagementController.startFlightsManagement();
        }
        else if (actionEvent.getSource() == logOutBtn){
            //Closing current stage
            Stage stage = (Stage) logOutBtn.getScene().getWindow();
            stage.close();

            //start the application from the Login screen using the Main class
            Stage primaryStage = new Stage();
            new Main().start(primaryStage);
        }
        else {
            Stage stage = (Stage)exitBtn.getScene().getWindow();
            stage.close();
              return;
        }

    }

}
