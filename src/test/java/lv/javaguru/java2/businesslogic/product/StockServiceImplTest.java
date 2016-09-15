package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class StockServiceImplTest {

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;

    @Autowired
    StockServiceImpl stockService;

    Cart cart;

    @Before
    public void before() {
        cart = new Cart();
    }

    @Test
    public void test() throws ServiceException {
        cart.add(productDAO.getById(1), 1);
        cart.add(productDAO.getById(2), 2);
        cart.add(productDAO.getById(3), 3);
        stockService.supply(cart);
    }
}