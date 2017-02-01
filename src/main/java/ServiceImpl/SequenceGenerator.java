package ServiceImpl;

import org.hibernate.Session;

public class SequenceGenerator {
    private ConfigDB configDB;

    public SequenceGenerator() {
        configDB = new ConfigDB();
    }

    public Long generateSequencePassengers() {
        Session session = configDB.getSession();
        long maxId;
        try {
            String query = "SELECT MAX(passenger.id) from PassengerDao passenger";
            maxId = (Long) session.createQuery(query).uniqueResult();
        } catch (NullPointerException ex) {
            maxId = 1;
            return maxId;
        }
        maxId++;
        return maxId;
    }

    public Long generateSequenceUserDetails() {
        Session session = configDB.getSession();
        long maxId;
        try {
            String query = "SELECT MAX(user.id) from UserDetailsDao user";
            maxId = (Long) session.createQuery(query).uniqueResult();
        } catch (NullPointerException ex) {
            maxId = 1;
            return maxId;
        }
        maxId++;
        return maxId;
    }

}
