package ServiceImpl;

import Dao.SeatDao;
import Models.AvailableSeatWrapper;

import java.util.Arrays;
import java.util.List;

public class SeatSelectionImpl {


    public SeatSelectionImpl() {

    }

    public AvailableSeatWrapper getAvailableSeats(String bus_no, long routeId) {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();
        SeatDao seatDao = new SeatDao();
        seatDao = fetchAvailableSeats(bus_no,routeId);
        availableSeatWrapper.setDisabledReserved(getSeatList(seatDao.getDisabledReserved()));
        availableSeatWrapper.setSeniorCitizenReserved(getSeatList(seatDao.getSeniorCitizenReserved()));
        availableSeatWrapper.setWomenReservation(getSeatList(seatDao.getWomenReservation()));
        availableSeatWrapper.setGeneral(getSeatList(seatDao.getGeneral()));
        return availableSeatWrapper;

    }

    private List<String> getSeatList(String disabledReserved) {
        String[] array = disabledReserved.split("-");
        List<String> list = Arrays.asList(array);
        return list;

    }

    private SeatDao fetchAvailableSeats(String bus_no,long routeId) {

        SeatDao seatDao = new SeatDao();
        seatDao.setDisabledReserved("B1-B2");
        seatDao.setId(1);
        seatDao.setSeniorCitizenReserved("B3-B4");
        seatDao.setWomenReservation("A1-A2-A3-A4");
        seatDao.setGeneral("C1-C2-C3-C4-D1-D2-D3-D4");
        return seatDao;
    }
}
