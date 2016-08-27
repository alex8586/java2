package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileUpdateServiceImpl implements ProfileUpdateService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private UserProfileFormatValidationService userProfileFormatValidationService;

    public boolean update(String name, String email, String password) throws ServiceException {
        if (!userProvider.authorized())
            throw new IllegalRequestException();

        if (!userProfileFormatValidationService.validate(name, email, password))
            throw new IllegalStateException();

        User user = userProvider.getUser();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);
        userDAO.update(user);
        return true;
    }
}
