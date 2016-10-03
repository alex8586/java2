package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.database.RateDAO;
import lv.javaguru.java2.domain.Rate;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RateValidationServiceImplTest {

    @InjectMocks
    RateValidationServiceImpl rateValidationService;
    @Mock
    private RateDAO rateDAO;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cantRateAnotherTime() {
        User user = new User();
        user.setId(1);
        Mockito.doReturn(Mockito.mock(Rate.class)).when(rateDAO).getByUserIdAndProductId(1, 1);
        assertFalse(rateValidationService.canRate(user, 1));
    }

    @Test
    public void canRateIfNeverRatedBefore() {
        User user = new User();
        user.setId(1);
        Mockito.doReturn(null).when(rateDAO).getByUserIdAndProductId(1, 1);
        assertTrue(rateValidationService.canRate(user, 1));
    }

    @Test
    public void returnFalseWhenUserNotLogged(){
        assertFalse(rateValidationService.canRate(null, 3));
    }
}