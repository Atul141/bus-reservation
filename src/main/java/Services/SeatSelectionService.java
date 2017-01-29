package Services;


import Models.AvailableSeatWrapper;
import ServiceImpl.SeatSelectionImpl;

public class SeatSelectionService {

    public AvailableSeatWrapper getAvailableSeat(String bus_no, long routeId) {
        SeatSelectionImpl seatSelection = new SeatSelectionImpl();
        return seatSelection.getAvailableSeats(bus_no, routeId);
    }
}
