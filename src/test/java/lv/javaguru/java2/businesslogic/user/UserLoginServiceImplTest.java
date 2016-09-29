package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class UserLoginServiceImplTest {
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserProvider userProvider;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UserLoginServiceImpl userLoginService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
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
        user.setPassword("encripted" + goodPass);
        Mockito.doReturn(user).when(userDAO).getByEmail(goodMail);
        Mockito.doReturn(true).when(passwordEncoder).matches(goodPass, user.getPassword());
        User authenticated = userLoginService.authenticate(goodMail, goodPass);
        assertEquals(user, authenticated);
        verify(userDAO, times(1)).getByEmail(goodMail);
    }
}