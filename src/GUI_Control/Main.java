package GUI_Control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main extends Application {

    private static SplashScreen splashScreen;
    private static final List<Image> icons = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{


        GUIState.setStage(primaryStage);
        GUIState.setHostServices(this.getHostServices());

        final Stage splashStage = new Stage(StageStyle.TRANSPARENT);


        if (Main.splashScreen.visible()) {
            final Scene splashScene = new Scene(splashScreen.getParent(), Color.TRANSPARENT);
            splashStage.setScene(splashScene);
            splashStage.getIcons().addAll(icons);
            splashStage.initStyle(StageStyle.TRANSPARENT);
            splashStage.setTitle("Welcome");
            splashStage.show();
        }



        CompletableFuture.supplyAsync(() -> {
            Platform.runLater(() -> {
                try {
                    initControl(GUIState.getStage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return GUIState.getStage();
        }).whenComplete((stage, throwable) -> {
            if (throwable != null) {
                System.out.println("Failed to load application context: "+throwable);
                Platform.runLater(() -> showErrorAlert(throwable));
            } else
                Platform.runLater(()-> {
                    showInitialView();
                    if (Main.splashScreen.visible()) {
                        splashStage.hide();
                        splashStage.setScene(null);
                    }
                });
        });

    }


    public static void main(String[] args) {
        launch(new SplashScreen(), args);
    }
    public static void launch(final SplashScreen splashScreen, final String[] args) {
        if (splashScreen != null)
            Main.splashScreen = splashScreen;
        else
            Main.splashScreen = new SplashScreen();

        if (SystemTray.isSupported())
            GUIState.setSystemTray(SystemTray.getSystemTray());

        Application.launch(Main.class, args);
    }

    private static Collection<Image> loadDefaultIcons() {
        final Class<Main> clazz = Main.class;
        return Arrays.asList(new Image(clazz.getResource("drops.png").toExternalForm()));
    }

    private static void showErrorAlert(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Oops! An unrecoverable error occurred.\n" + "Please contact your software vendor.\n\n" + "The application will stop now.\n\n" + "Error: " + throwable.getMessage());
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    private static void initControl(Stage stage) throws IOException {


        Parent root = FXMLLoader.load(Main.class.getResource("LoginScene.fxml"));
        stage.setTitle("Welcome to Friendly Skies");
        stage.setScene(new Scene(root, 500, 600));

        icons.addAll(loadDefaultIcons());
    }

    private void showInitialView() {
        GUIState.getStage().initStyle(StageStyle.DECORATED);
        GUIState.getStage().getIcons().addAll(icons);
        GUIState.getStage().show();
        GUIState.getStage().centerOnScreen();
    }

}
