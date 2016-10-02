package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;

public interface RateFormatValidationService {

    void validate(int rate) throws ServiceException;
}
