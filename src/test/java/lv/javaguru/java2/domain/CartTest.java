package lv.javaguru.java2.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CartTest {
    @Test
    public void testAddingWithoutQuantity() throws Exception {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product);
        cart.add(product);
        assertEquals(2, (int) cart.getQuantity(product));
    }

    @Test
    public void testAddingWithQuantity() throws Exception {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product, 4);
        cart.add(product, 3);
        assertEquals(7, (int) cart.getQuantity(product));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingZeroItemsFails() {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNegativeItemsFails() {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product, -2);
    }

    @Test
    public void testRemovingSeveralItems() throws Exception {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product, 5);
        cart.remove(product, 4);
        assertEquals(1, (int) cart.getQuantity(product));
    }

    @Test
    public void testRemovingProduct() throws Exception {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product, 10);
        cart.remove(product);
        assertNull(cart.getQuantity(product));
    }

    @Test
    public void removingLastItemRemovesProduct() {
        Cart cart = new Cart();
        Product product = new Product();
        cart.add(product, 2);
        cart.remove(product, 1);
        cart.remove(product, 1);
        assertNull(cart.getQuantity(product));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingZeroItemsFails() {
        new Cart().remove(new Product(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNegativeItemsFails() {
        new Cart().remove(new Product(), -1);
    }
}