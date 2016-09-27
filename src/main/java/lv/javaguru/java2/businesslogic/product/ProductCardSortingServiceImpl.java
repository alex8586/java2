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

        keyToKey.put("byname", "BYNAME");
        keyToKey.put("byprice", "BYPRICE");
        keyToKey.put("bystock", "BYSTOCK");
        keyToKey.put("byrating", "BYRATING");
        keyToKey.put("byviews", "BYVIEWS");
        keyToKey.put("bynovelty", "BYNOVELTY");

        comparators.put("BYNAME", Comparator.comparing(ProductCard::getProductName));
        comparators.put("BYPRICE", Comparator.comparing(ProductCard::getProductPrice));
        comparators.put("BYSTOCK", Comparator.comparing(ProductCard::getQuantity).reversed());
        comparators.put("BYRATING", Comparator.comparing(ProductCard::getAverageRate).reversed());
        comparators.put("BYVIEWS", Comparator.comparing(ProductCard::getViewCount).reversed());
        comparators.put("BYNOVELTY", Comparator.comparing(ProductCard::getProductId).reversed());
    }

    public void sort(String publicKey, List<ProductCard> productCards) {
        String privateKey = keyToKey.getOrDefault(publicKey, defaultSortingStrategy);
        productCards.sort(comparators.get(privateKey));
    }

    public void sortReversed(String publicKey, List<ProductCard> productCards) {
        String privateKey = keyToKey.getOrDefault(publicKey, defaultSortingStrategy);
        productCards.sort(comparators.get(privateKey).reversed());
    }

    public Map<String, String> sortingStrategies() {
        return keyToKey;
    }
}
