package Services;


import Models.OrderDetails;
import Models.Route;

public class OrderDetailsService {


    public Route updateRoute(Route route, int size) {
        route.setAvailableNoSeats(route.getAvailableNoSeats()-size);
        return route;
    }
}
