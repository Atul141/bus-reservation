package Services;


import Dao.SeatsDao;
import Models.AvailableSeatWrapper;
import ServiceImpl.SeatSelectionImpl;

import java.util.List;

public class SeatSelectionService {

    private SeatSelectionImpl seatSelection;
    public SeatSelectionService(){
        seatSelection=new SeatSelectionImpl();
    }

    public AvailableSeatWrapper getAvailableSeat(String bus_no, long routeId) {
        return seatSelection.getAvailableSeats(bus_no, routeId);
    }

    public void updateAvailableSeats(AvailableSeatWrapper availableSeatWrapper) {
        SeatsDao seatsDao= new SeatsDao();
        seatsDao.setId(availableSeatWrapper.getId());
        seatsDao.setGeneral(mapToString(availableSeatWrapper.getGeneral()));
        seatsDao.setDisabledReserved(mapToString(availableSeatWrapper.getDisabledReserved()));
        seatsDao.setSeniorCitizenReserved(mapToString(availableSeatWrapper.getSeniorCitizenReserved()));
        seatsDao.setWomenReservation(mapToString(availableSeatWrapper.getWomenReservation()));
        seatSelection.updateAvailableSeats(seatsDao);

    }

    private String  mapToString(List<String > availableSeats){
        String seats="";

        for(int i=0;i<availableSeats.size();i++){
            seats=seats+availableSeats.get(i)+"-";
        }
        return seats;
    }

}
