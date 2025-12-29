package dao;

import java.util.List;

import exceptions.FlightNotFoundException;
import model.Flight;

public interface FlightDao {
	
	void save(Flight flight);
	
	List<Flight> findAll() throws FlightNotFoundException;
	
	Flight findById(int id) throws FlightNotFoundException;
	
	Flight update(Flight flight) throws FlightNotFoundException;
	
	void deleteById(int id) throws FlightNotFoundException;
	
	List<Flight> findByRoute(String source, String destination) throws FlightNotFoundException;
	
	List<Flight> findByAirline(String airline) throws FlightNotFoundException;
}
