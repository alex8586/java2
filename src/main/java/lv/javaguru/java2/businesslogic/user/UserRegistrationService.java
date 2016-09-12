package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;

public interface UserRegistrationService {
    boolean allowRegistration();

    User register(UserProfile userProfile) throws ServiceException;
}
