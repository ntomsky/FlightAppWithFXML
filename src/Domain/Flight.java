package Domain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Flight {
    private SimpleIntegerProperty flightID;
    private SimpleStringProperty airlineName;
    private SimpleStringProperty departureCity;
    private SimpleStringProperty arrivalCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private SimpleDoubleProperty ticketPrice;
    public Flight(){}

    public Flight(int flightID, String airlineName, String departureCity, String destinationCity, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, double ticketPrice) {
        this.flightID = new SimpleIntegerProperty(flightID);
        this.airlineName = new SimpleStringProperty(airlineName);
        this.departureCity = new SimpleStringProperty(departureCity);
        this.arrivalCity = new SimpleStringProperty(destinationCity);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ticketPrice = new SimpleDoubleProperty(ticketPrice);
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getFlightID() {
        return flightID.get();
    }

    public SimpleIntegerProperty flightIDProperty() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID.set(flightID);
    }

    public String getAirlineName() {
        return airlineName.get();
    }

    public SimpleStringProperty airlineNameProperty() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName.set(airlineName);
    }

    public String getDepartureCity() {
        return departureCity.get();
    }

    public SimpleStringProperty departureCityProperty() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity.set(departureCity);
    }


    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getTicketPrice() {
        return ticketPrice.get();
    }

    public SimpleDoubleProperty ticketPriceProperty() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice.set(ticketPrice);
    }

    public String getArrivalCity() {
        return arrivalCity.get();
    }

    public SimpleStringProperty arrivalCityProperty() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity.set(arrivalCity);
    }
}
