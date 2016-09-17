package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

import java.util.Map;

public interface UserLoginService {
    Map<String, Object> model() throws ServiceException;
    User authenticate(String email, String password) throws ServiceException;
    void login(User user);
}
