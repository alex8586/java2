package lv.javaguru.java2.businesslogic.order;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.order.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OrderServiceImplTest {

    @Mock
    OrderDAO orderDAO;

    @Mock
    Order order;

    @InjectMocks
    OrderServiceImpl orderService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void doInvokeDAO() {
        orderService.create(order);
        Mockito.verify(orderDAO, Mockito.times(1)).create(order);
    }
}