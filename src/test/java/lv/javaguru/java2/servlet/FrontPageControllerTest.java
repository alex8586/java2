package lv.javaguru.java2.servlet;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;


public class FrontPageControllerTest {

    Map<String, Object> mvcModelData = new HashMap<>();
    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    private ProductDAOImpl productDAO = new ProductDAOImpl();
    //    private FrontPageController frontPageController = new FrontPageController(categoryDAO, productDAO);
    private ApplicationContext springContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    private FrontPageController frontPageController = springContext.getBean(FrontPageController.class);

    @Before
    public void before() {
        createCategories();
    }




    private void createCategories() {
        Category category;
        category = new Category();
        category.setName("test1");
        categoryDAO.create(category);

        category = new Category();
        category.setName("test2");
        categoryDAO.create(category);
    }
}