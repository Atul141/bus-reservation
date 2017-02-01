package ServiceTest;


import Models.AvailableSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SeatSelectionImpl;
import ServiceImpl.SyntaxSugar;
import Services.SeatSelectionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SeatSelectionServiceTest {


    private SeatSelectionImpl seatSelection;
    private SeatSelectionService seatSelectionService;
    private ConfigDB configDB;

    @Before
    public void setup() {
        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        initMocks(this);
        seatSelection=mock(SeatSelectionImpl.class);
        seatSelectionService=new SeatSelectionService(configDB);
    }


    @Test
    public void shouldInvokeSeatSelectionMethodOfSeatSelectionImpl() {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();
        availableSeatWrapper.setId(1);

        when(seatSelection.getAvailableSeats(anyString(), anyLong())).thenReturn(availableSeatWrapper);
        assertEquals(1, seatSelectionService.getAvailableSeat("KA-09 G-2020", 1L).getId());
    }

}
