package Services;


import Dao.PassengerDao;
import Models.OrderDetails;
import Models.Passenger;
import Models.PassengerWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.PassengerDetailsImpl;
import ServiceImpl.SequenceGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PassengerDetailsService {

    private PassengerDetailsImpl passengerDetailsImpl;
    private SequenceGenerator sequenceGenerator;

    public PassengerDetailsService(ConfigDB configDB) {
        sequenceGenerator = new SequenceGenerator();
        passengerDetailsImpl = new PassengerDetailsImpl(configDB);
    }

    public void savePassengerDetails(PassengerWrapper passengerWrapper, long orderDetails) {
        PassengerDao passengerDao;
        List<Passenger> passengerList = passengerWrapper.getPassengerList();
        for (Passenger passenger : passengerList) {
            passengerDao = mapPassenger(passenger, orderDetails);
            passengerDao.setId(sequenceGenerator.generateSequencePassengers());
            passengerDetailsImpl.savePassengerDetails(passengerDao);
        }
    }

    private PassengerDao mapPassenger(Passenger passenger, long orderDetails) {
        PassengerDao passengerDao = new PassengerDao();
        passengerDao.setAge(passenger.getAge());
        passengerDao.setGender(passenger.getGender());
        passengerDao.setIsDisabled(passenger.getIsDisabled() ? 'Y' : 'N');
        passengerDao.setIsSeniorCitizen(passenger.getIsSeniorCitizen() ? 'Y' : 'N');
        passengerDao.setName(passenger.getName());
        passengerDao.setSeat(passenger.getSeat());
        passengerDao.setOrderId(orderDetails);
        return passengerDao;


    }

    private List<Passenger> mapPassengerDao(List<PassengerDao> passengerDaoList) {
        Passenger passenger;
        List<Passenger> passengerList = new ArrayList<Passenger>();
        for (PassengerDao passengerDao : passengerDaoList) {
            passenger = new Passenger();

            passenger.setAge(passengerDao.getAge());
            passenger.setGender(passengerDao.getGender());
            if (passengerDao.getIsDisabled() == 'Y')
                passenger.setIsDisabled(true);
            else
                passenger.setIsDisabled(false);
            if (passengerDao.getIsSeniorCitizen() == 'Y')
                passenger.setIsSeniorCitizen(true);
            else
                passenger.setIsSeniorCitizen(false);
            passenger.setName(passengerDao.getName());
            passenger.setSeat(passengerDao.getSeat());
            passengerList.add(passenger);
        }
        return passengerList;
    }

    public PassengerWrapper getPassengerDetails(long id) {
        PassengerWrapper passengerWrapper = new PassengerWrapper();
        List<PassengerDao> passengerDaoList = passengerDetailsImpl.getPassengerListBasedOnId(id);
        List<Passenger> passengerList = mapPassengerDao(passengerDaoList);
        passengerWrapper.setPassengerList(passengerList);
        return passengerWrapper;
    }
}
