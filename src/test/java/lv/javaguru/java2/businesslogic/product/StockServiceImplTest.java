package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.businesslogic.serviceexception.InsufficientSupplyException;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StockServiceImplTest {

    @Mock
    ProductDAO productDAO;

    @InjectMocks
    StockServiceImpl stockService;

    Product product32, product11218;

    Cart cart;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        initProducts();
        Mockito.doReturn(product32).when(productDAO).getById(1L);
        Mockito.doReturn(product11218).when(productDAO).getById(2L);
        cart = new Cart();
    }

    @Test
    public void successWhenBuyOneItem() throws ServiceException {
        assertEquals(5, product32.getFreshStockQuantity());
        cart.add(product32, 4);
        stockService.supply(cart);
        checkStock(product32, 0, 1);
    }

    @Test
    public void successWhenBuyTwoItems() throws ServiceException {
        assertEquals(5, product32.getFreshStockQuantity());
        assertEquals(13, product11218.getFreshStockQuantity());
        cart.add(product32, 1);
        cart.add(product11218, 9);
        stockService.supply(cart);
        checkStock(product32, 2, 2);
        checkStock(product11218, 0, 0, 0, 0, 4);
    }

    @Test
    public void successWhenBuyAllItems() throws ServiceException {
        assertEquals(5, product32.getFreshStockQuantity());
        assertEquals(13, product11218.getFreshStockQuantity());
        cart.add(product32, 5);
        cart.add(product11218, 13);
        stockService.supply(cart);
        checkStock(product32, 0, 0);
        checkStock(product11218, 0, 0, 0, 0, 0);
    }

    @Test(expected = InsufficientSupplyException.class)
    public void failsWhenNotEnoughStock() throws ServiceException {
        assertEquals(5, product32.getFreshStockQuantity());
        cart.add(product32, 6);
        stockService.supply(cart);
    }


    private void initProducts() {
        product32 = new Product();
        product32.setId(1);
        stock(product32, 3, 2);

        product11218 = new Product();
        product11218.setId(2);
        stock(product11218, 1, 1, 2, 1, 8);
    }

    private void stock(Product product, int... quantity) {
        for (int q : quantity) {
            Stock stock = new Stock();
            stock.setQuantity(q);
            stock.setExpireDate(new Date(new Date().getTime() + 24 * 3600_000));
            stock.setProductId(product.getId());
            product.getStockList().add(stock);
        }
    }

    private void checkStock(Product product, int... quantity) {
        int i = 0;
        int sum = 0;
        for (int q : quantity) {
            assertEquals(q, product.getFresh().get(i).getQuantity());
            i++;
            sum += q;
        }
        assertEquals(sum, product.getFreshStockQuantity());

    }

}