package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;


public class FrontPageControllerTest {

    Map<String, Object> mvcModelData = new HashMap<>();
    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    private ProductDAOImpl productDAO = new ProductDAOImpl();
    private FrontPageController frontPageController = new FrontPageController(categoryDAO, productDAO);

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