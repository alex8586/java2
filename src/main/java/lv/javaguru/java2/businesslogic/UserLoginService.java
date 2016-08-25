package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

public interface UserLoginService {
    User login(String email, String password) throws ServiceException;
}
