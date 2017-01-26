package Services;


import Dao.RouteDao;
import Models.Route;
import ServiceImpl.GetRoutes;

import java.util.List;

public class RouteService {

    private Route route;
    private RouteDao routeDao;
    private GetRoutes getRoutes ;
    private List<Route> routesList;

    public RouteService(){
        getRoutes=new GetRoutes();
        routeDao=new RouteDao();
    }
    public List<Route> getRouteList(Route route){
        routesList=getRoutes.getRoutes(route);
        return routesList;
    }
}
