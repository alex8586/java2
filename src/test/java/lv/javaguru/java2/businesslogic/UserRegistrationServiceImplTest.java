package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.businesslogic.user.UserRegistrationServiceImpl;
import lv.javaguru.java2.businesslogic.validators.UserProfileFormatValidationService;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

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

    private UserProfile userProfile;

    @Mock
    private UserDAO userDAO;
    @Mock
    private UserProvider userProvider;
    @Mock
    private UserProfileFormatValidationService userProfileFormatValidationService;
    @Spy
    private UserProfileUtil userProfileUtil;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        userProfile = userProfileUtil.build(goodName, goodMail, goodPass, goodPass);
    }

    @Test
    public void registrationAllowedWhenNoCurrentUser() {
        Mockito.doReturn(false).when(userProvider).authorized();
        assertTrue(userRegistrationService.allowRegistration());
    }

    @Test
    public void registrationDisallowedWhenNoCurrentUser() {
        Mockito.doReturn(true).when(userProvider).authorized();
        assertFalse(userRegistrationService.allowRegistration());
    }

    @Test(expected = IllegalRequestException.class)
    public void registrationRecheckIfUserAuthorized() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        userRegistrationService.register(userProfile);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void testFailsWithSameException() throws ServiceException {
        WrongFieldFormatException exception = new WrongFieldFormatException("error");
        Mockito.doThrow(exception).when(userProfileFormatValidationService).validate(userProfile);
        userRegistrationService.register(userProfile);
    }


    @Test(expected = ServiceException.class)
    public void registerAttemptFailsWhenEmailAlreadyBusy() throws ServiceException {
        User dummy = new User();
        Mockito.doReturn(dummy).when(userDAO).getByEmail(goodMail);
        userRegistrationService.register(userProfile);
    }

    @Test
    public void returnNewUserWhenEverythingRight() throws ServiceException {
        Mockito.doReturn(null).when(userDAO).getByEmail(goodMail);
        User user = userRegistrationService.register(userProfile);
        assertEquals(goodName, user.getFullName());
        assertEquals(goodMail, user.getEmail());
        assertEquals(goodPass, user.getPassword());
        verify(userDAO, times(1)).getByEmail(goodMail);
        verify(userDAO, times(1)).create(any());
    }
}