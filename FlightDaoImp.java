package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import exceptions.FlightNotFoundException;
import factory.ConnectionFactory;
import model.Flight;

public class FlightDaoImp implements FlightDao {

	@Override
	public void save(Flight flight) {
		String sql = "INSERT INTO FLIGHT(ID, AIRLINE, SOURCE, DESTINATION, DEPARTURE, ARRIVAL, PRICE, SEATS_AVAILABLE, STATUS)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";

		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, flight.getId());
			ps.setString(2, flight.getAirline());
			ps.setString(3, flight.getSource());
			ps.setString(4, flight.getDestination());
			ps.setObject(5, flight.getDeparture());
			ps.setObject(6, flight.getArrival());
			ps.setDouble(7, flight.getPrice());
			ps.setInt(8, flight.getSeatsAvailable());
			ps.setString(9, flight.getStatus().name());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Flight> findAll() throws FlightNotFoundException {
		List<Flight> list = new ArrayList<>();
		String SelectSql = "SELECT * FROM FLIGHT";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(SelectSql)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Flight(rs.getInt("ID"), rs.getString("AIRLINE"), rs.getString("SOURCE"),
						rs.getString("DESTINATION"), rs.getObject("DEPARTURE", java.time.LocalDateTime.class),
						rs.getObject("ARRIVAL", java.time.LocalDateTime.class), rs.getDouble("PRICE"),
						rs.getInt("SEATS_AVAILABLE"), model.FlightStatus.valueOf(rs.getString("STATUS"))));
			}
			
			 if (list.isEmpty()) {
		            throw new FlightNotFoundException("No flights found in database");
		     }
		} catch (SQLException e) {
	        throw new FlightNotFoundException("Database error: " + e.getMessage());
		}
		
		return list;
	}

	@Override
	public Flight findById(int id) throws FlightNotFoundException {
		String sql = "SELECT * FROM FLIGHT WHERE ID = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Flight(rs.getInt("ID"), rs.getString("AIRLINE"), rs.getString("SOURCE"),
						rs.getString("DESTINATION"), rs.getObject("DEPARTURE", java.time.LocalDateTime.class),
						rs.getObject("ARRIVAL", java.time.LocalDateTime.class), rs.getDouble("PRICE"),
						rs.getInt("SEATS_AVAILABLE"), model.FlightStatus.valueOf(rs.getString("STATUS")));
			} else {
				throw new FlightNotFoundException("Id not Found : " + id);
			}
		} catch (SQLException e) {
			throw new FlightNotFoundException("Data base Error " + e.getMessage());
		}
	}

	@Override
	public Flight update(Flight flight) throws FlightNotFoundException {
		String UpdateSql =
				"UPDATE FLIGHT SET AIRLINE=?, SOURCE=?, DESTINATION=?, DEPARTURE=?, ARRIVAL=?, PRICE=?, SEATS_AVAILABLE=?, STATUS=? WHERE ID=?";


		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(UpdateSql)) {

			ps.setString(1, flight.getAirline());
			ps.setString(2, flight.getSource());
			ps.setString(3, flight.getDestination());
			ps.setObject(4, flight.getDeparture());
			ps.setObject(5, flight.getArrival());
			ps.setDouble(6, flight.getPrice());
			ps.setInt(7, flight.getSeatsAvailable());
			ps.setString(8, flight.getStatus().name());
			ps.setInt(9, flight.getId());

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				throw new FlightNotFoundException("Flight not found Exception");
			} 		
			
			return flight;

		} catch (SQLException e) {
			throw new FlightNotFoundException("Database error: " + e.getMessage());
		}

	}

	@Override
	public void deleteById(int id) throws FlightNotFoundException {
		String DeleteSql = "DELETE FROM FLIGHT WHERE id=?";

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(DeleteSql)) {

			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected == 0) {
				throw new FlightNotFoundException("Flight not found Exception");
			} else {
				System.out.println("Deleted flight" + id);
			}
		} catch (SQLException e) {
			throw new FlightNotFoundException("Database error: " + e.getMessage());
		}

	}

	@Override
	public List<Flight> findByRoute(String source, String destination) throws FlightNotFoundException {
		List<Flight> list = new ArrayList<>();
		String SelectSql = "SELECT * FROM FLIGHT WHERE SOURCE = ? AND DESTINATION = ?";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(SelectSql)) {

			ps.setString(1, source);
			ps.setString(2, destination);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Flight(rs.getInt("ID"), rs.getString("AIRLINE"), rs.getString("SOURCE"),
						rs.getString("DESTINATION"), rs.getObject("DEPARTURE", java.time.LocalDateTime.class),
						rs.getObject("ARRIVAL", java.time.LocalDateTime.class), rs.getDouble("PRICE"),
						rs.getInt("SEATS_AVAILABLE"), model.FlightStatus.valueOf(rs.getString("STATUS"))));
			}
			
			if (list.isEmpty()) {
			    throw new FlightNotFoundException(
			        "No flights found from " + source + " to " + destination
			    );
			}

		} catch (SQLException e) {
			throw new FlightNotFoundException("Database error: " + e.getMessage());
		}

		return list;		
	}

	@Override
	public List<Flight> findByAirline(String airline) throws FlightNotFoundException {
		List<Flight> list = new ArrayList<>();
		String SelectSql = "SELECT * FROM FLIGHT WHERE AIRLINE = ?";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(SelectSql)) {

			ps.setString(1, airline);
		
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Flight(rs.getInt("ID"), rs.getString("AIRLINE"), rs.getString("SOURCE"),
						rs.getString("DESTINATION"), rs.getObject("DEPARTURE", java.time.LocalDateTime.class),
						rs.getObject("ARRIVAL", java.time.LocalDateTime.class), rs.getDouble("PRICE"),
						rs.getInt("SEATS_AVAILABLE"), model.FlightStatus.valueOf(rs.getString("STATUS"))));
			}
			
			if (list.isEmpty()) {
			    throw new FlightNotFoundException(
			        "No flights found for airline: " + airline
			    );
			}

		} catch (SQLException e) {
			throw new FlightNotFoundException("Database error: " + e.getMessage());
		}

		return list;	
	}

}
