package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightBuilder {
    private int flightID;
    private int flightCapacity;
    private String airlineName;
    private String departureCity;
    private String destinationCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double ticketPrice;

    public FlightBuilder setFlightID(int flightID) {
        this.flightID = flightID;
        return this;
    }

    public FlightBuilder setFlightCapacity(int flightCapacity) {
        this.flightCapacity = flightCapacity;
        return this;
    }

    public FlightBuilder setAirlineName(String airlineName) {
        this.airlineName = airlineName;
        return this;
    }

    public FlightBuilder setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
        return this;
    }

    public FlightBuilder setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
        return this;
    }

    public FlightBuilder setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public FlightBuilder setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public FlightBuilder setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public FlightBuilder setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public Flight createFlight() {
        return new Flight(flightID, flightCapacity, airlineName, departureCity, destinationCity, departureDate, departureTime, arrivalTime, ticketPrice);
    }
}