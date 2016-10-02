package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ReviewValidationServiceImpl implements ReviewValidationService {

    @Autowired
    private ReviewDAO reviewDAO;
    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Override
    public boolean canComment(User user, long productId) {
        Product product = productDAO.getById(productId);
        List<Review> reviewList = reviewDAO.getByUserAndProduct(product, user);

        if (user.isAdmin())
            return true;

        Date startOfDay = getStartOfDay(new Date());
        for(Review review : reviewList){
            if(review.getDate().compareTo(startOfDay) >= 0) {
                return false;
            }
        }
        return true;
    }

    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
