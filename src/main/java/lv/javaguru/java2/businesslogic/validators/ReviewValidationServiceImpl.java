package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.businesslogic.serviceexception.RecordIsNotAvailable;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.helpers.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    public boolean canComment(User user, long productId) throws ServiceException {
        Product product = productDAO.getById(productId);
        if (product == null)
            throw new RecordIsNotAvailable();

        if (user.isAdmin())
            return true;

        List<Review> reviewList = reviewDAO.getByUserAndProduct(product, user);
        if (reviewList.size() == 0)
            return true;

        Date startOfDay = getStartOfDay(new Date());
        for(Review review : reviewList){
            if (review.getDate().compareTo(startOfDay) >= 0) {
                return false;
            }
        }
        return true;
    }

    private Date getStartOfDay(Date date) {
        LocalDate localDate = DateUtils.asLocalDate(date);
        return DateUtils.asDate(localDate.atStartOfDay());
    }
}
