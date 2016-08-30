package lv.javaguru.java2.businesslogic;

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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProfileUpdateServiceImplTest {

    private static String goodName = "NameSurname";
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserProvider userProvider;
    @Mock
    private UserProfileFormatValidationService userProfileFormatValidationService;

    @InjectMocks
    private ProfileUpdateServiceImpl profileUpdateService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalRequestException.class)
    public void testFailsIfNoUserLogged() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        profileUpdateService.update(goodName, goodMail, goodPass);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void testFailsWithSameException() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        WrongFieldFormatException exception = new WrongFieldFormatException("error");
        Mockito.doThrow(exception).when(userProfileFormatValidationService).validate(any(), any(), any());
        profileUpdateService.update(goodName, goodMail, goodName);
    }

    @Test
    public void testUpdateSucceedsWhenEverythingFine() throws ServiceException {
        User user = new User();
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(true).when(userProfileFormatValidationService).validate(any(), any(), any());
        profileUpdateService.update(goodName, goodMail, goodName);

        verify(userProvider, times(1)).getUser();
        verify(userDAO, times(1)).update(user);
    }
}