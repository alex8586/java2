package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.database.StockDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import lv.javaguru.java2.dto.ProductCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductCardBuilder {

    @Autowired
    StockDAO stockDAO;

    public ProductCard build(Product product) {
        ProductCard productCard = new ProductCard();

        productCard.setProductId(product.getId());
        productCard.setProductName(product.getName());
        productCard.setProductDescription(product.getDescription());
        productCard.setProductImgUrl(product.getImgUrl());
        productCard.setProductPrice(product.getPrice());

        int quantity = 0;
        Date expireDate = null;
        List<Stock> allStock = stockDAO.allByProduct(product);
        for (Stock stock : allStock) {
            quantity += stock.getQuantity();
            if (stock.getQuantity() <= 0)
                continue;
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

    public List<ProductCard> build(List<Product> products) {
        List<ProductCard> productCards = new ArrayList<>();
        for (Product product : products) {
            productCards.add(build(product));
        }
        return productCards;
    }


}
