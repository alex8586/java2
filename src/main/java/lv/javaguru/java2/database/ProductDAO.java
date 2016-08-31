package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;

import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    List<Product> getAllByCategory(Category category);

    List<Product> getCategoryContent(Category category);

    Product getRandomProduct();

    Product getRandomProductWithoutCurrentCategoryId(long id);
}
