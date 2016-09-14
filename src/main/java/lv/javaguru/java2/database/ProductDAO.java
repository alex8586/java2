package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.Stock;

import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    List<Product> getAllByCategory(Category category);

    List<Product> getByCategoryTree(Category category);

    Product getRandomProduct();

    Product getRandomProductWithoutCurrentCategoryId(long id);

    List<Stock> getStock(long id);
}
