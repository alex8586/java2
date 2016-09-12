package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import lv.javaguru.java2.dto.ProductCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCardUtilTest {

    private static Date today = new Date();
    private Product product1;
    private ProductCard productCard;

    @InjectMocks
    private ProductCardUtil productCardBuilder = new ProductCardUtil();

    public static Stock newStock(int quantity, int days) {
        Stock stock = new Stock();
        stock.setQuantity(quantity);
        stock.setExpireDate(addDays(days));
        return stock;
    }

    public static Date addDays(int days) {
        return new Date(today.getTime() + (1000 * 60 * 60 * 24 * days));
    }

    public Product newProduct(int id) {
        Product product = new Product();
        product.setId(id);
        product.setName("name" + id);
        product.setDescription("desc" + id);
        product.setPrice(id);
        product.setCategoryId(id);
        return product;
    }

    @Before
    public void before() {
        product1 = newProduct(123);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWithSingleStock() {
        List<Stock> allStock = new ArrayList<Stock>();
        allStock.add(newStock(3, 3));
        product1.setStockList(allStock);
        productCard = productCardBuilder.build(product1);
        assertDesiredResult(product1, 3, addDays(3), productCard);
    }

    @Test
    public void testWithTwoStocks() {
        List<Stock> allStock = new ArrayList<Stock>();
        allStock.add(newStock(3, 3));
        allStock.add(newStock(5, 2));
        product1.setStockList(allStock);
        productCard = productCardBuilder.build(product1);
        assertDesiredResult(product1, 8, addDays(2), productCard);
    }

    @Test
    public void testEmptyStockDoNotAffectExpireDate() {
        List<Stock> allStock = new ArrayList<Stock>();
        allStock.add(newStock(3, 7));
        allStock.add(newStock(6, 4));
        allStock.add(newStock(1, 6));
        allStock.add(newStock(0, 2));
        product1.setStockList(allStock);
        productCard = productCardBuilder.build(product1);
        assertDesiredResult(product1, 10, addDays(4), productCard);
    }

    @Test
    public void testNoStock() {
        product1.setStockList(new ArrayList<Stock>());
        productCard = productCardBuilder.build(product1);
        assertDesiredResult(product1, 0, null, productCard);
    }

    private void assertDesiredResult(Product product, int quantity, Date date, ProductCard productCard) {
        assertEquals(productCard.getProductId(), product.getId());
        assertEquals(productCard.getProductName(), product.getName());
        assertEquals(productCard.getProductDescription(), product.getDescription());
        assertEquals(productCard.getProductImgUrl(), product.getImgUrl());
        assertEquals(productCard.getProductPrice(), product.getPrice());
        assertEquals(productCard.getStockQuantity(), quantity);
        assertEquals(productCard.getStockExpireDate(), date);
    }


}