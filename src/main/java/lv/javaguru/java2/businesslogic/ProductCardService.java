package lv.javaguru.java2.businesslogic;


import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.dto.ProductCard;

import java.util.List;

public interface ProductCardService {
    ProductCard forProduct(Product product);

    List<ProductCard> forProductList(List<Product> products);
}
