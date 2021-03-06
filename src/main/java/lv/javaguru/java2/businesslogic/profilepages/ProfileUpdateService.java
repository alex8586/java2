package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;

import java.util.Map;

public interface ProfileUpdateService {
    Map<String, Object> model() throws ServiceException;

    Map<String, Object> model(User user) throws ServiceException;
    void update(UserProfile userProfile) throws ServiceException;
    void update(UserProfile userProfile, User user) throws ServiceException;
}
