package lv.javaguru.java2.businesslogic.validators;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;

public interface UserProfileFormatValidationService {
    boolean validate(String name, String email, String password) throws ServiceException;
}
