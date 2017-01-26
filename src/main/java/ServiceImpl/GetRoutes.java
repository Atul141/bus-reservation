package ServiceImpl;


import Models.Route;

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

    public List<Route> getRoutes(Route route) {
        route.setArrivalTime(new Time(1230));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 28);
        Date date = calendar.getTime();
        route.setDate(date);
        route.setDepartureTime(new Time(1730));
        route.setDestination("Mysore");
        route.setSource("Bangalore");
        route.setPrice(300);
        route.setBus_no("KA-09 G-9000");
        route.setDistance(150);
        route.setId(1);

        routes.setArrivalTime(new Time(0530));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2017);
        calendar1.set(Calendar.MONTH, 4);
        calendar1.set(Calendar.DATE, 28);
        Date date1 = calendar1.getTime();
        routes.setDate(date1);
        routes.setDepartureTime(new Time(1230));
        routes.setDestination("Mangalore");
        routes.setSource("Bangalore");
        routes.setPrice(600);
        routes.setBus_no("KA-05 G-9000");
        routes.setDistance(150);
        routes.setId(2);
        routeList.add(route);
        routeList.add(routes);
        return routeList;
    }


}
