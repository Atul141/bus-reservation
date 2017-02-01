package Services;


import Dao.PassengerDao;
import Models.Passenger;
import Models.PassengerWrapper;
import ServiceImpl.PassengerDetailsImpl;

import java.sql.Timestamp;
import java.util.List;

public class PassengerDetailsService {

    private PassengerDetailsImpl passengerDetailsImpl;

    public PassengerDetailsService() {
        passengerDetailsImpl = new PassengerDetailsImpl();
    }

    public void savePassengerDetails(PassengerWrapper passengerWrapper) {
        PassengerDao passengerDao;
        List<Passenger> passengerList = passengerWrapper.getPassengerList();
        for (Passenger passenger : passengerList) {
            passengerDao = mapPassenger(passenger, passengerWrapper.getTimestamp());
            passengerDetailsImpl.savePassengerDetails(passengerDao);
        }
    }

    private PassengerDao mapPassenger(Passenger passenger, Timestamp timestamp) {
        PassengerDao passengerDao = new PassengerDao();
        passengerDao.setAge(passenger.getAge());
        passengerDao.setGender(passenger.getGender());
        passengerDao.setIsDisabled(passenger.getIsDisabled()?'Y':'N');
        passengerDao.setIsSeniorCitizen(passenger.getIsSeniorCitizen()?'Y':'N');
        passengerDao.setName(passenger.getName());
        passengerDao.setSeat(passenger.getSeat());
        passengerDao.setTimestamp(timestamp);
        return passengerDao;

    }
}
