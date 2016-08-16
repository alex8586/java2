package lv.javaguru.java2.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CartTest {

    private static final String NAME = "name";
    private static final String VENDOR_CODE = "AA1";

    private Product product;
    private Cart cart;

    @Before
    public void before() {
        product = new Product(NAME, VENDOR_CODE);
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

}