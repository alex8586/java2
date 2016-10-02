package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.RateFormatValidationService;
import lv.javaguru.java2.businesslogic.validators.RateValidationService;
import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

public class RateServiceImplTest {
    @InjectMocks
    RateServiceImpl rateService;
    @Mock
    User user;
    @Mock
    private RateDAO rateDAO;
    @Mock
    private RateFormatValidationService rateFormatValidationService;
    @Mock
    private RateValidationService rateValidationService;
    private List<Rate> rateList;


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        rateList = new ArrayList<>();
    }


    @Test(expected = ServiceException.class)
    public void testRate() throws ServiceException {
        Mockito.doThrow(ServiceException.class).when(rateFormatValidationService).validate(22);
        rateService.rate(123, user, 22);
    }

    @Test(expected = IllegalRequestException.class)
    public void testFailWhenNoUser() throws ServiceException {
        rateService.rate(123, null, 4);
    }

    @Test(expected = ServiceException.class)
    public void testFailWhenCantRate() throws ServiceException {
        Mockito.doReturn(false).when(rateValidationService).canRate(user, 123);
        rateService.rate(123, user, 4);
    }

    @Test
    public void createRateWhenEverythingFine() throws ServiceException {
        Mockito.doReturn(true).when(rateValidationService).canRate(user, 123);
        rateService.rate(123, user, 4);
        Mockito.verify(rateDAO, times(1)).create(any());
    }

    @Test
    public void testGetAverageRateOnZeroItems() {
        assertEquals(0.0d, rateService.getAverageRate(rateList), 0.01);
    }

    @Test
    public void testGetAverageRateOnOneItem() {
        addRate(2);
        assertEquals(2.0d, rateService.getAverageRate(rateList), 0.01);
    }

    @Test
    public void testGetAverageRateOnTwoItems() {
        addRate(2, 4);
        assertEquals(3.0d, rateService.getAverageRate(rateList), 0.01);
    }

    @Test
    public void testGetAverageRateOnMultipleItems() {
        addRate(3, 5, 5, 4, 3, 5);
        assertEquals(25d / 6d, rateService.getAverageRate(rateList), 0.01);
    }


    @Test
    public void rateColorReturnWhiteForZero() {
        assertEquals("white", rateService.getRateColor(0));
    }

    @Test
    public void rateColorReturnColorForGoodRate() {
        assertEquals(7, rateService.getRateColor(2.6).length());
    }

    @Test
    public void rateColorRoundsRateCorrectly() {
        assertEquals(rateService.getRateColor(3.9d), rateService.getRateColor(4.4d));
    }


    private void addRate(int... ratedAs) {
        for (int rated : ratedAs) {
            Rate rate = new Rate();
            rate.setRate(rated);
            rateList.add(rate);
        }
    }

}