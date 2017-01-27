package ServiceImpl;


import Models.Route;
import Services.NumberOfSeatService;

import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GetRoutes {
    private Route routes;
    private List<Route> routeList;

    public GetRoutes() {
        routeList = new ArrayList<Route>();
        routes = new Route();
    }

    public List<Route> getRoutes(Route route3) {
        Route route1 = new Route();
        Route route2 = new Route();
        route1.setArrivalTime(new Time(1230));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 28);
        Date date = calendar.getTime();
        route1.setDate(date);
        route1.setDepartureTime(new Time(1730));
        route1.setDestination("Mysore");
        route1.setSource("Bangalore");
        route1.setPrice(300);
        route1.setBus_no("KA-09 G-9000");
        route1.setDistance(150);
        route1.setId(1);
        route1.setAvailableNoSeats(24);

        NumberOfSeatService numberOfSeatService1 = new NumberOfSeatService();
        NumberOfSeatService numberOfSeatService2 = new NumberOfSeatService();
        route1.setAvailableSeat(numberOfSeatService1.getSeatNumber(route1.getAvailableNoSeats()));

        route2.setArrivalTime(new Time(0530));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2017);
        calendar1.set(Calendar.MONTH, 4);
        calendar1.set(Calendar.DATE, 28);
        Date date1 = calendar1.getTime();
        route2.setDate(date1);
        route2.setDepartureTime(new Time(1230));
        route2.setDestination("Mangalore");
        route2.setSource("Bangalore");
        route2.setPrice(600);
        route2.setBus_no("KA-05 G-9000");
        route2.setDistance(300);
        route2.setId(2);
        route2.setAvailableNoSeats(34);
        route2.setAvailableSeat(numberOfSeatService2.getSeatNumber(route2.getAvailableNoSeats()));

        routeList.add(route1);
        routeList.add(route2);
        return routeList;
    }


}
