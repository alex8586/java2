package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

import java.util.Map;

public interface ProfileOrderService {
    Map<String, Object> model(User user) throws ServiceException;

    Map<String, Object> model() throws ServiceException;
    Map<String, Object> getById(long id);
}
