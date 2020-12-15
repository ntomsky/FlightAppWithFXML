package GUI_Control;

import DAO.BookedFlights_dataAccess;
import DAO.FlightSchedule_dataAccess;
import Domain.*;
import GUI_Control.Admin.FlightsManagementController;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MyFlightSceneController implements Initializable {
    //My Booked Flight TableView Elements
    @FXML private Button searchFlightButton;
    @FXML private TableView<Flight> myFlightsTableView;
    @FXML private TextField filteredField;
    @FXML private TableColumn<Flight, Integer> fligthtNumCol;
    @FXML private TableColumn<Flight, Integer> airlineCol;
    @FXML private TableColumn<Flight, String> departCityCol;
    @FXML private TableColumn<Flight, String> arrivalCityCol;
    @FXML private TableColumn<Flight, LocalDate> departureDateCol;
    @FXML private TableColumn<Flight, String> flightTimeCol;
    @FXML private TableColumn<Flight, String> takeOffLandCol;

    //buttons
    @FXML private Button backToMenuBtn;
    //@FXML private Button cancelFlightBtn;

    //Book New Flight List
    private final ObservableList<Flight> bookedFlightList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fligthtNumCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        departCityCol.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        arrivalCityCol.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        departureDateCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        takeOffLandCol.setCellValueFactory(new PropertyValueFactory<>("takeOffLandTime"));
        flightTimeCol.setCellValueFactory(new PropertyValueFactory<>("flightTime"));

        try {
            bookedFlightList.addAll(BookedFlights_dataAccess.getBookings(CurrentUser.getCurrentUser()));
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in getting list of booked flights from Booked_flights Class");
            PopUpAlertBox.display("Oops","Something went wrong, Contact Support");
        }
            myFlightsTableView.setItems(bookedFlightList);
    }
    //Btn handler
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {

        if(actionEvent.getSource() == backToMenuBtn) {
            MainMenuController.MainMenuInitializer(backToMenuBtn);
        }

        //else call for flight cancellation method
    }


    //starts MyFlight Screen
    protected static void startMyFlight(Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(MyFlightSceneController.class.getResource("MyFlightScene.fxml"));
        primaryStage.setTitle("My Flights");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }



}
