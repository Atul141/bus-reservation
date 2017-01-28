package Dao;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "routedetails")
public class RouteDao {

    @Id
    private long id;
    @Column(columnDefinition = "VARCHAR(100)")
    private String source;
    @Column(columnDefinition = "VARCHAR(100)")
    private String destination;
    @Column(columnDefinition = "DATE")
    private java.util.Date date;
    @Column(columnDefinition = "TIME")
    private Time arrivalTime;
    @Column(columnDefinition = "TIME")
    private Time departureTime;
    @Column(columnDefinition = "INTEGER")
    private int price;
    @Column(columnDefinition = "VARCHAR(100)")
    private String bus_no;
    @Column(columnDefinition = "INTEGER")
    private int distance;
    @Column(columnDefinition = "INTEGER")
    private int availableNoSeats;


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

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
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

}