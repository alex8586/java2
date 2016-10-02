package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.validators.ReviewValidationService;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReviewServiceImpl implements ReviewService {

    private static final String CAN_NOT_COMMENT_TODAY = "Not allowed to review twice per day";

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private ReviewValidationService reviewValidationService;

    @Override
    public void addComment(long productId, User user, String comment) throws ServiceException {

        if (!reviewValidationService.canComment(user, productId)) {
            throw new ServiceException(CAN_NOT_COMMENT_TODAY);
        }

        Date date = new Date();
        Review review = new Review();
        review.setUserId(user.getId());
        review.setProductId(productId);
        review.setComment(comment);
        review.setDate(date);
        review.setUserName(user.getFullName());
        reviewDAO.create(review);
    }
}
