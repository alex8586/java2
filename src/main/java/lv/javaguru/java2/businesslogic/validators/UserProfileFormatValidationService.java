package lv.javaguru.java2.businesslogic.validators;


import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.dto.UserProfile;

public interface UserProfileFormatValidationService {
    void validate(UserProfile userProfile) throws ServiceException;
}
