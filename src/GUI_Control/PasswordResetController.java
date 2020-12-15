package GUI_Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordResetController {

    @FXML private Button submitBtn;

    //starts Password Reset Screen
    protected static void startPasswordReset(Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(MyFlightSceneController.class.getResource("PasswordResetScene.fxml"));
        primaryStage.setTitle("Password Reset");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {

        if(actionEvent.getSource() == submitBtn) {
            MainMenuController.MainMenuInitializer(submitBtn);
        }

        //else call for flight cancellation method
    }
}
