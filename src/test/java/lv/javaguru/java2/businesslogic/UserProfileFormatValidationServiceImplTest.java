package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.businesslogic.validators.UserProfileFormatValidationService;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserProfileFormatValidationServiceImplTest {
    private static String goodName = "NameSurname";
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";

    private UserProfile userProfile;

    @Autowired
    private UserProfileUtil userProfileUtil;

    @Autowired
    private UserProfileFormatValidationService profileFormatValidationService;

    @Before
    public void before() {
        userProfile = userProfileUtil.build(goodName, goodMail, goodPass, goodPass);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void failsFailsWhenSomeFieldsAreEmpty() throws ServiceException {
        userProfile.setEmail("");
        profileFormatValidationService.validate(userProfile);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void failsWhenPassAndAreNameEquals() throws ServiceException {
        userProfile.setPassword(goodName);
        profileFormatValidationService.validate(userProfile);
    }

    @Test(expected = WrongFieldFormatException.class)
    public void failsWhenPasswordsNotMatch() throws ServiceException {
        userProfile.setRepeatPassword(goodPass + "s");
        profileFormatValidationService.validate(userProfile);
    }

    @Test
    public void successWhenEverythingRight() throws ServiceException {
        profileFormatValidationService.validate(userProfile);
    }

}