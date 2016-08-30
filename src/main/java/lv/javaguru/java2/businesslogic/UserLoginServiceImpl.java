package lv.javaguru.java2.businesslogic;
import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserLoginServiceImpl implements UserLoginService {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String WRONG_EMAIL = "user with such email not found";
    private final String WRONG_PASSWORD = "wrong password";

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    private UserProvider currentUser;

    @Override
    public boolean allowLogin() {
        return !currentUser.authorized();
    }

    @Override
    public User authenticate(String email, String password) throws ServiceException {
        if (!allowLogin())
            throw new IllegalRequestException();

        if (email == null || password == null)
            throw new IllegalRequestException();

        if (email.isEmpty() || password.isEmpty())
            throw new WrongFieldFormatException(EMPTY_FIELDS);

        User user = userDAO.getByEmail(email);
        if (user == null) {
            throw new ServiceException(WRONG_EMAIL);
        } else if (!user.getPassword().equals(password)) {
            throw new ServiceException(WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void login(User user) {
        currentUser.setUser(user);
    }

    @Override
    public void logout() {
        currentUser.setUser(null);
    }
}
