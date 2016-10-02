package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.domain.User;

public interface ReviewService {

    void addReview(long productId, User user, String comment) throws ServiceException;
}
