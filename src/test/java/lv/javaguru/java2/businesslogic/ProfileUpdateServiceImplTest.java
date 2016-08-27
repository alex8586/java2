package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormat;
import lv.javaguru.java2.database.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;

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

    @Test(expected = WrongFieldFormat.class)
    public void testFailsWithSameException() throws ServiceException {
        WrongFieldFormat exception = new WrongFieldFormat("error");
        Mockito.doThrow(exception).when(userProfileFormatValidationService).validate(any(), any(), any());
        profileUpdateService.update(goodName, goodMail, goodName);
    }
}