package ServiceImpl;


import Dao.PassengerDao;

public class PassengerDetailsImpl {

    private SaveToDb saveToDb;

    public PassengerDetailsImpl(ConfigDB configDB) {
        this.saveToDb = new SaveToDb(configDB);
    }
    public void savePassengerDetails(PassengerDao passenger){
        saveToDb.saveToDb(passenger);
    }

}
