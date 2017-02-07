package ServiceImpl;


import Dao.OrderDetailsDao;
import Models.OrderDetails;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsImpl {

    private SaveImpl saveImpl;
    private ConfigDB configDB;

    public OrderDetailsImpl(ConfigDB configDB) {
        this.configDB = configDB;
        saveImpl = new SaveImpl(configDB);
    }

    public void saveOrderDetails(OrderDetailsDao orderDetails) {
        saveImpl.saveToDb(orderDetails);
    }

    public List<OrderDetailsDao> getOrderDetails(String email) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM OrderDetailsDao orders where orders.email=" + "'" + email + "'";
        List<OrderDetailsDao> orderDetailsDaoList = new ArrayList<OrderDetailsDao>();
        try {
            orderDetailsDaoList = (List<OrderDetailsDao>) session.createQuery(query).list();
            session.load(new OrderDetailsDao(), LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return orderDetailsDaoList;

    }

    public OrderDetailsDao getOrderDetailsBasedOnId(long id) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM OrderDetailsDao orders where orders.id=" + id + " ";
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        try {
            orderDetailsDao = (OrderDetailsDao) session.createQuery(query).uniqueResult();
            session.load(orderDetailsDao, LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return orderDetailsDao;
    }

    public void deleteOrder(OrderDetailsDao orderDetails) {

        orderDetails.setStatus(SyntaxSugar.CANCEL);
        UpdateImpl update = new UpdateImpl(configDB);
        update.UpdateDb(orderDetails);
    }

    public List<OrderDetailsDao> getOrderDetailsBaseOnStatus(String status) {
        Session session = configDB.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "FROM OrderDetailsDao orders where orders.status=" + "'" + status + "'";
        List<OrderDetailsDao> orderDetailsDaoList = new ArrayList<OrderDetailsDao>();
        try {
            orderDetailsDaoList = (List<OrderDetailsDao>) session.createQuery(query).list();
            session.load(new OrderDetailsDao(), LockMode.READ);
            transaction.commit();
            session.close();
        } catch (Throwable ex) {
            System.out.println("error creating session " + ex);
        }
        return orderDetailsDaoList;

    }
}
