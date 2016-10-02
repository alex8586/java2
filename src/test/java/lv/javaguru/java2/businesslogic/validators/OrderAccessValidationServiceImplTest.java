package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.order.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OrderAccessValidationServiceImplTest {

    @Mock
    LockedResourceAccessService lockedResourceAccessService;
    @InjectMocks
    OrderAccessValidationServiceImpl orderAccessValidationService;
    @Mock
    private OrderDAO orderDAO;
    @Mock
    private Order order;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserCantSeeUnExistingOrder() {
        Mockito.doReturn(null).when(orderDAO).getById(1);
        assertFalse(orderAccessValidationService.isValid(1, 1));
    }

    @Test
    public void testUserCantSeeOrderIfDoesNotOwnIt() {
        Mockito.doReturn(order).when(orderDAO).getById(1);
        Mockito.doReturn(2L).when(order).getUserId();
        assertFalse(orderAccessValidationService.isValid(1, 1));
    }


    @Test
    public void testUserCanSeeOrderIfDoesNotOwnIt() {
        Mockito.doReturn(order).when(orderDAO).getById(1);
        Mockito.doReturn(1L).when(order).getUserId();
        assertTrue(orderAccessValidationService.isValid(1, 1));
    }


    @Test
    public void testVisitorCantSeeOrderWhenKeysNotMatch() {
        Mockito.doReturn(order).when(orderDAO).getById(1);
        Mockito.doReturn(false).when(lockedResourceAccessService).validateKey(order, "A");
        assertFalse(orderAccessValidationService.isValid(1, "A"));
    }

    @Test
    public void testVisitorCanSeeOrderWhenKeysNotMatch() {
        Mockito.doReturn(order).when(orderDAO).getById(1);
        Mockito.doReturn(true).when(lockedResourceAccessService).validateKey(order, "A");
        assertTrue(orderAccessValidationService.isValid(1, "A"));
    }


}