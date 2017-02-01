package ServiceImpl;


import Dao.OrderDetailsDao;

public class OrderDetailsImpl {

    private SaveToDb saveToDb;
    private ConfigDB configDB;

    public OrderDetailsImpl(ConfigDB configDB) {
        this.configDB=configDB;
        saveToDb = new SaveToDb(configDB);
    }

    public void saveOrderDetails(OrderDetailsDao orderDetails) {
        saveToDb.saveToDb(orderDetails);
    }
}
