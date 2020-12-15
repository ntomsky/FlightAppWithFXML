package GUI_Control;

import DAO.BookedFlights_dataAccess;
import DAO.FlightSchedule_dataAccess;
import Domain.*;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.ResourceBundle;

import static DAO.FlightSchedule_dataAccess.deleteFlight;

public class BookNewFlightController implements Initializable {
    //Book New Flight TableView Elements
    @FXML private Button searchFlightButton;
    @FXML private TableView<Flight> flightScheduleTableView;
    @FXML private TextField filteredWhereField;
    @FXML private TextField filteredFromField;
    @FXML private DatePicker filteredDateField;
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
    @FXML private Button seeBookedBtn;

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

        //wrap the observable list in FilteredList
        FilteredList<Flight> filteredData = new FilteredList<>(flightScheduleDataList, b -> true);

            //set the filter predicate whenever the filter changes
            filteredFromField.textProperty().addListener((observable, oldValue, newValue) ->{
                    filteredData.setPredicate(flight -> {

                        //if empty show all
                        if(newValue == null || newValue.isEmpty())
                        {
                            return true;
                        }

                        //compare departure and typed city
                        String lowerCaseFiler = newValue.toLowerCase();

                        if(flight.getDepartureCity().toLowerCase().contains(lowerCaseFiler)){
                            return true;//filter matches first letter of a city
                        }
                        else
                            return false;
                    });
            });

            //this portion monitors where search field with listener
            filteredWhereField.textProperty().addListener((observable, oldValue, newValue) ->{
                    filteredData.setPredicate(flight -> {

                        //if empty show all
                        if(newValue == null || newValue.isEmpty())
                        {
                            return true;
                        }

                        //compare value with arrival cities
                        String lowerCaseFiler = newValue.toLowerCase();

                        if(flight.getArrivalCity().toLowerCase().contains(lowerCaseFiler)){
                            return true;//matches arrival city name
                        }
                        else
                            return false;


                    });
            });
            // this part is for filtered Date search, it updates the search based
            //on picked date from Date Picker
        filteredDateField.valueProperty().addListener((observable, oldValue, newValue) ->{
                    filteredData.setPredicate(flight -> {

                        //if DatePicker is empty show all
                        if(newValue == null)
                        {
                            return true;
                        }

                        //create a new value form picked dat
                        LocalDate pickedDate = newValue;

                       //compare with flight date
                        if(flight.getDepartureDate().isEqual(pickedDate)){
                            return true;//matches arrival city name
                        }
                        else
                            return false;


                    });
            });


        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Flight> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(flightScheduleTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        flightScheduleTableView.setItems(sortedData);



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
         Stage stage = (Stage) backToMainButton.getScene().getWindow();
         stage.close();
        MainMenuController.MainMenuInitializer(backToMainButton);
    }

    public void bookingButtonHandler(javafx.event.ActionEvent event) throws IOException {

        //assigning selected row to a new object
        if(event.getSource() == bookFlightBtn) {
            selectedFlight = flightScheduleTableView.getSelectionModel().getSelectedItem();

            try {
                //sending request to DB for processing
                int result = BookedFlights_dataAccess.bookFlight(CurrentUser.getCurrentUser(), selectedFlight.getFlightID());
                if (result == -1) {
                    PopUpAlertBox.display("Full Capacity", "We are sorry, this flight is already full");
                    return;
                } else if (result == -2) {
                    PopUpAlertBox.display("Double Booking", "It looks like you already have a seat on this flight!");
                    return;
                } else
                    PopUpAlertBox.display("Confirmation", "Congratulations! Your Flight has been booked!");
                toBookNewFlightScene();
            } catch (Exception ex) {
                ex.getMessage();
                PopUpAlertBox.display("Oops!", "Something went wrong. Please try again later");
            }
        }
        else
            //close the stage and switch to MyFlight

            MyFlightSceneController.startMyFlight(seeBookedBtn);


    }

}
