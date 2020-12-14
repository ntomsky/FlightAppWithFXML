package GUI_Control;

import DAO.FlightSchedule_dataAccess;
import Domain.Flight;
import Domain.FlightBuilder;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookNewFlightController implements Initializable {
    //Book New Flight TableView Elements
    @FXML private Button searchFlightButton;
    @FXML private TableView<Flight> flightScheduleTableView;
    @FXML private TextField filteredField;
    @FXML private TableColumn<Flight, Integer> fligthtNumCol;
    @FXML private TableColumn<Flight, Integer> airlineCol;
    @FXML private TableColumn<Flight, String> departCityCol;
    @FXML private TableColumn<Flight, String> arrivalCityCol;
    @FXML private TableColumn<Flight, LocalDate> departureDateCol;
    @FXML private TableColumn<Flight, LocalTime> departureTimeCol;
    @FXML private TableColumn<Flight, LocalTime> arrivalTimeCol;
    @FXML private TableColumn<Flight, Double> ticketPriceCol;

    //Back to Main Menu Button
    @FXML private Button backToMainButton;

    //Book New Flight List
    private final ObservableList<Flight> flightScheduleDataList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        fligthtNumCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        departCityCol.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        arrivalCityCol.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        departureDateCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        departureTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        ticketPriceCol.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        try {
            flightScheduleDataList.addAll(FlightSchedule_dataAccess.getListOfFlights());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        flightScheduleTableView.setItems(flightScheduleDataList);

    }

    //Call to Book New Flight Scene
    public static void  toBookNewFlightScene() throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(BookNewFlightController.class.getResource("BookNewFlightScene.fxml"));
        primaryStage.setTitle("Book New Flight");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    //Transition back to main
     public void setBackToMainButton() throws IOException {
        MainMenuController.MainMenuInitializer(backToMainButton);
    }


}
