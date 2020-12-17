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

    public int getFlightCapacity() {
        return flightCapacity.get();
    }

    public Flight(){}

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public int getFlightID() {
        return flightID.get();
    }

    public String getDepartureCity() {
        return departureCity.get();
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public String getTicketPrice() {
        return ticketPrice.get();
    }

    public String getArrivalCity() {
        return arrivalCity.get();
    }

    public void setFlightTime() {
        long timeOfFlight = departureTime.until(arrivalTime,MINUTES);
        long hours = timeOfFlight / 60;
        long min = timeOfFlight % 60;
        flightTime = hours + "h " + min + "m";
    }

    public void setTakeOffLandTime() {
        this.takeOffLandTime = new SimpleStringProperty(departureTime + " - " + arrivalTime);
    }
}
