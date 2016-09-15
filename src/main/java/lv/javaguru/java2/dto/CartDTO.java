package lv.javaguru.java2.dto;

import java.util.HashMap;


public class CartDTO {
    private HashMap<ProductCard, Integer> productCards;
    private long cartTotalPrice;
    private long cartCheckSum;

    public HashMap<ProductCard, Integer> getProductCards() {
        return productCards;
    }

    public void setProductCards(HashMap<ProductCard, Integer> productCards) {
        this.productCards = productCards;
    }

    public long getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(long cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public long getCartCheckSum() {
        return cartCheckSum;
    }

    public void setCartCheckSum(long cartCheckSum) {
        this.cartCheckSum = cartCheckSum;
    }
}
