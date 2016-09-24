package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.validators.UserProfileFormatValidationService;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileUpdateServiceImpl implements ProfileUpdateService {
    private final String USER_ALREADY_EXISTS = "User already exists";
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private UserProfileFormatValidationService userProfileFormatValidationService;
    @Autowired
    private UserProfileUtil userProfileUtil;
    @Autowired
    private TemplateService templateService;

    public Map<String, Object> model() throws ServiceException {
        if (!userProvider.authorized())
            throw new UnauthorizedAccessException();
        return model(userProvider.getUser());
    }

    public Map<String, Object> model(User user) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(templateService.model(user));
        return map;
    }

    public void update(UserProfile userProfile, User user) throws ServiceException {
        userProfileFormatValidationService.validate(userProfile);
        if (!user.getEmail().equals(userProfile.getEmail())) {
            User alreadyExists = userDAO.getByEmail(userProfile.getEmail());
            if (alreadyExists != null) {
                throw new ServiceException(USER_ALREADY_EXISTS);
            }
        }
        userProfileUtil.updateUser(userProfile, user);
        user.setPassword(passwordEncoder.encode(userProfile.getPassword()));
        userDAO.update(user);
    }

    public void update(UserProfile userProfile) throws ServiceException {
        if (!userProvider.authorized())
            throw new UnauthorizedAccessException();
        User currentUser = userProvider.getUser();
        update(userProfile, currentUser);
    }
}
