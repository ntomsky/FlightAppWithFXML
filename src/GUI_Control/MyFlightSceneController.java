package GUI_Control;

import Domain.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;

public class MyFlightSceneController {
    //Book New Flight TableView Elements
    @FXML
    private Button searchFlightButton;
    @FXML private TableView<Flight> flightScheduleTableView;
    @FXML private TableColumn<Flight, Integer> fligthtNumCol;
    @FXML private TableColumn<Flight, Integer> airlineCol;
    @FXML private TableColumn<Flight, String> departCityCol;
    @FXML private TableColumn<Flight, String> arrivalCityCol;
    @FXML private TableColumn<Flight, LocalDate> departureDateCol;
    @FXML private TableColumn<Flight, LocalTime> departureTimeCol;
    @FXML private TableColumn<Flight, LocalTime> arrivalTimeCol;
    @FXML private TableColumn<Flight, Double> ticketPriceCol;
}
