package Services;


import Dao.OrderDetailsDao;
import Models.AvailableSeatWrapper;
import Models.OrderDetails;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.OrderDetailsImpl;
import ServiceImpl.SequenceGenerator;
import ServiceImpl.UpdateImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderDetailsService {

    private ConfigDB configDB;
    private OrderDetailsImpl orderDetailsImpl;

    public OrderDetailsService(ConfigDB configDB) {

        this.configDB = configDB;
        orderDetailsImpl = new OrderDetailsImpl(configDB);
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


    public long saveOrder(OrderDetails orderDetails) {
        SequenceGenerator sequenceGenerator = new SequenceGenerator(configDB);
        Long id = sequenceGenerator.generateSequenceOrderDetails();
        orderDetails.setId(id);
        orderDetailsImpl.saveOrderDetails(mapOrderDetails(orderDetails));
        return id;
    }

    private OrderDetailsDao mapOrderDetails(OrderDetails orderDetails) {
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        orderDetailsDao.setId(orderDetails.getId());
        orderDetailsDao.setEmail(orderDetails.getEmail());
        orderDetailsDao.setPrice(orderDetails.getPrice());
        orderDetailsDao.setRoute_id(orderDetails.getRoute_id());
        orderDetailsDao.setStatus(orderDetails.getStatus());
        orderDetailsDao.setTime(orderDetails.getTime());
        return orderDetailsDao;
    }

    public OrderDetails mapOrderDetailsDao(OrderDetailsDao orderDetailsDao) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(orderDetailsDao.getId());
        orderDetails.setEmail(orderDetailsDao.getEmail());
        orderDetails.setPrice(orderDetailsDao.getPrice());
        orderDetails.setRoute_id(orderDetailsDao.getRoute_id());
        orderDetails.setStatus(orderDetailsDao.getStatus());
        orderDetails.setTime(orderDetailsDao.getTime());
        return orderDetails;
    }

    public OrderDetails getOrderBasedOnId(long id) {
        OrderDetailsDao orderDetailsDao = orderDetailsImpl.getOrderDetailsBasedOnId(id);
        return mapOrderDetailsDao(orderDetailsDao);
    }

    public void deleteOrder(OrderDetails orderDetails) {
        orderDetailsImpl.deleteOrder(mapOrderDetails(orderDetails));
    }

    public List<OrderDetailsDao> getOrderDetails(String email) {

        return orderDetailsImpl.getOrderDetails(email);
    }

    public List<OrderDetails> getOrderDetailsBasedOnStatus(String status) {

        List<OrderDetailsDao> orderDetailsListDao = orderDetailsImpl.getOrderDetailsBaseOnStatus(status);
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        for (OrderDetailsDao orderDetailsDao : orderDetailsListDao) {
            orderDetailsList.add(mapOrderDetailsDao(orderDetailsDao));
        }
        return orderDetailsList;
    }

    public void updateOrderDetails(OrderDetails orderDetails) {
        UpdateImpl update = new UpdateImpl(configDB);
        update.UpdateDb(mapOrderDetails(orderDetails));
    }
}
