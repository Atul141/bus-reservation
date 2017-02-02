package Services;

import Models.*;
import ServiceImpl.ConfigDB;
import ServiceImpl.SeatSelectionImpl;
import ServiceImpl.TotalSeatSelectionImpl;

import java.util.LinkedList;
import java.util.List;

public class CancelBookingService {
    private ConfigDB configDB;

    public CancelBookingService(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public Route updateRoute(Route route, int size) {
        route.setAvailableNoSeats(route.getAvailableNoSeats() + size);
        return route;
    }


    public AvailableSeatWrapper updateAvailableSeats(PassengerWrapper passengerWrapper, Route route, AvailableSeatWrapper availableSeatWrapper, AvailableSeatWrapper totalAvailableSeats) {


        for (Passenger passenger : passengerWrapper.getPassengerList()) {

            if (totalAvailableSeats.getGeneral().contains(passenger.getSeat())) {
                List<String> availableSeat = new LinkedList<String>(availableSeatWrapper.getGeneral());
                availableSeat.add(passenger.getSeat());
                availableSeatWrapper.setGeneral(availableSeat);
            }
            if (totalAvailableSeats.getWomenReservation().contains(passenger.getSeat())) {
                List<String> availableSeat = new LinkedList<String>(availableSeatWrapper.getWomenReservation());
                availableSeat.add(passenger.getSeat());
                availableSeatWrapper.setWomenReservation(availableSeat);
            }
            if (totalAvailableSeats.getDisabledReserved().contains(passenger.getSeat())) {
                List<String> availableSeat = new LinkedList<String>(availableSeatWrapper.getDisabledReserved());
                availableSeat.add(passenger.getSeat());
                availableSeatWrapper.setDisabledReserved(availableSeat);
            }
            if (totalAvailableSeats.getSeniorCitizenReserved().contains(passenger.getSeat())) {
                List<String> availableSeat = new LinkedList<String>(availableSeatWrapper.getSeniorCitizenReserved());
                availableSeat.add(passenger.getSeat());
                availableSeatWrapper.setSeniorCitizenReserved(availableSeat);
            }
        }
        return availableSeatWrapper;

    }
}
