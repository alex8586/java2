package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.businesslogic.product.RateService;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;
import lv.javaguru.java2.dto.ProductCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCardUtil {

    @Autowired
    private RateService rateService;

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
        if (product.getProductStatisticLine() != null) {
            productCard.setViewCount(product.getProductStatisticLine().getUserVisits() +
                    product.getProductStatisticLine().getVisitorVisits());
            productCard.setAverageRate(product.getProductStatisticLine().getAvgRate());
            String rateColor = rateService.getRateColor(product.getProductStatisticLine().getAvgRate());
            productCard.setRateColorCode(rateColor);
        }
        return productCard;
    }

    public ProductCard build(Product product, Integer quantity) {
        ProductCard productCard = build(product);
        productCard.setQuantity(quantity);
        return productCard;
    }

    public ProductCard build(Product product) {
        List<Stock> allStock = product.getFresh();
        return build(product, allStock);
    }

    public List<ProductCard> build(List<Product> products) {
        return products.stream()
                .map(product -> build(product))
                .collect(Collectors.toList());
    }

}
