package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomSaleOfferTest {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private RandomSaleOffer randomSaleOffer;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnEmptyProductWhenDAOReturnNull() {
        Mockito.doReturn(null).when(productDAO).getRandomProduct();
        Product product = randomSaleOffer.getOffer();
        assertEquals(0, product.getId());
        assertEquals(null, product.getName());
        assertNotNull(product.getImgUrl());
    }


    @Test
    public void returnProductFromDAO() {
        Mockito.doReturn(sampleProduct(123)).when(productDAO).getRandomProduct();
        assertEquals(123, randomSaleOffer.getOffer().getId());
    }

    @Test(expected = DBException.class)
    public void failsWhenDAOThrowException() {
        Mockito.doThrow(new DBException("")).when(productDAO).getRandomProduct();
        randomSaleOffer.getOffer();
    }


    public Product sampleProduct(int code) {
        Product product = new Product();
        product.setName("name" + code);
        product.setDescription("desc" + code);
        product.setId(code);
        return product;
    }


}

