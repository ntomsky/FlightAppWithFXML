package GUI_Control.Admin;

import DAO.FlightSchedule_dataAccess;
import static DAO.FlightSchedule_dataAccess.*;
import Domain.Flight;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class FlightsManagementController implements Initializable {

    //Flight Schedule TableView for FlightManagement Scene
    @FXML
    private TableColumn<Flight, Double> ticketPriceCol;
    @FXML
    private Button searchFlightButton;
    @FXML
    private TableView<Flight> flightScheduleTableView;
    @FXML
    private TextField filteredField;
    @FXML
    private TableColumn<Flight, Integer> flightNumCol;
    @FXML
    private TableColumn<Flight, Integer> airlineCol;
    @FXML
    private TableColumn<Flight, Integer> planeCapCol;
    @FXML
    private TableColumn<Flight, String> departCityCol;
    @FXML
    private TableColumn<Flight, String> arrivalCityCol;
    @FXML
    private TableColumn<Flight, LocalDate> departureDateCol;
    @FXML
    private TableColumn<Flight, LocalTime> departureTimeCol;
    @FXML
    private TableColumn<Flight, LocalTime> arrivalTimeCol;

    //Book New Flight List
    private final ObservableList<Flight> flightScheduleDataList = FXCollections.observableArrayList();

    //Back Buttons
    @FXML
    private Button backToAdminbtn;

    //Menu Button
    @FXML
    private Button addNewFlightBtn;
    @FXML private Button deleteFlightBtn;

//    //selected Flight Obj for processing
    private Flight selectedFlight;
//    //labels for fields of selected line in TableView
//    @FXML private Label selectedFlightID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flightNumCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        departCityCol.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        arrivalCityCol.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        departureDateCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        departureTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        ticketPriceCol.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        planeCapCol.setCellValueFactory(new PropertyValueFactory<>("flightCapacity"));

        try {
            flightScheduleDataList.addAll(FlightSchedule_dataAccess.getListOfFlights());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        flightScheduleTableView.setItems(flightScheduleDataList);

    }

    //start Flights Mgmt screen
    static void startFlightsManagement() throws IOException {

        //Screen initiation
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(FlightsManagementController.class.getResource("FlightsManagementScene.fxml"));
        primaryStage.setTitle("Flights Management");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    // transition to add Flight Screen
    public void transitionToAddNewFlight() throws IOException {
        //close and transition to Add new Flights
        Stage stage = (Stage) addNewFlightBtn.getScene().getWindow();
        stage.close();

        AddNewFlightController.startAddNewFlightScene();

    }

    //back Btn handler
    public void handleBackButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
            //close current
            Stage stage = (Stage) backToAdminbtn.getScene().getWindow();
            stage.close();
            AdminSceneController.initialize();
    }

    //This method removes flight from the DataBase
    public void processFlightDelete(javafx.event.ActionEvent event)
    {
        if(event.getSource() == deleteFlightBtn) {
            selectedFlight = flightScheduleTableView.getSelectionModel().getSelectedItem();
        }

        try{
            deleteFlight(selectedFlight.getFlightID());
            PopUpAlertBox.display("Confirmation", "Flights has been deleted");
        }
        catch (Exception ex){
            ex.getMessage();
        }

    }
}
