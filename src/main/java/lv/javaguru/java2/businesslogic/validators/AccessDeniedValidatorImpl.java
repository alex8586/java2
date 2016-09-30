package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.user.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedValidatorImpl implements AccessDeniedValidator {

    @Autowired
    private UserProvider userProvider;

    public boolean isDenied(){
        if(userProvider.getUser() == null) {
            return true;
        }else if(!userProvider.getUser().isAdmin()){
            return true;
        }
        return false;
    }
}
