package lv.javaguru.java2.businesslogic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogoutServiceImpl implements UserLogoutService {

    @Autowired
    private UserProvider currentUser;

    @Override
    public void logout() {
        currentUser.setUser(null);
    }
}
