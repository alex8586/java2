package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface ReviewDAO extends CrudDAO<Review> {
    List<Review> getByProduct(Product product);

    List<Review> getByUser(User user);

    List<Review> getByUserAndProduct(Product product, User user);
}
