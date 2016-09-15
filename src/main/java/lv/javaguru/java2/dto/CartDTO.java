package lv.javaguru.java2.dto;

import java.util.List;


public class CartDTO {
    private List<ProductCard> productCards;
    private long totalPrice;
    private long cartCheckSum;

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public void setProductCards(List<ProductCard> productCards) {
        this.productCards = productCards;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long cartTotalPrice) {
        this.totalPrice = cartTotalPrice;
    }

    public long getCartCheckSum() {
        return cartCheckSum;
    }

    public void setCartCheckSum(long cartCheckSum) {
        this.cartCheckSum = cartCheckSum;
    }
}
