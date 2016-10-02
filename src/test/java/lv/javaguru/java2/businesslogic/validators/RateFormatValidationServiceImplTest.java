package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.junit.Before;
import org.junit.Test;


public class RateFormatValidationServiceImplTest {

    private static final int MINIMAL_RATE = 1;
    private static final int MAXIMAL_RATE = 5;

    RateFormatValidationServiceImpl rateFormatValidationService;

    @Before
    public void before() {
        rateFormatValidationService = new RateFormatValidationServiceImpl();
    }

    @Test(expected = ServiceException.class)
    public void cantRateWithZero() throws ServiceException {
        rateFormatValidationService.validate(0);
    }

    @Test(expected = ServiceException.class)
    public void cantRateWithRateOverMaximum() throws ServiceException {
        rateFormatValidationService.validate(MAXIMAL_RATE + 1);
    }

    @Test
    public void canRateWithingRateBounds() throws ServiceException {
        for (int i = MINIMAL_RATE; i <= MAXIMAL_RATE; i++) {
            rateFormatValidationService.validate(i);
        }
    }

}