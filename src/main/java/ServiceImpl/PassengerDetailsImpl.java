package ServiceImpl;


import Dao.PassengerDao;

public class PassengerDetailsImpl {

    private SaveImpl saveImpl;

    public PassengerDetailsImpl(ConfigDB configDB) {
        this.saveImpl = new SaveImpl(configDB);
    }
    public void savePassengerDetails(PassengerDao passenger){
        saveImpl.saveToDb(passenger);
    }

}
