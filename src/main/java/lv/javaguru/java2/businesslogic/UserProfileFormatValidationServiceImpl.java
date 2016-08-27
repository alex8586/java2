package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.serviceexception.IllegalRequestException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.WrongFieldFormat;
import org.springframework.stereotype.Component;

@Component
public class UserProfileFormatValidationServiceImpl implements UserProfileFormatValidationService {
    private final String EMPTY_FIELDS = "All fields must be filled";
    private final String BAD_PASSWORD = "Password isn't secure";

    @Override
    public boolean validate(String name, String email, String password) throws ServiceException {
        if (email == null || password == null || name == null)
            throw new IllegalRequestException();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty())
            throw new WrongFieldFormat(EMPTY_FIELDS);

        if (name.equals(password))
            throw new WrongFieldFormat(BAD_PASSWORD);
        return true;
    }
}
