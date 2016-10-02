package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.CountVisitorsDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class StatisticCountServiceImplTest {

    @InjectMocks
    StatisticCountServiceImpl statisticCountService;
    @Mock
    private CountVisitorsDAO countVisitorsDAO;
    @Mock
    private CountUsersDAO countUsersDAO;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testZero() {
        long productId = 123;
        Mockito.doReturn(0L).when(countUsersDAO).getCountByProductId(productId);
        Mockito.doReturn(0L).when(countVisitorsDAO).getCountByProductId(productId);
        assertEquals(0, statisticCountService.getProductViews(productId));
    }

    @Test
    public void testZeroPlusFortyTwo() {
        long productId = 123;
        Mockito.doReturn(0L).when(countUsersDAO).getCountByProductId(productId);
        Mockito.doReturn(42L).when(countVisitorsDAO).getCountByProductId(productId);
        assertEquals(42, statisticCountService.getProductViews(productId));
    }

    @Test
    public void testOverNineThousand() {
        long productId = 123;
        Mockito.doReturn(8999L).when(countUsersDAO).getCountByProductId(productId);
        Mockito.doReturn(2L).when(countVisitorsDAO).getCountByProductId(productId);
        assertEquals(9001, statisticCountService.getProductViews(productId));
    }


}