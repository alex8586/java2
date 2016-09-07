package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.UserProfileFormatValidationService;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
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
    private UserProvider currentUser;

    @Autowired
    private UserProfileFormatValidationService userProfileFormatValidationService;

    @Override
    public boolean allowRegistration() {
        return !currentUser.authorized();
    }

    @Override
    public User register(String name, String email, String password) throws ServiceException {

        if (!userProfileFormatValidationService.validate(name, email, password))
            throw new IllegalStateException();

        User user = userDAO.getByEmail(email);
        if (user != null) {
            throw new ServiceException(USER_ALREADY_EXISTS);
        }
        user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);
        userDAO.create(user);
        return user;
    }
}
