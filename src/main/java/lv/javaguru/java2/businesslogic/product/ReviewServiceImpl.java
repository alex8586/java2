package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ProductProvider productProvider;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public void addComment(String comment){
        User user = userProvider.getUser();
        long productId = productProvider.getProductId();
        Review review = new Review();
        review.setUserId(user.getId());
        review.setProductId(productId);
        review.setRate(1);
        review.setComment(comment);
        reviewDAO.create(review);
    }
}
