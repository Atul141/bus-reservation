package ServiceTest;


import ServiceImpl.RoutesImpl;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class RouteServiceTest {


    @Mock
    RoutesImpl routesImpl;
    @Test
    public void getRoutesList(){
        initMocks(this);

    }
}
