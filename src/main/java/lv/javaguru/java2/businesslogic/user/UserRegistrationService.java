package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;

import java.util.Map;

public interface UserRegistrationService {
    boolean allowRegistration();

    Map<String, Object> model() throws ServiceException;
    User register(UserProfile userProfile) throws ServiceException;
}
