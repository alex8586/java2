package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccessDeniedValidatorImplTest {

    @InjectMocks
    private AccessDeniedValidatorImpl accessDeniedValidator;

    @Mock
    private UserProvider userProvider;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void accessDeniedWhenUserNotLogged(){
        Mockito.doReturn(null).when(userProvider).getUser();
        assertEquals(true, accessDeniedValidator.isDenied());
    }

    @Test
    public void accessDeniedWhenUserLoggedIn(){
        User user = mock(User.class);
        user.setAdmin(false);
        Mockito.doReturn(user).when(userProvider).getUser();
        assertEquals(true, accessDeniedValidator.isDenied());
    }

    @Test
    public void accessAllowedWhenYouAdministrator(){
        User admin = mock(User.class);
        admin.setAdmin(true);
        Mockito.doReturn(admin).when(userProvider).getUser();
        when(userProvider.getUser().isAdmin()).thenReturn(true);
        assertEquals(false, accessDeniedValidator.isDenied());
    }
}
