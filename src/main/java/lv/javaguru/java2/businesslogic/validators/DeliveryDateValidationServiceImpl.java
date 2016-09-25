package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DeliveryDateValidationServiceImpl implements DeliveryDateValidationService {

    private static final int CUT_OF_TIME_HOUR = 17;

    @Override
    public void validate(LocalDate delivery_date) throws ServiceException {
        if (delivery_date.compareTo(LocalDate.now()) < 0)
            throw new ServiceException("Provided delivery date is in the past");
        if (delivery_date.compareTo(LocalDate.now()) > 0)
            return;
        if (LocalDateTime.now().getHour() >= CUT_OF_TIME_HOUR)
            throw new ServiceException("Today's delivery is closed at " + CUT_OF_TIME_HOUR + " o'clock");
    }
}
