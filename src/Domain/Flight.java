package Domain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Flight {
    private SimpleIntegerProperty flightID;
    private SimpleIntegerProperty flightCapacity;
    private SimpleStringProperty airlineName;
    private SimpleStringProperty departureCity;
    private SimpleStringProperty arrivalCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String flightTime;
    private SimpleStringProperty ticketPrice;
    private SimpleStringProperty takeOffLandTime;

    public Flight(int flightID, int flightCapacity, String airlineName, String departureCity, String destinationCity, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, double ticketPrice) {
        this.flightID = new SimpleIntegerProperty(flightID);
        this.airlineName = new SimpleStringProperty(airlineName);
        this.departureCity = new SimpleStringProperty(departureCity);
        this.arrivalCity = new SimpleStringProperty(destinationCity);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ticketPrice = new SimpleStringProperty("$" + ticketPrice);
        this.flightCapacity = new SimpleIntegerProperty(flightCapacity);
        setFlightTime();
        setTakeOffLandTime();
    }

    public String getAirlineName() {
        return airlineName.get();
    }

    public SimpleStringProperty airlineNameProperty() {
        return airlineName;
    }

    public int getFlightCapacity() {
        return flightCapacity.get();
    }

    public SimpleIntegerProperty flightCapacityProperty() {
        return flightCapacity;
    }

    public void setFlightCapacity(int flightCapacity) {
        this.flightCapacity.set(flightCapacity);
    }

    public Flight(){}

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

    public String getTicketPrice() {
        return ticketPrice.get();
    }

    public SimpleStringProperty ticketPriceProperty() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
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

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime() {
        long timeOfFlight = departureTime.until(arrivalTime,MINUTES);
        long hours = timeOfFlight / 60;
        long min = timeOfFlight % 60;
        flightTime = hours + "h " + min + "m";
    }

    public String getTakeOffLandTime() {
        return takeOffLandTime.get();
    }

    public SimpleStringProperty takeOffLandTimeProperty() {
        return takeOffLandTime;
    }

    public void setTakeOffLandTime() {
        this.takeOffLandTime = new SimpleStringProperty(departureTime + " - " + arrivalTime);
    }
}
