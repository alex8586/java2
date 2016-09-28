package lv.javaguru.java2.businesslogic.product;


import lv.javaguru.java2.dto.ProductCard;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ProductCardSortingServiceImpl extends SortingService<ProductCard> {

    public ProductCardSortingServiceImpl() {
        defaultSortingStrategy = "BYNAME";

        keyToKey.put("A to Z", "BYNAME");
        keyToKey.put("Cheap first", "BYPRICE");
        //keyToKey.put("", "BYSTOCK");
        keyToKey.put("By rating", "BYRATING");
        keyToKey.put("By views", "BYVIEWS");
        keyToKey.put("Newest first", "BYNOVELTY");

        comparators.put("BYNAME", Comparator.comparing(ProductCard::getProductName));
        comparators.put("BYPRICE", Comparator.comparing(ProductCard::getProductPrice));
        comparators.put("BYSTOCK", Comparator.comparing(ProductCard::getQuantity).reversed());
        comparators.put("BYRATING", Comparator.comparing(ProductCard::getAverageRate).reversed());
        comparators.put("BYVIEWS", Comparator.comparing(ProductCard::getViewCount).reversed());
        comparators.put("BYNOVELTY", Comparator.comparing(ProductCard::getProductId).reversed());
    }
}
