package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import lv.javaguru.java2.dto.ProductCard;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCardBuilderTest {

    private static Product product1;
    private static Date today = new Date();

    private ProductCard productCard;
    private ProductCardBuilder productCardBuilder = new ProductCardBuilder();

    @BeforeClass
    public static void beforeClass() {
        product1 = newProduct(1);
    }

    public static Product newProduct(int id) {
        Product product = new Product();
        product.setId(id);
        product.setName("name" + id);
        product.setDescription("desc" + id);
        product.setPrice(id);
        product.setCategoryId(id);
        return product;
    }

    public static Stock newStock(int quantity, int days) {
        Stock stock = new Stock();
        stock.setQuantity(quantity);
        stock.setExpireDate(addDays(days));
        return stock;
    }

    public static Date addDays(int days) {
        return new Date(today.getTime() + (1000 * 60 * 60 * 24 * days));
    }
    
    @Test
    public void testWithSingleStock() {
        List<Stock> allStock = new ArrayList<Stock>();
        allStock.add(newStock(3, 3));
        productCard = productCardBuilder.build(product1, allStock);

        assertEquals(productCard.getProductId(), product1.getId());
        assertEquals(productCard.getProductName(), product1.getName());
        assertEquals(productCard.getProductDescription(), product1.getDescription());
        assertEquals(productCard.getProductImgUrl(), product1.getImgUrl());
        assertEquals(productCard.getStockQuantity(), 3);
        assertEquals(productCard.getStockExpireDate(), addDays(3));
    }

    @Test
    public void testWithTwoStocks() {
        List<Stock> allStock = new ArrayList<Stock>();
        allStock.add(newStock(3, 3));
        allStock.add(newStock(5, 2));
        productCard = productCardBuilder.build(product1, allStock);
        assertEquals(productCard.getProductId(), product1.getId());
        assertEquals(productCard.getProductName(), product1.getName());
        assertEquals(productCard.getProductDescription(), product1.getDescription());
        assertEquals(productCard.getProductImgUrl(), product1.getImgUrl());
        assertEquals(productCard.getStockQuantity(), 8);
        assertEquals(productCard.getStockExpireDate(), addDays(2));
    }

    @Test
    public void testEmptyStockDoNotAffectExpireDate() {
        List<Stock> allStock = new ArrayList<Stock>();
        allStock.add(newStock(3, 7));
        allStock.add(newStock(6, 4));
        allStock.add(newStock(1, 6));
        allStock.add(newStock(0, 2));
        productCard = productCardBuilder.build(product1, allStock);
        assertEquals(productCard.getProductId(), product1.getId());
        assertEquals(productCard.getProductName(), product1.getName());
        assertEquals(productCard.getProductDescription(), product1.getDescription());
        assertEquals(productCard.getProductImgUrl(), product1.getImgUrl());
        assertEquals(productCard.getStockQuantity(), 10);
        assertEquals(productCard.getStockExpireDate(), addDays(4));
    }

}