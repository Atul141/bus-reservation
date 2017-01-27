package Models;

import java.util.Date;
import java.sql.Time;
import java.util.List;

public class Route {
    private long id;
    private String source;
    private String destination;
    private Date date;
    private Time arrivalTime;
    private Time departureTime;
    private int price;
    private String bus_no;
    private int distance;
    private int availableNoSeats;
    private List<Integer> availableSeat;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBus_no() {
        return bus_no;
    }

    public void setBus_no(String bus_no) {
        this.bus_no = bus_no;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getAvailableNoSeats() {
        return availableNoSeats;
    }

    public void setAvailableNoSeats(int availableNoSeats) {
        this.availableNoSeats = availableNoSeats;
    }

    public List<Integer> getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(List<Integer> availableSeat) {
        this.availableSeat = availableSeat;
    }
}
