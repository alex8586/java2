package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Component
public class DeliveryDateValidationServiceImpl implements DeliveryDateValidationService {

    private static final int CUT_OF_TIME_HOUR = 14;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.ENGLISH);

    @Override
    public LocalDate validate(String date) throws ServiceException {
        LocalDate delivery_date ;
        try {
            delivery_date = LocalDate.parse(date, formatter);
        }catch (DateTimeParseException e){
            throw new ServiceException("All fields must be filled");
        }
        if (delivery_date.compareTo(LocalDate.now()) < 0)
            throw new ServiceException("Entered date is in the past");
        if (delivery_date.compareTo(LocalDate.now()) > 0)
            return delivery_date;
        if (LocalDateTime.now().getHour() >= CUT_OF_TIME_HOUR)
            throw new ServiceException("Working time till " + CUT_OF_TIME_HOUR + " o'clock");
        return delivery_date;
    }
}
