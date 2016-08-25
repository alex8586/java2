package lv.javaguru.java2.servlet;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class FrontPageControllerTest {

    Map<String, Object> mvcModelData = new HashMap<>();

    @Autowired
    private CategoryDAOImpl categoryDAO;

    @Autowired
    private ProductDAOImpl productDAO;

    @Autowired
    private FrontPageController frontPageController;

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

    @Test
    void success() {
        
    }
}