package ServiceImpl;


import Dao.OrderDetailsDao;
import Models.OrderDetails;

public class OrderDetailsImpl {

    private SaveToDb saveToDb;

    public OrderDetailsImpl() {
        saveToDb = new SaveToDb();
    }

    public void saveOrderDetails(OrderDetailsDao orderDetails) {
        saveToDb.saveToDb(orderDetails);
    }
}
