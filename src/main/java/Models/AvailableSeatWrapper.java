package Models;

import java.util.List;

public class AvailableSeatWrapper {

    private int id;
    private List<String> womenReservation;
    private List<String> seniorCitizenReserved;
    private List<String> disabledReserved;
    private List<String> general;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getWomenReservation() {
        return womenReservation;
    }

    public void setWomenReservation(List<String> womenReservation) {
        this.womenReservation = womenReservation;
    }

    public List<String> getSeniorCitizenReserved() {
        return seniorCitizenReserved;
    }

    public void setSeniorCitizenReserved(List<String> seniorCitizenReserved) {
        this.seniorCitizenReserved = seniorCitizenReserved;
    }

    public List<String> getDisabledReserved() {
        return disabledReserved;
    }

    public void setDisabledReserved(List<String> disabledReserved) {
        this.disabledReserved = disabledReserved;
    }

    public List<String> getGeneral() {
        return general;
    }

    public void setGeneral(List<String> general) {
        this.general = general;
    }
}

