package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SessionUserProviderTest {
    SessionUserProvider userProvider;
    private User user = new User();

    @Before
    public void before() {
        userProvider = new SessionUserProvider();
        user.setId(123);
    }

    @Test
    public void notAuthorizedWhenNoUserSet() {
        assertFalse(userProvider.authorized());
    }

    @Test
    public void isAuthorizedWhenUserSet() {
        userProvider.setUser(user);
        assertTrue(userProvider.authorized());
    }

    @Test
    public void notCurrentWhenIdDiffers() {
        userProvider.setUser(user);
        assertFalse(userProvider.isCurrent(1234));
    }

    @Test
    public void isCurrentWhenIdMatch() {
        userProvider.setUser(user);
        assertTrue(userProvider.isCurrent(123));
    }
}