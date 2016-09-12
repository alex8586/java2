package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.businesslogic.user.UserLoginServiceImpl;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class UserLoginServiceImplTest {
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserProvider userProvider;
    @InjectMocks
    private UserLoginServiceImpl userLoginService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginAllowedWhenNoCurrentUser() {
        Mockito.doReturn(false).when(userProvider).authorized();
        assertTrue(userLoginService.allowLogin());
    }

    @Test
    public void testRegistrationDisallowedWhenNoCurrentUser() {
        Mockito.doReturn(true).when(userProvider).authorized();
        assertFalse(userLoginService.allowLogin());
    }

    @Test(expected = IllegalRequestException.class)
    public void testLoginFailsWhenAlreadyLoggedIn() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        userLoginService.authenticate(goodMail, goodPass);
    }

    @Test(expected = IllegalRequestException.class)
    public void testLoginAttemptFailsWhenNotAllFieldsProvided() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        userLoginService.authenticate(null, goodPass);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void testLoginAttemptFailsWhenSomeFieldsAreEmpty() throws ServiceException {
        userLoginService.authenticate(goodMail, "");
    }

    @Test(expected = ServiceException.class)
    public void testLoginAttemptFailsWhenNoUserFound() throws ServiceException {
        Mockito.doReturn(null).when(userDAO).getByEmail(goodMail);
        userLoginService.authenticate(goodMail, goodPass);
    }

    @Test(expected = ServiceException.class)
    public void testLoginAttemptFailsWhenNoPasswordsMismatch() throws ServiceException {
        User user = new User();
        user.setPassword(goodPass);
        Mockito.doReturn(user).when(userDAO).getByEmail(goodMail);
        userLoginService.authenticate(goodMail, "not" + goodPass);
    }

    @Test
    public void testReturnUserWhenEverythingRight() throws ServiceException {
        User user = new User();
        user.setPassword(goodPass);
        Mockito.doReturn(user).when(userDAO).getByEmail(goodMail);
        User authenticated = userLoginService.authenticate(goodMail, goodPass);
        assertEquals(user, authenticated);
        verify(userDAO, times(1)).getByEmail(goodMail);
    }
}