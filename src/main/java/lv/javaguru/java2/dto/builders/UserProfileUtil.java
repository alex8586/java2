package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserProfile;
import org.springframework.stereotype.Component;


@Component
public class UserProfileUtil {
    public UserProfile build(String fullName, String email, String password, String repeatPassword) {
        if (fullName == null || email == null || password == null || repeatPassword == null)
            throw new NullPointerException();
        UserProfile userProfile = new UserProfile();
        userProfile.setFullName(fullName);
        userProfile.setPassword(password);
        userProfile.setRepeatPassword(repeatPassword);
        return userProfile;
    }

    public User buildUser(UserProfile userProfile) {
        User user = new User();
        updateUser(userProfile, user);
        return user;
    }

    public void updateUser(UserProfile userProfile, User user) {
        user.setFullName(userProfile.getFullName());
        user.setEmail(userProfile.getEmail());
        user.setPassword(userProfile.getPassword());
    }
}
