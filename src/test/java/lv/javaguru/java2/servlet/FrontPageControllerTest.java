package lv.javaguru.java2.servlet;

import lv.javaguru.java2.DatabaseCleaner;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by algis on 16.11.8.
 */
public class FrontPageControllerTest {

    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    private FrontPageController frontPageController = new FrontPageController(categoryDAO);
    private DatabaseCleaner cleaner = new DatabaseCleaner();

    Map<String,Object> mvcModelData;
    @Before
    public void before() throws DBException{
        cleaner.cleanDatabase();
        createCategories();
        mvcModelData = (Map<String,Object>)frontPageController.execute(null).getData();
        assertNotNull(mvcModelData);
    }

    @Test
    public void responseContainsRightNumberOfItems() throws DBException{
        assertEquals(2 , mvcModelData.size());
    }
    @Test
    public void doReturnListOfCategories() throws DBException{

        List<Category> categoriesFromControler = (List<Category>) mvcModelData.get("categories");
        assertNotNull(categoriesFromControler);

        List<Category> categoriesFromDAO = categoryDAO.getAll();
        assertEquals(categoriesFromDAO.size() , categoriesFromControler.size());
        assertEquals(2 , categoriesFromControler.size() );
        assertEquals(categoriesFromDAO.get(0).getName() , categoriesFromControler.get(0).getName());
        assertEquals(categoriesFromDAO.get(1).getName() , categoriesFromControler.get(1).getName());
    }

    @Test
    public void doNotReturnProducts() throws DBException{
        List<Product> products = (List<Product>) mvcModelData.get("products");
        assertNull(products);
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