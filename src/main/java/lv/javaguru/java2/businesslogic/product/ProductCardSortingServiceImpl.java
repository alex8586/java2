package lv.javaguru.java2.businesslogic.product;


import lv.javaguru.java2.dto.ProductCard;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductCardSortingServiceImpl implements ProductCardSortingService {

    private final Map<String, String> keyToKey = new HashMap<String, String>();
    private final Map<String, Comparator<ProductCard>> comparators = new HashMap<>();
    private final String defaultSortingStrategy;

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

    public void sort(String key, List<ProductCard> productCards) {
        if (key == null)
            key = defaultSortingStrategy;
        productCards.sort(comparators.get(key));
    }

    public void sortReversed(String key, List<ProductCard> productCards) {
        System.out.println("sorting back");
        //productCards.sort(comparators.get(key).reversed());
    }

    public Map<String, String> sortingStrategies() {
        return keyToKey;
    }
}
