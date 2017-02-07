package ServiceImpl;


import Dao.OrderDetailsDao;
import Dao.PassengerDao;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PassengerDetailsImpl {

    private SaveImpl saveImpl;
    private ConfigDB configDB;
    private DeleteImpl deleteImpl;

    public PassengerDetailsImpl(ConfigDB configDB) {
        this.configDB = configDB;
        saveImpl = new SaveImpl(configDB);
        deleteImpl = new DeleteImpl(configDB);
    }

    public void savePassengerDetails(PassengerDao passenger) {

        saveImpl.saveToDb(passenger);
    }

    public List<PassengerDao> getPassengerListBasedOnId(long id) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM PassengerDao passenger where passenger.orderId=" + "'" + id + "'";
        List<PassengerDao> passengerDaoList = new ArrayList<PassengerDao>();
        try {
            passengerDaoList = (List<PassengerDao>) session.createQuery(query).list();
            session.lock(new PassengerDao(), LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        System.out.println(passengerDaoList);

        return passengerDaoList;
    }

    public void deletePassengerDetails(PassengerDao passengerDao) {
        deleteImpl.deleteDb(passengerDao);
    }
}
