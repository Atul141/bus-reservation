package Services;


import Dao.OrderDetailsDao;
import Dao.RouteDao;
import Models.OrderDetails;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.OrderDetailsImpl;
import ServiceImpl.PassengerDetailsImpl;
import ServiceImpl.RoutesImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserBookingsService {
    private ConfigDB configDB;
    private OrderDetailsImpl orderDetailsImpl;
    private PassengerDetailsImpl passengerDetailsImpl;
    private RoutesImpl routesImpl;

    public UserBookingsService(ConfigDB configDB) {
        this.configDB = configDB;
        orderDetailsImpl = new OrderDetailsImpl(configDB);
        passengerDetailsImpl = new PassengerDetailsImpl(configDB);
        routesImpl = new RoutesImpl(configDB);
    }

    public List<OrderDetails> getOrderDetailsList(String email) {

        List<OrderDetailsDao> orderDetailsDaoList = orderDetailsImpl.getOrderDetails(email);
        List<OrderDetails> orderDetailsList = mapOrderDetailsDao(orderDetailsDaoList);
        orderDetailsList = addRouteDetails(orderDetailsList);
        return orderDetailsList;
    }

    private List<OrderDetails> addRouteDetails(List<OrderDetails> orderDetailsList) {
        Route route;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (OrderDetails orderDetails : orderDetailsList) {
            route = getRouteFromRouteService(orderDetails.getRoute_id());
            orderDetails.setSource(route.getSource());
            orderDetails.setDestination(route.getDestination());
            orderDetails.setDate(formatter.format(route.getDate()));
        }
        return orderDetailsList;
    }

    private Route getRouteFromRouteService(long routeId) {
        RoutesImpl routesImpl = new RoutesImpl(configDB);
        RouteService routeService = new RouteService(configDB);
        List<RouteDao> routeDaoList = new ArrayList<RouteDao>();
        RouteDao routeDao;
        routeDao = routesImpl.getRoutesBasedOnId(routeId);
        routeDaoList.add(routeDao);
        List<Route> routeList = routeService.mapRoutes(routeDaoList);
        return routeList.get(0);

    }

    private List<OrderDetails> mapOrderDetailsDao(List<OrderDetailsDao> orderDetailsDaoList) {
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        OrderDetails orderDetails;
        for (OrderDetailsDao orderDetailsDao : orderDetailsDaoList) {
            orderDetails = new OrderDetails();
            orderDetails.setId(orderDetailsDao.getId());
            orderDetails.setEmail(orderDetailsDao.getEmail());
            if (orderDetailsDao.getStatus().compareTo("cancelled") != 0) {
                orderDetails.setStatus(orderDetailsDao.getStatus());
            } else {
                continue;
            }
            orderDetails.setPrice(orderDetailsDao.getPrice());
            orderDetails.setTime(orderDetails.getTime());
            orderDetails.setRoute_id(orderDetailsDao.getRoute_id());
            orderDetailsList.add(orderDetails);
        }
        return orderDetailsList;
    }
}
