package GUI_Control.Admin;

import static DAO.FlightSchedule_dataAccess.*;

import DAO.FlightSchedule_dataAccess;
import Domain.Flight;
import Domain.FlightBuilder;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static Helpers.EntryVerifiers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

public class AddNewFlightController{
    //back To FlightMgmView
    @FXML private Button backBtn;

    //textfields from new flight form
    @FXML private TextField flightNumTF, airlineTF, departCityTF,
    arrivalCityTF, departTimeTF, arrivalTimeTF,
    flightCapTF, txPriceTF;
    @FXML private DatePicker departDateDP;

    //Add new Flight Button
    @FXML private Button addFlightBtn;


    public static void startAddNewFlightScene() throws IOException {
    //Screen initiation
    Stage primaryStage = new Stage();
    Parent root = FXMLLoader.load(AddNewFlightController.class.getResource("AddNewFlight.fxml"));
    primaryStage.setTitle("Add New Flight");
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.show();
    }


    //Back Btn handler
    public void handleBackButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {

            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.close();
            FlightsManagementController.startFlightsManagement();
    }

    //creating and registering new Flight
    public void addNewFlight() throws SQLException, ClassNotFoundException, IOException {

        //validate FlightsNum entries for digits only
        if(!flightNumValid(flightNumTF.getText())){
            //pop up wrong entry
          PopUpAlertBox.display("Incorrect Flight Number", "Please enter numbers between 0 and 9999");
          return;
        }

        if(flightNumExists(flightNumTF.getText())){
            PopUpAlertBox.display("Wrong Flight Number", "This Flight Number Already Exists");
            return;
        }
        //validating Airline name
        int airlineID = retrieveAirlineID(airlineTF.getText());
        if(airlineID == -1) {
            //if -1 - no match for name in DB
            PopUpAlertBox.display("Airine Name Not Found", "Please enter a valid Airline Name");
            return;
        }

        //validate Date Selection
        if(departDateDP.getValue() == null){
            PopUpAlertBox.display("Date Error", "Flight Date not chosen");
            return;
        }

        //validate time entry format
        LocalTime departTime, arrivalTime;
        if(isTimeEntryValid(departTimeTF.getText()) && isTimeEntryValid(arrivalTimeTF.getText())){
            departTime = LocalTime.parse(departTimeTF.getText());
            arrivalTime = LocalTime.parse(arrivalTimeTF.getText());
        }
        else{
            System.out.println("wrong time entry");
            //call for wrong Time PopUP
            PopUpAlertBox.display("Incorrect Time Format","Please Enter Time in \"HH:mm\" format");
            return;
        }

        //if all above are valid. proceed with the flight creating and putting to DB
        Flight flight = new FlightBuilder()
                .setFlightID(Integer.parseInt(flightNumTF.getText()))
                .setAirlineName(airlineTF.getText())
                .setDepartureCity(departCityTF.getText())
                .setDestinationCity(arrivalCityTF.getText())
                .setDepartureTime(departTime)
                .setArrivalTime(arrivalTime)
                .setDepartureDate(departDateDP.getValue())
                .setFlightCapacity(Integer.parseInt(flightCapTF.getText()))
                .setTicketPrice(Integer.parseInt(txPriceTF.getText()))
                .createFlight();

        System.out.println("new flight obj created");

        //add flight to Flight_Schedule DB
        try {
            FlightSchedule_dataAccess.addNewFlight(flight);
        }
        catch (SQLException ex){
            System.out.println("SQL error. check Flight_Schedule queries");
        }

        catch (Exception ex){
            //TODO CREATE NEW POPUP
            System.out.println("Problem in Data Entry");
        }
        //Call a confirmation PopUp Box
        PopUpAlertBox.display("Confirmation", "Flight " + flight.getAirlineName() + " " + flight.getFlightID() + " has been added");

    }
}


