package GUI_Control;

import Domain.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordResetController {

    //buttons
    @FXML private Button submitBtn;
    @FXML private Button backBtn;

    //starts Password Reset Screen
    public static void startPasswordReset(Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(MyFlightSceneController.class.getResource("PasswordResetScene.fxml"));
        primaryStage.setTitle("Password Reset");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws Exception {

        if(actionEvent.getSource() == submitBtn) {
            MainMenuController.MainMenuInitializer(submitBtn);
        }
        else if(actionEvent.getSource() == backBtn) {
            //Closing current stage
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.close();


            //start the application from the Login screen using the Main class
            Stage primaryStage = new Stage();
            new Main().start(primaryStage);
        }

        //else call for flight cancellation method
    }
}
