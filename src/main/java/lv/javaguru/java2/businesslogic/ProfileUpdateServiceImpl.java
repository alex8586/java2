package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.UserProfileFormatValidationService;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProfileUpdateServiceImpl implements ProfileUpdateService {
    private final String USER_ALREADY_EXISTS = "User already exists";

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private UserProfileFormatValidationService userProfileFormatValidationService;
    @Autowired
    private UserProfileUtil userProfileUtil;

    public void update(UserProfile userProfile, User user) throws ServiceException {
        userProfileFormatValidationService.validate(userProfile);
        if (user.getEmail().equals(userProfile.getEmail())) {
            User alreadyExists = userDAO.getByEmail(userProfile.getEmail());
            if (alreadyExists != null) {
                throw new ServiceException(USER_ALREADY_EXISTS);
            }
        }
        userProfileUtil.updateUser(userProfile, user);
        userDAO.update(user);
    }

    public void update(UserProfile userProfile) throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();
        User currentUser = userProvider.getUser();
        update(userProfile, currentUser);
    }
}
