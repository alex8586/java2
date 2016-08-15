package lv.javaguru.java2.servlet;

import lv.javaguru.java2.IntegrationTest;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class FrontPageControllerTest extends IntegrationTest {

    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    private FrontPageController frontPageController = new FrontPageController(categoryDAO);
    Map<String, Object> mvcModelData = new HashMap<>();

    @Before
    public void before() throws DBException{
        createCategories();
    }

    @Test
    @Ignore
    public void doReturnListOfCategories() throws DBException{
    }

    @Test
    @Ignore
    public void doNotReturnProducts() throws DBException{
    }


    private void createCategories() throws DBException{
        Category category;
        category = new Category();
        category.setName("test1");
        categoryDAO.create(category);

        category = new Category();
        category.setName("test2");
        categoryDAO.create(category);
    }
}