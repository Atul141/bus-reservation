package ServiceImpl;


import Dao.OrderDetailsDao;

public class OrderDetailsImpl {

    private SaveImpl saveImpl;
    private ConfigDB configDB;

    public OrderDetailsImpl(ConfigDB configDB) {
        this.configDB=configDB;
        saveImpl = new SaveImpl(configDB);
    }

    public void saveOrderDetails(OrderDetailsDao orderDetails) {
        saveImpl.saveToDb(orderDetails);
    }
}
