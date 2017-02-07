package ServiceImpl;


import Dao.OrderDetailsDao;
import Dao.SeatsDao;
import Dao.TotalSeatsDao;
import Models.AvailableSeatWrapper;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class TotalSeatSelectionImpl {

    private ConfigDB configDB;

    public TotalSeatSelectionImpl(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public AvailableSeatWrapper getAvailableSeats(String bus_no, long routeId) {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();
        TotalSeatsDao seatsDao = fetchAvailableSeats(bus_no, routeId);
        availableSeatWrapper.setId(seatsDao.getId());
        availableSeatWrapper.setSeniorCitizenReserved(getSeatList(seatsDao.getSeniorCitizenReserved()));
        availableSeatWrapper.setDisabledReserved(getSeatList(seatsDao.getDisabledReserved()));
        availableSeatWrapper.setWomenReservation(getSeatList(seatsDao.getWomenReservation()));
        availableSeatWrapper.setGeneral(getSeatList(seatsDao.getGeneral()));
        return availableSeatWrapper;

    }

    private List<String> getSeatList(String disabledReserved) {
        System.out.println(disabledReserved);
        String[] array = disabledReserved.split("-");
        List<String> list = Arrays.asList(array);
        return list;

    }

    private TotalSeatsDao fetchAvailableSeats(String bus_no, long routeId) {

        TotalSeatsDao seatsDao = new TotalSeatsDao();
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query subQuery = session.createQuery("Select bus.seat_no From BusDao bus WHERE bus.routeid=" + routeId + " and bus.number=" + "'" + bus_no + "'");
            Query query = session.createQuery("FROM  TotalSeatsDao seat WHERE seat.id IN(:ids)").setParameterList("ids", subQuery.getResultList());
            seatsDao = (TotalSeatsDao) query.getResultList().get(0);
            session.lock(seatsDao, LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return seatsDao;
    }

}
