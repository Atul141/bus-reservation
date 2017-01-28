package Services;


import Dao.RouteDao;
import Models.Route;
import ServiceImpl.GetRoutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteService {

    private Route route;
    private RouteDao routeDao;
    private GetRoutes getRoutes;
    private List<Route> routesList;
    private List<RouteDao> routesDaoList;

    public RouteService() {
        route = new Route();
        routesList = new ArrayList<Route>();
        getRoutes = new GetRoutes();
        routeDao = new RouteDao();
    }

    public List<Route> getRouteList(Route routeDetails) {
        routeDetails.setSource(routeDetails.getSource().toUpperCase());
        routeDetails.setDestination(routeDetails.getDestination().toUpperCase());
        routesDaoList = getRoutes.getRoutes(routeDetails);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        NumberOfSeatService[] numberOfSeatService = new NumberOfSeatService[routesDaoList.size()];
        for (int index = 0; index < routesDaoList.size(); index++) {
            route = new Route();
            numberOfSeatService[index] = new NumberOfSeatService();
            routeDao = routesDaoList.get(index);
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
