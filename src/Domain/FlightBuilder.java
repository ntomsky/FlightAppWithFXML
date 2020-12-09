package Domain;

import java.time.LocalTime;

public class FlightBuilder {
    private int flightID;
    private String departureCity;
    private String destinationCity;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int airlineID;

    public FlightBuilder setFlightID(int flightID) {
        this.flightID = flightID;
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

    public FlightBuilder setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public FlightBuilder setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public FlightBuilder setAirlineID(int airlineID) {
        this.airlineID = airlineID;
        return this;
    }

    public Flight createFlight() {
        return new Flight(flightID, departureCity, destinationCity, departureTime, arrivalTime, airlineID);
    }
}