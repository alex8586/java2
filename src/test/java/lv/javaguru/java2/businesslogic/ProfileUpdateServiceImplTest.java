package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.businesslogic.validators.UserProfileFormatValidationService;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProfileUpdateServiceImplTest {

    private static String goodName = "NameSurname";
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";

    private UserProfile userProfile;
    private User user;

    @Mock
    private UserDAO userDAO;
    @Mock
    private UserProvider userProvider;
    @Mock
    private UserProfileFormatValidationService userProfileFormatValidationService;
    @Spy
    private UserProfileUtil userProfileUtil;
    @InjectMocks
    private ProfileUpdateServiceImpl profileUpdateService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        userProfile = userProfileUtil.build(goodName, goodMail, goodPass, goodPass);
        user = userProfileUtil.buildUser(userProfile);
    }

    @Test(expected = IllegalRequestException.class)
    public void failsIfNoUserLogged() throws ServiceException {
        Mockito.doReturn(false).when(userProvider).authorized();
        profileUpdateService.update(userProfile);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void forwardFieldValidaionException() throws ServiceException {
        Mockito.doReturn(true).when(userProvider).authorized();
        WrongFieldFormatException exception = new WrongFieldFormatException("error");
        Mockito.doThrow(exception).when(userProfileFormatValidationService).validate(userProfile);
        profileUpdateService.update(userProfile);
    }

    @Test(expected = ServiceException.class)
    public void failsWhenNewEmailIsBusy() throws ServiceException {
        Mockito.doReturn(true).when(userProfileFormatValidationService).validate(userProfile);
        userProfile.setEmail("another@mail");
        Mockito.doReturn(Mockito.mock(User.class)).when(userDAO).getByEmail(userProfile.getEmail());
        profileUpdateService.update(userProfile, user);
    }

    @Test
    public void processCorrectData() throws ServiceException {
        Mockito.doReturn(true).when(userProfileFormatValidationService).validate(userProfile);
        profileUpdateService.update(userProfile, user);
        Mockito.verify(userProfileUtil, times(1)).updateUser(userProfile, user);
        Mockito.verify(userDAO, times(1)).update(user);
    }

    @Test
    public void processCorrectDataForCurrentUser() throws ServiceException {
        userProfile.setEmail("another@mail");
        Mockito.doReturn(true).when(userProvider).authorized();
        Mockito.doReturn(user).when(userProvider).getUser();
        Mockito.doReturn(true).when(userProfileFormatValidationService).validate(userProfile);
        Mockito.doReturn(null).when(userDAO).getByEmail(userProfile.getEmail());

        profileUpdateService.update(userProfile);

        Mockito.verify(userProvider, times(1)).authorized();
        Mockito.verify(userProvider, times(1)).getUser();
        Mockito.verify(userProfileFormatValidationService, times(1)).validate(userProfile);
        Mockito.verify(userDAO, times(1)).getByEmail(userProfile.getEmail());
        Mockito.verify(userProfileUtil, times(1)).updateUser(userProfile, user);
        Mockito.verify(userDAO, times(1)).update(user);
    }
}