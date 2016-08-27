package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RandomSaleOfferTest {

    @Mock
    private ProductDAO productDAO;
    private RandomSaleOffer randomSaleOffer;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnNullWhenDAOReturnEmptyArray() {
        Mockito.doReturn(new ArrayList<Product>()).when(productDAO).getAll();
        randomSaleOffer = new RandomSaleOffer(productDAO);
        Product product = randomSaleOffer.getOffer();
        assertNull(product);
    }

    @Test
    public void returnProductWhenDAOReturnSingleProduct() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(sampleProduct(123));
        Mockito.doReturn(products).when(productDAO).getAll();
        randomSaleOffer = new RandomSaleOffer(productDAO);
        assertEquals(123, randomSaleOffer.getOffer().getId());
    }

    @Test
    public void returnRandomProductWhenDAOReturnMultipleProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(sampleProduct(123));
        products.add(sampleProduct(124));
        products.add(sampleProduct(125));
        products.add(sampleProduct(126));
        Mockito.doReturn(products).when(productDAO).getAll();
        randomSaleOffer = new RandomSaleOffer(productDAO);

        long id = randomSaleOffer.getOffer().getId();
        int paranoicCounter = 0;
        while (randomSaleOffer.getOffer().getId() == id) {
            paranoicCounter++;
            if (paranoicCounter == 100)
                fail();
        }
    }

    @Test(expected = DBException.class)
    public void failsWhenDAOThrowException() {
        Mockito.doThrow(new DBException("")).when(productDAO).getAll();
        randomSaleOffer = new RandomSaleOffer(productDAO);
    }

    public Product sampleProduct(int code) {
        Product product = new Product();
        product.setName("name" + code);
        product.setDescription("desc" + code);
        product.setId(code);
        return product;
    }


}

