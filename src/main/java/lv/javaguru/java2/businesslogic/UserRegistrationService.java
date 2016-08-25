package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

public interface UserRegistrationService {
    User register(String name, String email, String password) throws ServiceException;
}
