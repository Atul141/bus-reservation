package ServiceImpl;


import Dao.RouteDao;
import Dao.UserDetailsDao;
import Models.Route;
import Services.NumberOfSeatService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class RoutesImpl {

    private ConfigDB configDB;

    public RoutesImpl() {
        configDB = new ConfigDB();
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
        UpdateImpl update = new UpdateImpl();
        update.UpdateDb(routeDao);

    }
}
