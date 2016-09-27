package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.dto.ProductCard;

import java.util.List;
import java.util.Map;

public interface ProductCardSortingService {
    void sort(String publicKey, List<ProductCard> productCards);

    void sortReversed(String publicKey, List<ProductCard> productCards);

    Map<String, String> sortingStrategies();
}
