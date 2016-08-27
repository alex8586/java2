package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormat;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String BAD_PASSWORD = "Password isn't secure";
    private final String USER_ALREADY_EXISTS = "User already exists";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserProvider currentUser;

    @Override
    public boolean allowRegistration() {
        return !currentUser.authorized();
    }

    @Override
    public User register(String name, String email, String password) throws ServiceException {
        if (email == null || password == null || name == null)
            throw new IllegalRequestException();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty())
            throw new WrongFieldFormat(EMPTY_FIELDS);

        if (name.equals(password))
            throw new WrongFieldFormat(BAD_PASSWORD);

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
