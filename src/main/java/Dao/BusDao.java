package Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buswrapper")
public class BusDao {

    @Id
    @Column(name = "bus_no",columnDefinition = "VARCHAR(50)")
    private String number;
    @Column(name = "seat", columnDefinition = "INTEGER")
    private int seat_no;
    @Column(name = "routeId", columnDefinition = "INTEGER" )
    private int routeid;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
    }

    public int getRoute_no() {
        return routeid;
    }

    public void setRoute_no(int route_no) {
        this.routeid = route_no;
    }
}
