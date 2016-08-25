package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormat;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginServiceImpl implements UserLoginService {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String WRONG_EMAIL = "user with such email not found";
    private final String WRONG_PASSWORD = "wrong password";

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(String email, String password) throws ServiceException {
        if (email == null || password == null)
            throw new IllegalRequestException();

        if (email.isEmpty() || password.isEmpty())
            throw new WrongFieldFormat(EMPTY_FIELDS);

        User user = userDAO.getByEmail(email);
        if (user == null) {
            throw new ServiceException(WRONG_EMAIL);
        } else if (!user.getPassword().equals(password)) {
            throw new ServiceException(WRONG_PASSWORD);
        }
        return user;
    }
}
