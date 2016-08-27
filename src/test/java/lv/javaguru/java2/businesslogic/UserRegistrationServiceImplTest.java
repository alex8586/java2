package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormat;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserRegistrationServiceImplTest {

    private static String goodName = "NameSurname";
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserProvider currentUser;
    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrationAllowedWhenNoCurrentUser() {
        Mockito.doReturn(false).when(currentUser).authorized();
        assertTrue(userRegistrationService.allowRegistration());
    }

    @Test
    public void testRegistrationDisallowedWhenNoCurrentUser() {
        Mockito.doReturn(true).when(currentUser).authorized();
        assertFalse(userRegistrationService.allowRegistration());
    }

    @Test(expected = IllegalRequestException.class)
    public void testRegisterAttemptFailsWhenNotAllFieldsProvided() throws ServiceException {
        userRegistrationService.register(null, goodMail, goodPass);
    }

    @Test(expected = WrongFieldFormat.class)
    public void testRegisterAttemptFailsWhenSomeFieldsAreEmpty() throws ServiceException {
        userRegistrationService.register(goodName, "", goodPass);
    }

    @Test(expected = WrongFieldFormat.class)
    public void testRegisterAttemptFailsWhenPassAndAreNameEquals() throws ServiceException {
        userRegistrationService.register(goodName, goodMail, goodName);
    }

    @Test(expected = ServiceException.class)
    public void testRegisterAttemptFailsWhenEmailAlreadyBusy() throws ServiceException {
        User dummy = new User();
        Mockito.doReturn(dummy).when(userDAO).getByEmail(goodMail);
        userRegistrationService.register(goodName, goodMail, goodPass);
    }

    @Test
    public void testReturnNewUserWhenEverythingRight() throws ServiceException {
        Mockito.doReturn(null).when(userDAO).getByEmail(goodMail);
        User user = userRegistrationService.register(goodName, goodMail, goodPass);
        assertEquals(goodName, user.getFullName());
        assertEquals(goodMail, user.getEmail());
        assertEquals(goodPass, user.getPassword());

        verify(userDAO, times(1)).getByEmail(goodMail);
        verify(userDAO, times(1)).create(any());
    }
}