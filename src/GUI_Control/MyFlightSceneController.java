package GUI_Control;

import DAO.FlightSchedule_dataAccess;
import Domain.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    //Book New Flight List
    private final ObservableList<Flight> flightScheduleDataList = FXCollections.observableArrayList();

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
            flightScheduleDataList.addAll(FlightSchedule_dataAccess.getListOfFlights());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        myFlightsTableView.setItems(flightScheduleDataList);

    }
}
