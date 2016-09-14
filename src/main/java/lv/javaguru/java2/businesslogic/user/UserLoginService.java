package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

public interface UserLoginService {
    boolean allowLogin();
    User authenticate(String email, String password) throws ServiceException;
    void login(User user);
    void logout();
}
