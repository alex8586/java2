package lv.javaguru.java2.businesslogic.user;

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
public class UserRegistrationServiceImpl implements UserRegistrationService {

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

    @Override
    public boolean allowRegistration() {
        return !userProvider.authorized();
    }

    @Override
    public User register(UserProfile userProfile) throws ServiceException {
        if (userProvider.authorized())
            throw new IllegalRequestException();

        userProfileFormatValidationService.validate(userProfile);
        User alreadyExists = userDAO.getByEmail(userProfile.getEmail());
        if (alreadyExists != null) {
            throw new ServiceException(USER_ALREADY_EXISTS);
        }
        User newUser = userProfileUtil.buildUser(userProfile);
        userDAO.create(newUser);
        return newUser;
    }
}
