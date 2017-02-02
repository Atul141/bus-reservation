package Dao;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "totalseats")
public class TotalSeatsDao {

    @Id
    private int id;
    private String womenReservation;
    private String seniorCitizenReserved;
    private String disabledReserved;
    private String general;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWomenReservation() {
        return womenReservation;
    }

    public void setWomenReservation(String womenReservation) {
        this.womenReservation = womenReservation;
    }

    public String getSeniorCitizenReserved() {
        return seniorCitizenReserved;
    }

    public void setSeniorCitizenReserved(String seniorCitizenReserved) {
        this.seniorCitizenReserved = seniorCitizenReserved;
    }

    public String getDisabledReserved() {
        return disabledReserved;
    }

    public void setDisabledReserved(String disabledReserved) {
        this.disabledReserved = disabledReserved;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

}
