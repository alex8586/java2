package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeliveryDateValidationServiceImpl implements DeliveryDateValidationService {

    private final int CUT_OF_TIME_HOUR;

    public DeliveryDateValidationServiceImpl(@Value("14") int cutOfTineHour) {
        CUT_OF_TIME_HOUR = cutOfTineHour;
    }

    @Override
    public LocalDate validate(LocalDate delivery_date) throws ServiceException {
        if (delivery_date.compareTo(LocalDate.now()) < 0)
            throw new ServiceException("Entered date is in the past");
        if (delivery_date.compareTo(LocalDate.now()) > 0)
            return delivery_date;
        if (LocalDateTime.now().getHour() >= CUT_OF_TIME_HOUR)
            throw new ServiceException("Working time till " + CUT_OF_TIME_HOUR + " o'clock");
        return delivery_date;
    }
}
