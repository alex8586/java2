package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;

import java.time.LocalDate;


public interface DeliveryDateValidationService {
    void validate(LocalDate delivery_date) throws ServiceException;
}
