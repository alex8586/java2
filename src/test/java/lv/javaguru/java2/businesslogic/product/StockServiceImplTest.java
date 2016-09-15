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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Rollback(false)
    public void test() throws ServiceException {
        cart.add(productDAO.getById(1), 2);
        cart.add(productDAO.getById(2), 2);
        cart.add(productDAO.getById(3), 4);
        cart.add(productDAO.getById(4), 4);
        stockService.supply(cart);
    }
}