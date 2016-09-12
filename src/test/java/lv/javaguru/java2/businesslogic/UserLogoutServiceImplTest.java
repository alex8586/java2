package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.user.UserLogoutService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserLogoutServiceImplTest {

    @Autowired
    UserProvider userProvider;

    @Autowired
    UserLogoutService userLogoutService;

    @Test
    public void testLogout() {
        userProvider.setUser(new User());
        assertTrue(userProvider.authorized());
        userLogoutService.logout();
        assertTrue(!userProvider.authorized());
    }
}