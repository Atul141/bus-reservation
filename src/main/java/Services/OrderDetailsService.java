package Services;


import Dao.OrderDetailsDao;
import Models.AvailableSeatWrapper;
import Models.OrderDetails;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.OrderDetailsImpl;
import ServiceImpl.SequenceGenerator;

import java.util.LinkedList;
import java.util.List;

public class OrderDetailsService {

    private ConfigDB configDB;

    public OrderDetailsService(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public Route updateRoute(Route route, int size) {
        route.setAvailableNoSeats(route.getAvailableNoSeats() - size);
        return route;
    }

    public AvailableSeatWrapper updateAvailableSeats(AvailableSeatWrapper availableSeatWrapper, SelectedSeatWrapper selectedSeatWrapper) {

        List<String> availableGeneral = new LinkedList<String>(availableSeatWrapper.getGeneral());
        availableGeneral = removeAll(availableGeneral, selectedSeatWrapper.getSelectedSeatGeneral());
        availableSeatWrapper.setGeneral(availableGeneral);


        List<String> availableWomen = new LinkedList<String>(availableSeatWrapper.getWomenReservation());
        availableWomen = removeAll(availableWomen, selectedSeatWrapper.getSelectedSeatWomen());
        availableSeatWrapper.setWomenReservation(availableWomen);

        List<String> availableSenior = new LinkedList<String>(availableSeatWrapper.getSeniorCitizenReserved());
        availableSenior = removeAll(availableSenior, selectedSeatWrapper.getSelectedSeatSeniorCitizen());
        availableSeatWrapper.setSeniorCitizenReserved(availableSenior);

        List<String> availableDisabled = new LinkedList<String>(availableSeatWrapper.getDisabledReserved());
        availableDisabled = removeAll(availableDisabled, selectedSeatWrapper.getSelectedSeatDisabled());
        availableSeatWrapper.setDisabledReserved(availableDisabled);

        return availableSeatWrapper;
    }

    private List<String> removeAll(List<String> availableSeats, List<String> selectedSeats) {
        for (String selectedSeat : selectedSeats) {
            availableSeats.remove(selectedSeat);
        }
        return availableSeats;
    }


    public void saveOrder(OrderDetails orderDetails) {
        OrderDetailsImpl orderDetailsImpl = new OrderDetailsImpl(configDB);
        SequenceGenerator sequenceGenerator = new SequenceGenerator();

        orderDetails.setId(sequenceGenerator.generateSequenceOrderDetails());
        orderDetailsImpl.saveOrderDetails(mapOrderDetails(orderDetails));
    }

    public OrderDetailsDao mapOrderDetails(OrderDetails orderDetails) {
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        orderDetailsDao.setId(orderDetails.getId());
        orderDetailsDao.setEmail(orderDetails.getEmail());
        orderDetailsDao.setPrice(orderDetails.getPrice());
        orderDetailsDao.setRoute_id(orderDetails.getRoute_id());
        orderDetailsDao.setStatus(orderDetails.getStatus());
        orderDetailsDao.setTime(orderDetails.getTime());
        return orderDetailsDao;
    }
}
