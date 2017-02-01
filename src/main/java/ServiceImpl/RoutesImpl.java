package ServiceImpl;


import Dao.RouteDao;
import Models.Route;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoutesImpl {

    private ConfigDB configDB;

    public RoutesImpl(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public List<RouteDao> getRoutes(Route routes) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM RouteDao route where route.source=" + "'" + routes.getSource() + "'" + "and route.destination=" + "'" + routes.getDestination() + "'" + "and route.date=" + "'" + routes.getDate() + "'";
        List<RouteDao> routeDaoList = new ArrayList<RouteDao>();
        try {
            routeDaoList = (List<RouteDao>) session.createQuery(query).list();
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return routeDaoList;
    }


    public RouteDao getRoutesBasedOnId(long id) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM RouteDao route where route.id=" + "'" + id + "'";
        RouteDao routeDao = new RouteDao();
        try {
            routeDao = (RouteDao) session.createQuery(query).uniqueResult();
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return routeDao;
    }

    public void updateRoute(RouteDao routeDao) {
        UpdateImpl update = new UpdateImpl(configDB);
        update.UpdateDb(routeDao);

    }
}
