package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserProfileUtilTest {

    private static String goodName = "NameSurname";
    private static String goodMail = "name@host.domain";
    private static String goodPass = "SomePass123";

    UserProfile userProfile;
    UserProfileUtil userProfileUtil = new UserProfileUtil();

    @Before
    public void before() {
    }

    @Test(expected = NullPointerException.class)
    public void buildFailsWhenThereAreNullFields() {
        userProfile = userProfileUtil.build(goodName, null, goodPass, goodPass);
    }

    @Test
    public void buildSucceedWhenInputIsCorrect() {
        userProfile = userProfileUtil.build(goodName, goodMail, goodPass, goodPass);
        assertNotNull(userProfile);
        assertEquals(goodName, userProfile.getFullName());
        assertEquals(goodMail, userProfile.getEmail());
        assertEquals(goodPass, userProfile.getPassword());
        assertEquals(goodPass, userProfile.getRepeatPassword());
    }

    @Test
    public void buildUserMapFieldsCorrectly() {
        userProfile = userProfileUtil.build(goodName, goodMail, goodPass, goodPass);
        User user = userProfileUtil.buildUser(userProfile);
        assertEquals(goodName, user.getFullName());
        assertEquals(goodMail, user.getEmail());
        assertEquals(goodPass, user.getPassword());
    }

    @Test
    public void updateUserMapFieldsCorrectly() {
        User user = new User();
        user.setEmail("mail");
        user.setFullName("fullname");

        userProfile = userProfileUtil.build(goodName, goodMail, goodPass, goodPass);
        userProfileUtil.updateUser(userProfile, user);
        assertEquals(goodName, user.getFullName());
        assertEquals(goodMail, user.getEmail());
        assertEquals(goodPass, user.getPassword());
    }
}