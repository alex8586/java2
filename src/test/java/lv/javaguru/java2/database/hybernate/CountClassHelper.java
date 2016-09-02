package lv.javaguru.java2.database.hybernate;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CountClassHelper {

    @Qualifier("JDBC_ProductDAO")
    @Autowired
    private ProductDAO productDAO;

    @Qualifier("JDBC_CategoryDAO")
    @Autowired
    private CategoryDAO categoryDAO;

    @Qualifier("JDBC_UserDAO")
    @Autowired
    private UserDAO userDAO;

    private Random random = new Random();

    public long createCategory(){
        Category category = new Category();
        category.setName("category");
        return categoryDAO.create(category);
    }

    public long createProduct(long categoryId){
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setName("product");
        product.setPrice(123);
        product.setImgUrl("url");
        return productDAO.create(product);
    }

    public long createUser(){
        User user = new User();
        user.setEmail("a@b" + random.nextInt(100000) + ".com");
        user.setFullName("Name Surname " + random.nextInt(100000));
        user.setPassword("pass" + random.nextInt(100000));
        return userDAO.create(user);
    }
}
