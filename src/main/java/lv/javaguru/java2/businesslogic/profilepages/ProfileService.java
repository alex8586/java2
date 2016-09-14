package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

import java.util.Map;

/**
 * Created by algis on 16.14.9.
 */
public interface ProfileService {
    Map<String, Object> model() throws ServiceException;

    Map<String, Object> model(User user) throws ServiceException;
}
