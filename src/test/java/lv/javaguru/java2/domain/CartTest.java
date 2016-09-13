package lv.javaguru.java2.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CartTest {

    private static final String NAME = "name";

    private Product product;
    private Product otherProduct;
    private Cart cart;

    @Before
    public void before() {
        product = new Product();
        otherProduct = new Product();
        cart = new Cart();
    }

    @Test
    public void addWithoutQuantity() {
        cart.add(product);
        cart.add(product);
        assertTrue(cart.getQuantity(product) == 2);
    }

    @Test
    public void addWithQuantity() {
        cart.add(product, 4);
        cart.add(product, 3);
        assertTrue(cart.getQuantity(product) == 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addZeroQuantityShouldFail() {
        cart.add(product, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNegativeQuantityShouldFail() {
        cart.add(product, -2);
    }

    @Test
    public void removeProducts() {
        cart.add(product, 5);
        cart.remove(product, 4);
        assertTrue(cart.getQuantity(product) == 1);
    }

    @Test
    public void remove() {
        cart.add(product, 10);
        cart.remove(product);
        assertNull(cart.getQuantity(product));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFromEmptyCartShouldFail() {
        cart.remove(product);
        assertNull(cart.getQuantity(product));
    }

    @Test
    public void removeLastItemShouldRemoveProduct() {
        cart.add(product, 2);
        cart.remove(product, 1);
        cart.remove(product, 1);
        assertNull(cart.getQuantity(product));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeZeroQuantityShouldFail() {
        cart.remove(product, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNegativeQuantityShouldFail() {
        cart.remove(product, -1);
    }

    @Test
    public void setQuantityForProductInCart() {
        cart.add(product);
        cart.setQuantity(product, 5);
        assertTrue(cart.getQuantity(product) == 5);
    }

    @Test
    public void setQuantityForProductNotInCart() {
        cart.setQuantity(product, 5);
        assertTrue(cart.getQuantity(product) == 5);
    }

    @Test
    public void getTotalPriceTest() {
        Product product;
        for (int i = 0; i < 5; i++) {
            product = new Product();
            product.setPrice(5);
            cart.add(product, i + 1);
        }
        assertTrue(cart.getTotalPrice(cart) == 75);
    }

    @Test
    public void getTotalPriceTest2(){
        product.setPrice(12);
        otherProduct.setPrice(4);
        cart.add(product, 5);
        cart.add(otherProduct, 8);

        assertTrue(cart.getTotalPrice(cart) ==92);
    }


}