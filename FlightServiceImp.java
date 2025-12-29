package service;

import java.util.List;

import dao.FlightDao;
import dao.FlightDaoImp;
import exceptions.FlightNotFoundException;
import model.Flight;

public class FlightServiceImp implements FlightService{
	FlightDao dao = new FlightDaoImp();
	@Override
	public void addFlight(Flight flight) {
		dao.save(flight);
	}

	@Override
	public List<Flight> getAllFlights() throws FlightNotFoundException {
		return dao.findAll();
	}

	@Override
	public Flight getFlightById(int id) throws FlightNotFoundException {
		return dao.findById(id);
	}

	@Override
	public Flight updateFlight(Flight flight) throws FlightNotFoundException {
		return dao.update(flight);
	}

	@Override
	public void deleteFlight(int id) throws FlightNotFoundException {
		dao.deleteById(id);
	}

	@Override
	public List<Flight> searchByRoute(String source, String destination) throws FlightNotFoundException {
		return dao.findByRoute(source, destination);
	}

	@Override
	public List<Flight> searchByAirline(String airline) throws FlightNotFoundException {
		return dao.findByAirline(airline);
	}

	@Override
	public int bookSeat(int flightId) throws FlightNotFoundException {
		Flight f = dao.findById(flightId);
		
		if(f.getSeatsAvailable()<=0) {
			throw new FlightNotFoundException(
					"No seats available on flight " + flightId);
		}
		
		f.setSeatsAvailable(f.getSeatsAvailable() - 1);
		dao.update(f);
		return f.getSeatsAvailable();
	}

}
