package ServiceImpl;

import org.hibernate.Session;

public class SequenceGenerator {
    private ConfigDB configDB;

    public SequenceGenerator(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public Long generateSequencePassengers() {
        String query = "SELECT MAX(passenger.id) from PassengerDao passenger";
        return generateSequence(query);
    }

    public Long generateSequenceUserDetails() {
        String query = "SELECT MAX(user.id) from UserDetailsDao user";
        return generateSequence(query);
    }

    public Long generateSequenceOrderDetails() {
        String query = "SELECT MAX(orders.id) from OrderDetailsDao orders";
        return generateSequence(query);
    }

    private Long generateSequence(String query) {
        Session session = configDB.getSession();
        long maxId;
        try {
            maxId = (Long) session.createQuery(query).uniqueResult();
        } catch (NullPointerException ex) {
            maxId = 1;
            return maxId;
        }
        maxId++;
        return maxId;
    }

}
