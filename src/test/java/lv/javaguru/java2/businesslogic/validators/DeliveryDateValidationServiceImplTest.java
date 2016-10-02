package lv.javaguru.java2.businesslogic.validators;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class DeliveryDateValidationServiceImplTest {

    @InjectMocks
    DeliveryDateValidationServiceImpl deliveryDateValidationService;

    @Before
    public void before() {
        deliveryDateValidationService = new DeliveryDateValidationServiceImpl(14);
    }

    @Test(expected = ServiceException.class)
    public void failsIfDateIsInThePast() throws ServiceException {
        LocalDate yesterday = LocalDate.now().minus(Period.ofDays(1));
        deliveryDateValidationService.validate(yesterday);
    }

    @Test
    public void succeedIfDateIsInTheFuture() throws ServiceException {
        LocalDate dayAfterTomorrow = LocalDate.now().plus(Period.ofDays(2));
        deliveryDateValidationService.validate(dayAfterTomorrow);
    }

    @Test(expected = ServiceException.class)
    public void failsIfCutOfTimeIsPassed() throws ServiceException {
        DeliveryDateValidationServiceImpl deliveryDateValidationService;
        deliveryDateValidationService = new DeliveryDateValidationServiceImpl(-1);
        LocalDate today = LocalDate.now();
        deliveryDateValidationService.validate(today);
    }

    @Test
    public void succeedIfCutOfTimeNotPassed() throws ServiceException {
        DeliveryDateValidationServiceImpl deliveryDateValidationService;
        deliveryDateValidationService = new DeliveryDateValidationServiceImpl(25);
        LocalDate today = LocalDate.now();
        deliveryDateValidationService.validate(today);
    }

    @Test(expected = ServiceException.class)
    public void failsIfOnCutOffTomeHour() throws ServiceException {
        DeliveryDateValidationServiceImpl deliveryDateValidationService;
        deliveryDateValidationService = new DeliveryDateValidationServiceImpl(LocalDateTime.now().getHour());
        LocalDate today = LocalDate.now();
        deliveryDateValidationService.validate(today);
    }


}