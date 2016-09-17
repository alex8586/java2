package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserLoginServiceImpl implements UserLoginService {

    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String WRONG_EMAIL = "user with such email not found";
    private final String WRONG_PASSWORD = "wrong password";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    private UserProvider currentUser;

    @Autowired
    private TemplateService templateService;

    @Override
    public Map<String, Object> model() throws ServiceException {
        if (currentUser.authorized()) {
            throw new IllegalRequestException();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(templateService.model());
        return map;
    }

    @Override
    public User authenticate(String email, String password) throws ServiceException {
        if (currentUser.authorized())
            throw new IllegalRequestException();

        if (email == null || password == null)
            throw new IllegalRequestException();

        if (email.isEmpty() || password.isEmpty())
            throw new WrongFieldFormatException(EMPTY_FIELDS);

        User user = userDAO.getByEmail(email);
        if (user == null) {
            throw new ServiceException(WRONG_EMAIL);
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ServiceException(WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void login(User user) {
        currentUser.setUser(user);
    }

}
