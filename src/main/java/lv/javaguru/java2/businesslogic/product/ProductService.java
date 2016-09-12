package lv.javaguru.java2.businesslogic.product;


import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    List<Product> getAll();

    List<Product> getByCategory(Category category);

}
