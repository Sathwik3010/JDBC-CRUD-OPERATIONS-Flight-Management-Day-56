package controller;

import java.time.LocalDateTime;
import java.util.List;

import exceptions.FlightNotFoundException;
import model.Flight;
import model.FlightStatus;
import service.FlightService;
import service.FlightServiceImp;

public class FlightController {

    public static void main(String[] args) {

        FlightService service = new FlightServiceImp();

//        try {
//            service.addFlight(new Flight(1, "Indigo", "Bengaluru", "Mumbai",
//                    LocalDateTime.of(2025, 12, 1, 6, 30),
//                    LocalDateTime.of(2025, 12, 1, 8, 15),
//                    5500.0, 10, FlightStatus.SCHEDULED));
//
//            service.addFlight(new Flight(2, "AirIndia", "Bengaluru", "Delhi",
//                    LocalDateTime.of(2025, 12, 1, 7, 0),
//                    LocalDateTime.of(2025, 12, 1, 9, 50),
//                    7500.0, 5, FlightStatus.SCHEDULED));
//
//            service.addFlight(new Flight(3, "SpiceJet", "Mumbai", "Bengaluru",
//                    LocalDateTime.of(2025, 12, 1, 10, 0),
//                    LocalDateTime.of(2025, 12, 1, 11, 45),
//                    5200.0, 0, FlightStatus.DELAYED));
//
//            service.addFlight(new Flight(4, "Vistara", "Bengaluru", "Mumbai",
//                    LocalDateTime.of(2025, 12, 1, 18, 30),
//                    LocalDateTime.of(2025, 12, 1, 20, 15),
//                    6500.0, 3, FlightStatus.SCHEDULED));
//
//            System.out.println("Display:");
//            System.out.println("------------");
//
//            List<Flight> flights = service.getAllFlights();
//            for (Flight f : flights) {
//                System.out.println(f);
//            }
//
//        } catch (FlightNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
        
		System.out.println("\nSearch flights Bengaluru -> Mumbai:");
		try {
			List<Flight> fetchflights = service.searchByRoute("Bengaluru", "Mumbai");
			
			for(Flight f : fetchflights) {
				System.out.println(f);
			}
		} catch (FlightNotFoundException e) {
            System.out.println(e.getMessage());
		}
		
		System.out.println("\nSearch flights by airline 'Indigo':");
		
		try {
			List<Flight> fetchingflights = service.searchByAirline("Vistara");
			
			for(Flight f : fetchingflights) {
				System.out.println(f);
			}
		}catch(FlightNotFoundException e) {
            System.out.println(e.getMessage());
		}
		
		System.out.println("\nAttempting to book a seat on flight id=2");
		try {
			double seatsLeft = service.bookSeat(2);
			System.out.println("Seat booked. Seats remaining: " + (int) seatsLeft);
		} catch (FlightNotFoundException e) {
			System.out.println("Booking failed: " + e.getMessage());
		}
		
		System.out.println("\nAttempting to book a seat on flight id=3 (should fail due to 0 seats):");
		try {
			service.bookSeat(3);
		} catch (FlightNotFoundException e) {
			System.out.println("Booking failed: " + e.getMessage());
		}
		
		System.out.println("\nUpdate flight id=4 status to CANCELLED");
		try {
		Flight f4 = service.getFlightById(4);
		f4.setStatus(FlightStatus.CANCELLED);
		service.updateFlight(f4);
			System.out.println("Updated Flight Details: "+service.getFlightById(4));
		} catch (FlightNotFoundException e) {
			System.out.println("Update failed: " + e.getMessage());
		}
		
		System.out.println("\nDelete flight id=1");
		try {
			service.deleteFlight(1);
			System.out.println("Flight 1 deleted");
		} catch (FlightNotFoundException e) {
			System.out.println("Delete failed: " + e.getMessage());
		}
		
		System.out.println("\nFinal Flights");
		try {
			for(Flight f : service.getAllFlights()) {
				System.out.println(f);
			}
		} catch (FlightNotFoundException e) {
			System.out.println("Flights Not Found: "+e.getMessage());
		}
    }
}
