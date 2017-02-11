package Services;

import Models.*;
import ServiceImpl.ConfigDB;
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


    public AvailableSeatWrapper updateAvailableSeats(PassengerWrapper passengerWrapper, AvailableSeatWrapper availableSeatWrapper, AvailableSeatWrapper totalAvailableSeats) {


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

    public void cancelBooking(Route route, PassengerWrapper passengerWrapper, OrderDetails orderDetails) {

        SeatSelectionService seatSelectionService = new SeatSelectionService(configDB);
        RouteService routeService = new RouteService(configDB);

        route = updateRoute(route, passengerWrapper.getPassengerList().size());
        routeService.updateRoute(route);

        TotalSeatSelectionImpl totalSeatSelection = new TotalSeatSelectionImpl(configDB);
        AvailableSeatWrapper totalAvailableSeats = totalSeatSelection.getAvailableSeats(route.getBus_no(), route.getId());
        AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());
        availableSeatWrapper = updateAvailableSeats(passengerWrapper, availableSeatWrapper, totalAvailableSeats);
        seatSelectionService.updateAvailableSeats(availableSeatWrapper);

        PassengerDetailsService passengerDetailsService = new PassengerDetailsService(configDB);
        passengerDetailsService.deletePassengerList(passengerWrapper, orderDetails.getId());

        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        orderDetailsService.deleteOrder(orderDetails);
    }
}
