package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public void addComment(long productId, User user, String comment) {
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
