package ServiceImpl;

import Dao.SeatsDao;
import Models.AvailableSeatWrapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class SeatSelectionImpl {


    private ConfigDB configDB;

    public SeatSelectionImpl(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public AvailableSeatWrapper getAvailableSeats(String bus_no, long routeId) {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();
        SeatsDao seatsDao = fetchAvailableSeats(bus_no, routeId);
        availableSeatWrapper.setId(seatsDao.getId());
        availableSeatWrapper.setDisabledReserved(getSeatList(seatsDao.getDisabledReserved()));
        availableSeatWrapper.setSeniorCitizenReserved(getSeatList(seatsDao.getSeniorCitizenReserved()));
        availableSeatWrapper.setWomenReservation(getSeatList(seatsDao.getWomenReservation()));
        availableSeatWrapper.setGeneral(getSeatList(seatsDao.getGeneral()));
        return availableSeatWrapper;

    }

    private List<String> getSeatList(String disabledReserved) {
        String[] array = disabledReserved.split("-");
        List<String> list = Arrays.asList(array);
        return list;

    }

    private SeatsDao fetchAvailableSeats(String bus_no, long routeId) {

        SeatsDao seatsDao = new SeatsDao();
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query subQuery = session.createQuery("Select bus.seat_no From BusDao bus WHERE bus.routeid=" + routeId + " and bus.number=" + "'" + bus_no + "'");
            Query query = session.createQuery("FROM  SeatsDao seat WHERE seat.id IN(:ids)").setParameterList("ids", subQuery.getResultList());
            seatsDao = (SeatsDao) query.getResultList().get(0);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return seatsDao;
    }

    public void updateAvailableSeats(SeatsDao seatsDao) {

        UpdateImpl update = new UpdateImpl();
        update.UpdateDb(seatsDao);
    }
}
