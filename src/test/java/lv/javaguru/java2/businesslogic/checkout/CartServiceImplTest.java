package lv.javaguru.java2.businesslogic.checkout;

import lv.javaguru.java2.businesslogic.product.StockService;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Cart;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.builders.CartDTOUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class CartServiceImplTest {

    int productId = 123;
    int quantity = 2;
    int alreadyInCart = 5;
    @Mock
    private ProductDAO productDAO;
    @Mock
    private CartDTOUtil cartDTOUtil;
    @Mock
    private CartProvider cartProvider;
    @Mock
    private StockService stockService;
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private Product product;
    @Mock
    private Cart cart;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void removeWhenProductExists() {
        Mockito.doReturn(product).when(productDAO).getById(productId);
        cartService.removeProducts(cart, productId, quantity);
        Mockito.verify(cart, Mockito.times(1)).remove(product, quantity);
    }

    @Test
    public void doNotRemoveWhenProductNotExists() {
        Mockito.doReturn(null).when(productDAO).getById(productId);
        cartService.removeProducts(cart, productId, quantity);
        Mockito.verify(cart, Mockito.times(0)).remove(product, quantity);
    }

    @Test
    public void doNotAddWhenProductNotExists() {
        Mockito.doReturn(null).when(productDAO).getById(productId);
        cartService.addProducts(cart, productId, quantity);
        Mockito.verify(cart, Mockito.times(0)).get(product);
    }

    @Test
    public void doNotAddWhenNoAsMuchStockAvailable() {
        Mockito.doReturn(product).when(productDAO).getById(productId);
        Mockito.doReturn(alreadyInCart).when(cart).get(product);
        Mockito.doReturn(false).when(stockService).isValid(alreadyInCart + quantity, product);
        cartService.addProducts(cart, productId, quantity);
        Mockito.verify(cart, Mockito.times(1)).get(product);
        Mockito.verify(stockService, Mockito.times(1)).isValid(alreadyInCart + quantity, product);
    }

    @Test
    public void doAddWhenStockAvailable() {
        Mockito.doReturn(product).when(productDAO).getById(productId);
        Mockito.doReturn(alreadyInCart).when(cart).get(product);
        Mockito.doReturn(true).when(stockService).isValid(alreadyInCart + quantity, product);
        cartService.addProducts(cart, productId, quantity);
        Mockito.verify(cart, Mockito.times(1)).get(product);
        Mockito.verify(stockService, Mockito.times(1)).isValid(alreadyInCart + quantity, product);
        Mockito.verify(cart, Mockito.times(1)).add(product, quantity);
    }

}