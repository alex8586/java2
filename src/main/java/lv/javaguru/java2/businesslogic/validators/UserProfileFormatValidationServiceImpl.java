package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormatException;
import lv.javaguru.java2.dto.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileFormatValidationServiceImpl implements UserProfileFormatValidationService {
    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String BAD_PASSWORD = "Password isn't secure";
    private final String PASSWORDS_NOT_MATCH = "Passwords not match";

    @Override
    public void validate(UserProfile userProfile) throws ServiceException {
        if (userProfile.getFullName().isEmpty() || userProfile.getEmail().isEmpty() ||
                userProfile.getPassword().isEmpty() || userProfile.getRepeatPassword().isEmpty()) {
            throw new WrongFieldFormatException(EMPTY_FIELDS);
        }
        if (userProfile.getPassword().equals(userProfile.getFullName()))
            throw new WrongFieldFormatException(BAD_PASSWORD);
        if (userProfile.getPassword().equals(userProfile.getRepeatPassword()))
            throw new WrongFieldFormatException(PASSWORDS_NOT_MATCH);
    }
}
