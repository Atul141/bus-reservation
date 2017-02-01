package ServiceImpl;


import Dao.PassengerDao;

public class PassengerDetailsImpl {

    private SaveToDb saveToDb;

    public PassengerDetailsImpl() {
        this.saveToDb = new SaveToDb();
    }
    public void savePassengerDetails(PassengerDao passenger){
        saveToDb.saveToDb(passenger);
    }

}
