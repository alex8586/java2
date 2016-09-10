package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;

public interface ProfileUpdateService {
    void update(UserProfile userProfile) throws ServiceException;

    void update(UserProfile userProfile, User user) throws ServiceException;
}
