package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

public interface ReviewValidationService {

    boolean canComment(User user, long productId) throws ServiceException;
}
