package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import lv.javaguru.java2.dto.ProductCard;

import java.util.Date;
import java.util.List;

public class ProductCardBuilder {
    public ProductCard build(Product product, List<Stock> allStock) {
        ProductCard productCard = new ProductCard();

        productCard.setProductId(product.getId());
        productCard.setProductName(product.getName());
        productCard.setProductDescription(product.getDescription());
        productCard.setProductImgUrl(product.getImgUrl());
        productCard.setProductPrice(product.getPrice());

        int quantity = 0;
        Date expireDate = null;
        for (Stock stock : allStock) {
            quantity += stock.getQuantity();
            if (expireDate == null) {
                expireDate = stock.getExpireDate();
            } else if (expireDate.compareTo(stock.getExpireDate()) > 0) {
                expireDate = stock.getExpireDate();
            }
        }
        productCard.setStockQuantity(quantity);
        productCard.setStockExpireDate(expireDate);
        return productCard;
    }
}
