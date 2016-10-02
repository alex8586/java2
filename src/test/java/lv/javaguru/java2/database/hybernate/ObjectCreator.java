package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ReviewDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Review;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class ObjectCreator {

    @Qualifier("ORM_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Qualifier("ORM_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;

    @Qualifier("ORM_UserDAO")
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    private Random random = new Random();

    long createCategory(){
        Category category = new Category();
        category.setName("category");
        return categoryDAO.create(category);
    }

    long createProduct(long categoryId){
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setName("product");
        product.setPrice(123);
        product.setImgUrl("url");
        return productDAO.create(product);
    }

    long createUser(){
        User user = new User();
        user.setEmail("name@server" + random.nextInt(100000) + ".com");
        user.setFullName("Name Surname " + random.nextInt(100000));
        user.setPassword("password" + random.nextInt(100000));
        return userDAO.create(user);
    }

    long createReview(){
        Review review = new Review();
        review.setUserId(createUser());
        review.setProductId(createProduct(createCategory()));
        review.setUserName("userName" + random.nextInt(100000));
        review.setComment("review" + random.nextInt(100000));
        review.setDate(new Date());
        return reviewDAO.create(review);
    }
}
