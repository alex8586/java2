package lv.javaguru.java2.businesslogic.validators;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.time.Period;

public class DeliveryDateValidationServiceImplTest {

    @InjectMocks
    DeliveryDateValidationServiceImpl deliveryDateValidationService;

    @Before
    public void before() {

    }

    @Test(expected = ServiceException.class)
    public void failsIfDateIsInThePast() throws ServiceException {
        LocalDate yesterday = LocalDate.now().minus(Period.ofDays(1));
        deliveryDateValidationService.validate(yesterday);
    }

}