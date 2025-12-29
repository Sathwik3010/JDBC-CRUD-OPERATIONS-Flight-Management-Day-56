package service;

import java.util.List;

import exceptions.FlightNotFoundException;
import model.Flight;

public interface FlightService {
	void addFlight(Flight flight);

	List<Flight> getAllFlights()throws FlightNotFoundException;

	Flight getFlightById(int id) throws FlightNotFoundException;

	Flight updateFlight(Flight flight) throws FlightNotFoundException;

	void deleteFlight(int id) throws FlightNotFoundException;

	// extras
	List<Flight> searchByRoute(String source, String destination) throws FlightNotFoundException;

	List<Flight> searchByAirline(String airline) throws FlightNotFoundException;

	// decrement seats and return updated seats
	int bookSeat(int flightId) throws FlightNotFoundException;
}
