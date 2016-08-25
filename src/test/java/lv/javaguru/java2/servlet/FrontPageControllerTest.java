package lv.javaguru.java2.servlet;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class FrontPageControllerTest {

    @Autowired
    private CategoryDAOImpl categoryDAO;

    @Autowired
    private ProductDAOImpl productDAO;

    @Autowired
    private FrontPageController frontPageController;

    @Ignore
    @Test
    public void success() {

    }
}