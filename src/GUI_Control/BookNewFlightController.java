package GUI_Control;

import DAO.BookedFlights_dataAccess;
import DAO.FlightSchedule_dataAccess;
import Domain.*;
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
import java.util.ResourceBundle;

import static DAO.FlightSchedule_dataAccess.deleteFlight;

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
    @FXML private TableColumn<Flight, String> flightTimeCol;
    @FXML private TableColumn<Flight, String> takeOffLandCol;
    @FXML private TableColumn<Flight, String> ticketPriceCol;
    @FXML private TableColumn<Flight, Integer> flightCapCol;

    //Back to Main Menu Button
    @FXML private Button backToMainButton;

    //Book New Flight List
    private final ObservableList<Flight> flightScheduleDataList = FXCollections.observableArrayList();

    private Flight selectedFlight;
    //Book Flight Button
    @FXML private Button bookFlightBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        fligthtNumCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        departCityCol.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        arrivalCityCol.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        departureDateCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        takeOffLandCol.setCellValueFactory(new PropertyValueFactory<>("takeOffLandTime"));
        flightTimeCol.setCellValueFactory(new PropertyValueFactory<>("flightTime"));
        ticketPriceCol.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        flightCapCol.setCellValueFactory(new PropertyValueFactory<>("flightCapacity"));

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

    public void bookNewFlight(javafx.event.ActionEvent event){

        //assigning selected row to a new object
        if(event.getSource() == bookFlightBtn) {
            selectedFlight = flightScheduleTableView.getSelectionModel().getSelectedItem();
        }

        try{
            //sending request to DB for processing
            int cap = BookedFlights_dataAccess.bookFlight(CurrentUser.getCurrentUser(),selectedFlight.getFlightID());
            if(cap == -1){
                PopUpAlertBox.display("Full Capacity", "We are sorry, this flight is already full");
            }
            else
            PopUpAlertBox.display("Confirmation", "Congratulations! Your Flight has been booked!");
        }
        catch (Exception ex){
            ex.getMessage();
            PopUpAlertBox.display("Oops!", "Something went wrong. Please try again later");
        }

    }

}
