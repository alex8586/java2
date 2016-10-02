package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DeliveryDateValidationServiceImpl implements DeliveryDateValidationService {

    private static final int CUT_OF_TIME_HOUR = 14;

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
