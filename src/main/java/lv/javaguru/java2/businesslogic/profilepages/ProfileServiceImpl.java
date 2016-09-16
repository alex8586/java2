package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    UserProvider userProvider;

    @Autowired
    TemplateService templateService;

    @Override
    public Map<String, Object> model() throws ServiceException {
        if (!userProvider.authorized()) {
            throw new UnauthorizedAccessException();
        }
        return model(userProvider.getUser());
    }

    @Override
    public Map<String, Object> model(User user) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(templateService.model(user));
        return map;
    }
}
