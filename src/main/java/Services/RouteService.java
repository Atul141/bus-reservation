package Services;


import Dao.RouteDao;
import Models.Route;
import ServiceImpl.RoutesImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RouteService {


    public List<Route> getRouteList(Route routeDetails) {
        RoutesImpl routesImpl = new RoutesImpl();
        routeDetails.setSource(routeDetails.getSource().toUpperCase());
        routeDetails.setDestination(routeDetails.getDestination().toUpperCase());
        return mapRoutes(routesImpl.getRoutes(routeDetails));
    }

    public Route getRouteBasedOnId(long id) {
        RoutesImpl routesImpl = new RoutesImpl();
        List<RouteDao> routeDaoList = new ArrayList<RouteDao>();
        routeDaoList.add(routesImpl.getRoutesBasedOnId(id));
        return mapRoutes(routeDaoList).get(0);
    }

    public List<Route> mapRoutes(List<RouteDao> routesDaoList) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Route> routesList = new ArrayList<Route>();
        NumberOfSeatService[] numberOfSeatService = new NumberOfSeatService[routesDaoList.size()];

        for (int index = 0; index < routesDaoList.size(); index++) {
            Route route = new Route();
            numberOfSeatService[index] = new NumberOfSeatService();
            RouteDao routeDao = routesDaoList.get(index);
            route.setSelectedDate(formatter.format(routeDao.getDate()));
            route.setId(routeDao.getId());
            route.setDate(routeDao.getDate());
            route.setAvailableNoSeats(routeDao.getAvailableNoSeats());
            route.setPrice(routeDao.getPrice());
            route.setDepartureTime(routeDao.getDepartureTime());
            route.setArrivalTime(routeDao.getArrivalTime());
            route.setSource(routeDao.getSource());
            route.setDestination(routeDao.getDestination());
            route.setSource(routeDao.getSource());
            route.setDistance(routeDao.getDistance());
            route.setBus_no(routeDao.getBus_no());
            route.setAvailableSeat(numberOfSeatService[index].getSeatNumber(route.getAvailableNoSeats()));
            routesList.add(route);
        }
        return routesList;
    }

}
