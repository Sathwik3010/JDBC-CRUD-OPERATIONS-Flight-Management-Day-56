package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

	private int id;
	private String airline;
	private String source;
	private String destination;
	private LocalDateTime departure;
	private LocalDateTime arrival;
	private double price;
	private int seatsAvailable;
	private FlightStatus status;
	
	public Flight(int id, String airline, String source, String destination, LocalDateTime departure,
			LocalDateTime arrival, double price, int seatsAvailable, FlightStatus status) {
		super();
		this.id = id;
		this.airline = airline;
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.price = price;
		this.seatsAvailable = seatsAvailable;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

		return "Flight [id=" + id + ", airline=" + airline + ", " + source + " -> " + destination + ", depart="
				+ (departure != null ? departure.format(fmt) : "null") + ", arrive="
				+ (arrival != null ? arrival.format(fmt) : "null") + ", price=" + price + ", seatsAvailable="
				+ seatsAvailable + ", status=" + status + "]";
	}

}
