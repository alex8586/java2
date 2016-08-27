package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormat;
import lv.javaguru.java2.config.SpringConfig;
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

    @Autowired
    private UserProfileFormatValidationService profileFormatValidationService;

    @Test(expected = IllegalRequestException.class)
    public void failsWhenNotAllFieldsProvided() throws ServiceException {
        profileFormatValidationService.validate(null, goodMail, goodPass);
    }

    @Test(expected = WrongFieldFormat.class)
    public void failsFailsWhenSomeFieldsAreEmpty() throws ServiceException {
        profileFormatValidationService.validate(goodName, "", goodPass);
    }

    @Test(expected = WrongFieldFormat.class)
    public void failsWhenPassAndAreNameEquals() throws ServiceException {
        profileFormatValidationService.validate(goodName, goodMail, goodName);
    }

    @Test
    public void successWhenEverythingRight() throws ServiceException {
        profileFormatValidationService.validate(goodName, goodMail, goodPass);
    }

}